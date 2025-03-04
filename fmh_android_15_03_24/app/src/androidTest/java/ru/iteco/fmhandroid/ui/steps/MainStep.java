package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitDisplayed;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.elements.MainElement;

public class MainStep { // Действия на главном экране (авторизованная зона)
    MainElement mainElement = new MainElement();

    public void checkMainIsDisplayed() { // Проверка что открыта главная страница (отображаются все элемен)
        onView(isRoot()).perform(waitDisplayed(R.id.authorization_image_button, 4000));
        mainElement.profileButton.check(matches(isDisplayed()));
        mainElement.menuButton.check(matches(isDisplayed()));
        mainElement.ourMissionButton.check(matches(isDisplayed()));
        mainElement.titleOfNewsContainer.check(matches(isDisplayed()));
        mainElement.allNewsButton.check(matches(isDisplayed()));    }
    public void logout() { // Выход из авторизованной зоны (для предваирительных условий последующих тестов)
        onView(isRoot()).perform(waitDisplayed(R.id.authorization_image_button, 3000));
        mainElement.profileButton.perform(click());
        mainElement.logOutButton.perform(click());
    }

    // Переход на страницу "новости" через меню
    public void openNewsPage() {
        mainElement.menuButton.perform(click());
        mainElement.newsOfMenu.perform(click());
    }
    // переход на страницу "О программе" через меню
    public void openAboutPage() {
        mainElement.menuButton.perform(click());
        mainElement.aboutOfMenu.perform(click());
    }
    // Переход на страницу "все новости" через кнопку в теле страницы
    public void openAllNews() {
        mainElement.allNewsButton.perform(click());
    }
    // переход на страницу цитат (@id/our_mission_image_button)
    public void openQuotesPage() {
        mainElement.ourMissionButton.perform(click());
    }
}
