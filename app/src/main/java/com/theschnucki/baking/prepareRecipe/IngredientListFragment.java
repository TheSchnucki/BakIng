package com.theschnucki.baking.prepareRecipe;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theschnucki.baking.R;
import com.theschnucki.baking.model.Ingredient;

import java.util.ArrayList;

/**
 * Created by theSchnucki on 26.07.2018.
 */
public class IngredientListFragment extends Fragment {

    private final static String LOG_TAG = IngredientListFragment.class.getSimpleName();

    private ArrayList<Ingredient> ingredients;

    private RecyclerView mRecyclerView;
    private static IngredientsAdapter mIngredientsAdapter;

    //required empty constructor
    public IngredientListFragment () {}

    public void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceStates){
        View rootView = inflater.inflate(R.layout.fragment_ingredient_list, container, false);

        mRecyclerView = rootView.findViewById(R.id.ingredient_list_rv);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        mIngredientsAdapter = new IngredientsAdapter();

        mRecyclerView.setAdapter(mIngredientsAdapter);

        loadIngredientData();

        return rootView;
    }

    private void loadIngredientData () {
        if (getArguments() != null) {
            Bundle extras = getArguments();
            ingredients = extras.getParcelableArrayList("ingredients");
            Log.v(LOG_TAG, "-----" + ingredients.size());
            mIngredientsAdapter.setIngredentsList(ingredients);
        }
    }
}
