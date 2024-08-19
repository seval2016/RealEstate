package com.project.payload.messages;

public class ErrorMessages {

    private ErrorMessages() {
    }

    public static final String NOT_PERMITTED_METHOD_MESSAGE = "You do not have any permission to do this operation";

    // User Errors
    public static final String PASSWORD_NOT_MATCHED = "Your passwords are not matched";
    public static final String ALREADY_REGISTER_MESSAGE_USERNAME = "Error : User with username %s is already registered";
    public static final String ALREADY_REGISTER_MESSAGE_SSN = "Error : User with ssn %s is already registered";
    public static final String ALREADY_REGISTER_MESSAGE_EMAIL = "Error : User with email %s is already registered";
    public static final String ALREADY_REGISTER_MESSAGE_PHONE = "Error : User with phone number %s is already registered";
    public static final String ROLE_NOT_FOUND = "There is no role like that, check the database";
    public static final String NOT_FOUND_USER_USERROLE_MESSAGE = "Error: User not found with user-role %s";

    public static final String NOT_FOUND_USER_MESSAGE = "Error: User not found with id %s";

    public static final String NOT_FOUND_USER_WITH_ROLE_MESSAGE = "Error: The role information of the user with id %s is not role: %s";
    public static final String USER_ROLE_DELETE_ERROR = "Error deleting the user role. It may be associated with users.";

    // Advert Errors
    public static final String ADVERT_NOT_FOUND = "The requested advert was not found.";
    public static final String ADVERT_CREATION_ERROR = "There was an error creating the advert. Please try again.";
    public static final String ADVERT_UPDATE_ERROR = "Error updating the advert. Please check your input and try again.";
    public static final String ADVERT_DELETE_ERROR = "Error deleting the advert. Please ensure the advert exists.";

    // Country Errors
    public static final String COUNTRY_NOT_FOUND = "The specified country does not exist.";
    public static final String COUNTRY_CREATION_ERROR = "Error creating the country. Please try again.";
    public static final String COUNTRY_UPDATE_ERROR = "Error updating the country. Please check your input.";
    public static final String COUNTRY_DELETE_ERROR = "Error deleting the country. It may be associated with other entities.";

    // City Errors
    public static final String CITY_NOT_FOUND = "The specified city does not exist.";
    public static final String CITY_CREATION_ERROR = "Error creating the city. Please try again.";
    public static final String CITY_UPDATE_ERROR = "Error updating the city. Please check your input.";
    public static final String CITY_DELETE_ERROR = "Error deleting the city. It may be associated with other entities.";

    // District Errors
    public static final String DISTRICT_NOT_FOUND = "The specified district does not exist.";
    public static final String DISTRICT_CREATION_ERROR = "Error creating the district. Please try again.";
    public static final String DISTRICT_UPDATE_ERROR = "Error updating the district. Please check your input.";
    public static final String DISTRICT_DELETE_ERROR = "Error deleting the district. It may be associated with other entities.";

    // Category Errors
    public static final String CATEGORY_NOT_FOUND = "The specified category does not exist.";
    public static final String CATEGORY_CREATION_ERROR = "Error creating the category. Please try again.";
    public static final String CATEGORY_UPDATE_ERROR = "Error updating the category. Please check your input.";
    public static final String CATEGORY_DELETE_ERROR = "Error deleting the category. It may be associated with other entities.";

    // Favorite Errors
    public static final String FAVORITE_NOT_FOUND = "The requested favorite item does not exist.";
    public static final String FAVORITE_CREATION_ERROR = "Error adding the item to favorites. Please try again.";
    public static final String FAVORITE_DELETE_ERROR = "Error removing the item from favorites. Please ensure it exists.";

    // TourRequest Errors
    public static final String TOUR_REQUEST_START_DATE_IS_EARLIER_THAN_LAST_REGISTRATION_DATE = "Error: The start date cannot be earlier than the last registration date";
    public static final String TOUR_REQUEST_END_DATE_IS_EARLIER_THAN_START_DATE = "Error: The end date cannot be earlier than start date";
    public static final String TOUR_REQUEST_NOT_FOUND = "The requested tour could not be found.";
    public static final String TOUR_REQUEST_CREATION_ERROR = "Error creating the tour request. Please try again.";
    public static final String TOUR_REQUEST_UPDATE_ERROR = "Error updating the tour request. Please check your input.";
    public static final String TOUR_REQUEST_DELETE_ERROR = "Error deleting the tour request. Please ensure it exists.";

    // Image Errors
    public static final String IMAGE_NOT_FOUND = "The requested image does not exist.";
    public static final String IMAGE_UPLOAD_ERROR = "Error uploading the image. Please try again.";
    public static final String IMAGE_DELETE_ERROR = "Error deleting the image. Please ensure it exists.";
    public static final String IMAGE_FORMAT_ERROR = "Invalid image format. Please upload a valid image file.";

    // AdvertType Errors
    public static final String ADVERT_TYPE_NOT_FOUND = "The specified advert type does not exist.";
    public static final String ADVERT_TYPE_CREATION_ERROR = "Error creating the advert type. Please try again.";
    public static final String ADVERT_TYPE_UPDATE_ERROR = "Error updating the advert type. Please check your input.";
    public static final String ADVERT_TYPE_DELETE_ERROR = "Error deleting the advert type. It may be associated with adverts.";

    // General Errors
    public static final String DATABASE_CONNECTION_ERROR = "Error connecting to the database. Please try again later.";
    public static final String DATABASE_QUERY_ERROR = "Error executing database query. Please check your input or contact support.";
    public static final String UNAUTHORIZED_ACCESS = "You are not authorized to perform this action.";
    public static final String INVALID_INPUT_FORMAT = "Invalid input format. Please enter the correct information.";
    public static final String REQUIRED_FIELD_MISSING = "A required field is missing. Please fill out all required fields.";
    public static final String UNKNOWN_ERROR = "An unknown error occurred. Please contact support.";

}
