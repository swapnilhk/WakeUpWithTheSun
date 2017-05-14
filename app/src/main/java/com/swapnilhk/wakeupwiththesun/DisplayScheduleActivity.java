package com.swapnilhk.wakeupwiththesun;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.swapnilhk.wakeupwiththesun.adapter.ScheduleAdapter;
import com.swapnilhk.wakeupwiththesun.model.AlarmState;

/**
 * Created by swapn_000 on 5/14/2017.
 */

public class DisplayScheduleActivity extends Activity {
    private RecyclerView scheduleRecyclerView;
    private RecyclerView.Adapter scheduleAdapter;
    private RecyclerView.LayoutManager scheduleLayoutManager;
    public static final String ALARM_STATE = "alarmState";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_recycler_view);
        scheduleRecyclerView = (RecyclerView) findViewById(R.id.schedule_recycler_view);

        Bundle b = this.getIntent().getExtras();
        if(b == null)
            return;
        AlarmState alarmState = (AlarmState) b.get(ALARM_STATE);
        if(alarmState == null)
            return;

        // use a linear layout manager
        scheduleLayoutManager = new LinearLayoutManager(this);
        scheduleRecyclerView.setLayoutManager(scheduleLayoutManager);
        scheduleRecyclerView.setHasFixedSize(true);
        // specify an adapter (see also next example)
        scheduleAdapter = new ScheduleAdapter(alarmState);
        scheduleRecyclerView.setAdapter(scheduleAdapter);
    }
}
