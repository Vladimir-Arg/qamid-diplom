package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitDisplayed;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.elements.AuthElement;

public class AuthStep { // Экран авторизации
    AuthElement authElements = new AuthElement();

    public void authScreenIsVisible() {
        authElements.getAuthorizationHeader().check(matches(isDisplayed()));
    }
    public void checkLoginAndPasswordFieldsAreDisplayed() {
        authElements.getLoginField().check(matches(isDisplayed()));
        authElements.getPasswordField().check(matches(isDisplayed()));
    }
    public void validLoginAndPasswordAuthorization(DataHelper.AuthInfo info) {
        authElements.getLoginField().perform(typeText(info.getLogin()));
        authElements.getPasswordField().perform(typeText(info.getPassword())).perform(closeSoftKeyboard());
        authElements.getLoginButton().perform(click());
    }
    public void loginIn() {

        try {
            onView(isRoot()).perform(waitDisplayed(R.id.enter_button, 5000));
            validLoginAndPasswordAuthorization(DataHelper.validLoginAndPassword());
            onView(isRoot()).perform(waitDisplayed(R.id.authorization_image_button, 1000));
        } catch (Exception e){
        }
    }
}
