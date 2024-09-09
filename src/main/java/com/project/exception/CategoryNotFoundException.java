package com.project.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message) {
        super(message);
    }

    public static class ManagerNotFoundException extends RuntimeException {

        public ManagerNotFoundException(String message) {
            super(message);
        }

        public ManagerNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
        //diğer managerler içinde class
    }



}
