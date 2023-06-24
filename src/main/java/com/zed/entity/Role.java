package com.zed.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;


    private String name;


    private String description;

    private String flag;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}