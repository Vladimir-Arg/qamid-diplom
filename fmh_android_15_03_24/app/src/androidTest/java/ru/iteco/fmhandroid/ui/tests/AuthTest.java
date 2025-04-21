
package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.iteco.fmhandroid.ui.data.DataHelper.emptyLoginAndPassword;
import static ru.iteco.fmhandroid.ui.data.DataHelper.invalidLoginAndPassword;
import static ru.iteco.fmhandroid.ui.data.DataHelper.invalidLoginAndPasswordAnalogicValid;
import static ru.iteco.fmhandroid.ui.data.DataHelper.passwordAndLoginWithSpaceAtTheBeginning;
import static ru.iteco.fmhandroid.ui.data.DataHelper.passwordAndLoginWithSpaceAtTheEnd;
import static ru.iteco.fmhandroid.ui.data.DataHelper.validLoginAndPassword;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitDisplayed;

import android.view.View;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.AuthStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;


@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class AuthTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    AuthStep authStep = new AuthStep();
    MainStep mainStep = new MainStep();
    private View decorView;
    @Before
    public void startPage() {
        onView(isRoot()).perform(waitDisplayed(R.id.container_custom_app_bar_include_on_fragment_main, 8000));
        try {
            authStep.authScreenIsDisplayed();
            authStep.checkAuthPageIsDisplayed();
        } catch (NoMatchingViewException e) {
            mainStep.logout();
        }
        mActivityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());

    }
    @After
    public void tearDown() {
        try {
            mainStep.logout();
        } catch (Exception ignored) {
        }
    }
    @Test
    @Feature(value = "Тесты авторизации")
    @DisplayName("Авторизация с валидными данными")
    public void validLoginValidPasswordTest() {

        authStep.loginAndPasswordAuthorization(validLoginAndPassword());
        mainStep.checkMainIsDisplayed();
    }
    @Test
    @Feature(value = "Тесты авторизации")
    @DisplayName("Авторизация с невалидными данными")
    public void invalidLoginAndPasswordTest() {
        authStep.loginAndPasswordAuthorization(invalidLoginAndPassword());
//        authStep.checkToastMessageText("Something went wrong. Try again later.", decorView); // выводится такой текст. с этой строкой тест проходит
        authStep.checkToastMessageText("Wrong login or password", decorView); // текстовка ошибки должна быть такая
    }
    @Test
    @Feature(value = "Тесты авторизации")
    @DisplayName("Авторизация с пустыми данными")
    public void emptyLoginAndPasswordTest() {
        authStep.loginAndPasswordAuthorization(emptyLoginAndPassword());
        authStep.checkToastMessageText("Login and password cannot be empty", decorView); // правильный вариант
  }

    @Test
    @Feature(value = "Тесты авторизации")
    @DisplayName("Авторизация с невалидными данными, аналогичными валидным")
    public void invalidLoginAndPasswordAnalogicValidTest() { // авторизация должна провалиться, но она пролоходит
        authStep.loginAndPasswordAuthorization(invalidLoginAndPasswordAnalogicValid());
        authStep.checkToastMessageText("Wrong login or password", decorView); // должна быть такая ошибка
    }
    @Test
    @Feature(value = "Тесты авторизации")
    @DisplayName("Авторизация с невалидными данными (валидные данные + пробел)")
    public void passwordAndLoginWithSpaceAtTheEndTest() {
        authStep.loginAndPasswordAuthorization(passwordAndLoginWithSpaceAtTheEnd());
        authStep.checkToastMessageText("Wrong login or password", decorView); //   должна быть такая ошибка
    }
    @Test
    @Feature(value = "Тесты авторизации")
    @DisplayName("Авторизация с невалидными данными(пробел + валидные данные)")
    public void passwordAndLoginWithSpaceAtTheBeginningTest() {
        authStep.loginAndPasswordAuthorization(passwordAndLoginWithSpaceAtTheBeginning());
        authStep.checkToastMessageText("Wrong login or password", decorView); // должна быть такая ошибка
    }
}