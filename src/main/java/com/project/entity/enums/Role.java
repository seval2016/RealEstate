package com.project.entity.enums;

public enum Role {

    ADMIN ("Admin"),

    CUSTOMER ("Customer"),

    MANAGER("Manager");

    public final String name;

    Role(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }
}
