import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.couriers.CourierResponse;
import org.example.couriers.CourierSteps;
import org.example.couriers.CreateCourier;
import org.example.couriers.LoginCourier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

public class CreateTest {
    private CourierSteps courierSteps;
    private CreateCourier createCourier;
    private CourierResponse courierResult;

    int courierID;

    @Before
    @Step("Создание данных для теста")
    public void setUp() {
        courierSteps = new CourierSteps();
        createCourier = new CreateCourier("kjbkb", "jbkb", "hknl");
        courierResult = new CourierResponse();
    }

    @Test
    @DisplayName("Создание нового курьера")
    @Description("курьера можно создать")
    public void courierCreatePositive() {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en"), new RandomService());
        String loginRandom = fakeValuesService.bothify("???????");

        createCourier.setLogin(loginRandom);
        ValidatableResponse validatableResponse = courierSteps.courierCreate(createCourier);
        LoginCourier courierLogin = LoginCourier.from(createCourier);
        courierID = courierSteps.courierLogin(courierLogin).extract().jsonPath().getInt("id");
        courierResult.courierCreateValid(validatableResponse);
    }

    @Test
    @DisplayName("Создание курьера с уже существующими данными")
    @Description("курьера нельзя создать с уже существующими данными")
    public void courierCannotCreateExistData() {
        courierSteps.courierCreate(createCourier);
        ValidatableResponse validatableResponse = courierSteps.courierCreate(createCourier);
        courierResult.courierCreateExistingData(validatableResponse);
    }

    @Test
    @DisplayName("Создание курьера с пустым полем логина")
    @Description("курьера нельзя создать без логина")
    public void courierCannotCreateWithOutLogin() {
        createCourier.setLogin(null);
        ValidatableResponse validatableResponse = courierSteps.courierCreate(createCourier);
        courierResult.courierCreateError(validatableResponse);
    }

    @Test
    @DisplayName("Создание курьера с пустым полем пароля")
    @Description("курьера нельзя создать без пароля")
    public void courierCannotCreateWithOutPassword() {
        createCourier.setPassword(null);
        ValidatableResponse validatableResponse = courierSteps.courierCreate(createCourier);
        courierResult.courierCreateError(validatableResponse);
    }

    @Test
    @DisplayName("Создание курьера без логина и пароля")
    @Description("курьера нельзя создать без пароля и логина")
    public void courierCannotCreateWithOutDate() {
        createCourier.setLogin(null);
        createCourier.setPassword(null);
        ValidatableResponse validatableResponse = courierSteps.courierCreate(createCourier);
        courierResult.courierCreateError(validatableResponse);
    }

    @After
    @Step("Удаление тестовых данных курьера")
    public void courierDeleted(){
        if(courierID > 0) {
            courierSteps.courierDeleted(courierID);
        }
    }
}