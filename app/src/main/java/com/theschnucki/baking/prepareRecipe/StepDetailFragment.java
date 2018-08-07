package com.theschnucki.baking.prepareRecipe;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.theschnucki.baking.R;
import com.theschnucki.baking.model.Step;

/**
 * Created by theSchnucki on 19.06.2018.
 */
public class StepDetailFragment extends Fragment {

    private final static String LOG_TAG = StepDetailFragment.class.getSimpleName();

    private TextView shortDescriptionTv;
    private TextView descriptionTv;


    //private SimpleExoPlayer mExoPlayer;
    private PlayerView mPlayerView;
    private SimpleExoPlayer mExoPlayer;

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

        if (savedInstanceState != null){
            step = savedInstanceState.getParcelable("step");
        } else {
            loadStepData();
        }

        shortDescriptionTv = rootView.findViewById(R.id.step_short_description_tv);
        mPlayerView = rootView.findViewById(R.id.step_video);
        descriptionTv = rootView.findViewById(R.id.step_description_tv);

        shortDescriptionTv.setText(step.getShortDescription());
        descriptionTv.setText(step.getDescription());


        mPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.ic_no_picture));

        // TODO initialize ExoPlayer
        String videoUrl = step.getVideoURL();
        Log.v(LOG_TAG, "----- Is valid URL: " + URLUtil.isValidUrl(videoUrl));


        if (URLUtil.isValidUrl(videoUrl) && !TextUtils.isEmpty(videoUrl)){
            Uri videoUri = Uri.parse(videoUrl);
            initializeExoPlayer(videoUri);
        } else {
            mPlayerView.setVisibility(View.GONE);
        }

        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null){

        }
    }

    private void loadStepData () {
        if (getArguments() != null) {
            Bundle extras = getArguments();
            step = extras.getParcelable("step");
            Log.v(LOG_TAG, "------ Step " + step.getShortDescription());
        }
    }

    private void initializeExoPlayer (Uri mediaUri){
        if (mExoPlayer == null) {
                //Crete an instance of ExoPlayer
                TrackSelector trackSelector = new DefaultTrackSelector();
                LoadControl loadControl = new DefaultLoadControl();
                mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
                mPlayerView.setPlayer(mExoPlayer);
                String userAgent = Util.getUserAgent(getContext(),"BakIng");
                MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                        getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
                mExoPlayer.prepare(mediaSource);
                mExoPlayer.setPlayWhenReady(true);
            }
    }

    private void releasePlayer () {
        if (mExoPlayer!= null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("step", step);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

}
