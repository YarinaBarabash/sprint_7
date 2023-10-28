package org.example.couriers;

public class LoginCourier {
    public String login;
    public String password;

    public LoginCourier(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public static LoginCourier from(CreateCourier CreateCourier) {
        return new LoginCourier(CreateCourier.getLogin(), CreateCourier.getPassword());
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
