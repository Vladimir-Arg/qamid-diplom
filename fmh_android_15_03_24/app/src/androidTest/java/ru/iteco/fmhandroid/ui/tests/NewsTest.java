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
    public void shouldDisplayCompleteContentOnNewsPage() { // Переход на страницу "новости" через меню
        mainStep.openNewsPage();
        newsStep.checkNewsIsDisplayed();
    }
    @Test
    public void shouldDisplayCompleteContentAllNewsPage() { // Переход на страницу "все новости" через кнопку в теле страницы
        mainStep.openAllNews();
        newsStep.checkNewsIsDisplayed();
    }
    @Test

    public void shouldAccessControlPanelWithAllElements() {
        mainStep.openNewsPage();
        newsControlPanelStep.openNewsControlPanelElement();
        newsControlPanelStep.checkThatControlPanelContentIsFull();
    }
    @Test
    public void shouldReturnToHomePageFromNewsPageWithFullContentCheck() {
        mainStep.openNewsPage();
        newsStep.checkNewsIsDisplayed();
        newsStep.checkGoBackMainPage();
        newsStep.checkNewsIsDisplayed();
    }

    @Test
    public void shouldCancelNewsFilterWithoutApplyingChanges() { // Тест проверяет функциональность отмены фильтрации новостей
        mainStep.openNewsPage();
        newsControlPanelStep.openNewsControlPanelElement();
        newsControlPanelStep.openNewsFilter();
        newsFilterStep.clickCancelButton();
        newsControlPanelStep.checkThatControlPanelContentIsFull();
    }
}
