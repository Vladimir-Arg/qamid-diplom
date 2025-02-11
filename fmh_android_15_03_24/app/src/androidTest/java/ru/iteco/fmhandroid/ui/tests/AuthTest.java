
package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.data.DataHelper.emptyLoginAndPassword;
import static ru.iteco.fmhandroid.ui.data.DataHelper.invalidLoginAndPassword;
import static ru.iteco.fmhandroid.ui.data.DataHelper.invalidLoginAndPasswordAnalogicValid;
import static ru.iteco.fmhandroid.ui.data.DataHelper.toastMessage;
import static ru.iteco.fmhandroid.ui.data.DataHelper.toastMessageEmpty;
import static ru.iteco.fmhandroid.ui.data.DataHelper.validLoginAndPassword;
import static ru.iteco.fmhandroid.ui.data.DataHelper.*;

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
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.ToastMatcher;
import ru.iteco.fmhandroid.ui.steps.AuthStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;


@LargeTest
//@RunWith(AndroidJUnit4.class)
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
            authStep.authScreenIsVisible();
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
    public void validLoginValidPasswordTest() {

        authStep.loginAndPasswordAuthorization(validLoginAndPassword());
//        authStep.loginIn();
        mainStep.checkMainIsDisplayed();
    }
    @Test
    public void invalidLoginAndPasswordTest() {
        authStep.loginAndPasswordAuthorization(invalidLoginAndPassword());
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
        authStep.checkToastMessageText("Something went wrong. Try again later.", decorView);

//        onView(withText("Something went wrong. Try again later."))
//                .inRoot(isDialog())
//                .check(matches(isDisplayed()));

//        onView(withText("Something went wrong. Try again later."))
//                .check(matches(isDisplayed()));

    }
    @Test
    public void emptyLoginAndPasswordTest() {
        authStep.loginAndPasswordAuthorization(emptyLoginAndPassword());
        onView(withText(toastMessageEmpty)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));    }

    @Test
    public void invalidLoginAndPasswordAnalogicValidTest() { // авторизация должна провалиться, но она пролоходит
        authStep.loginAndPasswordAuthorization(invalidLoginAndPasswordAnalogicValid());
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));    }
    @Test
    public void passwordAndLoginWithSpaceAtTheEndTest() {
        authStep.loginAndPasswordAuthorization(passwordAndLoginWithSpaceAtTheEnd());
        onView(withText(toastMessageEmpty)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));    }
    @Test
    public void passwordAndLoginWithSpaceAtTheBeginningTest() {
        authStep.loginAndPasswordAuthorization(passwordAndLoginWithSpaceAtTheBeginning());
        onView(withText(toastMessageEmpty)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));    }
}
