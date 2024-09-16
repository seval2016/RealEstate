package com.project.contactmessage.entity;



public enum ContactStatus {

    UNREAD(0),
    READ(1),
    ;

    private final int value;

    ContactStatus(int value) {
        this.value = value;
    }


    public int getValue() {
        return value;
    }

    public static ContactStatus fromValue(int value) {
        for (ContactStatus contactStatus : ContactStatus.values()) {
            if (contactStatus.getValue() == value) {
                return contactStatus;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }

}