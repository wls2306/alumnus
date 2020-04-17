package com.bcu.alumnus.repo;

import com.bcu.alumnus.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepositiory extends JpaRepository<Job,Integer> {
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
     */

    @Query(value = "select *from job where job_part_id=?1 and job_status=1 and job_type=2 order by job_create_date desc",nativeQuery = true)
    List<Job> getJobInfoByjobPartIdandjobtype2(String jobPartId);

     @Query(value = "select *from job where job_part_id=?1 and job_status=1",nativeQuery = true )
    List<Job> getJobInfoByjobPartId(String jobPartId);

     @Query(value = "select *from job where job_part_id=?1",nativeQuery = true )
    List<Job> getJobInfoInPartByByjobPartId(String jobPartId);

     @Query(value = "select *from job where job_status=?1",nativeQuery = true)
     List<Job> getJobInfoByjobstatus(String jobstatus);

     @Query(value = "select *from job ",nativeQuery = true)
     List<Job> getAllJobInfo();

     @Query(value = "update Job set jobStatus=?2 where jobId=?1")
     int updateJobstatusByjobid(Integer jobid,String jobstatus);

    int deleteByjobId(Integer jobId);

    Job getJobByjobId(Integer jobId);


//    int updateJobByjobId(Job job);


    List<Job> getJobByjobUserId(String jobUserId);
}
