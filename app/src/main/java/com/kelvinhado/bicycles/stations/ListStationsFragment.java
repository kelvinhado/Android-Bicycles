package com.kelvinhado.bicycles.stations;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.kelvinhado.bicycles.R;
import com.kelvinhado.bicycles.Tags;
import com.kelvinhado.bicycles.network.StationsRequest;
import com.kelvinhado.bicycles.network.VolleyApplication;

import java.util.ArrayList;
import java.util.List;

public class ListStationsFragment extends ListFragment {

    OnStationSelectedListener mListener = null;
    private List<Station> stations;
    private ListStationsAdapter lvAdapter;
    private String stationContractName;


    public ListStationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        if(getArguments() != null) {
//            stationContractName = getArguments().getString(Tags.TAG_STATION_CONTRACT_NAME);
//        }
//        else {
        if (getActivity() != null)
        stationContractName = getActivity().getIntent().getExtras().getString(Tags.TAG_STATION_CONTRACT_NAME);
//        }
        stations = new ArrayList<>();
        lvAdapter = new ListStationsAdapter(getActivity(), stations);

    }

    @Override
    public void onStart() {
        super.onStart();

        StationsRequest stationsRequest = new StationsRequest(stationContractName,
                //if its works
                new Response.Listener<Stations>() {
                    @Override
                    public void onResponse(Stations stations) {
                        populateListView(stations);
                    }
                },
                // if it does not work
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("err001", error.getMessage());
                    }
                });

        // Add the request to the RequestQueue.
        VolleyApplication.getRequestQueue().add(stationsRequest);
    }


    private void populateListView(Stations stations) {
        lvAdapter = new ListStationsAdapter(getActivity(), stations);
        setListAdapter(lvAdapter);
    }

















       /* To hundle interation, the activity must implements this interface */

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnStationSelectedListener) activity;
        } catch (ClassCastException e) {
            // Unchecked exception.
            throw new ClassCastException(activity.toString()
                    + " must implement OnCustomItemSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Send the event to the host activity
        Station station = (Station) getListView().getItemAtPosition(position);
        mListener.onStationSelected(station);
    }

    public // Interface interne
    interface OnStationSelectedListener {
        void onStationSelected(Station station);
    }

    public static ListStationsFragment newInstance(String stationContractName) {
        ListStationsFragment myFragment = new ListStationsFragment();
        Bundle args = new Bundle();
        args.putString(Tags.TAG_STATION_CONTRACT_NAME, stationContractName);
        myFragment.setArguments(args);

        return myFragment;
    }

}
