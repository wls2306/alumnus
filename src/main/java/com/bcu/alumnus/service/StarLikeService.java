package com.bcu.alumnus.service;

import com.bcu.alumnus.config.GlobalConfig;
import com.bcu.alumnus.entity.Message;
import com.bcu.alumnus.entity.StarLike;
import com.bcu.alumnus.repo.StarLikeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StarLikeService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private StarLikeRepository starLikeRepository;

    @Resource
    private GlobalConfig config;

    /**
     * 1. 添加点赞记录 @UseToken(level=1)
     * 2. 通过明星编号查找点赞记录
     * 3. 通过明星编号统计点赞数量
     * 4. 通过用户编号查找点赞记录
     * 5. 通过用户编号统计点赞数量
     * 6. 通过点赞编号、用户编号删除该点赞记录 @UseToken(level=1)
     * 7. ...
     */

    /**
    * @Author: Shj
    * @Date: 14:49 2020/4/11
    * @Description: 添加点赞记录
    */
    public Message insertStarLike(StarLike starLike){
        logger.info("添加点赞记录，明星编号：{}，用户编号：{}",starLike.getStarId(),starLike.getUserId());
        StarLike rs = starLikeRepository.save(starLike);
        return Message.success(null).add(rs);
    }

    /**
    * @Author: Shj
    * @Date: 14:20 2020/4/11
    * @Description: 通过明星编号查找点赞记录
    */
    public Message getStarLikeByStarId(int starId){
        logger.info("通过明星编号查找点赞记录,明星编号：{}",starId);
        return Message.success(null).add(starLikeRepository.getStarLikeByStarId(starId));
    }

    /**
    * @Author: Shj
    * @Date: 14:28 2020/4/11
    * @Description: 通过明星编号统计点赞数量
    */
    public Message getStarLikeCountByStarId(String starId){
        logger.info("通过明星编号统计点赞数量，明星编号：{}",starId);
        return Message.success(null).add(starLikeRepository.getStarLikeCountByStarId(starId));
    }

    /**
    * @Author: Shj
    * @Date: 14:35 2020/4/11
    * @Description: 通过用户编号查找点赞记录
    */
    public Message getStarLikeByUserId(String userId){
        logger.info("通过用户编号查找点赞记录，用户编号：{}",userId);
        return Message.success(null).add(starLikeRepository.getStarLikeByUserId(userId));
    }

    /**
    * @Author: Shj
    * @Date: 14:38 2020/4/11
    * @Description: 通过用户编号统计点赞数量
    */
    public Message getStarLikeCountByUserId(String userId){
        logger.info("通过用户编号统计点赞数量，用户编号：{}",userId);
        return Message.success(null).add(starLikeRepository.getStarLikeCountByUserId(userId));
    }

    /**
    * @Author: Shj
    * @Date: 14:40 2020/4/11
    * @Description: 通过点赞编号、用户编号删除该点赞记录
    */
    public Message deleteByIdAndUserId(int id, String userId){
        logger.info("通过点赞编号、用户编号删除该点赞记录，点赞编号：{}，用户编号：{}",id,userId);
        if (starLikeRepository.deleteByIdAndUserId(id,userId) > 0) {
            return Message.success(null);
        }
        return Message.fail(null);
    }
}
