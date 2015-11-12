package com.kelvinhado.bicycles.network;

import com.android.volley.Response;
import com.kelvinhado.bicycles.Parameters;
import com.kelvinhado.bicycles.contracts.Contracts;

/**
 * Created by kel on 10/16/15.
 */
public class ContractsRequest extends JacksonRequest<Contracts> {

    private static final String URL = Parameters.URL_CONTRACTS;

    public ContractsRequest(Response.Listener<Contracts> listener, Response.ErrorListener errorListener) {
        super(Method.GET, URL, Contracts.class, listener, errorListener);

    }


}
