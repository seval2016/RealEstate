package com.project.entity.enums;

public enum Log {

    CREATED ("Advert is created and wait for approve"),
    UPDATED ("Advert is updated"),
    DELETED ("Advert is deleted"),
    DECLINED ("Advert is declined by manager"),
    TOUR_REQUEST_CREATED ("Tour request is created"),
    TOUR_REQUEST_ACCEPTED ("Tour request is accepted"),
    TOUR_REQUEST_DECLINED ("Tour request is declined"),
    TOUR_REQUEST_CANCELED ("Tour request is canceled");

    public final String log;

    Log(String log) {this.log = log;}

    public String getLog() {return log;}




}
