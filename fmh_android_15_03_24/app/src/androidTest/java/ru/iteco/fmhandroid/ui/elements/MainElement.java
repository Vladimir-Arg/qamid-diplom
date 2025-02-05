package ru.iteco.fmhandroid.ui.elements;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class MainElement {
    private final ViewInteraction avatarImageButton = onView(withId(R.id.authorization_image_button));
    private final ViewInteraction exitButton = onView(withText("Log out"));
    private final ViewInteraction mainMenuButton = onView(withId(R.id.main_menu_image_button));
    private final ViewInteraction sectionMain = onView(withText("All news"));
    private final ViewInteraction sectionNews = onView(withText("News"));
    private final ViewInteraction allNewsButton = onView(withId(R.id.all_news_text_view));

    private final ViewInteraction quotesButton = onView(withId(R.id.our_mission_image_button));


    public ViewInteraction getAvatarImageButton() {
        return avatarImageButton;
    }

    public ViewInteraction getExitButton() {
        return exitButton;
    }

    public ViewInteraction getMainMenuButton() {
        return mainMenuButton;
    }

    public ViewInteraction getSectionMain() {
        return sectionMain;
    }

    public ViewInteraction getSectionNews() {
        return sectionNews;
    }

    public ViewInteraction getAllNewsButton() {
        return allNewsButton;
    }

    public ViewInteraction getQuotesButton() {
        return quotesButton;
    }
}
