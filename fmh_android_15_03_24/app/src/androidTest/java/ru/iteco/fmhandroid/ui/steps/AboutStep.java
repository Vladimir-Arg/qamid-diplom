package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import ru.iteco.fmhandroid.ui.elements.AboutElement;

public class AboutStep {
    AboutElement aboutElement = new AboutElement();

    public void checkAboutIsDisplayed() {
//        elementWaiting(withId(R.id.about_company_info_label_text_view), 5000);
        aboutElement.logo.check(matches(isDisplayed()));
        aboutElement.backButton.check(matches(isDisplayed()));
        aboutElement.versionTitleField.check(matches(isDisplayed()));
        aboutElement.versionNumberField.check(matches(isDisplayed()));
        aboutElement.policyText.check(matches(isDisplayed()));
        aboutElement.termsOfUseText.check(matches(isDisplayed()));
        aboutElement.infoCompany.check(matches(isDisplayed()));
        aboutElement.privacyPolicyValue.check(matches(isDisplayed()));
        aboutElement.termsOfUseValue.check(matches(isDisplayed()));
    }

    public void goMain() { // На Главную страницу
        aboutElement.backButton.perform(click());
    }

    public void goToPrivacyPolicy() {
        aboutElement.privacyPolicyValue.perform(click());

    }

    public void goToTermsOfUse() {
        aboutElement.termsOfUseValue.perform(click());
    }
}
