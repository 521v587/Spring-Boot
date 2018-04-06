package cn.dean.girl.utils;

import cn.dean.girl.domain.Result;

public class ResultUtils {

    public static Result success(Object object) {
        Result result = new Result();
        //成功code设置为0
        result.setCode(0);
        //设置成功信息为"成功"
        result.setMsg("成功");
        //设置数据内容
        result.setData(object);
        return result;
    }

    public static Result success() {
        //成功，但不返回对象
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        //传入error code
        result.setCode(code);
        //传入出错信息
        result.setMsg(msg);
        return result;
    }
}
