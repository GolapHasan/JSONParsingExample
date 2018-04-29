package com.hakao.jsonparsingexample;

import org.json.JSONObject;

public class UserModel {
    String id;
    String name;
    AddressModel addressModel;

    public UserModel(){}

    public UserModel(JSONObject userObject) {
        try {
            id = userObject.getString("id");
            name = userObject.getString("name");
            addressModel = new AddressModel(userObject.getJSONObject("address"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
