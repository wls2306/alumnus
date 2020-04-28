package com.bcu.alumnus.service;

import com.bcu.alumnus.entity.Part;
import com.bcu.alumnus.repo.PartRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

/**
* @Author: Wls
* @Date: 15:52 2020/4/21
* @Description: 学部信息服务
*/
@Service
public class PartService {

    @Resource
    private PartRepository partRepository;

    public Part findPartByIdInner(@NotBlank(message = "学部编号不得为空") String partId){
        return partRepository.findByPartId(partId);
    }

    public String findPartNameByPartId(@NotBlank(message = "学部名称不得为空")String partId){
        Integer p=Integer.valueOf(partId);
        return partRepository.findPartNameByPartId(p);
    }

}
