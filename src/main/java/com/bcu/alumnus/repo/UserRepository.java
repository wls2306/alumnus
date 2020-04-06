package com.bcu.alumnus.repo;

import com.bcu.alumnus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User getUserByUserIdAndUserPassword(String userId,String userPassword);

    User getUserByUserOpenId(String userOpenId);

    List<User> getUserByUserClassId(String userClassId);

    List<User> getUserByUserPartId(String partId);

    User getUserByUserId(String userId);





}
