package com.bcu.alumnus.service;

import com.bcu.alumnus.config.GlobalConfig;
import com.bcu.alumnus.entity.Message;
import com.bcu.alumnus.entity.Star;
import com.bcu.alumnus.repo.StarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.File;
import java.util.Date;


@Service
public class StarService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private StarRepository starRepository;

    @Resource
    private GlobalConfig config;

    /**
     * 1. 校园明星发布，初始发布状态后台设置为0，待审核。 @UseToken(level=2)
     * 2. 通过编号进行校园明星审核（将状态改为1）@UseToken(level=4)
     * 3. 删除校园明星 @UseToken(level=4)
     * 4. 通过学部编号查找状态为可见的校园明星，时间倒序排列
     * 5. 查找所有状态为可见的校园明星，时间倒序排列
     * 6. 通过校园明星编号查询校园明星
     * 7. 通过用户编号查询校园明星
     * 8. 通过学部编号查询校园明星
     * 9. 根据可见状态查找校园明星
     * 10. 通过校园明星编号更新点赞数量
     * 11. 通过主人公编号查询校园明星
     * 12. ...
     */

    /**
    * @Author: Shj
    * @Date: 21:20 2020/4/7
    * @Description: 新增校园明星
    */
    public Message insertStar(Star star){
        logger.info("添加校园明星，标题：{}，学部：{}",star.getStarTitle(),star.getStarPartId());
        star.setStarCreateDate(new Date());
        star.setStarStatus("0");

        /*
         * 将处于临时文件夹的文件转移到业务文件夹
         * */
        File titleImg = new File(config.globalFilePath+"/tmp/"+star.getStarTitleImg());
        if (titleImg.exists()) {
            titleImg.renameTo(new File(config.globalFilePath+"/star/"+star.getStarTitleImg()));
            star.setStarTitle("/resource/star/"+star.getStarTitleImg());
        }

        for (String fileName : star.getStarImg().split(";")) {
            File tmpImg = new File(config.globalFilePath+"/tmp/"+fileName);
            if (tmpImg.exists()) {
                tmpImg.renameTo(new File(config.globalFilePath+"/star/"+fileName));
                star.setStarTitle("/resource/star/"+fileName);
            }
        }

        Star rs = starRepository.save(star);
        logger.info("校园明星已添加，编号：{}",rs.getStarId());
        return Message.success(null).add(rs);
    }

    /**
    * @Author: Shj
    * @Date: 22:19 2020/4/7
    * @Description: 发布明星根据编号(审核)
    */
    public Message publishStarByStarId(String starId){
        logger.info("明星状态变更，明星编号：{}，变更状态：{}",starId,"1");
        if (starRepository.updateStarStatusByStarId("1",starId)>0) {
            return Message.success(null);
        }
        return Message.fail("操作失败，可能是明星编号错误，请检查");
    }

    /**
    * @Author: Shj
    * @Date: 22:25 2020/4/7
    * @Description: 根据编号删除校园明星
    */
    @Transactional
    public Message deleteStarByStarId(String starId){
        logger.info("删除明星，编号是：{}",starId);
        if (starRepository.deleteByStarId(starId)>0) {
            return Message.success(null);
        }
        return Message.fail(null);
    }

    /**
     * @Author: Shj
     * @Date: 22:12 2020/4/7
     * @Description: 通过学部编号查找状态为可见的校园明星
     */
    public Message getStarByStarPartIdAndAndStarStatus(String partId){
        logger.info("通过学部编号查找状态为可见的校园明星，学部：{}",partId);
        return Message.success(null).add(starRepository.getStarByStarPartIdAndAndStarStatus(partId));
    }

    /**
    * @Author: Shj
    * @Date: 22:28 2020/4/7
    * @Description: 查找所有状态为可见的校园明星
    */
    public Message getAllStar(String starStatus){
        logger.info("根据状态获取明星，明星状态：{}",starStatus);
        return Message.success(null).add(starRepository.getAllStar(starStatus));
    }

    /**
    * @Author: Shj
    * @Date: 22:41 2020/4/7
    * @Description: 根据明星编号获取校园明星
    */
    public Message getStarByStarId(String starId){
        logger.info("根据明星编号获取明星，明星编号：{}",starId);
        Star s = starRepository.getStarByStarId(starId);
        if(s != null){
            return Message.success(null).add(s);
        }
        logger.info("明星未找到，明星编号：{}",starId);
        return Message.fail("明星未找到");
    }

    /**
    * @Author: Shj
    * @Date: 22:48 2020/4/7
    * @Description: 根据用户编号获取校园明星
    */
    /*public Message getStarByUserId(String userId){
        logger.info("根据用户编号获取校园明星，用户编号：{}",userId);
        return Message.success(null).add(starRepository.getStarByUserId(userId));
    }*/

    /**
    * @Author: Shj
    * @Date: 22:51 2020/4/7
    * @Description: 根据学部编号获取校园明星
    */
    public Message getStarByStarPartId(String starPartId){
        logger.info("根据学部编号获取校园明星，学部编号：{}",starPartId);
        return Message.success(null).add(starRepository.getStarByStarPartId(starPartId));
    }

    /**
    * @Author: Shj
    * @Date: 22:54 2020/4/7
    * @Description: 根据可见状态获取校园明星
    */
    public Message getStarByStarStatus(String starStatus){
        logger.info("根据可见状态获取校园明星，可见状态：{}",starStatus);
        return Message.success(null).add(starRepository.getStarByStarStatus(starStatus));
    }

    /**
    * @Author: Shj
    * @Date: 22:57 2020/4/7
    * @Description: 根据校园明星编号更新点赞数量
    */
    public Message updateStarLikeCountByStarId(String starLikeCount, String starId){
        logger.info("根据校园明星编号更新点赞数量，明星编号：{}，点赞数量：{}",starId,starLikeCount);
        if(starRepository.updateStarLikeCountByStarId(starLikeCount,starId)>0) {
            return Message.success(null);
        }
        return Message.fail("操作失败，可能是明星编号错误，请检查");
    }

    /**
    * @Author: Shj
    * @Date: 23:02 2020/4/7
    * @Description: 根据主人公编号获取校园明星
    */
    public Message getStarByStarUserId(String starUserId){
        logger.info("根据主人公编号获取校园明星，主人公编号：{}",starUserId);
        return Message.success(null).add(starRepository.getStarByStarUserId(starUserId));
    }


}
