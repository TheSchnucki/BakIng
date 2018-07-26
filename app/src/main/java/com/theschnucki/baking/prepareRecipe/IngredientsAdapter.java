package com.theschnucki.baking.prepareRecipe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.theschnucki.baking.R;
import com.theschnucki.baking.model.Ingredient;
import com.theschnucki.baking.model.Step;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theSchnucki on 11.06.2018.
 */
public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsAdapterViewHolder> {

    public static final String LOG_TAG = IngredientsAdapter.class.getSimpleName();

    private List<Ingredient> mIngredientList;


    // empty Constructor
    public IngredientsAdapter() {}


    public class IngredientsAdapterViewHolder extends RecyclerView.ViewHolder{
        public TextView ingredientNameTv;
        public TextView ingredientMeasureTv;
        public TextView ingredientAmountTv;

        public IngredientsAdapterViewHolder(View view) {
            super(view);
            ingredientNameTv = view.findViewById(R.id.ingredient_name_tv);
            ingredientMeasureTv = view.findViewById(R.id.ingredient_measure_tv);
            ingredientAmountTv = view.findViewById(R.id.ingredient_amount_tv);
        }
    }

    @Override
    public IngredientsAdapterViewHolder onCreateViewHolder (ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = LayoutInflater.from(context).inflate(R.layout.ingredient_list_item, viewGroup, false);
        return new IngredientsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder (IngredientsAdapterViewHolder ingredientsAdapterViewHolder, int position) {
        ingredientsAdapterViewHolder.ingredientNameTv.setText(mIngredientList.get(position).getName());
        ingredientsAdapterViewHolder.ingredientMeasureTv.setText(mIngredientList.get(position).getMeasure());
        ingredientsAdapterViewHolder.ingredientAmountTv.setText(mIngredientList.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        if (null == mIngredientList) return 0;
        return mIngredientList.size();
    }

    public void setIngredentsList(ArrayList<Ingredient> ingredientsList) {
        mIngredientList = ingredientsList;
        notifyDataSetChanged();
    }
}
