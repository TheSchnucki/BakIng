package com.theschnucki.baking.prepareRecipe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theschnucki.baking.R;

/**
 * Created by theSchnucki on 11.06.2018.
 */
public class StepsListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private static StepsAdapter mStepsAdapter;

    //empty Constructor
    public StepsListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceStates) {

        View rootView = inflater.inflate(R.layout.fragment_step_list, containter, false);

        mRecyclerView = rootView.findViewById(R.id.step_list_rv);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mStepsAdapter = new StepsAdapter();

        mRecyclerView.setAdapter(mStepsAdapter);

        return  rootView;
    }
        // TODO set Step list from recipe

}
