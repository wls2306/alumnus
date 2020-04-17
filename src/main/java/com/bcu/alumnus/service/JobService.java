package com.bcu.alumnus.service;

import com.bcu.alumnus.config.GlobalConfig;
import com.bcu.alumnus.entity.Job;
import com.bcu.alumnus.entity.Message;
import com.bcu.alumnus.repo.JobRepositiory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;

@Service
@Validated
public class JobService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private JobRepositiory jobRepositiory;

    @Resource
    private GlobalConfig config;




    /**
     * @Author: WHW
     * @Date: 12:59 2020/4/10
     * @Description: 添加岗位内推记录
     */
     public Message insertJob(Job job){
         logger.info("添加岗位内推记录");
         job.setJobCreateDate(new Date());
         job.setJobStatus("1");
         /*
          * 将处于临时文件夹的文件转移到业务文件夹
          * */
        for(String fileName:job.getJobEnviromentImg().split(";")) {
             File tmpImg = new File(config.globalFilePath + "/tmp/" + fileName);
             if (tmpImg.exists()) {
                 tmpImg.renameTo(new File(config.globalFilePath + "/job/" + fileName));
                 job.setJobEnviromentImg("/resource/job/" + fileName);
             }
         }
         Job rs =jobRepositiory.save(job);
         logger.info("岗位内推记录已添加，编号：{}",rs.getJobId());
         return Message.success(null).add(rs);

     }

    /**
     * @Author: WHW
     * @Date: 13:03 2020/4/10
     * @Description: 查询学部编号、以及岗位类型为校级的岗位内推信息，状态均为可见，时间倒序排列
     */
    public Message getJobInfoByjobPartIdandjobtype2(String jobPartId){
        logger.info("查询学部编号、以及岗位类型为校级的岗位内推信息,学部：{}",jobPartId);
        return Message.success(null).add(jobRepositiory.getJobInfoByjobPartIdandjobtype2(jobPartId));
    }

    /**
     * @Author: WHW
     * @Date: 13:15 2020/4/10
     * @Description: 通过学部编号查询可见的岗位内推信息
     */
    public Message getJobInfoByjobPartId(String jobPartId){
         logger.info("通过学部编号查询可见的岗位内推信息,学部：{}",jobPartId);
         return Message.success(null).add(jobRepositiory.getJobInfoByjobPartId(jobPartId));
     }

    /**
     * @Author: WHW
     * @Date: 13:19 2020/4/10
     * @Description: 通过学部编号查询学部内的内推信息 @UseToken(level=3)
     */
    public Message getJobInfoInPartByByjobPartId(String jobPartId){
        logger.info("通过学部编号查询学部内的内推信息,学部：{}",jobPartId);
        return Message.success(null).add(jobRepositiory.getJobInfoInPartByByjobPartId(jobPartId));
    }

    /**
     * @Author: WHW
     * @Date: 13:25 2020/4/10
     * @Description: 根据状态查询内推信息
     */
    public Message getJobInfoByjobstatus(String jobstatus){
        logger.info("根据状态查询内推信息，状态：{}",jobstatus);
        return Message.success(null).add(jobRepositiory.getJobInfoByjobstatus(jobstatus));
    }

    /**
     * @Author: WHW
     * @Date: 13:30 2020/4/10
     * @Description: 查询所有岗位内推信息 @UseToken(level=4)
     */
    public Message getAllJobInfo(){
        logger.info("查询所有岗位内推信息");
        return Message.success(null).add(jobRepositiory.getAllJobInfo());
    }

    /**
     * @Author: WHW
     * @Date: 13:34 2020/4/10
     * @Description: 通过内推编号变更内推状态 @UseToken(level=3)
     */
    public Message updateJobstatusByjobid(Integer jobid,String jobstatus){
        logger.info("通过内推编号变更内推状态,编号：{}，更改为：{}",jobid,jobstatus);
        if(jobRepositiory.updateJobstatusByjobid(jobid,jobstatus)>0){
            return Message.success(null);
        }
        return  Message.fail("更改失败！");
     }

    /**
     * @Author: WHW
     * @Date: 13:35 2020/4/10
     * @Description: 通过内推编号删除岗位内推信息 @UseToken(level=1)
     */
    public Message deleteJobByjobId(Integer jobId){
        logger.info("通过内推编号删除岗位内推信息，编号：{}",jobId);
        if(jobRepositiory.deleteByjobId(jobId)>0){
            return Message.success(null);
        }
        return Message.fail("删除失败！");
    }

    /**
     * @Author: WHW
     * @Date: 13:59 2020/4/10
     * @Description: 通过内推编号查询内推信息
     */
    public Message getJobByjobId(Integer jobId){
        logger.info("通过内推编号查询内推信息，编号：{}",jobId);
        return Message.success(null).add(jobRepositiory.getJobByjobId(jobId));
    }

    /**
     * @Author: WHW
     * @Date: 14:08 2020/4/10
     * @Description: 通过内推编号修改内推信息
     */
    public Message updateJobByjobId(Job job){
        Integer jobId =job.getJobId();
        logger.info("通过内推编号修改内推信息，编号：{}",jobId);
        String jobPartId=job.getJobPartId();
        String jobUserId=job.getJobUserId();
        String jobName=job.getJobName();
        String jobCompany=job.getJobCompany();
        String jobSalary=job.getJobSalary();
        String jobAddress=job.getJobAddress();
        String jobExperience=job.getJobExperience();
        String jobEducation=job.getJobEducation();
        String jobDetail=job.getJobDetail();
        for(String fileName:job.getJobEnviromentImg().split(";")) {
            File tmpImg = new File(config.globalFilePath + "/tmp/" + fileName);
            if (tmpImg.exists()) {
                tmpImg.renameTo(new File(config.globalFilePath + "/job/" + fileName));
                job.setJobEnviromentImg("/resource/job/" + fileName);
            }
        }
        String jobEnviromentImg=job.getJobEnviromentImg();
        String jobUserName=job.getJobUserName();
        String jobUserPhone=job.getJobUserPhone();
        String jobUserEmail=job.getJobUserEmail();
        Date jobCreateDate=new Date();
        String jobType=job.getJobType();
        String jobStatus=job.getJobStatus();
        Job rs =jobRepositiory.save(job);
         return Message.success("更改成功！");

    }

    /**
     * @Author: WHW
     * @Date: 14:19 2020/4/10
     * @Description: 通过推荐人编号获取所有内推信息
     */
    public Message getJobByjobUserId(String jobUserId){
        logger.info("通过推荐人编号获取所有内推信息，编号：{}",jobUserId);
        return Message.success(null).add(jobRepositiory.getJobByjobUserId(jobUserId));
    }
}
