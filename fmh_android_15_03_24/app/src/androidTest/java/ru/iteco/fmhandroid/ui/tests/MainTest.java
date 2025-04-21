package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
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
import ru.iteco.fmhandroid.ui.steps.AboutStep;
import ru.iteco.fmhandroid.ui.steps.AuthStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;
import ru.iteco.fmhandroid.ui.steps.NewsStep;
import ru.iteco.fmhandroid.ui.steps.QuotesStep;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class MainTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    AuthStep authStep = new AuthStep();
    MainStep mainStep = new MainStep();
    QuotesStep quotesStep = new QuotesStep();
    NewsStep newsStep = new NewsStep();
    AboutStep aboutStep = new AboutStep();

    private View decorView;
    @Before
    public void mainPage() {
        onView(isRoot()).perform(waitDisplayed(R.id.container_custom_app_bar_include_on_fragment_main, 8000));
        try {
            authStep.authScreenIsDisplayed();
            authStep.loginIn();
            mainStep.checkMainIsDisplayed();
        } catch (NoMatchingViewException e) {
            mainStep.logout();
            authStep.loginIn();
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
    @Feature(value = "Тесты по главной странице")
    @DisplayName("Проверка что главная страница открыта, отображаются все поля")
    public void checkMainTest() {
        mainStep.checkMainIsDisplayed();
    }
    @Test
    @Feature(value = "Тесты по главной странице")
    @DisplayName("Переход на страницу \"новости\" через меню")
    public void openNewsInMenuTest() {
        mainStep.openNewsPage();
        newsStep.checkNewsIsDisplayed();
    }
    @Test
    @Feature(value = "Тесты по главной странице")
    @DisplayName("переход на страницу \"О программе\" через меню")
    public void openAboutTest() {
        mainStep.openAboutPage();
        aboutStep.checkAboutIsDisplayed();
    }
    @Test
    @Feature(value = "Тесты по главной странице")
    @DisplayName("Переход на страницу \"все новости\" через кнопку в теле страницы")
    public void openNewsTest() {

        mainStep.openAllNews();
        newsStep.checkNewsIsDisplayed();
    }
    @Test
    @Feature(value = "Тесты по главной странице")
    @DisplayName("Переход на страницу цитат")
    public void openQuotesTest() {   //  (@id/our_mission_image_button)

        mainStep.openQuotesPage();
        quotesStep.checkQuotesIsDisplayed();
    }
}