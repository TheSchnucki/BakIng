package com.theschnucki.baking;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.theschnucki.baking.chooseRecipe.RecipeActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

/**
 * Created by theSchnucki on 07.08.2018.
 */

@RunWith(AndroidJUnit4.class)
public class ChooseRecipeActivityTest {

    public static final String CAKE_NAME = "Recipe introduction";

    @Rule
    public ActivityTestRule<RecipeActivity> mActivityTestRule = new ActivityTestRule<> (RecipeActivity.class);

    @Before
    public void init() {
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void checkDisplay () {
        onView(withId(R.id.recipe_list_fragment)).check(matches(isDisplayed()));
    }

    @Test
    public void checkMenu () {
        onView(withId(R.id.recipe_list_fragment)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void checkMenuContent () {
        onView(withId(R.id.recipe_list_fragment)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }
}
