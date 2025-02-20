package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.elements.QuotesElement;

public class QuotesStep {
    QuotesElement quotesElement = new QuotesElement();

    public void checkQuotesIsDisplayed() { // Проверка, что в блоке с цитатами полный контент
        quotesElement.logo.check(matches(isDisplayed()));
        quotesElement.title.check(matches(isDisplayed()));
        quotesElement.ourMissionList.check(matches(isDisplayed()));
    }

    public void checkQuote(int number) { //Развернуть/свернуть цитату
        quotesElement.missionConstraintLayout.check(matches(isDisplayed()));
        quotesElement.missionConstraintLayout.perform(actionOnItemAtPosition(number, click()));
    }

    public void descriptionIsDisplay(String text) { // Отображение дополнительной цитаты
        onView(allOf(
                withId(R.id.our_mission_item_description_text_view),
                withText(text),
                isCompletelyDisplayed()))
                .check(matches(isDisplayed()));
    }
}
