package com.bcu.alumnus.service;

import com.bcu.alumnus.entity.Apply;
import com.bcu.alumnus.entity.Message;
import com.bcu.alumnus.repo.ActivityRepository;
import com.bcu.alumnus.repo.ApplyRepository;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

@Service
@Validated
public class ApplyService {

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Resource
    private ApplyRepository applyRepository;
    @Resource
    private ActivityRepository activityRepository;

    /**
     * @Author: GuoZiZhou
     * @Date: 15:55 2020/4/21
     * @Description:添加报名信息
     */
    public Message insertApply(Apply apply){
        logger.info("添加活动申请，活动编号{}，报名者姓名{}",apply.getApplyId(),apply.getApplyUserName());
        if(activityRepository.getActMemberWay(apply.getApplyId()).equals("1")){
            apply.setApplyStatus("0");
        }else{
            apply.setApplyStatus("1");
        }
        Apply rs = applyRepository.save(apply);
        logger.info("校园新闻已添加，编号：{}",rs.getApplyId());
        return Message.success(null).add(rs);
    }

    /**
     * @Author: GuoZiZhou
     * @Date: 10:29 2020/4/20
     * @Description: 根据活动编号查询已录取的报名信息
     */
    public Message getApplyByApplyIdAndApplyStatus(int ApplyId){
        logger.info("根据活动编号查询已录取的报名信息，活动编号：{}",ApplyId);
        return Message.success(null).add(applyRepository.getApplyByApplyIdAndApplyStatus(ApplyId,"1"));
    }

    /**
     * @Author: GuoZiZhou
     * @Date: 10:41 2020/4/20
     * @Description: 根据活动编号查询所有的报名信息
     */
    public Message getApplyByApplyId(int ApplyId){
        logger.info("根据活动编号查询所有的报名信息，活动编号：{}",ApplyId);
        return Message.success(null).add(applyRepository.getApplyByApplyId(ApplyId));
    }

    /**
     * @Author: GuoZiZhou
     * @Date: 10:47 2020/4/20
     * @Description: 根据用户编号查询所有的报名信息
     */
    public Message getApplyByApplyUserId(String ApplyUserId){
        logger.info("根据用户编号查询所有的报名信息，用户编号：{}",ApplyUserId);
        return Message.success(null).add(applyRepository.getApplyByApplyUserId(ApplyUserId));
    }

    /**
     * @Author: GuoZiZhou
     * @Date: 10:56 2020/4/20
     * @Description: 通过用户编号查询和录取状态查询报名信息
     */
    public Message getApplyByApplyUserIdAndApplyStatus(String ApplyUserId,String ApplyStatus){
        logger.info("根据用户编号查询所有的报名信息，用户编号：{},录取状态：{}",ApplyUserId,ApplyStatus);
        return Message.success(null).add(applyRepository.getApplyByApplyUserIdAndApplyStatus(ApplyUserId,ApplyStatus));
    }

    /**
     * @Author: GuoZiZhou
     * @Date: 11:18 2020/4/20
     * @Description: 通过活动编号统计报名数量
     */
    public Message countApplyByApplyId(int ApplyId){
        logger.info("通过活动编号统计报名数量，活动编号：{}",ApplyId);
        return Message.success(null).add(applyRepository.countApplyByApplyId(ApplyId));
    }

    /**
     * @Author: GuoZiZhou
     * @Date: 11:37 2020/4/20
     * @Description: 通过活动编号统计已录取的报名数量
     */
    public Message countApplyByApplyIdAndApplyStatus(int ApplyId){
        logger.info("通过活动编号统计已录取的报名数量，活动编号：{}",ApplyId);
        return Message.success(null).add(applyRepository.countApplyByApplyIdAndApplyStatus(ApplyId,"1"));
    }

    /**
     * @Author: GuoZiZhou
     * @Date: 15:24 2020/4/20
     * @Description: 通过活动编号、用户编号修改录取状态
     */
    public Message UpdateApplyStatusByApplyIdAndApplyUserId(int ApplyId,String ApplyUserId){
        logger.info("通过活动编号、用户编号修改录取状态,活动编号：{}，用户编号：{}",ApplyId,ApplyUserId);
        int result = applyRepository.updateApplyStatusByApplyIdAndApplyUserId(ApplyId,ApplyUserId);
        if(result>0){
            return Message.success(null).add(result);
        }else{
            return Message.fail(null).add(result);
        }
    }

    /**
     * @Author: GuoZiZhou
     * @Date: 15:24 2020/4/20
     * @Description: 根据活动编号将已录取的报名记录导出为Excel
     */
    /*public Message OutExcel(int ApplyId){
        logger.info("根据活动编号将已录取的报名记录导出为Excel,活动编号：{}",ApplyId);
        HSSFWorkbook wb = new HSSFWorkbook();
    }*/
}