package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;

import ru.iteco.fmhandroid.ui.elements.NewsFilterElement;

public class NewsFilterStep {
    NewsFilterElement newsFilterElement = new NewsFilterElement();

    public void checkThatFilterNewsBlockContentIsFull() { // все поля страницы присутсвуют и видимы
        newsFilterElement.titlePage.check(matches(isDisplayed()));
        newsFilterElement.categoryText.check(matches(isDisplayed()));
        newsFilterElement.dateStartText.check(matches(isDisplayed()));
        newsFilterElement.dateEndText.check(matches(isDisplayed()));
        newsFilterElement.filterActive.check(matches(isDisplayed()));
        newsFilterElement.filterNotActive.check(matches(isDisplayed()));
        newsFilterElement.filterButton.check(matches(isDisplayed()));
        newsFilterElement.cancelButton.check(matches(isDisplayed()));
    }

    public void clickFilterButton() { // Нажать кнопку 'FILTER'
        newsFilterElement.filterButton.perform(click());
    }

    public void clickCancelButton() { // Нажать кнопку 'CANCEL'
        newsFilterElement.cancelButton.perform(click());
    }

    public void clickOKButton() { // Нажать кнопку 'ОК' в сообщении
        newsFilterElement.okButtonMessage.perform(click());
    }
    public void clickCancelMessageButton() { // Нажать кнопку 'CANCEL' в сообщении
        newsFilterElement.cancelButtonMessage.perform(click());
    }
    public void fillInStartDateField(String startDate) { // ввести начальную дату
        newsFilterElement.dateStartText.perform(replaceText(startDate));
    }

    public void fillInEndDateField(String endDate) { // ввести конечную дату
        newsFilterElement.dateEndText.perform(replaceText(endDate));
    }

    public void clickActiveCheckBox() {  // нажать на чекбокс 'Active'
        newsFilterElement.filterActive.perform(click());
    }

    public void clickNotActiveCheckBox() { // нажать на чекбокс 'Active'
        newsFilterElement.filterNotActive.perform(click());
    }

    public void checkBoxStatusActive(boolean checked) { // чекбокс Active отмечен или нет
        if (checked) {
            newsFilterElement.filterActive.check(matches(isChecked()));
        } else {
            newsFilterElement.filterActive.check(matches(isNotChecked()));
        }
    }

    public void checkBoxStatusNotActive(boolean checked) { // чекбокс Not active отмечен или нет
        if (checked) {
            newsFilterElement.filterNotActive.check(matches(isChecked()));
        } else {
            newsFilterElement.filterNotActive.check(matches(isNotChecked()));
        }
    }
}
