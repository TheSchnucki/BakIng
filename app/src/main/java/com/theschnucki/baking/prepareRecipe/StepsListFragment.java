package com.theschnucki.baking.prepareRecipe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theschnucki.baking.R;
import com.theschnucki.baking.chooseRecipe.RecipeListFragment;
import com.theschnucki.baking.model.Recipe;
import com.theschnucki.baking.model.Step;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theSchnucki on 11.06.2018.
 */
public class StepsListFragment extends Fragment {

    private final static String LOG_TAG = StepsListFragment.class.getSimpleName();

    private ArrayList<Step> steps;

    private RecyclerView mRecyclerView;
    private static StepsAdapter mStepsAdapter;

    //empty Constructor
    public StepsListFragment() {}

    @Override
    public void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceStates) {

        View rootView = inflater.inflate(R.layout.fragment_step_list, containter, false);

        mRecyclerView = rootView.findViewById(R.id.step_list_rv);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mStepsAdapter = new StepsAdapter();

        mRecyclerView.setAdapter(mStepsAdapter);

        loadStepsData();

        return  rootView;
    }

        // TODO set Step list from recipe
        private void loadStepsData () {
            if (getArguments() != null){
                Bundle extras = getArguments();
                steps = extras.getParcelableArrayList("steps");
                Log.v(LOG_TAG,"-----" + steps.size());
                mStepsAdapter.setStepsList(steps);
            }
        }
}
