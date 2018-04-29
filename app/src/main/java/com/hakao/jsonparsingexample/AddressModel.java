package com.hakao.jsonparsingexample;

import org.json.JSONObject;

public class AddressModel {
    String street;
    String city;

    public AddressModel(){}

    public AddressModel(JSONObject addressObject) {
        try {
            street = addressObject.getString("street");
            city = addressObject.getString("city");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
