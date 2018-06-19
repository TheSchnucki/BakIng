package com.theschnucki.baking.prepareRecipe;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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

    public static Recipe recipe = null;


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

        ArrayList<Step> steps = recipe.getSteps();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("steps", steps);

        StepsListFragment stepsListFragment = new StepsListFragment();
        stepsListFragment.setArguments(bundle);

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.activity_prepare_fl, stepsListFragment).commit();


        Log.v(LOG_TAG, "Recipe name: " + recipe.getName());
    }



    public void onStepSelected (Step step) {

        Log.v(LOG_TAG, "-----Step: " + step.getId() + " selected");
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("step", step);
//
//        StepDetailFragment stepDetailFragment = new StepDetailFragment();
//        stepDetailFragment.setArguments(bundle);
//
//        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.activity_prepare_fl, stepDetailFragment).commit();
    }


    private void closeOnError(){
        finish();
    }

}