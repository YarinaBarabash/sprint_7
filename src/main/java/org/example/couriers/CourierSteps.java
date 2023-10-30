package org.example.couriers;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.example.endpoints.API.*;

public class CourierSteps {

    public static RequestSpecification requestSpecification() { //есть сомнения
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL);
    }

    @Step("Регистрация нового курьера")
    public ValidatableResponse courierCreate(CreateCourier createCourier) {
        return requestSpecification()
                .body(createCourier)
                .when()
                .post(POST_CREATE_COURIER)
                .then();
    }


    @Step("Логин курьера")
    public ValidatableResponse courierLogin(LoginCourier loginCourier) {
        return requestSpecification()
                .body(loginCourier)
                .when()
                .post(LOGIN_COURIER)
                .then();
    }

    @Step("Удаление курьера")
    public ValidatableResponse courierDeleted(int courierID) {
        return requestSpecification()
                .when()
                .delete(DELETE_COURIER + courierID)
                .then();
    }
}

