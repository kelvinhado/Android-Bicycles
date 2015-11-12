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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kelvinhado.bicycles.R;
import com.kelvinhado.bicycles.Tags;
import com.kelvinhado.bicycles.network.StationRequest;
import com.kelvinhado.bicycles.network.VolleyApplication;


public class DisplayStationInfoFragment extends Fragment {

    // TODO Clean the code in this class
    MapView mMapView;
    private GoogleMap googleMap;
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
        View v =  inflater.inflate(R.layout.fragment_display_station_info, container, false);
        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();// needed to get the map to display immediately
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        googleMap = mMapView.getMap();

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        updateStation(stationNumber,stationContractName);
    }
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
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

            // latitude and longitude
            LatLng globePosition = new LatLng(station.position.lat, station.position.lng);

            // create marker
            MarkerOptions marker = new MarkerOptions().position(globePosition).title("dispo : " + station.available_bikes);

            // Changing marker icon
            marker.icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

            // adding marker
            googleMap.addMarker(marker);
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(globePosition).zoom(20).build();
            googleMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));

            // Perform any camera updates here

        }
        else{
            tvName.setText("nothing to show");
        }


    }
}
