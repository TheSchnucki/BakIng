package com.theschnucki.baking.chooseRecipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.theschnucki.baking.R;

public class RecipeActivity extends AppCompatActivity {

    private static final String LOG_TAG = RecipeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
