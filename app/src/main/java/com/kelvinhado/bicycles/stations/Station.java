package com.kelvinhado.bicycles.stations;

/**
 * Created by kel on 10/13/15.
 */
public class Station {
    //static datas
    public int number;
    public String contract_name;
    public String name;
    public String address;
    public Position position;
    public boolean banking;
    public boolean bonus;
    // dynamic datas
    public String status;
    public int bike_stands;
    public int available_bike_stands;
    public int available_bikes;
    public String last_update;



    @Override
    public String toString() {
        return "Station{" +
                "number=" + number +
                ", contract_name='" + contract_name + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contract_name='" + contract_name + '\'' +
                ", position=" + position +
                ", banking=" + banking +
                ", bonus=" + bonus +
                ", status='" + status + '\'' +
                ", bike_stands=" + bike_stands +
                ", available_bike_stands=" + available_bike_stands +
                ", available_bikes=" + available_bikes +
                ", last_update=" + last_update +
                '}';
    }

    protected static class Position {
        public double lat;
        public double lng;

        public Position() {
        }

        @Override
        public String toString() {
            return "Position{" +
                    "lat=" + lat +
                    ", lng=" + lng +
                    '}';
        }
    }
}