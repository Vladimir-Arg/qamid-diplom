package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitDisplayed;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.elements.NewsControlPanelElement;
import ru.iteco.fmhandroid.ui.elements.NewsElement;

public class NewsControlPanelStep {
    NewsControlPanelElement newsControlPanelElement = new NewsControlPanelElement();
    NewsElement newsElement = new NewsElement();
    NewsCreateEditStep newsCreateEditStep = new NewsCreateEditStep();


    public void openNewsControlPanelElement() {
        Allure.step("Переход в панель управления со страницы Новости");
        newsElement.controlPanelButton.perform(click());
        waitDisplayed(R.id.add_news_image_view, 5000);
    }

    public void checkThatControlPanelContentIsFull() {
        Allure.step("Проверка, что в панели управления полный контент");
        waitDisplayed(R.id.add_news_image_view, 5000);
        newsControlPanelElement.logo.check(matches(isDisplayed()));
        newsControlPanelElement.sortButton.check(matches(isDisplayed()));
        newsControlPanelElement.filterButton.check(matches(isDisplayed()));
        newsControlPanelElement.addNewsButton.check(matches(isDisplayed()));
    }

    public void clickSortNewsButton() {
        Allure.step("Нажать кнопку сортировки");
        newsControlPanelElement.sortButton.perform(click());
    }

    public void openNewsFilter() {
        Allure.step("Открыть расширенный фильтр");
        newsControlPanelElement.filterButton.perform(click());
    }

    public void openCreateNewsButton() {
        Allure.step("Нажать кнопку создание новости");
        newsControlPanelElement.addNewsButton.perform(click());
    }

    public void clickDeleteNews(String newsTitle) {
        Allure.step("Удалить новость с указанным заголовком");
        newsControlPanelElement.deleteNewsButton(newsTitle).perform(click());
        NewsCreateEditStep.clickOKButton();
    }

    public void clickEditNews(String newsTitle) {
        Allure.step("Нажать кнопку Корректировка новости");
        newsControlPanelElement.editNewsButton(newsTitle).perform(click());
    }

    public void checkIfNewsWithTitle(String titleText) {
        Allure.step("Проверка наличия новости с указанным заголовком");
        onView(allOf(withText(titleText), isDisplayed())).check(matches(isDisplayed()));
    }

    public void checkIfNoNewsWithTitle(String titleText) {
        Allure.step("Проверка, что новости с указанным заголовком нет");
        onView(allOf(withText(titleText), isDisplayed())).check(doesNotExist());
    }


}
