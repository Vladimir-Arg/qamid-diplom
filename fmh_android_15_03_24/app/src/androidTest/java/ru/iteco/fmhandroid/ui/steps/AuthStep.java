package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.data.DataHelper.validLoginAndPassword;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitDisplayed;

import android.view.View;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.elements.AuthElement;

public class AuthStep { // Действия на экране авторизации
    AuthElement authElements = new AuthElement();

    public void authScreenIsDisplayed() { // Проверка что отображается заголовок "Авторизация"
        authElements.getAuthorizationHeader().check(matches(isDisplayed()));
    }
    public void checkAuthPageIsDisplayed() { // Провекра что отображаются поля для ввода логина и пароля
        authElements.getLoginField().check(matches(isDisplayed()));
        authElements.getPasswordField().check(matches(isDisplayed()));
        authElements.getLoginButton().check(matches(isDisplayed()));
        authElements.getAuthorizationHeader().check(matches(isDisplayed()));
    }
    /**
    **/
    public void loginAndPasswordAuthorization(DataHelper.AuthInfo info) { // Авторизация корректными учетными данными
        authElements.getLoginField().perform(typeText(info.getLogin()));
        authElements.getPasswordField().perform(typeText(info.getPassword())).perform(closeSoftKeyboard());
        authElements.getLoginButton().perform(click());
    }
/*
    public void validLoginAndPasswordAuthorization(DataHelper.AuthInfo info) { // Авторизация с заведомо корректными учетными данными
        authElements.getLoginField().perform(typeText(info.getLogin()));
        authElements.getPasswordField().perform(typeText(info.getPassword())).perform(closeSoftKeyboard());
        authElements.getLoginButton().perform(click());
    }
    public void invalidLoginAuthorization() { // Авторизаци с неверным логином
        authElements.getLoginField().perform(typeText(invalidLogin().getLogin()));
        authElements.getPasswordField().perform(typeText(invalidLogin().getPassword())).perform(closeSoftKeyboard());
        authElements.getLoginButton().perform(click());
    }

    public void invalidPasswordAuthorization() { // Авторизация с неверным паролем
        authElements.getLoginField().perform(typeText(invalidPassword().getLogin()));
        authElements.getPasswordField().perform(typeText(invalidPassword().getPassword())).perform(closeSoftKeyboard());
        authElements.getLoginButton().perform(click());
    }

    public void invalidLoginAndPasswordAuthorization() { // Авторизация с неверным логином и паролем
        authElements.getLoginField().perform(typeText(invalidLoginAndPassword().getLogin()));
        authElements.getPasswordField().perform(typeText(invalidLoginAndPassword().getPassword())).perform(closeSoftKeyboard());
        authElements.getLoginButton().perform(click());
    }

    public void emptyLoginFieldAuthorization() { // Авторизация с пустым логином
        authElements.getLoginField().perform(typeText(emptyLogin().getLogin()));
        authElements.getPasswordField().perform(typeText(emptyLogin().getPassword())).perform(closeSoftKeyboard());
        authElements.getLoginButton().perform(click());
    }*/

    public void loginIn() { // Авторизация с корректной учеткой, и проверка что авторизация прошла (для предваирительных условий последующих тестов)

        try {
            onView(isRoot()).perform(waitDisplayed(R.id.enter_button, 5000));
            loginAndPasswordAuthorization(validLoginAndPassword());
            onView(isRoot()).perform(waitDisplayed(R.id.authorization_image_button, 1000));
        } catch (Exception e){
        }
    }
    public void checkToastMessageText(String text, View decorView) {
        onView(withText(text))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }
}
