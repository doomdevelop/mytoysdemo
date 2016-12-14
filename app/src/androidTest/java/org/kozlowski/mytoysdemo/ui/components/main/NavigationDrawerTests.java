package org.kozlowski.mytoysdemo.ui.components.main;

import android.content.res.Resources;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.kozlowski.mytoysdemo.R;
import org.kozlowski.mytoysdemo.ui.component.main.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.contrib.DrawerActions.close;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsInstanceOf.any;

/**
 * Created by and on 14.12.16.
 */
@RunWith(AndroidJUnit4.class)
public class NavigationDrawerTests {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity
        .class);

    @Test
    public void testCloseNavigationWithBackButton() {
        onView(withId(R.id.drawer_layout)).perform(open()).check(matches(isOpen(Gravity.LEFT)));
        pressBack();
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT)));
    }

    @Test
    public void testOpenNavigation() {
        onView(withId(R.id.drawer_layout)).perform(open()).check(matches(isOpen(Gravity.LEFT)));
    }

    @Test
    public void testBackArrowOnNavigation() {
        onView(withId(R.id.drawer_layout)).perform(open()).check(matches(isOpen(Gravity.LEFT)));
        //the back arrow on header must be gone
        onView(withRecyclerView(R.id.rv_navigation).atPosition(0))
            .check(matches(hasDescendant(allOf(withId(R.id.header_back_arrow),
                withEffectiveVisibility(ViewMatchers.Visibility.GONE)))));
    }

    @Test
    public void testCloseButtonOnNavigation() {
        onView(withId(R.id.drawer_layout)).perform(open()).check(matches(isOpen(Gravity.LEFT)));
        //the close button must be Visible
        onView(withRecyclerView(R.id.rv_navigation).atPosition(0))
            .check(matches(hasDescendant(allOf(withId(R.id.header_close),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))));
    }

    @Test
    public void testClickOnNodeItemInNavigation() {
        onView(withId(R.id.drawer_layout)).perform(open()).check(matches(isOpen(Gravity.LEFT)));

        onView(withRecyclerView(R.id.rv_navigation).atPosition(2)).perform(recyclerClick());
        //back arrow must be visible
        onView(withRecyclerView(R.id.rv_navigation).atPosition(0))
            .check(matches(hasDescendant(allOf(withId(R.id.header_back_arrow),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))));
        //there is title of navigation category on header
        onView(withRecyclerView(R.id.rv_navigation).atPosition(0))
            .check(matches(hasDescendant(allOf(withId(R.id.header_title),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))));
        //navigation still open
        onView(withId(R.id.drawer_layout)).check(matches(isOpen(Gravity.LEFT)));

    }

    public RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    public static ViewAction recyclerClick() {
        return new ViewAction() {

            @Override
            public Matcher<View> getConstraints() {
                return any(View.class);
            }

            @Override
            public String getDescription() {
                return "performing click() on recycler view item";
            }

            @Override
            public void perform(UiController uiController, View view) {
                view.performClick();
            }
        };
    }

    /**
     * Source: https://gist.github.com/baconpat/8405a88d04bd1942eb5e430d33e4faa2
     */
    private final class RecyclerViewMatcher {

        private final int recyclerViewId;

        public RecyclerViewMatcher(int recyclerViewId) {
            this.recyclerViewId = recyclerViewId;
        }

        public Matcher<View> atPosition(final int position) {
            return atPositionOnView(position, -1);
        }

        public Matcher<View> atPositionOnView(final int position, final int targetViewId) {

            return new TypeSafeMatcher<View>() {
                Resources resources = null;
                View childView;

                public void describeTo(Description description) {
                    String idDescription = Integer.toString(recyclerViewId);
                    if (this.resources != null) {
                        try {
                            idDescription = this.resources.getResourceName(recyclerViewId);
                        } catch (Resources.NotFoundException var4) {
                            idDescription = String.format("%s (resource name not found)", recyclerViewId);
                        }
                    }

                    description.appendText("RecyclerView with id: " + idDescription + " at position: " + position);
                }

                public boolean matchesSafely(View view) {

                    this.resources = view.getResources();

                    if (childView == null) {
                        RecyclerView recyclerView =
                            (RecyclerView) view.getRootView().findViewById(recyclerViewId);
                        if (recyclerView != null && recyclerView.getId() == recyclerViewId) {
                            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                            if (viewHolder != null) {
                                childView = viewHolder.itemView;
                            }
                        } else {
                            return false;
                        }
                    }

                    if (targetViewId == -1) {
                        return view == childView;
                    } else {
                        View targetView = childView.findViewById(targetViewId);
                        return view == targetView;
                    }
                }
            };
        }
    }
}
