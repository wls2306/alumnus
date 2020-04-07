package com.bcu.alumnus.service;

import cn.hutool.core.util.StrUtil;
import com.bcu.alumnus.config.GlobalConfig;
import com.bcu.alumnus.entity.Message;
import com.bcu.alumnus.entity.News;
import com.bcu.alumnus.entity.UserInfo;
import com.bcu.alumnus.repo.NewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewsService {

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Resource
    private NewsRepository newsRepository;

    @Resource
    private GlobalConfig config;

    /**
     *
     *1. 校园新闻发布 @UseToken(level=3)
     *2. 校园新闻查询，通过学部编号查询以及校级新闻，且状态为可见的新闻，时间倒序排列
     *3. 通过新闻编号查看新闻详情
     *4. 通过新闻编号删除新闻 @UseToken(level=3)
     *5. 根据新闻状态查找新闻
     *6. 通过学部编号查找新闻
     *7. 通过新闻类别查找新闻
     *8. 查找所有的新闻 @UseToken(level=4)
     */

    /**
    * @Author: Wls
    * @Date: 9:09 2020/4/7
    * @Description: 新增校园新闻
    */
    /*
     * 文件上传流程：
     * 客户端请求上传文件，文件上传保存在临时文件夹，返回文件名
     * 文章发布时，携带后台返回的文件名，后台从临时文件夹中将文件移动到业务目录
     * 定时任务，每天凌晨三点清空临时文件夹的所有内容
     */
    public Message insertNews(News news)
    {
        logger.info("添加校园新闻，标题：{}，学部：{}",news.getNewsTitle(),news.getNewsPartId());
        news.setNewsDate(new Date());
        news.setNewsStatus("0");

        /*
        * 将处于临时文件夹的文件转移到业务文件夹
        * */
        File titleImg = new File(config+"/tmp/"+news.getNewsTitleImg());
        if (titleImg.exists()) {
            titleImg.renameTo(new File(config+"/news/"+news.getNewsTitleImg()));
            news.setNewsTitle("/resource/news/"+news.getNewsTitleImg());
        }

        for (String fileName : news.getNewsContentImg().split(";")) {
            File tmpImg = new File(config+"/tmp/"+fileName);
            if (tmpImg.exists()) {
                tmpImg.renameTo(new File(config+"/news/"+fileName));
                news.setNewsTitle("/resource/news/"+fileName);
            }
        }

        News rs= newsRepository.save(news);
        logger.info("校园新闻已添加，编号：{}",rs.getNewsId());
        return Message.success(null).add(rs);
    }

    /**
    * @Author: Wls
    * @Date: 9:43 2020/4/7
    * @Description: 小程序获取混合新闻列表
    */
    public Message getNewsForAlumnus(String partId){
        logger.info("小程序端获取校园新闻列表，学部：{}",partId);

        return Message.success(null).add(newsRepository.getNewsForAlumnus(partId));
    }


    /**
    * @Author: Wls
    * @Date: 9:44 2020/4/7
    * @Description: 根据新闻编号获取新闻详情
    */
    public Message getNewsDetailByNewsId(String newsId){
        logger.info("获取校园新闻详情，编号：{}",newsId);
        News n=newsRepository.getNewsByNewsId(newsId);
        if (n != null) {
            return Message.success(null).add(n);
        }
        logger.info("新闻未找到，编号：{}",newsId);
        return Message.fail("新闻未找到");
    }


    /**
    * @Author: Wls
    * @Date: 10:11 2020/4/7
    * @Description: 根据学部编号获取新闻
    */
    public Message getNewsByPartId(String partId){
        logger.info("根据学部编号获取新闻，学部编号：{}",partId);
        return Message.success(null).add(newsRepository.getNewsByNewsPartId(partId));
    }

    /**
    * @Author: Wls
    * @Date: 10:11 2020/4/7
    * @Description: 根据新闻类型获取新闻
    */
    public Message getNewsByNewsType(String newsType){
        logger.info("根据新闻类型获取新闻，新闻类型：{}",newsType);
        return Message.success(null).add(newsRepository.getNewsByNewsType(newsType));
    }

    /**
    * @Author: Wls
    * @Date: 9:44 2020/4/7
    * @Description: 校园新闻的条件搜索
    */
    public Message getNewsConditionSearch(String newsId,String newsPartId,String newsType,String newsStatus)
    {
        Specification<UserInfo> query= (Specification<UserInfo>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates=new ArrayList<>();
            if (StrUtil.isNotEmpty(newsId)) {
                predicates.add(criteriaBuilder.equal(root.get("newsId"),newsId));
            }

            if (StrUtil.isNotEmpty(newsPartId)) {
                predicates.add(criteriaBuilder.equal(root.get("newsPartId"),newsPartId));
            }

            if (StrUtil.isNotEmpty(newsType)) {
                predicates.add(criteriaBuilder.equal(root.get("newsType"),newsType));
            }

            if (StrUtil.isNotEmpty(newsStatus)) {
                predicates.add(criteriaBuilder.equal(root.get("newsStatus"),newsStatus));
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return Message.success(null).add(newsRepository.findAll(query));

    }

    /**
    * @Author: Wls
    * @Date: 10:15 2020/4/7
    * @Description: 发布新闻根据编号
    */
    public Message publishNewsByNewsId(String newsId){
        logger.info("新闻状态变更，新闻编号：{}，变更状态：{}",newsId,"1");
        if (newsRepository.updateNewsStatusByNewsId("1",newsId)>0) {
            return Message.success(null);
        }
        return Message.fail("操作失败，可能是新闻编号错误，请检查");
    }


    /**
    * @Author: Wls
    * @Date: 15:26 2020/4/7
    * @Description: 根据编号删除校园新闻
    */
    @Transactional
    public Message deleteNewsById(String newsId){
        logger.info("删除新闻，编号是：{}",newsId);
        if (newsRepository.deleteByNewsId(newsId)>0) {
            return Message.success(null);
        }
        return Message.fail(null);
    }


}
