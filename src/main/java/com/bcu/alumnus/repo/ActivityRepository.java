package com.bcu.alumnus.repo;

import com.bcu.alumnus.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer>, JpaSpecificationExecutor {

    /**
     *
     *1. 通过学部编号查找已审核的校园活动
     *2. 通过创建人编号查找已审核的校园活动
     *3. 查找校级活动
     *4. 通过活动创建人编号和活动状态查找校园活动
     *5. 通过学部编号查找所有活动
     *6. 查找所有的活动
     *7. 通过活动状态查找所有活动
     *8. 通过活动类型查找所有的活动
     *9. 通过活动编号查找活动
     *10. 修改活动状态
     */

    List<Activity> getActivityByActPartIdAndActStatusNot(String actPartId,String actStatus);

    List<Activity> getActivityByActUserIdAndActStatusNot(String actUserId,String actStatus);

    List<Activity> getActivityByActPartIdAndActTypeAndActStatusNot(String actPartId,String ActType,String Status);

    List<Activity> getActivityByActUserIdAndActStatus(String actUserId,String ActStatus);

    List<Activity> getActivityByActPartId(String actPartId);

    List<Activity> findAll();

    List<Activity> getActivityByActStatus(String actStatus);

    List<Activity> getActivityByActType(String ActType);

    List<Activity> getActivityByActId(String ActId);

    @Query("update Activity set actStatus=?1 where actId=?2")
    int updataActStatusByActId(String ActStatus,String ActId);

}
