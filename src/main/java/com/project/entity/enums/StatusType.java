package com.project.entity.enums;

public enum StatusType {

    PENDING(0),
    Approved(1),
    Declined(2),
    Canceled(3);

    public final Integer id;

    StatusType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
