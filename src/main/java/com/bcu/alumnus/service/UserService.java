package com.bcu.alumnus.service;

import com.bcu.alumnus.repo.UserRepository;
import com.bcu.alumnus.entity.Message;
import com.bcu.alumnus.entity.User;
import com.bcu.alumnus.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Service
@Validated
public class UserService {

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Resource
    private UserRepository userRepository;


    /**
    * @Author: Wls
    * @Date: 15:00 2020/4/5
    * @Description: 用户登录通过用户名和密码
    */
    public Message userLoginByUserIdAndPassword(@NotBlank(message = "用户ID不得为空") String userId, @NotBlank(message = "用户密码不得为空")String password){
        User u= userRepository.getUserByUserIdAndUserPassword(userId,password);
        if(u!=null)
        {
            String token= JwtUtil.createJwtToken(u);
            return Message.success(null).add(token);
        }else {
            return Message.fail("用户名或密码错误");
        }

    }

    /**
    * @Author: Wls
    * @Date: 11:43 2020/4/6
    * @Description: 用户登录通过jsCode（微信小程序一键登录）
    */
    public Message userLoginByJsCode(@NotBlank(message = "jsCode不得为空") String jsCode){
        //TODO：等待微信小程序账号申请下来再实现微信登录
        return Message.success(null);

    }

    /**
    * @Author: Wls
    * @Date: 12:23 2020/4/6
    * @Description: [内部调用] 根据用户编号获取用户账号信息
    */
    public User getUserByUserId(String userId){
        return userRepository.getUserByUserId(userId);
    }

    /**
    * @Author: Wls
    * @Date: 12:30 2020/4/6
    * @Description: 新增管理用户
    */
    public Message insertManageUser(String userId,String userName,String password,String partId,String type)
    {
        User u = new User();
        u.setUserId(userId);
        u.setUserName(userName);
        u.setUserPassword(password);
        u.setUserPartId(partId);
        u.setUserType(type);
        u.setUserStatus("1");
        userRepository.save(u);

        return Message.success(null);
    }


    /**
    * @Author: Wls
    * @Date: 12:42 2020/4/6
    * @Description: 根据班级查询用户
    */
    public Message getUserByClassId(String classId){
        List<User> rs=userRepository.getUserByUserClassId(classId);
        return Message.success(null).add(rs);
    }

    /**
    * @Author: Wls
    * @Date: 12:43 2020/4/6
    * @Description: 根据学部查询用户
    */
    public Message getUserByPartId(String partId){
        List<User> rs=userRepository.getUserByUserPartId(partId);
        return Message.success(null).add(rs);
    }



    /**
    * @Author: Wls
    * @Date: 15:05 2020/4/5
    * @Description: 查询用户信息
    */
    public Message searchUserInfoByUserId(String userId){
        logger.info("查找用户：{}",userId);
          User u= userRepository.getUserByUserId(userId);
          if (u!=null){
              return Message.success(null).add(u);
          }
        return Message.fail("用户未找到");
    }


}
