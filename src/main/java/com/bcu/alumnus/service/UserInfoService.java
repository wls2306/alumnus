package com.bcu.alumnus.service;

import cn.hutool.core.util.StrUtil;
import com.bcu.alumnus.entity.Message;
import com.bcu.alumnus.entity.User;
import com.bcu.alumnus.entity.UserInfo;
import com.bcu.alumnus.repo.UserInfoRepository;
import com.bcu.alumnus.repo.UserRepository;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Validated
public class UserInfoService {
    private Logger logger= LoggerFactory.getLogger(getClass());

    @Resource
    private UserInfoRepository userInfoRepository;

    @Resource
    private PartService partService;

    /**
    * @Author: Wls
    * @Date: 13:15 2020/4/6
    * @Description: 根据用户编号查找用户详细信息
     */
    public Message<UserInfo> getUserInfoByUserId(@NotBlank(message="userId不得为空") String userId){
        logger.info("查询用户详细信息：{}",userId);
        UserInfo u=userInfoRepository.getUserInfoByUserId(userId);
        logger.info("{}",u.toString());
        if (Strings.isNotBlank(u.getUserId())) {
            return Message.success(null).add(u);
        }
        return Message.fail("用户未找到");

    }

    /**
    * @Author: Wls
    * @Date: 16:57 2020/4/6
    * @Description: [内部调用] 根据用户编号查找用户详细信息
    */
    public UserInfo getUserInfoByUserId2(String userId){
        logger.info("查询用户详细信息：{}",userId);
        UserInfo u=userInfoRepository.getUserInfoByUserId(userId);
        if (u != null) {
            return u;
        }
        return null;
    }

    /**
    * @Author: Wls
    * @Date: 13:15 2020/4/6
    * @Description: 根据班级名称查找用户详细信息
     */
    public Message<List<UserInfo>> getUserInfoByClassName(@NotBlank(message="userClassName不得为空")String className){
        logger.info("根据班级查找用户:{}",className);
        return Message.success(null).add(userInfoRepository.getUserInfoByUserClassName(className));
    }

    /**
    * @Author: Wls
    * @Date: 13:19 2020/4/6
    * @Description: 根据学部名称查找用户详细信息
    */
    public Message<List<UserInfo>> getUserInfoByPartName(@NotBlank(message = "partName不得为空") String partName){
        logger.info("根据学部查找用户:{}",partName);
        return Message.success(null).add(userInfoRepository.getUserInfoByUserPartName(partName));
    }

    /**
    * @Author: Wls
    * @Date: 16:03 2020/4/21
    * @Description: 根据学部编号查找用户详细信息
    */
    public Message<List<UserInfo>> getUserInfoByUserPartId(@NotBlank(message = "partId 不得为空")String partId){
        String partName = partService.findPartNameByPartId(partId);
        return Message.success(null).add(userInfoRepository.getUserInfoByUserPartName(partName));
    }

    /**
    * @Author: Wls
    * @Date: 13:22 2020/4/6
    * @Description: 更新用户信息(小程序端)，仅可更新联系方式信息
    */
    public Message updateUserInfoByUserIdForAlumnus(String userId,String userPhone,String userEmail){
        logger.info("更新用户信息：id:{},phone:{},email:{}",userId,userPhone,userEmail);
        if (userInfoRepository.updateUserInfoForAlumnus(userPhone,userEmail,new Date(),userId)>0) {
            return Message.success(null);
        }
        return Message.fail(null);
    }

    /**
    * @Author: Wls
    * @Date: 14:52 2020/4/6
    * @Description: 更新用户信息（管理端），可更改所有用户信息
    */
    public Message updateUserInfoByUserIdForAdmin(UserInfo userInfo){
        userInfo.setUserUpdateTime(new Date());
        userInfoRepository.save(userInfo);//若在数据库中找到主键一样的记录，即进行覆盖更新操作！
        return Message.success(null);
    }



    /**
    * @Author: Wls
    * @Date: 14:32 2020/4/6
    * @Description: 用户详细信息的动态条件查询
    */

    /**
     * 其中Root是查询的根对象，CriteriaBuilder可以用来构建查询关系。
     * 以predicates.add(criteriaBuilder.equal(root.get("number"),player.getNumber()))为例，
     * "number"指定了实体Player的属性number，通过root.get("number")获取实体属性number对应库表中的字段名称，
     * player.getNumber()获取当前传入的查询条件值，equal指的是等于，criteriaBuilder将他们构建为对应的sql语句。
     * 此段代码的含义是where条件，如：number='19001'。（指定的属性名必须与查询实体的属性名一致，否则会报错。）
     * 相同的道理，predicates.add(criteriaBuilder.like(root.get("name"),"%"+player.getName()+"%"))即为模糊匹配。
     * 最后return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]))
     * 返回Specification的实例，然后作为参数传入到playerRepository.findAll(query)中执行拼接好的动态条件查询。
     * 当然criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]))取的and关系，还可以使用or，道理都一样。
     * 作者：张小黑的猫
     * 链接：https://www.jianshu.com/p/376872de82c7
     * 来源：简书
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    */
    public Message<List<UserInfo>> getUserInfoConditionSearch(String userId,String userName,String className,String partName,String level,String phone)
    {
        Specification<UserInfo> query=new Specification<UserInfo>() {
            @Override
            public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                if (StrUtil.isNotEmpty(userId)) {
                    predicates.add(criteriaBuilder.equal(root.get("userId"),userId));
                }

                if (StrUtil.isNotEmpty(userName)) {
                    predicates.add(criteriaBuilder.equal(root.get("userName"),userName));
                }

                if (StrUtil.isNotEmpty(className)) {
                    predicates.add(criteriaBuilder.equal(root.get("className"),className));
                }

                if (StrUtil.isNotEmpty(partName)) {
                    predicates.add(criteriaBuilder.equal(root.get("partName"),partName));
                }

                if (StrUtil.isNotEmpty(level)) {
                    predicates.add(criteriaBuilder.equal(root.get("level"),level));
                }

                if (StrUtil.isNotEmpty(phone)) {
                    predicates.add(criteriaBuilder.equal(root.get("phone"),phone));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return Message.success(null).add(userInfoRepository.findAll(query));

    }







}
