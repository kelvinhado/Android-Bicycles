package com.kelvinhado.bicycles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.kelvinhado.bicycles.stations.DisplayStationInfoFragment;
import com.kelvinhado.bicycles.stations.ListStationsFragment;
import com.kelvinhado.bicycles.stations.Station;


public class StationActivity extends ActionBarActivity
                                    implements ListStationsFragment.OnStationSelectedListener{

    private String stationContractName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);

        stationContractName = "undefined";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            stationContractName = extras.getString(Tags.TAG_STATION_CONTRACT_NAME);
        }



        // we have to feed the framefragment otherwise will have nothing to show for the portrait mode.
        if (findViewById(R.id.frameLayoutStationActivity) != null) {
            final ListStationsFragment listStationsFragment = ListStationsFragment.newInstance(stationContractName);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayoutStationActivity, listStationsFragment)
                    .commit();
        }

    }

    @Override
    public void onStationSelected(Station selectedStation) {

        if (findViewById(R.id.frameLayoutStationActivity) == null) {
            // If we are in landscape mode, we show article in the second
            // fragment. frameLayoutStationDetails
//            final DisplayStationInfoFragment detailsStationFragment = (DisplayStationInfoFragment) getSupportFragmentManager()
//                    .findFragmentById(R.id.fragmentStationDetails);
//            detailsStationFragment.updateStation(selectedStation.number, selectedStation.contract_name);

            final DisplayStationInfoFragment stationFragment = DisplayStationInfoFragment.newInstance(selectedStation);
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayoutStationDetails, stationFragment);
            ft.addToBackStack(null);
            ft.commit();
        } else {
            // Else, we show the other fragment in portrait mode.
            final DisplayStationInfoFragment stationFragment = DisplayStationInfoFragment.newInstance(selectedStation);
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayoutStationActivity, stationFragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }



    /* Menu Area */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_station, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.action_map_mode :
                Intent mapActivity = new Intent(StationActivity.this, StationMapsActivity.class);
                startActivity(mapActivity);
                break;
            default :
                // default action
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
