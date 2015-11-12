package com.kelvinhado.bicycles;

/**
 * Created by kel on 10/1/15.
 */
public class Parameters {

    public static final String API_KEY = "4c72a347b7c687c2407f7ae44a3998e0ceebb82b";
    public static final String URL_CONTRACTS = "https://api.jcdecaux.com/vls/v1/contracts"+"?apiKey="+ API_KEY;
    public static final String URL_STATION = "https://api.jcdecaux.com/vls/v1/stations";



    public static String generateStationUrl(int station_number, String contract_name) {
        return URL_STATION + "/" + Integer.toString(station_number) + "?contract=" + contract_name + "&apiKey="+ API_KEY;
    }

    public static String generateStationsUrl(String contract_name) {
        return URL_STATION + "?contract=" + contract_name + "&apiKey="+ API_KEY;
    }
}
