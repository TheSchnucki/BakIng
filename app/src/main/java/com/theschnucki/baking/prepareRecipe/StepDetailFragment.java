package com.theschnucki.baking.prepareRecipe;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.theschnucki.baking.R;
import com.theschnucki.baking.model.Step;

/**
 * Created by theSchnucki on 19.06.2018.
 */
public class StepDetailFragment extends Fragment {

    private final static String LOG_TAG = StepDetailFragment.class.getSimpleName();

    private TextView shortDescriptionTv;
    private TextView videoTv;
    private TextView descriptionTv;
    private TextView navigationTv;

    private Step step;

    // Required empty public constructor
    public StepDetailFragment() {}



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_step_detail, container, false);

        loadStepData();

        shortDescriptionTv = rootView.findViewById(R.id.step_short_description_tv);
        videoTv = rootView.findViewById(R.id.step_video_tv);
        descriptionTv = rootView.findViewById(R.id.step_description_tv);
        navigationTv = rootView.findViewById(R.id.step_navigation_tv);

        shortDescriptionTv.setText(step.getShortDescription());
        videoTv.setText("VIDEO \n" + step.getVideoURL());
        descriptionTv.setText(step.getDescription());
        navigationTv.setText("NAVIGATION");


        return rootView;
    }

    private void loadStepData () {
        if (getArguments() != null) {
            Bundle extras = getArguments();
            step = extras.getParcelable("step");
            Log.v(LOG_TAG, "------ Step " + step.getShortDescription());
        }
    }

}
