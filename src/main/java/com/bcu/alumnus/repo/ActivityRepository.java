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
     */

    List<Activity> getActivityByActPartIdAndActStatusNot(String actPartId,String num);

    List<Activity> getActivityByActUserIdAndActStatusNot(String actUserId,String num);

    List<Activity> getActivityByActTypeEquals(String num);

    List<Activity> getActivityByActUserIdaAndActStatusEquals(String actUserId,String num);

    List<Activity> getActivityByActPartId(String actPartId);
}
