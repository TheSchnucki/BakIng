package com.theschnucki.baking.prepareRecipe;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.theschnucki.baking.R;
import com.theschnucki.baking.model.Step;

/**
 * Created by theSchnucki on 19.06.2018.
 */
public class StepDetailFragment extends Fragment {

    private final static String LOG_TAG = StepDetailFragment.class.getSimpleName();

    private TextView shortDescriptionTv;
    private TextView descriptionTv;
    private TextView navigationTv;

    private SimpleExoPlayer mExoPlayer;
    private PlayerView mPlayerView;

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
        mPlayerView = rootView.findViewById(R.id.step_video);
        descriptionTv = rootView.findViewById(R.id.step_description_tv);
        navigationTv = rootView.findViewById(R.id.step_navigation_tv);

        shortDescriptionTv.setText(step.getShortDescription());
        descriptionTv.setText(step.getDescription());
        navigationTv.setText("NAVIGATION");

        mPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.ic_no_picture));

        //initialize ExoPlayer
        String videoUrl = step.getVideoURL();
        Log.v(LOG_TAG, "----- Is valid URL: " + URLUtil.isValidUrl(videoUrl));

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
