package com.theschnucki.baking.widget;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.theschnucki.baking.R;
import com.theschnucki.baking.model.Ingredient;
import com.theschnucki.baking.model.Recipe;

import java.util.ArrayList;

public class ListWidgetService extends RemoteViewsService {


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteViewsFactory(this.getApplicationContext());
    }
}

class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    public static final String LOG_TAG = ListRemoteViewsFactory.class.getSimpleName();

    Context mContext;
    Recipe recipe;
    ArrayList<Ingredient> ingredients;

    public ListRemoteViewsFactory (Context applicationContext) {
        mContext = applicationContext;
    }

    @Override
    public void onCreate() {
        Log.v(LOG_TAG, "----- onCreate");
    }

    @Override
    public void onDataSetChanged() {
        recipe = IngredientWidgetService.getRecipe();
        //Log.v(LOG_TAG, "----- Got Recipe: " +  recipe.getName());
        if (recipe != null) ingredients = recipe.getIngredients();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (ingredients == null) return 0;
        return ingredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        if (ingredients == null || ingredients.size() == 0) return null;

        String count = ingredients.get(position).getQuantity();
        String measure = ingredients.get(position).getMeasure();
        String name = ingredients.get(position).getName();

        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.ingredient_widget_list_item);

        views.setTextViewText(R.id.ingredient_widget_amount, count);
        views.setTextViewText(R.id.ingredient_widget_measure, measure);
        views.setTextViewText(R.id.ingredient_widget_name, name);

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
