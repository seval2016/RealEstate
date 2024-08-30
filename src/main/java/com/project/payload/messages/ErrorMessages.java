package com.project.payload.messages;

public class ErrorMessages {

    public static final String NOT_FOUND_IMAGE_MESSAGE = " Error: Image not found";

    private ErrorMessages() {
    }

    public static final String NOT_PERMITTED_METHOD_MESSAGE = "You do not have any permission to do this operation because the user is built-in.";

    // User Errors
    public static final String PASSWORD_NOT_MATCHED = "Error : Your passwords are not matched";
    public static final String ALREADY_REGISTER_MESSAGE_USERNAME = "Error : User with username %s is already registered";
    public static final String ALREADY_REGISTER_MESSAGE_SSN = "Error : User with ssn %s is already registered";
    public static final String ALREADY_REGISTER_MESSAGE_EMAIL = "Error : User with email %s is already registered";
    public static final String ALREADY_REGISTER_MESSAGE_PHONE = "Error : User with phone number %s is already registered";
    public static final String ROLE_NOT_FOUND = "Error : There is no role like that, check the database";
    public static final String NOT_FOUND_USER_USER_ROLE_MESSAGE = "Error: User not found with user-role %s";

    public static final String NOT_FOUND_USER_MESSAGE = "Error: User not found with id %s";

    public static final String NOT_FOUND_USER_WITH_ROLE_MESSAGE = "Error: The role information of the user with id %s is not role: %s";
    public static final String USER_ROLE_DELETE_ERROR = "Error : User Role is not deleted";

    // Advert Errors
    public static final String ADVERT_NOT_FOUND = "Error : The requested advert is not found.";
    public static final String ADVERT_CREATION_ERROR = "Error : Advert is not created. Please try again.";
    public static final String ADVERT_UPDATE_ERROR = "Error : Advert Update is not updated.";
    public static final String ADVERT_DELETE_ERROR = "Error : Advert is not deleted";

    // Country Errors
    public static final String COUNTRY_NOT_FOUND = "Error : Country does not exist.";
    public static final String COUNTRY_CREATION_ERROR = "Error : Country is not created. Please try again.";
    public static final String COUNTRY_UPDATE_ERROR = "Error : Country is not updated.";
    public static final String COUNTRY_DELETE_ERROR = "Error : Country is not deleted.";

    // City Errors
    public static final String CITY_NOT_FOUND = "Error : City does not exist.";
    public static final String CITY_CREATION_ERROR = "Error : City is not created. Please try again.";
    public static final String CITY_UPDATE_ERROR = "Error : City is not updated";
    public static final String CITY_DELETE_ERROR = "Error : City is not deleted";

    // District Errors
    public static final String DISTRICT_NOT_FOUND = "Error : District does not exist.";
    public static final String DISTRICT_CREATION_ERROR = "Error : District is not created. Please try again.";
    public static final String DISTRICT_UPDATE_ERROR = "Error : District is not updated.";
    public static final String DISTRICT_DELETE_ERROR = "Error : District is not deleted.";

    // Category Errors
    public static final String CATEGORY_NOT_FOUND = "Error : Category does not exist.";
    public static final String CATEGORY_CREATION_ERROR = "Error : Category is not created. Please try again.";
    public static final String CATEGORY_UPDATE_ERROR = "Error : Category is not updated.";
    public static final String CATEGORY_DELETE_ERROR = "Error : Category is not deleted.";

    // Favorite Errors
    public static final String FAVORITE_NOT_FOUND = "Error : Favorite does not exist.";
    public static final String FAVORITE_CREATION_ERROR = "Error : Favorite is not created. Please try again.";
    public static final String FAVORITE_DELETE_ERROR = "Error : Favorite is not deleted. Please ensure it exists.";

    // TourRequest Errors
    public static final String TOUR_REQUEST_START_DATE_IS_EARLIER_THAN_LAST_REGISTRATION_DATE = "Error: The start date cannot be earlier than the last registration date";
    public static final String TOUR_REQUEST_END_DATE_IS_EARLIER_THAN_START_DATE = "Error: The end date cannot be earlier than start date";
    public static final String TOUR_REQUEST_NOT_FOUND = "TError : Tour Request does not exist.";
    public static final String TOUR_REQUEST_CREATION_ERROR = "Error : Tour Request is not created. Please try again.";
    public static final String TOUR_REQUEST_UPDATE_ERROR = "Error : Tour Request is not updated. Please check your input.";
    public static final String TOUR_REQUEST_DELETE_ERROR = "Error : Tour Request is not deleted. Please ensure it exists.";

    // Image Errors
    public static final String IMAGE_NOT_FOUND = "Error : The requested image does not exist.";
    public static final String IMAGE_UPLOAD_ERROR = "Error uploading the image. Please try again.";
    public static final String IMAGE_DELETE_ERROR = "Error deleting the image. Please ensure it exists.";
    public static final String IMAGE_FORMAT_ERROR = "Error : Invalid image format. Please upload a valid image file.";

    // AdvertType Errors
    public static final String ADVERT_TYPE_NOT_FOUND = "Error : Advert Type does not exist.";
    public static final String ADVERT_TYPE_CREATION_ERROR = "Error : Advert Type is not created. Please try again.";
    public static final String ADVERT_TYPE_UPDATE_ERROR = "Error : Advert Type is not updated. Please check your input.";
    public static final String ADVERT_TYPE_DELETE_ERROR = "Error : Advert Type is not deleted. It may be associated with adverts.";

    // General Errors
    public static final String DATABASE_CONNECTION_ERROR = "Error connecting to the database. Please try again later.";
    public static final String DATABASE_QUERY_ERROR = "Error executing database query. Please check your input or contact support.";
    public static final String UNAUTHORIZED_ACCESS = "You are not authorized to perform this action.";
    public static final String INVALID_INPUT_FORMAT = "Invalid input format. Please enter the correct information.";
    public static final String REQUIRED_FIELD_MISSING = "A required field is missing. Please fill out all required fields.";
    public static final String UNKNOWN_ERROR = "An unknown error occurred. Please contact support.";

}