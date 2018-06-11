package com.theschnucki.baking.prepareRecipe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.theschnucki.baking.R;
import com.theschnucki.baking.chooseRecipe.RecipeActivity;

/**
 * Created by theSchnucki on 11.06.2018.
 */
public class PrepareActivity extends AppCompatActivity {

    private static final String LOG_TAG = RecipeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
    }

}