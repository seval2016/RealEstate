package com.project.entity.enums;

public enum RoleType {

    ADMIN ("Admin"),

    CUSTOMER ("Customer"),

    MANAGER("Manager");

    public final String name;

    RoleType(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }
}
