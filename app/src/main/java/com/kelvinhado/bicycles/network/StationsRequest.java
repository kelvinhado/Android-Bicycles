package com.kelvinhado.bicycles.network;

import com.android.volley.Response;
import com.kelvinhado.bicycles.Parameters;
import com.kelvinhado.bicycles.stations.Stations;

/**
 * Created by kel on 10/16/15.
 */
public class StationsRequest extends JacksonRequest<Stations> {

    public StationsRequest(String stationContractName, Response.Listener<Stations> listener, Response.ErrorListener errorListener) {
        super(Method.GET, Parameters.generateStationsUrl(stationContractName), Stations.class, listener, errorListener);

    }
}
