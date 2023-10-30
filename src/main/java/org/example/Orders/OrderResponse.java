package org.example.Orders;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.greaterThan;


public class OrderResponse {
    @Step("Создание заказа")
    public int createOrder(ValidatableResponse response) {
      return   response.assertThat()
                .statusCode(SC_CREATED)
                .body("track",greaterThan(0))
        .extract()
                .path("track");

    }
}
