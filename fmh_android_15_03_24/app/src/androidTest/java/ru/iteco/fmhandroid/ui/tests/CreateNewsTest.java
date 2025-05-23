package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.iteco.fmhandroid.ui.data.DataHelper.Rand.randomCategory;
import static ru.iteco.fmhandroid.ui.data.DataHelper.getCurrentDate;
import static ru.iteco.fmhandroid.ui.data.DataHelper.getCurrentTime;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitDisplayed;

import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

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
import ru.iteco.fmhandroid.ui.steps.AuthStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;
import ru.iteco.fmhandroid.ui.steps.NewsControlPanelStep;
import ru.iteco.fmhandroid.ui.steps.NewsCreateEditStep;
import ru.iteco.fmhandroid.ui.steps.NewsFilterStep;
import ru.iteco.fmhandroid.ui.steps.NewsStep;

@RunWith(AllureAndroidJUnit4.class)
public class CreateNewsTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    AuthStep authStep = new AuthStep();
    MainStep mainStep = new MainStep();
    NewsStep newsStep = new NewsStep();
    NewsControlPanelStep newsControlPanelStep = new NewsControlPanelStep();
    NewsFilterStep newsFilterStep = new NewsFilterStep();
    NewsCreateEditStep createNewsStep = new NewsCreateEditStep();
    private View decorView;

    @Before
    public void mainPage() {
        onView(isRoot()).perform(waitDisplayed(R.id.container_custom_app_bar_include_on_fragment_main, 8000));
        try {
            authStep.authScreenIsDisplayed();
            authStep.loginIn();
            mainStep.checkMainIsDisplayed();
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
    @Story("Переход к созданию новости и Наличие всех элементов")
    public void shouldDisplayAllElementsOnCreateNewsPage() {
        newsControlPanelStep.openNewsControlPanelElement();
        newsControlPanelStep.openCreateNewsButton();
        createNewsStep.checkThatnewsCreateEditElementContentIsFull();
    }

    @Test
    @Feature(value = "Тесты по разделу Новостей")
    @Story("Cоздание новости с валидными данными")
    public void shouldSuccessfullyCreateNewsWithValidData() {
        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();
        String title = "TestTitle";
        String description = "TestDescription";
        newsControlPanelStep.openNewsControlPanelElement();
        newsControlPanelStep.openCreateNewsButton();
        createNewsStep.createNews(randomCategory(), title, publicationDate,
                publicationTime, description);
        createNewsStep.clickSaveButton();
        Espresso.pressBack();
        newsControlPanelStep.checkIfNewsWithTitle(title);
    }

    @Test
    @Feature(value = "Тесты по разделу Новостей")
    @Story("Cоздание новости с пустыми данными")
    public void shouldFailToCreateNewsWithEmptyFieldsAndShowMessage() {
        newsControlPanelStep.openNewsControlPanelElement();
        newsControlPanelStep.openCreateNewsButton();
        createNewsStep.clickSaveButton();
        authStep.checkToastMessageText("Fill empty fields", decorView);
    }

    @Test
    @Feature(value = "Тесты по разделу Новостей")
    @Story("Отменить создание новости")
    public void shouldReturnToControlPanelOnNewsCreationCancellation() {
        newsControlPanelStep.openNewsControlPanelElement();
        newsControlPanelStep.openCreateNewsButton();
        createNewsStep.clickCancelButton();
        createNewsStep.clickOKButton();
        newsControlPanelStep.checkThatControlPanelContentIsFull();
    }
}
