import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.Orders.OrderResponse;
import org.example.Orders.OrderSteps;
import org.example.Orders.OrdersCreate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

@RunWith(Parameterized.class)
public class OrderCreateTest {
    private OrderSteps orderSteps;
    private OrdersCreate orderCreate;
    private OrderResponse orderResult;
    private List<String> color;

    public OrderCreateTest(List<String> color) {
        this.color = color;
    }


    @Parameterized.Parameters (name = "Color")
    public static Object[][] dataGen() {
        return new Object[][] {
                {List.of("BLACK", "GREY")},
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of()}
        };
    }

    @Before
    public void setUp() {
        orderSteps = new OrderSteps();
        orderResult = new OrderResponse();
    }

    @Test
    @DisplayName("Создание заказа с разными цветами")
    @Description("цвета самоката: черный и серый, черный, серый")
    public void orderCreateColorWithParam() {
        orderCreate = new OrdersCreate(color);
        ValidatableResponse validatableResponse = orderSteps.CreateOrder(orderCreate);
        orderResult.CreateOrder(validatableResponse);
    }
}
