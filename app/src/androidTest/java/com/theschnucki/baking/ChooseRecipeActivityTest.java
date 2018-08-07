package com.theschnucki.baking;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.theschnucki.baking.chooseRecipe.RecipeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

/**
 * Created by theSchnucki on 07.08.2018.
 */

@RunWith(AndroidJUnit4.class)
public class ChooseRecipeActivityTest {

    public static final String CAKE_NAME = "Nutella Pie";

    @Rule
    public ActivityTestRule<RecipeActivity> mActivityTestRule = new ActivityTestRule<> (RecipeActivity.class);

    @Test
    public void clickGridViewItem_OpensPrepareActivity () {
        onData(anything()).inAdapterView(withId(R.id.recipe_list_rv)).atPosition(1).perform(click());

        //onView(withId(R.id.step_list_rv)).check(matches(withText(CAKE_NAME)));
    }
}
