package com.bcu.alumnus.repo;

import com.bcu.alumnus.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepositiory extends JpaRepository<Job,Integer> {


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


    int updateJobByjobId(Job job);


    List<Job> getJobByjobUserId(String jobUserId);
}
