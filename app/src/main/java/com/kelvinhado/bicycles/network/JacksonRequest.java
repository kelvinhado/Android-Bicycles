package com.kelvinhado.bicycles.network;

/**
 * Created by kel on 10/16/15.
 */
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class JacksonRequest<T> extends Request<T> {
    private final ObjectMapper objectMapper;
    private final Class<T> mClazz;
    private final Response.Listener<T> mListener;

    public JacksonRequest(int method, String url, Class<T> clazz,
                          Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mClazz = clazz;
        this.mListener = listener;
        objectMapper = new ObjectMapper();
    }

    public JacksonRequest(int method, String url, Class<T> clazz,
                          Response.Listener<T> listener, Response.ErrorListener errorListener, ObjectMapper objectMapper) {
        super(method, url, errorListener);
        this.mClazz = clazz;
        this.mListener = listener;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(objectMapper.readValue(json, mClazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonMappingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonParseException e) {
            return Response.error(new ParseError(e));
        } catch (IOException e) {
            return Response.error(new ParseError(e));
        }
    }
}