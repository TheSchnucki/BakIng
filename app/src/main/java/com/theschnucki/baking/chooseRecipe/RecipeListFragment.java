package com.theschnucki.baking.chooseRecipe;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theschnucki.baking.R;
import com.theschnucki.baking.model.Recipe;
import com.theschnucki.baking.prepareRecipe.PrepareActivity;
import com.theschnucki.baking.utilities.JsonUtils;
import com.theschnucki.baking.utilities.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by theSchnucki on 06.06.2018.
 */
public class RecipeListFragment extends Fragment implements RecipeAdapter.RecipeAdapterOnClickHandler{

    private final static String LOG_TAG = RecipeListFragment.class.getSimpleName();

    public static final String EXTRA_RECIPE = "com.theschnucki.baking.chooseRecipe.RECIPE";

    private RecyclerView mRecyclerView;
    private static RecipeAdapter mRecipeAdapter;

    // Constructor
    public RecipeListFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadBakingData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the Fragment layout
        View rootView = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        mRecyclerView = rootView.findViewById(R.id.recipe_list_rv);

        //GridLayoutManager supports different number of columns
        int numberOfColumns = calculateNumberOfColumns(getContext());
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));

        mRecipeAdapter = new RecipeAdapter(this);

        mRecyclerView.setAdapter(mRecipeAdapter);

        return rootView;
    }

    private void loadBakingData () {
        new FetchRecipesTask().execute();
    }

    @Override
    public void onClick(Recipe recipe){

        Log.v(LOG_TAG, "Recipe chosen: " + recipe.getName());
        Intent intent = new Intent(getActivity(), PrepareActivity.class);
        intent.putExtra(EXTRA_RECIPE, recipe);
        Log.v(LOG_TAG, "Here the intent would start the PrepareActivity");
        // TODO activate intent call if ready
        startActivity(intent);
    }

    public class FetchRecipesTask extends AsyncTask<Void, Void, ArrayList<Recipe>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Recipe> doInBackground(Void... voids) {

            URL recipeRequestUrl = NetworkUtils.buildRecipesUrl();

            try {
                String jsonRecipeResponse = NetworkUtils.getResponseFromHttpsURL(recipeRequestUrl);

                ArrayList<Recipe> recipeList = JsonUtils.getRecipeListFromJson(RecipeListFragment.this.getActivity().getApplicationContext(), jsonRecipeResponse);

                return recipeList;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Recipe> loadedRecipeList) {
            //TODO add Error Handling if contents is missing
            if (loadedRecipeList != null) {
                mRecipeAdapter.setRecipeList(loadedRecipeList);
                Log.v(LOG_TAG, "Recipes loaded");
            } else {
                Log.v(LOG_TAG, "Recipes empty");
            }

        }
    }

    // this should calculate the number of rows in the main grid view
    private int calculateNumberOfColumns (Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 180; //see JsonUtils for resolution of downloaded image
        int noOfColumns = (int) (dpWidth/scalingFactor);
        return noOfColumns;
    }

}
