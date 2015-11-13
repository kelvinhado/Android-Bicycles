package com.kelvinhado.bicycles.stations;

import java.util.List;

/**
 * Created by Kelvin HADO
 * on 13/11/15
 * http://www.github.com/kelvinhado
 */

public class StationsData {
    private static StationsData ourInstance = new StationsData();
    private static Stations stations;

    public static StationsData getInstance() {
        return ourInstance;
    }

    private StationsData() {
    }
}
