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
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.util.Date;
import java.util.List;

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

        /*
         * 将处于临时文件夹的文件转移到业务文件夹
         * */
        File titleImg = new File(config+"/tmp/"+activity.getActTitleImg());
        if (titleImg.exists()) {
            titleImg.renameTo(new File(config+"/activitys/"+activity.getActTitleImg()));
            activity.setActTitleImg("/resource/activitys/"+activity.getActTitleImg());
        }

        for (String fileName : activity.getActTitleImg().split(";")) {
            File tmpImg = new File(config+"/tmp/"+fileName);
            if (tmpImg.exists()) {
                tmpImg.renameTo(new File(config+"/activitys/"+fileName));
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
    public Message getActivitybyActPartId(String actPartId){
        logger.info("查找已审核校园活动，通过学部编号{}",actPartId);
        return Message.success(null).add(activityRepository.getActivityByActPartIdAndActStatusNot(actPartId,"0"));
    }

    /**
     * @Author: GuoZiZhou
     * @Date: 16:52 2020/4/8
     * @Description: 通过创建人编号查找已审核的校园活动
     */
    public Message getActivitybyActUserId(String actUserId){
        logger.info("查找已审核校园活动，通过用户编号{}",actUserId);
        return Message.success(null).add(activityRepository.getActivityByActUserIdAndActStatusNot(actUserId,"0"));
    }

    /**
     * @Author: GuoZiZhou
     * @Date: 18:11 2020/4/8
     * @Description: 活动类型为校级的已审核校园活动
     */
    public Message getActivitybyActType(){
        logger.info("查找已审核校园活动，并且活动类型为校级");
        return Message.success(null).add(activityRepository.getActivityByActTypeEquals("0"));
    }

    /**
     * @Author: GuoZiZhou
     * @Date: 21:17 2020/4/8
     * @Description: 活动类型为校级的已审核校园活动
     */
    public Message getActivitybyActUserIdAndActStatus(String actUserId,String actStatus){
        logger.info("查找校园活动，通过创建用户编号{}，和活动状态{}",actUserId,actStatus);
        return Message.success(null).add(activityRepository.getActivityByActUserIdaAndActStatusEquals(actUserId,actStatus));
    }

    /**
     * @Author: GuoZiZhou
     * @Date: 21:36 2020/4/8
     * @Description: 通过学部编号查找所有活动
     */
    public Message getAllActivitybyActPartId(String actPartId){
        logger.info("查找所有校园活动，通过学部编号",actPartId);
        return Message.success(null).add(activityRepository.getActivityByActPartId(actPartId));
    }

}
