package com.swapnilhk.wakeupwiththesun.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.swapnilhk.wakeupwiththesun.R;
import com.swapnilhk.wakeupwiththesun.model.AlarmState;
import com.swapnilhk.wakeupwiththesun.model.ScheduleItem;
import com.swapnilhk.wakeupwiththesun.util.AlarmTimeUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by swapn_000 on 5/14/2017.
 */
public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private AlarmState alarmState;
    private ArrayList<ScheduleItem> scheduleDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public ScheduleAdapter(AlarmState alarmState) {
        this.alarmState = alarmState;
        this.scheduleDataset = new ArrayList<ScheduleItem>();
        Date d = new Date();
        for(int position = 0; position < 366; position ++)
            scheduleDataset.add(new ScheduleItem(alarmState.getLongitude(), alarmState.getLatitude(), new Date(d.getTime() + position * 24L * 60 * 60 * 1000)));
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.schedule_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(new SimpleDateFormat("yyyy-MM-dd").format(scheduleDataset.get(position).getDate())
                        +" : " + AlarmTimeUtil.getSunriseTime(scheduleDataset.get(position)));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return scheduleDataset.size();
    }
}