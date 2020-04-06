package com.bcu.alumnus.repo;

import com.bcu.alumnus.entity.User;
import com.bcu.alumnus.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,String>, JpaSpecificationExecutor {

    UserInfo getUserInfoByUserId(String userId);

    List<UserInfo> getUserInfoByUserClassName(String userClassName);

    List<UserInfo> getUserInfoByUserPartName(String userPartName);

    @Query("update UserInfo set userPhone=?1 ,userEmail=?2,userUpdateTime=?3 where userId=?4")
    int updateUserInfoForAlumnus(String userPhone, String userEmail,Date date,String userId);



}
