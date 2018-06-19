package com.theschnucki.baking.prepareRecipe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.theschnucki.baking.R;
import com.theschnucki.baking.model.Step;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theSchnucki on 11.06.2018.
 */
public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsAdapterViewHolder> {

    public static final String LOG_TAG = StepsAdapter.class.getSimpleName();

    private List<Step> mStepList;

    private final StepsAdapterOnClickHandler mClickHandler;

    public interface StepsAdapterOnClickHandler {
        void onClick (Step step);
    }

    //Constructor
    public StepsAdapter(StepsAdapterOnClickHandler clickHandler) {mClickHandler = clickHandler;}


    public class StepsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView stepNoTv;
        public TextView descriptionTv;

        public StepsAdapterViewHolder (View view) {
            super(view);
            stepNoTv = view.findViewById(R.id.step_no_tv);
            descriptionTv = view.findViewById(R.id.short_description_tv);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Log.v(LOG_TAG, "Steps Adapter onClick Position" + adapterPosition);
            Step step = mStepList.get(adapterPosition);
            mClickHandler.onClick(step);
        }
    }

    @Override
    public StepsAdapterViewHolder onCreateViewHolder (ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = LayoutInflater.from(context).inflate(R.layout.step_list_item, viewGroup, false);
        return new StepsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder (StepsAdapterViewHolder stepsAdapterViewHolder, int position) {
        stepsAdapterViewHolder.stepNoTv.setText(String.valueOf(position));
        stepsAdapterViewHolder.descriptionTv.setText(mStepList.get(position). getShortDescription());
    }

    @Override
    public int getItemCount() {
        if (null == mStepList) return 0;
        return mStepList.size();
    }

    public void setStepsList(ArrayList<Step> stepsList) {
        mStepList = stepsList;
        notifyDataSetChanged();
    }
}
