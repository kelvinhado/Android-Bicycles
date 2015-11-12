package com.kelvinhado.bicycles.stations;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.kelvinhado.bicycles.R;
import com.kelvinhado.bicycles.Tags;
import com.kelvinhado.bicycles.network.StationRequest;
import com.kelvinhado.bicycles.network.VolleyApplication;


public class DisplayStationInfoFragment extends Fragment {

    private Station station;
    private int stationNumber;
    private String stationContractName;

    public DisplayStationInfoFragment() {
        // Required empty public constructor
    }


    public static DisplayStationInfoFragment newInstance(Station station) {
        DisplayStationInfoFragment fragment = new DisplayStationInfoFragment();
        Bundle args = new Bundle();
        args.putInt(Tags.TAG_STATION_NUMBER, station.number);
        args.putString(Tags.TAG_STATION_CONTRACT_NAME, station.contract_name);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            station = null;
            stationNumber = getArguments().getInt(Tags.TAG_STATION_NUMBER);
            stationContractName = getArguments().getString(Tags.TAG_STATION_CONTRACT_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_station_info, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        updateStation(stationNumber,stationContractName);

    }


    public void updateStation(int stationNumber, String stationContractName) {

        StationRequest stationRequest = new StationRequest(stationNumber, stationContractName,
                //if its works
                new Response.Listener<Station>() {
                    @Override
                    public void onResponse(Station station) {
                        populateViews(true, station);
                    }
                },
                // if it does not work
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error.getMessage() != null)
                        Log.d("err001", error.getMessage());
                        populateViews(false, null);
                    }
                });

        // Add the request to the RequestQueue.
        VolleyApplication.getRequestQueue().add(stationRequest);
    }

    private void populateViews(boolean success, Station station) {

        TextView tvName = (TextView) getActivity().findViewById(R.id.tvStationName);
        TextView tvAvailableBike = (TextView) getActivity().findViewById(R.id.tvAvailableBikes);
        TextView tvBikeStand = (TextView) getActivity().findViewById(R.id.tvBikeStands);


        if(success) {
            Log.d("oo", station.toString());
            tvName.setText("" + station.name);
            tvAvailableBike.setText("" + station.available_bikes);
            tvBikeStand.setText("" + station.bike_stands);
        }
        else{
            tvName.setText("nothing to show");
        }

    }
}
