import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.couriers.CourierResponse;
import org.example.couriers.CourierSteps;
import org.example.couriers.LoginCourier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class LoginTest {

    private CourierSteps courierSteps;
    private CourierResponse courierResult;
    private LoginCourier loginCourier;
    int courierID;

    @Before
    public void setUp() {
        loginCourier = new LoginCourier("kjbkb", "jbkb");
        courierResult = new CourierResponse();
        courierSteps = new CourierSteps();
    }

    @Test
    public void LoginCourier() {
        courierSteps.courierLogin(loginCourier);
        ValidatableResponse validatableResponse = courierSteps.courierLogin(loginCourier);
        courierResult.courierLoginValid(validatableResponse);
        courierID = validatableResponse.extract().path("id");
    }

    @DisplayName("Логин курьера без логина")
    @Description("Проверяем, что нельзя залогиниться без логина")
    @Test
    public void LoginWithOutLogin() {
        loginCourier.setLogin(null);
        ValidatableResponse validatableResponse = courierSteps.courierLogin(loginCourier);
        courierResult.courierLoginError(validatableResponse);
    }

    @DisplayName("Логин курьера без пароля")
    @Description("Проверяем, что нельзя залогиниться без пароля")
    @Test
    public void LoginWithOutPassword() {
        loginCourier.setPassword("");
        ValidatableResponse validatableResponse = courierSteps.courierLogin(loginCourier);
        courierResult.courierLoginError(validatableResponse);
    }

   @DisplayName("Логин по несуществующим кредам")
   @Description("вернет ошибку с несуществующими кредами")
           @Test
public void NonExistentCredits() {
        loginCourier.setLogin("sjksjdk");
        loginCourier.setPassword("ддыы");
        ValidatableResponse validatableResponse = courierSteps.courierLogin(loginCourier);
        courierResult.courierLoginNonExistingData(validatableResponse);
   }


    @After
    @Step("Удаление курьера")
    public void deleteCourier() {
        if (courierID > 0) {
            courierSteps.courierDeleted(courierID);
        }
    }
}
