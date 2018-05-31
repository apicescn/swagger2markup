package com.szss.swagger2markup;

import io.swagger.annotations.ApiParam;

/**
 * @author 鼠笑天
 * @date 2018/5/30
 */
public class User {

    @ApiParam(name = "name", value = "user name", example = "zcg")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
