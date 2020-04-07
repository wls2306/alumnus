package com.bcu.alumnus.entity;

import com.google.common.base.Strings;

public class Message
{

    //状态码
    private boolean result;

    //错误和成功信息
    private String msg;

    //包含的数据
    private Object obj;


    public static Message success(String str)
    {
        Message msg = new Message();
        msg.setResult(true);
        if (Strings.isNullOrEmpty(str)) {
            msg.setMsg("操作成功");
        }else{
            msg.setMsg(str);
        }

        return msg;
    }


    public static Message fail(String str)
    {
        Message msg = new Message();
        msg.setResult(false);
        if (Strings.isNullOrEmpty(str)) {
            msg.setMsg("操作失败");
        }else{
            msg.setMsg(str);
        }
        return msg;
    }

    //添加包含的数据
    public Message add(Object obj)
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

    public Object getObj()
    {
        return obj;
    }

    public void setObj(Object obj)
    {
        this.obj= obj;
    }

}