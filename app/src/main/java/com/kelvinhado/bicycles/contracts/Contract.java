package com.kelvinhado.bicycles.contracts;

import java.util.List;

public class Contract {

    public String name;
    public String commercial_name;
    public String country_code;
    public List<String> cities;

    @Override
    public String toString() {
        return "Contract{" +
                "name='" + name + '\'' +
                ", commercial_name='" + commercial_name + '\'' +
                ", country_code='" + country_code + '\'' +
                ", cities=" + cities +
                '}';
    }
}


