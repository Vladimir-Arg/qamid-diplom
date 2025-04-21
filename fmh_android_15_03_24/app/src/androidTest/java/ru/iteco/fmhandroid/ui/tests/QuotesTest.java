package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitDisplayed;

import android.view.View;

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
import ru.iteco.fmhandroid.ui.steps.QuotesStep;

@RunWith(AllureAndroidJUnit4.class)
public class QuotesTest {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    QuotesStep quotesStep = new QuotesStep();
    AuthStep authStep = new AuthStep();
    MainStep mainStep = new MainStep();
    private View decorView;

    @Before
    public void mainPage() {
        onView(isRoot()).perform(waitDisplayed(R.id.container_custom_app_bar_include_on_fragment_main, 8000));
        try {
            authStep.authScreenIsDisplayed();
            authStep.loginIn();
            mainStep.openQuotesPage();
            quotesStep.checkQuotesIsDisplayed();

        } catch (NoMatchingViewException e) {
            mainStep.logout();
            authStep.loginIn();
            mainStep.openQuotesPage();
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
    @Feature(value = "Тесты по разделу Цитат")
    @Story("Тест проверяет наличие всех элементов на странице с цитатами")
    public void shouldDisplayAllElementsOnQuotesPage() {
        quotesStep.checkQuotesIsDisplayed();
    }

    @Test
    @Feature(value = "Тесты по разделу Цитат")
    @Story("Проверка функциональности раскрытия и сворачивания цитаты на странице")
    public void shouldToggleQuoteDisplay() {
        String quoteTestText = "\"Ну, идеальное устройство мира в моих глазах. Где никто не оценивает, никто не осудит, где говоришь, и тебя слышат, где, если страшно, тебя обнимут и возьмут за руку, а если холодно тебя согреют.” Юля Капис, волонтер";

        quotesStep.checkQuote(0);
        quotesStep.descriptionIsDisplay(quoteTestText);
        quotesStep.checkQuote(0);
    }
}
