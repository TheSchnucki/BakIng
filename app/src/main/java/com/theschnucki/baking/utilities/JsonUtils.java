package com.theschnucki.baking.utilities;

import android.content.Context;
import android.util.Log;

import com.theschnucki.baking.model.Ingredient;
import com.theschnucki.baking.model.Recipe;
import com.theschnucki.baking.model.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by theSchnucki on 05.06.2018.
 */
public class JsonUtils {

    private static final String LOG_TAG = JsonUtils.class.getSimpleName();


    private final static String RECIPE_ID = "id";
    private final static String RECIPE_NAME = "name";
    private final static String RECIPE_SERVINGS = "servings";
    private final static String RECIPE_IMAGE_URL = "image";

    private final static String RECIPE_INGREDIENTS_ARRAY = "ingredients";
    private final static String RECIPE_INGREDIENTS_QUANTITY = "quantity";
    private final static String RECIPE_INGREDIENTS_MEASURE = "measure";
    private final static String RECIPE_INGREDIENTS_DESCRIPTION = "ingredient";

    private final static String RECIPE_STEPS_ARRAY = "steps";
    private final static String RECIPE_STEPS_ID = "id";
    private final static String RECIPE_STEPS_DESCRIPTION_SHORT = "shortDescription";
    private final static String RECIPE_STEPS_DESCRIPTION = "description";
    private final static String RECIPE_STEPS_VIDEO_URL = "videoURL";
    private final static String RECIPE_STEPS_THUMBNAIL_URL = "thumbnailURL";




    public static ArrayList<Recipe> getRecipeListFromJson (Context context, String recipeJsonString) throws JSONException {

        //List for the recipes
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

        JSONArray recipeListJson = new JSONArray(recipeJsonString);

        //error handling is different than on the JSONObject

        try {
            for (int i=0; i < recipeListJson.length(); i++){
                //single recipe
                Recipe recipe = new Recipe();
                ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
                ArrayList<Step> steps = new ArrayList<Step>();

                //Get single JSON object from list
                JSONObject singleRecipe = recipeListJson.getJSONObject(i);

                recipe.setId(singleRecipe.getInt(RECIPE_ID));
                recipe.setName(singleRecipe.getString(RECIPE_NAME));

                JSONArray ingredientListJson = singleRecipe.getJSONArray(RECIPE_INGREDIENTS_ARRAY);
                for (int j = 0; j < ingredientListJson.length(); j++){
                    //single ingredient
                    Ingredient ingredient = new Ingredient();

                    //get single JSON object from list
                    JSONObject singleIngredient = ingredientListJson.getJSONObject(j);

                    ingredient.setQuantity(singleIngredient.getString(RECIPE_INGREDIENTS_QUANTITY));
                    ingredient.setMeasure(singleIngredient.getString(RECIPE_INGREDIENTS_MEASURE));
                    ingredient.setName(singleIngredient.getString(RECIPE_INGREDIENTS_DESCRIPTION));

                    Log.v(LOG_TAG, "Recipe: " + i + " Ingredient: " + j);
                    ingredients.add(ingredient);
                }

                JSONArray stepListJson = singleRecipe.getJSONArray(RECIPE_STEPS_ARRAY);
                for (int k = 0; k < stepListJson.length(); k++){
                    //single step
                    Step step = new Step();

                    //get single JSON object from list
                    JSONObject singleStep = stepListJson.getJSONObject(k);

                    step.setId(singleStep.getInt(RECIPE_STEPS_ID));
                    step.setShortDescription(singleStep.getString(RECIPE_STEPS_DESCRIPTION_SHORT));
                    step.setDescription(singleStep.getString(RECIPE_STEPS_DESCRIPTION));
                    step.setVideoURL(singleStep.getString(RECIPE_STEPS_VIDEO_URL));
                    step.setThumbnailURL(singleStep.getString(RECIPE_STEPS_THUMBNAIL_URL));

                    Log.v(LOG_TAG, "Recipe: " + i + " Description: " + k);
                    steps.add(step);
                }

                recipe.setServings(singleRecipe.getString(RECIPE_SERVINGS));
                recipe.setImageURL(singleRecipe.getString(RECIPE_IMAGE_URL));

                recipeList.add(recipe);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.v(LOG_TAG, "getRecipeListFromJason");
        return recipeList;

    }
}
