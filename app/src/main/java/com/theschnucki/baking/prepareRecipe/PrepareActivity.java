package com.theschnucki.baking.prepareRecipe;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.theschnucki.baking.R;
import com.theschnucki.baking.chooseRecipe.RecipeActivity;
import com.theschnucki.baking.chooseRecipe.RecipeListFragment;
import com.theschnucki.baking.model.Recipe;
import com.theschnucki.baking.model.Step;

import java.util.ArrayList;

/**
 * Created by theSchnucki on 11.06.2018.
 */
public class PrepareActivity extends AppCompatActivity implements StepsListFragment.OnStepChosenListener{

    private static final String LOG_TAG = RecipeActivity.class.getSimpleName();

    android.support.v4.app.FragmentManager fragmentManager;

    public static Recipe recipe = null;
    public static Step step = null;

    //TODO preserve display on rotation (fragment)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preperation);

        Intent intent = getIntent();
        if (intent == null) {
            Log.e(LOG_TAG, "Intent is null");
            closeOnError();
        } else {
            recipe = intent.getParcelableExtra(RecipeListFragment.EXTRA_RECIPE);
            if (recipe == null) {
                Log.e(LOG_TAG, "Parcel not found");
                closeOnError();
            }
        }

        //Set ActionBar Title to Recipe Name
        getSupportActionBar().setTitle(recipe.getName());

        showStepList(recipe);

        Log.v(LOG_TAG, "Recipe name: " + recipe.getName());
    }

    public void showStepList(Recipe recipe) {
        ArrayList<Step> steps = recipe.getSteps();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("steps", steps);

        StepsListFragment stepsListFragment = new StepsListFragment();
        stepsListFragment.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.activity_prepare_fl, stepsListFragment).commit();
    }


    // Set Step detail view Fragment
    public void onStepSelected (Step step) {

        Log.v(LOG_TAG, "-----Step: " + step.getId() + " selected");

        this.step = step;

        Bundle bundle = new Bundle();
        bundle.putParcelable("step", step);

        StepDetailFragment stepDetailFragment = new StepDetailFragment();
        stepDetailFragment.setArguments(bundle);

        fragmentManager.beginTransaction().replace(R.id.activity_prepare_fl, stepDetailFragment).commit();
    }

    /** Step Detail button navigation */
    public void stepNavigationBack (View view) {
        if (step.getId() > 0) {
            step = recipe.getSteps().get(step.getId()-1);
            onStepSelected(step);
        } else {
            Toast.makeText(this, "This is already the first step", Toast.LENGTH_LONG).show();
        }
    }

    public void showIngredientList (View view) {
        Toast.makeText(getApplicationContext(), "Show ingredient List button pressed",
                Toast.LENGTH_LONG).show();
        Log.v(LOG_TAG,"Show ingredient List button pressed");
    }

    public void stepNavigationSteps (View view) {
        showStepList(recipe);
    }

    public void stepNavigationForward (View view) {
        if (step.getId() < (recipe.getSteps().size() -1)) {
            step = recipe.getSteps().get(step.getId()+1);
            onStepSelected(step);
        } else {
            Toast.makeText(this, "This is already the last step", Toast.LENGTH_LONG).show();
        }    }

    private void closeOnError(){
        finish();
    }

}