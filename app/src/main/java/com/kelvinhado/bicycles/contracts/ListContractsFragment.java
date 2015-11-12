package com.kelvinhado.bicycles.contracts;


import android.app.Activity;
import android.app.Fragment;
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
import com.kelvinhado.bicycles.network.ContractsRequest;
import com.kelvinhado.bicycles.network.VolleyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListContractsFragment extends ListFragment {

    OnContractSelectedListener mListener = null;
    private List<Contract> contracts;
    private ListContractsAdapter lvAdapter;

    public ListContractsFragment() {
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

        contracts = new ArrayList<>();
        lvAdapter = new ListContractsAdapter(getActivity(), contracts);
    }

    @Override
    public void onStart() {
        super.onStart();

        ContractsRequest contractsRequest = new ContractsRequest(
                //if its works
                new Response.Listener<Contracts>() {
                    @Override
                    public void onResponse(Contracts contracts) {
                        populateListView(contracts);
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
        VolleyApplication.getRequestQueue().add(contractsRequest);
    }


private void populateListView(Contracts contracts) {
    ListContractsAdapter adapter = new ListContractsAdapter(getActivity(), contracts);
    setListAdapter(adapter);
}






    /* To hundle interation, the activity must implements this interface */

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnContractSelectedListener) activity;
        } catch (ClassCastException e) {
            // Unchecked exception.
            throw new ClassCastException(activity.toString()
                    + " must implement OnCustomItemSelectedListener");
        }
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Send the event to the host activity
        Contract contract = (Contract) getListView().getItemAtPosition(position);
        mListener.onContractSelected(contract);
    }

    public // Interface interne
    interface OnContractSelectedListener {
        void onContractSelected(Contract contract);
    }
}
