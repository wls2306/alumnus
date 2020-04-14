package com.bcu.alumnus.controller;

import com.bcu.alumnus.UseToken;
import com.bcu.alumnus.entity.Job;
import com.bcu.alumnus.entity.Message;
import com.bcu.alumnus.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "岗位内推模块")
@RequestMapping("api/job")
public class JobController {
    @Resource
    private JobService jobService;
    /**
     * 1. 添加岗位内推记录，初始状态为 1，可见 @UseToken(level=1)
     * 2. 查询学部编号、以及岗位类型为校级的岗位内推信息，状态均为可见，时间倒序排列
     * 3. 通过学部编号查询可见的岗位内推信息
     * 4. 通过学部编号查询学部内的内推信息 @UseToken(level=3)
     * 5. 根据状态查询内推信息
     * 6. 查询所有岗位内推信息 @UseToken(level=4)
     * 7. 通过内推编号变更内推状态 @UseToken(level=3)
     * 8. 通过内推编号删除岗位内推信息 @UseToken(level=1)
     * 9. 通过内推编号查询内推信息
     * 10.通过内推编号修改内推信息
     * 11.通过推荐人编号获取所有内推信息
     * 12....
     */

    @PostMapping("/insert")
    @ApiOperation(value = "添加岗位内推记录")
    @UseToken(level = 1)
    public Message insertJob(Job job){
        return  jobService.insertJob(job);
    }

    @GetMapping("/jobPartIdandjobtype2/{jobPartId}")
    @ApiOperation(value = "查询学部编号、以及岗位类型为校级的岗位内推信息")
    public Message getJobInfoByjobPartIdandjobtype2(String jobPartId){
        return jobService.getJobInfoByjobPartIdandjobtype2(jobPartId);
    }

    @GetMapping("/VISjobPartId/{jobPartId}")
    @ApiOperation(value = "通过学部编号查询可见的岗位内推信息")
    public Message getJobInfoByjobPartId(String jobPartId){
        return jobService.getJobInfoByjobPartId(jobPartId);
    }

    @GetMapping("/ALLjobPartId/{jobPartId}")
    @ApiOperation(value = "通过学部编号查询学部内的内推信息")
    @UseToken(level = 3)
    public Message getJobInfoInPartByByjobPartId(String jobPartId){
        return jobService.getJobInfoInPartByByjobPartId(jobPartId);
    }

    @GetMapping("/jobstatus/{jobstatus}")
    @ApiOperation(value = "根据状态查询内推信息")
    public Message getJobInfoByjobstatus(String jobstatus){
        return jobService.getJobInfoByjobstatus(jobstatus);
    }

    @GetMapping("/all")
    @ApiOperation(value = "查询所有岗位内推信息")
    @UseToken(level = 4)
    public Message getAllJobInfo(){
        return jobService.getAllJobInfo();
    }

    @PutMapping("/updateJobstatus/{jobid}")
    @ApiOperation(value = "通过内推编号变更内推状态")
    @UseToken(level = 3)
    public Message updateJobstatusByjobid(Integer jobid,String jobstatus){
        return jobService.updateJobstatusByjobid(jobid,jobstatus);
    }

    @DeleteMapping("/jobId/{jobId}")
    @ApiOperation(value = "通过内推编号删除岗位内推信息")
    @UseToken(level = 1)
    public Message deleteJobByjobId(Integer jobId){
        return jobService.deleteJobByjobId(jobId);
    }

    @GetMapping("/jobId/{jobId}")
    @ApiOperation(value = "通过内推编号查询内推信息")
    public Message getJobByjobId(Integer jobId){
        return  jobService.getJobByjobId(jobId);
    }

    @PutMapping("/updateALL/{jobid}")
    @ApiOperation(value = "通过内推编号修改内推信息")
    public Message updateJobByjobId(Job job){
        return jobService.updateJobByjobId(job);
    }

    @GetMapping("/jobUserId/{jobUserId}")
    @ApiOperation(value = "通过推荐人编号获取所有内推信息")
    public Message getJobByjobUserId(String jobUserId){
        return jobService.getJobByjobUserId(jobUserId);
    }
}
