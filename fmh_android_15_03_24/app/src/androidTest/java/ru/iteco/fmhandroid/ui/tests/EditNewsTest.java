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
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.elements.ControlPanelElement;
import ru.iteco.fmhandroid.ui.steps.AuthStep;
import ru.iteco.fmhandroid.ui.steps.ControlPanelStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;
import ru.iteco.fmhandroid.ui.steps.NewsControlPanelStep;
import ru.iteco.fmhandroid.ui.steps.NewsCreateEditStep;
import ru.iteco.fmhandroid.ui.steps.NewsFilterStep;
import ru.iteco.fmhandroid.ui.steps.NewsStep;
@RunWith(AllureAndroidJUnit4.class)

public class EditNewsTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    AuthStep authStep = new AuthStep();
    MainStep mainStep = new MainStep();
    NewsStep newsStep = new NewsStep();
    NewsControlPanelStep newsControlPanelStep = new NewsControlPanelStep();
    NewsFilterStep newsFilterStep = new NewsFilterStep();
    NewsCreateEditStep newsCreateEditStep = new NewsCreateEditStep();
    ControlPanelStep controlPanelStep = new ControlPanelStep();
    ControlPanelElement controlPnelElement = new ControlPanelElement();
    private View decorView;
    String publicationDate = getCurrentDate();
    String publicationTime = getCurrentTime();
    String title = "Новость321";
    String description = "Описание123";
    String newTitle = "Новость отредактирована33";
    String newDescription = "Описание отредактировано33";


    @Before
    public void mainPage() {
        onView(isRoot()).perform(waitDisplayed(R.id.container_custom_app_bar_include_on_fragment_main, 8000));
        try {
            authStep.loginIn();
            mainStep.openNewsPage();
            newsStep.checkNewsIsDisplayed();
            controlPanelStep.opencontrolPanelElement();
            controlPanelStep.openCreateNewsButton();
            newsCreateEditStep.createNews(randomCategory(), title, publicationDate,
                    publicationTime, description);
            newsCreateEditStep.clickSaveButton();

        } catch (NoMatchingViewException e) {
            mainStep.logout();
            authStep.loginIn();
            mainStep.openNewsPage();
            controlPanelStep.opencontrolPanelElement();
            controlPanelStep.openCreateNewsButton();
            newsCreateEditStep.createNews(randomCategory(), title, publicationDate,
                    publicationTime, description);
            newsCreateEditStep.clickSaveButton();
        }
        mActivityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }
  /*  @After
    public void tearDown() {
        try {
            mainStep.logout();
        } catch (Exception ignored) {
        }
    }*/
    @Test
    @Feature(value = "Тесты по разделу Новостей")
    @Story("Редактирование новости")
    public void shouldSuccessfullyEditNewsAndVerifyChanges() {
/* Контрольнаый пример для свайпа до нужного элемента
onView(withId(R.id.your_view_id))
                .perform(ViewActions.scrollTo())
                .check(matches(isDisplayed()));*/
//        Свайп до нужного элемента
//        controlPnelElement.editNewsButton(title).perform(ViewActions.scrollTo())
//                .check(matches(isDisplayed()));
        // Прокрутка на определенное расстояние
//        scrollBy(500); // Прокрутка на 500 пикселей вниз
//        простая прокрутка вниз
        Espresso.onView(ViewMatchers.withId(R.id.scrollView)).perform(ViewActions.swipeDown());

        controlPanelStep.clickEditNews(title);
        newsCreateEditStep.checkThatnewsCreateEditElementContentIsFull();
        newsCreateEditStep.EditNewsFields(randomCategory(), newTitle, publicationDate,
                publicationTime, newDescription);
        newsCreateEditStep.changeStatus();
        newsCreateEditStep.clickSaveButton();
        controlPanelStep.checkIfNewsWithTitle(newTitle);
    }
    @Test
    @Feature(value = "Тесты по разделу Новостей")
    @Story("Отмена редактирования новости")
    public void shouldCancelEditAndMaintainOriginalNewsDetails() {

        controlPanelStep.clickEditNews(title);
        newsCreateEditStep.checkThatnewsCreateEditElementContentIsFull();
        newsCreateEditStep.changeStatus();
        newsCreateEditStep.clickCancelButton();
        newsCreateEditStep.clickOKButton();
        controlPanelStep.checkThatControlPanelContentIsFull();
    }
}
