package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import android.view.View;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.elements.NewsCreateEditElement;

public class NewsCreateEditStep {
    NewsCreateEditElement newsCreateEditElement = new NewsCreateEditElement();

    public void checkThatnewsCreateEditElementContentIsFull() {
        Allure.step("Проверка, что в окне Редактирования новости полный контент");
        newsCreateEditElement.titlePage.check(matches(isDisplayed()));
        newsCreateEditElement.categoryText.check(matches(isDisplayed()));
        newsCreateEditElement.titleText.check(matches(isDisplayed()));
        newsCreateEditElement.descriptionText.check(matches(isDisplayed()));
        newsCreateEditElement.publicationDate.check(matches(isDisplayed()));
        newsCreateEditElement.time.check(matches(isDisplayed()));
        newsCreateEditElement.switcher.check(matches(isDisplayed()));
        newsCreateEditElement.saveButton.check(matches(isDisplayed()));
        newsCreateEditElement.cancelButton.check(matches(isDisplayed()));
    }

    public void fillInNewsCategoryField(String text) {
        Allure.step("Ввод данных в поле Категория");
        newsCreateEditElement.categoryText.perform(replaceText(text));
    }

    public void fillInNewsTitleField(String text) {
        Allure.step("Ввод данных в поле Заголовок");
        newsCreateEditElement.titleText.perform(replaceText(text));
    }

    public void fillInPublicDateField(String text) {
        Allure.step("Ввод данных в поле Дата публикации");
        newsCreateEditElement.publicationDate.perform(replaceText(text));
    }

    public void fillInTimeField(String text) {
        Allure.step("Ввод данных в поле Время");
        newsCreateEditElement.time.perform(replaceText(text));
    }

    public void fillInNewsDescriptionField(String text) {
        Allure.step("Ввод данных в поле Описание");
        newsCreateEditElement.descriptionText.perform(replaceText(text));
    }

    public void EditNewsFields(String category, String title, String publicationDate,
                               String publicationTime, String description) {
        Allure.step("Перезаполнение/редактирование данных новости");
        fillInNewsCategoryField(category);
        fillInNewsTitleField(title);
        fillInNewsDescriptionField(description);
        fillInPublicDateField(publicationDate);
        fillInTimeField(publicationTime);
    }

    public void changeStatus() {
        Allure.step("Поменять статус новости");
        newsCreateEditElement.switcher.perform(click());
    }

    public void clickSaveButton() {
        Allure.step("Нажатие кнопки Сохранить");
        newsCreateEditElement.saveButton.perform(click());
    }

    public void clickCancelButton() {
        Allure.step("Нажатие кнопки Отмена");
        newsCreateEditElement.cancelButton.perform(click());
    }

    public void clickOKButton() {
        Allure.step("Нажатие кнопки ОК в сообщении");
        newsCreateEditElement.okButtonMessage.perform(click());
    }
    public void checkToastMessageText(String text, View decorView) {
        Allure.step("Проверка сообщения");
        onView(withText(text))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }
    public void createNews(String category, String title, String publicationDate,
                           String publicationTime, String description) {
        Allure.step("Ввод данных для создания новости");
        fillInNewsCategoryField(category);
        fillInNewsTitleField(title);
        fillInPublicDateField(publicationDate);
        fillInTimeField(publicationTime);
        fillInNewsDescriptionField(description);
    }
}
