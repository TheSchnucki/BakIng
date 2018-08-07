package com.theschnucki.baking.prepareRecipe;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theschnucki.baking.R;
import com.theschnucki.baking.model.Step;

import java.util.ArrayList;

/**
 * Created by theSchnucki on 11.06.2018.
 */
public class StepsListFragment extends Fragment implements StepsAdapter.StepsAdapterOnClickHandler{

    private final static String LOG_TAG = StepsListFragment.class.getSimpleName();

    private ArrayList<Step> steps;

    private RecyclerView mRecyclerView;
    private static StepsAdapter mStepsAdapter;

    //Interface that triggers a callback in the host Activity
    OnStepChosenListener mCallback;

    //interface that calls a method in the host activity called onStepSelected
    public interface OnStepChosenListener {
        void onStepSelected (Step step);
    }

    @Override
    public void onAttach (Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnStepChosenListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement OnStepChosenListener");
        }
    }

    // Required empty public constructor
    public StepsListFragment() {}

    @Override
    public void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_step_list, container, false);

        mRecyclerView = rootView.findViewById(R.id.step_list_rv);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));


        mStepsAdapter = new StepsAdapter(this);

        mRecyclerView.setAdapter(mStepsAdapter);

        if (savedInstanceState != null){
            steps = savedInstanceState.getParcelableArrayList("steps");
        } else {
            loadStepsData();
        }

        return  rootView;
    }

    @Override
    public void onClick(Step step) {
        Log.v(LOG_TAG, "Step chosen: " + step.getId());
        mCallback.onStepSelected(step);
    }


    private void loadStepsData () {
        if (getArguments() != null){
            Bundle extras = getArguments();
            steps = extras.getParcelableArrayList("steps");
            Log.v(LOG_TAG,"-----" + steps.size());
            mStepsAdapter.setStepsList(steps);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("steps", steps);
    }
}
