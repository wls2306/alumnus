package com.bcu.alumnus.service;

import com.bcu.alumnus.config.GlobalConfig;
import com.bcu.alumnus.entity.Activity;
import com.bcu.alumnus.entity.Message;
import com.bcu.alumnus.repo.ActivityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;

@Service
@Validated
public class ActivityService {

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Resource
    private ActivityRepository activityRepository;

    @Resource
    private GlobalConfig config;

    /**
     *
     *1. 添加校园活动，初始状态为0，待审核,活动创建时间为当前时间 @UseToken(level=1)
     *2. 通过学部编号查找已审核的校园活动
     *3. 通过创建人编号查找已审核的校园活动
     *4. 查找校级活动
     *5. 通过创建人编号和活动状态查找活动
     *6. 通过学部编号查找所有活动
     *7. 查找所有活动那个
     *8. 通过活动状态查找所有的活动
     *9. 通过活动类型查找所有的活动
     *10. 通过活动编号查找活动
     *11. 修改活动状态
     */


    /**
     * @Author: GuoZiZhou
     * @Date: 15:18 2020/4/8
     * @Description: 添加校园活动，初始状态为0，待审核,活动创建时间为当前时间 @UseToken(level=1)
     */
    public Message addActivity(Activity activity){
        logger.info("添加校园活动. 编号：{}，标题：{}",activity.getActId(),activity.getActTitle());
        activity.setActStartTime(new Date());
        activity.setActStatus("0");

        /*         * 将处于临时文件夹的文件转移到业务文件夹
         * */
        File titleImg = new File(config.globalFilePath+"/tmp/"+activity.getActTitleImg());
        if (titleImg.exists()) {
            titleImg.renameTo(new File(config.globalFilePath+"/activitys/"+activity.getActTitleImg()));
            activity.setActTitleImg("/resource/activitys/"+activity.getActTitleImg());
        }

        for (String fileName : activity.getActTitleImg().split(";")) {
            File tmpImg = new File(config.globalFilePath+"/tmp/"+fileName);
            if (tmpImg.exists()) {
                tmpImg.renameTo(new File(config.globalFilePath+"/activitys/"+fileName));
                activity.setActTitleImg("/resource/activitys/"+fileName);
            }
        }
        Activity rs = activityRepository.save(activity);
        logger.info("校园活动已添加，编号：{}",rs.getActId());
        return Message.success(null).add(rs);
    }


    /**
     * @Author: GuoZiZhou
     * @Date: 16:18 2020/4/8
     * @Description: 通过学部编号查找已审核的校园活动
     */
    public Message getActivityByActPartId(String ActPartId){
        logger.info("通过学部编号查找已审核的校园活动，学部编号{}",ActPartId);
        return Message.success(null).add(activityRepository.getActivityByActPartIdAndActStatusNot(ActPartId,"0"));
    }

    /**
     * @Author: GuoZiZhou
     * @Date: 16:52 2020/4/8
     * @Description: 通过创建人编号查找已审核的校园活动
     */
    public Message getActivityByActUserId(String ActUserId){
        logger.info("通过创建人编号查找已审核的校园活动，创建人编号{}",ActUserId);
        return Message.success(null).add(activityRepository.getActivityByActUserIdAndActStatusNot(ActUserId,"0"));
    }

    /**
     * @Author: GuoZiZhou
     * @Date: 18:11 2020/4/8
     * @Description: .通过学部编号查找已审核的校园活动 以及 活动类型为校级的已审核校园活动
     */
    public Message getActivityByActPartIdAndActType(String ActPartId){
        logger.info("活动类型为校级的已审核校园活动");
        return Message.success(null).add(activityRepository.getActivityByActPartIdAndActTypeAndActStatusNot(ActPartId,"2","0"));
    }

    /**
     * @Author: GuoZiZhou
     * @Date: 21:17 2020/4/8
     * @Description: 通过创建人编号和活动状态查找活动
     */
    public Message getActivityByActUserIdAndActStatus(String ActUserId,String ActStatus){
        logger.info("查找校园活动，通过创建用户编号{}，和活动状态{}",ActUserId,ActStatus);
        return Message.success(null).add(activityRepository.getActivityByActUserIdAndActStatus(ActUserId,ActStatus));
    }

    /**
     * @Author: GuoZiZhou
     * @Date: 21:36 2020/4/8
     * @Description: 通过学部编号查找所有活动
     */
    public Message getAllActivityByActPartId(String ActPartId){
        logger.info("查找所有校园活动，通过学部编号",ActPartId);
        return Message.success(null).add(activityRepository.getActivityByActPartId(ActPartId));
    }

    /**
     * @Author: GuoZiZhou
     * @Date: 13:40 2020/4/9
     * @Description: 查找所有活动
     */
    public Message getAllActivity(){
        logger.info("查找所有校园活动");
        return Message.success(null).add(activityRepository.findAll());
    }

    /**
     * @Author: GuoZiZhou
     * @Date: 13:44 2020/4/9
     * @Description: 通过活动状态查找所有活动
     */
    public Message getActivityByActStatus(String ActStatus){
        logger.info("查找所有校园活动，活动状态{}",ActStatus);
        return Message.success(null).add(activityRepository.getActivityByActStatus(ActStatus));
    }

    /**
     * @Author: GuoZiZhou
     * @Date: 13:59 2020/4/9
     * @Description: 通过活动状态查找所有活动
     */
    public Message getActivityByActType(String ActType){
        logger.info("通过活动状态查找所有活动,活动类型{}",ActType);
        return Message.success(null).add(activityRepository.getActivityByActType(ActType));
    }

    /**
     * @Author: GuoZiZhou
     * @Date: 21:51 2020/4/12
     * @Description: 通过活动编号查找活动
     */
    public Message getActivityByActId(String ActId){
        logger.info("通过活动编号查找活动,活动id{}",ActId);
        return Message.success(null).add(activityRepository.getActivityByActId(ActId));
    }

    /**
     * @Author: GuoZiZhou
     * @Date: 22:07 2020/4/12
     * @Description: 修改活动状态
     */
    public Message updataActivityByActId(String ActStatus,String ActId){
        logger.info("审核活动,活动id{}",ActId);
        return Message.success(null).add(activityRepository.updataActStatusByActId(ActStatus,ActId));
    }

}
