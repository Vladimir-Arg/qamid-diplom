package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class QuotesElement {
    public ViewInteraction logo;
    public ViewInteraction title;
    public ViewInteraction ourMissionList;
    public ViewInteraction missionConstraintLayout;

    public QuotesElement () {
        logo = onView(withId(R.id.trademark_image_view));
        title = onView(withId(R.id.our_mission_title_text_view));
        ourMissionList = onView(withId(R.id.our_mission_item_list_recycler_view));
        missionConstraintLayout = onView(allOf(withId(R.id.our_mission_item_list_recycler_view)));
    }
}
