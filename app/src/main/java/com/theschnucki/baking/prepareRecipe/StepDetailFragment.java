package com.theschnucki.baking.prepareRecipe;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theschnucki.baking.R;

/**
 * Created by theSchnucki on 19.06.2018.
 */
public class StepDetailFragment extends Fragment {

    // Required empty public constructor
    public StepDetailFragment() {}



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_step_detail, container, false);
    }

}
