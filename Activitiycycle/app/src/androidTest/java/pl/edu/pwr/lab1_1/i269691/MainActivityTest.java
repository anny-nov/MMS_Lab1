package pl.edu.pwr.lab1_1.i269691;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import junit.framework.TestCase;
import android.support.test.rule.ActivityTestRule;
import org.junit.Test;
import org.junit.Rule;

public class MainActivityTest extends TestCase {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    public void testOnCreate() {
    }

    public void testOnCreateOptionsMenu() {
    }

    public void testOnOptionsItemSelected() {

    }

    public void testOnClick() {
        onView(withId(R.id.MassInput)).perform(typeText("68"));
        onView(withId(R.id.HeightInput)).perform(typeText("163"));
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.BMIRes)).check(matches(withText("25.59")));
    }
}