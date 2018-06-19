package com.theschnucki.baking.chooseRecipe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.theschnucki.baking.R;
import com.theschnucki.baking.model.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theSchnucki on 06.06.2018.
 */
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder> {

    public static final String LOG_TAG = RecipeAdapter.class.getSimpleName();

    private List<Recipe> mRecipeList;

    private final RecipeAdapterOnClickHandler mClickHandler;

    public interface RecipeAdapterOnClickHandler {
        void onClick (Recipe recipe);
    }

    //Constructor
    public RecipeAdapter(RecipeAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public class RecipeAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView recipeIv;
        public TextView nameTv;
        public TextView descriptionShortTv;

        public RecipeAdapterViewHolder (View view) {
            super(view);
            recipeIv = view.findViewById(R.id.recipe_iv);
            nameTv = view.findViewById(R.id.name_tv);
            descriptionShortTv = view.findViewById(R.id.description_short_tv);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            //Log.v(LOG_TAG, "Recipe Adapter onClick Position" + adapterPosition);
            Recipe recipe = mRecipeList.get(adapterPosition);
            mClickHandler.onClick(recipe);
        }
    }

    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = LayoutInflater.from(context).inflate(R.layout.recipe_list_item, viewGroup, false);
        return new RecipeAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder (RecipeAdapterViewHolder recipeAdapterViewHolder, int position) {
        String recipeImagePath = mRecipeList.get(position).getImageURL();
        if (recipeImagePath.length()> 5){
            Picasso.get()
                    .load(recipeImagePath)
                    .placeholder(R.drawable.ic_no_picture)
                    .into(recipeAdapterViewHolder.recipeIv);
        } else {
            Picasso.get()
                    .load(R.drawable.ic_no_picture)
                    .into(recipeAdapterViewHolder.recipeIv);
        }

        recipeAdapterViewHolder.nameTv.setText(mRecipeList.get(position).getName());
        recipeAdapterViewHolder.descriptionShortTv.setText(mRecipeList.get(position).getServings());
    }

    @Override
    public int getItemCount() {
        if (null == mRecipeList) return 0;
        return mRecipeList.size();
    }

    public void setRecipeList(ArrayList<Recipe> recipeList) {
        mRecipeList = recipeList;
        notifyDataSetChanged();
    }
}
