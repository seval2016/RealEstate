package com.project.entity.enums;

public enum LogPage {

    CREATED ("Advert is created and wait for approve"),
    UPDATED ("Advert is updated"),
    DELETED ("Advert is deleted"),
    DECLINED ("Advert is declined by manager"),
    TOUR_REQUEST_CREATED ("Tour request is created"),
    TOUR_REQUEST_ACCEPTED ("Tour request is accepted"),
    TOUR_REQUEST_DECLINED ("Tour request is declined"),
    TOUR_REQUEST_CANCELED ("Tour request is canceled");

    public final String msg;

    LogPage(String msg) { this.msg = msg; }

    public String getLogPage() { return msg; }




}
