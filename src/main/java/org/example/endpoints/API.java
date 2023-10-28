package org.example.endpoints;

public class API {//решила вынести в отдельное место для красоты
    public static String BASE_URL = "http://qa-scooter.praktikum-services.ru/";
    public static String LOGIN_COURIER = "/api/v1/courier/login";
    public static String POST_CREATE_COURIER = "/api/v1/courier";
    public static String DELETE_COURIER = "/api/v1/courier/:id";
    public static String ORDER_CREATE = "/api/v1/orders";
    public static String ORDER_GET = "/api/v1/orders";

}
