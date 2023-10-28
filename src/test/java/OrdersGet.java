import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.Orders.OrderSteps;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrdersGet {
        private OrderSteps orderSteps;

        @Test
        @DisplayName("Получение списка заказов")
        @Description("Список заказов не должен быть пустой")
        public void orderList() {
            orderSteps = new OrderSteps();
            ValidatableResponse validatableResponse = orderSteps.GetList();
            validatableResponse.assertThat()
                    .statusCode(SC_OK)
                    .body("orders", notNullValue());
        }
    }

