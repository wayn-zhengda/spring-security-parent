package com.wayn.core.common;

import lombok.Data;

@Data
public class R {

    private Object data;

    private String code;

    public R(Object data){
       this(data, "200");
    }
    public R(Object data, String code){
        this.data = data;
        this.code = code;
    }

    public static R ok(Object data){
        return new R(data);
    }
}
