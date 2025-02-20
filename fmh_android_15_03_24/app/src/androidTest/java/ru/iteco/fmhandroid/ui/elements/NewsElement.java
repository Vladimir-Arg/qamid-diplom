package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class NewsElement {
    public ViewInteraction logo;
    public ViewInteraction title;
    public ViewInteraction sort;
    public ViewInteraction filter;
    public ViewInteraction controlPanelButton;
    public ViewInteraction allNewsBlock;
    public ViewInteraction childNewsButton;

    public NewsElement() {
        logo = onView(withId(R.id.trademark_image_view));
        title = onView(withText("News"));
        sort = onView(withId(R.id.sort_news_material_button));
        filter = onView(withId(R.id.filter_news_material_button));
        controlPanelButton = onView(withId(R.id.edit_news_material_button));
        allNewsBlock = onView(withId(R.id.all_news_cards_block_constraint_layout));

    }
}
