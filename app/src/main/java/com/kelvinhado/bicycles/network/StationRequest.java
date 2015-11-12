package com.kelvinhado.bicycles.network;

import com.android.volley.Response;
import com.kelvinhado.bicycles.Parameters;
import com.kelvinhado.bicycles.stations.Station;

/**
 * Created by kel on 10/16/15.
 */
public class StationRequest extends JacksonRequest<Station> {


    public StationRequest(int stationNumber, String stationContractName, Response.Listener<Station> listener, Response.ErrorListener errorListener) {
        super(Method.GET, Parameters.generateStationUrl(stationNumber, stationContractName), Station.class, listener, errorListener);

    }

}
