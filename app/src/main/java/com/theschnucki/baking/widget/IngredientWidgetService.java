package com.theschnucki.baking.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.theschnucki.baking.model.Recipe;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class IngredientWidgetService extends IntentService {

    private static final String LOG_TAG = IngredientWidgetService.class.getSimpleName();

    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_UPDATE_INGREDIENTS = "com.theschnucki.baking.widget.action.update_ingredients";
    private static final String ACTION_UPDATE_RECIPE = "com.theschnucki.baking.widget.action.update_recipe";

    private static final String EXTRA_RECIPE = "com.theschnucki.baking.widget.extra.RECIPE";

    private Recipe recipe = null;

    public IngredientWidgetService() {
        super("IngredientWidgetService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionUpdateIngredients (Context context) {
        Intent intent = new Intent(context, IngredientWidgetService.class);
        intent.setAction(ACTION_UPDATE_INGREDIENTS);
        //intent.putExtra(EXTRA_RECIPE, recipe);
        context.startService(intent);


    }    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionUpdateRecipe (Context context, Recipe recipe) {
        Intent intent = new Intent(context, IngredientWidgetService.class);
        intent.setAction(ACTION_UPDATE_RECIPE);
        intent.putExtra(EXTRA_RECIPE, recipe);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_INGREDIENTS.equals(action)) {
                handleActionUpdateIngredients();
            } else if (ACTION_UPDATE_RECIPE.equals(action)) {
                recipe = intent.getParcelableExtra(EXTRA_RECIPE);
                handleActionUpdateRecipe();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionUpdateIngredients() {
        Log.v(LOG_TAG, "----- Service Handler for Ingredients is working");
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, IngredientWidgetProvider.class));

        IngredientWidgetProvider.updateIngredientWidgets(this, appWidgetManager, recipe, appWidgetIds);
    }

    private void handleActionUpdateRecipe() {
        Log.v(LOG_TAG, "----- Service Handler for Recipe is working for" + recipe.getName());
    }
}
