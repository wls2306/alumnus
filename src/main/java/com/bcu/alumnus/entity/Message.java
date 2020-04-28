package com.bcu.alumnus.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Strings;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;


public  class Message<T>
{

    public interface UnionSimpleView {}

    //状态码
    @ApiModelProperty(value = "请求结果")
    @JsonView(UnionSimpleView.class)
    private boolean result;

    //错误和成功信息
    @ApiModelProperty(value = "补充信息")
    @JsonView(UnionSimpleView.class)
    private String msg;

    //包含的数据
    @ApiModelProperty(value = "包含数据")
    @JsonView(UnionSimpleView.class)
    private T obj;

    private Message(boolean result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public static <T> Message<T> success(String str)
    {

//        Message<?> msg = new Message<>();
//        msg.setResult(true);
//        if (Strings.isNullOrEmpty(str)) {
//            msg.setMsg("操作成功");
//        }else{
//            msg.setMsg(str);
//        }

        return new Message<T>(true,str==null?"操作成功":str);
    }


    public static <T> Message<T> fail(String str)
    {
//        Message<?> msg = new Message<>();
//        msg.setResult(true);
//        if (Strings.isNullOrEmpty(str)) {
//            msg.setMsg("操作成功");
//        }else{
//            msg.setMsg(str);
//        }

        return new Message<T>(false,str==null?"操作失败":str);
    }

    //添加包含的数据
    public Message add(T obj)
    {
        this.setObj(obj);
        return this;
    }

    public boolean getResult()
    {
        return result;
    }

    public void setResult(boolean result)
    {
        this.result = result;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public T getObj()
    {
        return obj;
    }

    public void setObj(T obj)
    {
        this.obj= obj;
    }

}