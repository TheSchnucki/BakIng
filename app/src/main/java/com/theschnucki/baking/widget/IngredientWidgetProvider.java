package com.theschnucki.baking.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.theschnucki.baking.R;
import com.theschnucki.baking.model.Recipe;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientWidgetProvider extends AppWidgetProvider {

    private static final String LOG_TAG = IngredientWidgetProvider.class.getSimpleName();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, Recipe recipe,int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_ingredient_list);

        //Set the ListWidgetServiceIntent to act as the adapter for the ListView
        Intent intent = new Intent(context, ListWidgetService.class);
        views.setRemoteAdapter(R.id.widget_ingredient_list, intent);
        Log.v(LOG_TAG, "----- Remote View Set");
        //Set DetailActivity intent to launch when clicked

        //Set empty views
        views.setEmptyView(R.id.widget_ingredient_list, R.id.empty_view);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        IngredientWidgetService.startActionUpdateIngredients(context);
    }

    public static void updateIngredientWidgets (Context context, AppWidgetManager appWidgetManager, Recipe recipe, int[] appWidgetIds)
    {
        for (int appWidgetId : appWidgetIds ){
            updateAppWidget(context, appWidgetManager, recipe, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

