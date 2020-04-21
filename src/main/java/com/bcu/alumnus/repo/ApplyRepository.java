package com.bcu.alumnus.repo;

import com.bcu.alumnus.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyRepository extends JpaRepository<Apply,Integer>, JpaSpecificationExecutor {

    List<Apply> getApplyByApplyIdAndApplyStatus(int applyId,String ApplyStatus);

    List<Apply> getApplyByApplyId(int ApplyId);

    List<Apply> getApplyByApplyUserId(String ApplyUserId);

    List<Apply> getApplyByApplyUserIdAndApplyStatus(String ApplyUserId,String ApplyStatus);

    int countApplyByApplyId(int ApplyId);

    int countApplyByApplyIdAndApplyStatus(int ApplyId,String ApplyStatus);

    @Query(value = "update apply set ApplyStatus=1 where ApplyId=?1 and ApplyUserId=?2",nativeQuery = true)
    int updateApplyStatusByApplyIdAndApplyUserId(int ApplyId,String ApplyUserId);

}
