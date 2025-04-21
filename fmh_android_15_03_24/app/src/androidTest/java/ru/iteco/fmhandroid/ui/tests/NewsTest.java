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
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.AboutStep;
import ru.iteco.fmhandroid.ui.steps.AuthStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;
import ru.iteco.fmhandroid.ui.steps.NewsControlPanelStep;
import ru.iteco.fmhandroid.ui.steps.NewsFilterStep;
import ru.iteco.fmhandroid.ui.steps.NewsStep;
import ru.iteco.fmhandroid.ui.steps.QuotesStep;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    AuthStep authStep = new AuthStep();
    MainStep mainStep = new MainStep();
    QuotesStep quotesStep = new QuotesStep();
    NewsStep newsStep = new NewsStep();
    AboutStep aboutStep = new AboutStep();
    NewsControlPanelStep newsControlPanelStep = new NewsControlPanelStep();
    NewsFilterStep newsFilterStep = new NewsFilterStep();
    private View decorView;

    @Before
    public void mainPage() {
        onView(isRoot()).perform(waitDisplayed(R.id.container_custom_app_bar_include_on_fragment_main, 8000));
        try {
            authStep.authScreenIsDisplayed();
            authStep.loginIn();
            mainStep.openNewsPage();
            newsStep.checkNewsIsDisplayed();
        } catch (NoMatchingViewException e) {
            mainStep.logout();
            authStep.loginIn();
            mainStep.openNewsPage();
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
    @Feature(value = "Тесты по разделу Новостей")
    @Story("Проверка что страница новостей открыта, отображаются все поля")
    public void shouldDisplayCompleteContentOnNewsPage() { // Переход на страницу "новости" через меню
        newsStep.checkNewsIsDisplayed();
    }

    @Test
    @Feature(value = "Тесты по разделу Новостей")
    @Story("Проверка отображения панели управления новостями")
    public void shouldAccessControlPanelWithAllElements() {
        newsControlPanelStep.openNewsControlPanelElement();
        newsControlPanelStep.checkThatControlPanelContentIsFull();
    }

    @Test
    @Feature(value = "Тесты по разделу Новостей")
    @Story("Увозврат на главную страницу")
    public void shouldReturnToHomePageFromNewsPageWithFullContentCheck() {
        newsStep.checkGoBackMainPage();
        mainStep.checkMainIsDisplayed();
    }

    @Test
    @Feature(value = "Тесты по разделу Новостей")
    @Story("Проверка функциональности отмены фильтрации новостей")
    public void shouldCancelNewsFilterWithoutApplyingChanges() {
        newsControlPanelStep.openNewsControlPanelElement();
        newsControlPanelStep.openNewsFilter();
        newsFilterStep.clickCancelButton();
        newsControlPanelStep.checkThatControlPanelContentIsFull();
    }
}
