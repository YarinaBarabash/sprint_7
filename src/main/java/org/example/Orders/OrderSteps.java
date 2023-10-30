package org.example.Orders;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.example.endpoints.API.*;

public class OrderSteps {
    public static RequestSpecification requestSpecification() {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL);

    }
    @Step("Создание заказа")
    public ValidatableResponse createOrder(OrdersCreate createOrder) {
        return requestSpecification()
                .body(createOrder)
                .when()
                .post(ORDER_CREATE)
                .then();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse getList() {
        return requestSpecification()
                .when()
                .get(ORDER_GET)
                .then();
    }

}
