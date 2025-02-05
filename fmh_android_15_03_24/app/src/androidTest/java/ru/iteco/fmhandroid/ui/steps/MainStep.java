package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitDisplayed;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.elements.MainElement;

public class MainStep {
    MainElement mainElement = new MainElement();
//    PageObjectPage pageObjectPage = new PageObjectPage();

    public void entranceMain() {
//        pageObjectPage.menuPage("Main");
    }
    public void checkMainIsDisplayed() {
        onView(isRoot()).perform(waitDisplayed(R.id.authorization_image_button, 4000));
        mainElement.getAllNewsButton().check(matches(isDisplayed()));
    }
    public void logout() {
        onView(isRoot()).perform(waitDisplayed(R.id.authorization_image_button, 3000));
        mainElement.getAvatarImageButton().perform(click());
        mainElement.getExitButton().perform(click());
    }
}
