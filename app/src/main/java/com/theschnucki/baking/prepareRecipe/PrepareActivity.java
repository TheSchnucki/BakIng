package com.theschnucki.baking.prepareRecipe;

import android.app.Fragment;
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
import com.theschnucki.baking.model.Ingredient;
import com.theschnucki.baking.model.Recipe;
import com.theschnucki.baking.model.Step;

import java.util.ArrayList;

/**
 * Created by theSchnucki on 11.06.2018.
 */
public class PrepareActivity extends AppCompatActivity implements StepsListFragment.OnStepChosenListener{

    private static final String LOG_TAG = RecipeActivity.class.getSimpleName();

    android.support.v4.app.FragmentManager fragmentManager;
    android.support.v4.app.Fragment mContent;

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
        } else if (savedInstanceState != null){
            recipe = savedInstanceState.getParcelable("recipe");
            step = savedInstanceState.getParcelable("step");
            Log.v(LOG_TAG, "-----Recipe and Step extracted from SavedInstanceState");
        } else {
            recipe = intent.getParcelableExtra(RecipeListFragment.EXTRA_RECIPE);
            if (recipe == null) {
                Log.e(LOG_TAG, "Parcel not found");
                closeOnError();
            }
        }

        //Set ActionBar Title to Recipe Name
        getSupportActionBar().setTitle(recipe.getName());

        if (savedInstanceState != null)  {
            Log.v(LOG_TAG, "-----Fragment in OnSaveInstanceState not null!");
            //mContent = getSupportFragmentManager().getFragment(savedInstanceState,"myFragment");
        } else {

        }

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
        mContent = stepsListFragment;
    }

    public void switchToStepList(View view) {
        ArrayList<Step> steps = recipe.getSteps();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("steps", steps);

        StepsListFragment stepsListFragment = new StepsListFragment();
        stepsListFragment.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.activity_prepare_fl, stepsListFragment).commit();
        mContent = stepsListFragment;
    }


    public void showIngredientList (View view) {
        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ingredients", ingredients);

        IngredientListFragment ingredientListFragment = new IngredientListFragment();
        ingredientListFragment.setArguments(bundle);

        Log.v(LOG_TAG,"Show ingredient List button pressed");

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.activity_prepare_fl, ingredientListFragment).commit();
        mContent = ingredientListFragment;
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
        mContent = stepDetailFragment;
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


    public void stepNavigationSteps (View view) {
        showStepList(recipe);
    }

    public void stepNavigationForward (View view) {
        if (step.getId() < (recipe.getSteps().size() -1)) {
            step = recipe.getSteps().get(step.getId()+1);
            onStepSelected(step);
        } else {
            Toast.makeText(this, "This is already the last step", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Trying to save Fragment state before orientation
     */
    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("recipe", recipe);
        outState.putParcelable("step", step);
        getSupportFragmentManager().putFragment(outState, "myFragment", mContent);
    }

    private void closeOnError(){
        finish();
    }

}