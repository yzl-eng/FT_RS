package com.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public static Result success(){
        return new Result(1,"success","null");
    }
    public static Result success(Object data){
        return new Result(1,"success",data);
    }
    public static Result error(String msg){
        return new Result(0,msg,"null");
    }
    public Result data(String key, Object value) {
        Map<String, Object> resultMap = new HashMap<>();
        if (data != null && data instanceof Map) {
            resultMap = (Map<String, Object>) data;
        }
        resultMap.put(key, value);
        setData(resultMap);
        return this;
    }

}
