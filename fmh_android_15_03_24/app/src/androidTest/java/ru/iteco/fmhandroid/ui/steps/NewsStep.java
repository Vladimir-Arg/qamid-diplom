package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.elements.MainElement;
import ru.iteco.fmhandroid.ui.elements.NewsElement;


public class NewsStep {
    NewsElement newsElement = new NewsElement();
    MainStep mainStep = new MainStep();
MainElement mainElement = new MainElement();

    public void checkNewsIsDisplayed() {
        Allure.step("Проверка, что в блоке новостей полный контент");
        newsElement.logo.check(matches(isDisplayed()));
        newsElement.title.check(matches(isDisplayed()));
        newsElement.sort.check(matches(isDisplayed()));
        newsElement.filter.check(matches(isDisplayed()));
        newsElement.controlPanelButton.check(matches(isDisplayed()));
        newsElement.allNewsBlock.check(matches(isDisplayed()));
    }

    public void checkGoBackMainPage() {
        Allure.step("Возврат на Главную страницу со страницы Новости");
        mainElement.menuButton.perform(click());
        mainElement.mainOfMenu.perform(click());
    }

    public void clickSortNewsButton() {
        Allure.step("Нажать кнопку сортировки");
        newsElement.sort.perform(click());
    }

    public void openNewsFilter() {
        Allure.step("Открыть расширенный фильтр");
        newsElement.filter.perform(click());
    }

    public void clickOneNewsItem(int index) {
        Allure.step("Развернуть/свернуть новость");
        newsElement.childNewsButton.perform(actionOnItemAtPosition(index, click()));
    }

}
