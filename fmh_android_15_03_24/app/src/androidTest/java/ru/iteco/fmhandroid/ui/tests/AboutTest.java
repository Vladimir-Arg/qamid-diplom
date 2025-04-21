package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitDisplayed;

import android.view.View;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

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
import ru.iteco.fmhandroid.ui.steps.AboutStep;
import ru.iteco.fmhandroid.ui.steps.AuthStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;
import ru.iteco.fmhandroid.ui.steps.QuotesStep;

@RunWith(AllureAndroidJUnit4.class)
public class AboutTest {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    QuotesStep quotesStep = new QuotesStep();
    AboutStep aboutStep = new AboutStep();
    AuthStep authStep = new AuthStep();
    MainStep mainStep = new MainStep();
    private View decorView;

    @Before
    public void AboutPage() {
        onView(isRoot()).perform(waitDisplayed(R.id.container_custom_app_bar_include_on_fragment_main, 8000));
        try {
            authStep.authScreenIsDisplayed();
            authStep.loginIn();
            mainStep.openAboutPage();
            quotesStep.checkQuotesIsDisplayed();

        } catch (NoMatchingViewException e) {
            mainStep.logout();
            authStep.loginIn();
            mainStep.openAboutPage();
        }
        activityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }
    @After
    public void tearDown() {
        try {
            mainStep.logout();
        } catch (Exception ignored) {
        }
    }
    @Test
    @Feature(value = "Тесты по странице \"О приложении\"")
    @DisplayName("Наличие всех элементов страницы")
    public void shouldContainAllElementsOnAboutPage() {
        aboutStep.checkAboutIsDisplayed();
    }

    /*Тест проверяет функциональность кнопки возврата на главную страницу с страницы "О приложении" */
    @Test
    @Feature(value = "Тесты по странице \"О приложении\"")
    @DisplayName("Возвращение на главную")
    public void shouldReturnToMainPageFromAboutPage() {
        aboutStep.goMain();
        mainStep.checkMainIsDisplayed();
    }
    /*Тест предназначен для проверки возможности перехода на страницу "О приложении" из страницы "Новости". Однако кнопка перехода не активна, и перейти невозможно. */
    @Test
    @Feature(value = "Тесты по странице \"О приложении\"")
    @DisplayName("Переход к странице \"О приложении\", находясь на странице \"Новости\"")
    public void shouldNotNavigateToAboutPageFromNewsPageDueToInactiveLink() {
        aboutStep.goMain();
        mainStep.openNewsPage();
        mainStep.openAboutPage();
        aboutStep.checkAboutIsDisplayed();
    }

    /*Тест проверяет переход к политике конфиденциальности по кликабельной ссылке, хотя страница не загружается. */
    @Test
    @Feature(value = "Тесты по странице \"О приложении\"")
    @DisplayName("Переход к политике конфиденциальности по ссылке. Ссылка кликабельна, но страница фактически не загружается")
    public void shouldAttemptToOpenPrivacyPolicyLink() {
        Intents.init();
        aboutStep.goToPrivacyPolicy();
        intended(hasData("https://vhospice.org/#/privacy-policy"));
        Intents.release();
    }
    /*Тест проверяет переход к пользовательскому соглашению по кликабельной ссылке, но страница фактически не загружается */
    @Test
    @Feature(value = "Тесты по странице \"О приложении\"")
    @DisplayName("Переход к пользовательскому соглашению по ссылке. Ссылка кликабельна, но страница фактически не загружается")
    public void shouldAttemptToOpenTermsOfUseLink() {
        Intents.init();
        aboutStep.goToTermsOfUse();
        intended(hasData("https://vhospice.org/#/terms-of-use"));
        Intents.release();
    }
}