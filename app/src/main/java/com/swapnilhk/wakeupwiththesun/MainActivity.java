package com.swapnilhk.wakeupwiththesun;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.swapnilhk.wakeupwiththesun.model.AlarmState;
import com.swapnilhk.wakeupwiththesun.model.ScheduleItem;
import com.swapnilhk.wakeupwiththesun.util.AlarmStateUtil;
import com.swapnilhk.wakeupwiththesun.util.AlarmTimeUtil;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    private AlarmState alarmState;
    private static final int MY_PERMISSION_ACCESS_COURSE_LOCATION = 0;
    private Switch onOffSwitch;
    private TextView onOffText;
    private TextView locationStatus;
    private Button locationActionButton;
    private TextView nextAlarmSchedule;
    private Button viewScheduleButton;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an instance of GoogleAPIClient.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        // Get instances of on  the components on the screen
        onOffSwitch = (Switch) findViewById(R.id.onOffSwitch);
        onOffText = (TextView) findViewById(R.id.onOffText);
        locationStatus = (TextView) findViewById(R.id.locationStatus);
        locationActionButton = (Button) findViewById(R.id.locationActionButton);
        nextAlarmSchedule = (TextView) findViewById(R.id.nextAlarmSchedule);
        viewScheduleButton = (Button) findViewById(R.id.viewSchedule);

        // Add action listeners wherever necessary
        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onOffText.setText(getString(isChecked ? R.string.alarm_on : R.string.alarm_off));
                alarmState.setAlarmOn(isChecked);
            }
        });
        locationActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleApiClient.connect();
            }
        });
        viewScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisplayScheduleActivity.class);
                Bundle b = new Bundle();
                b.putSerializable(DisplayScheduleActivity.ALARM_STATE, alarmState);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        // Load alarm state
        alarmState = AlarmStateUtil.readAlarmState(this);
        // If state is available load and loan main layout. Get location if nt
        if (alarmState == null || alarmState.getLocationName() == null) {
            // Get location
            mGoogleApiClient.connect();
        }
        else{
            // populate view
            populateViewUsingAlarmState();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        AlarmStateUtil.saveAlarmState(this, alarmState);
        mGoogleApiClient.disconnect();
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(alarmState == null || alarmState.getLocationName() == null){
            mGoogleApiClient.connect();
        }
    }


    private void populateViewUsingAlarmState() {
        onOffSwitch.setChecked(alarmState.isAlarmOn());
        onOffText.setText(alarmState.isAlarmOn() ? getString(R.string.alarm_on) : getString(R.string.alarm_off));
        locationStatus.setText(alarmState.getLocationName());
        locationActionButton.setText(getString(R.string.locationButtonChange));
        nextAlarmSchedule.setText(AlarmTimeUtil.getSunriseTime(
                new ScheduleItem(alarmState.getLongitude(), alarmState.getLatitude(), new Date())));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_ACCESS_COURSE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mGoogleApiClient.connect();
                }
                //
            }
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSION_ACCESS_COURSE_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            return;
        }
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {

            alarmState.setLatitude(mLastLocation.getLatitude());
            alarmState.setLongitude(mLastLocation.getLongitude());

            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                StringBuilder stringBuilder = new StringBuilder();
                if(city != null && !city.equals(""))
                    stringBuilder.append(city);
                if(state != null && !state.equals(""))
                    stringBuilder.append(", "+state);
                if(country != null && !country.equals(""))
                    stringBuilder.append(", "+country);
                alarmState.setLocationName(stringBuilder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            populateViewUsingAlarmState();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

}
