package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AuthElement { // элементы на странице авторизации
    private final ViewInteraction loginField = onView(withHint("Login"));
    private final ViewInteraction passwordField = onView(withHint("Password"));
    private final ViewInteraction loginButton = onView(withId(R.id.enter_button));
    private final ViewInteraction authorizationHeader = onView(withText("Authorization"));
    public ViewInteraction getLoginField() {
        return loginField;
    }

    public ViewInteraction getPasswordField() {
        return passwordField;
    }

    public ViewInteraction getLoginButton() {
        return loginButton;
    }

    public ViewInteraction getAuthorizationHeader() {
        return authorizationHeader;
    }
}
