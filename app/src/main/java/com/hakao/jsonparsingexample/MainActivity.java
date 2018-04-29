package com.hakao.jsonparsingexample;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progress;
    List<UserModel> userModelList = new ArrayList<>();

    TextView messageTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageTV = findViewById(R.id.messageTV);

        getUserRequest();
    }

    void parseResponse(String response) {
        try {
            JSONArray userArray = new JSONArray(response);

            for (int i=0; i<userArray.length(); i++) {
                UserModel userModel = new UserModel(userArray.getJSONObject(i));
                userModelList.add(userModel);
            }

            UserModel firstUser = userModelList.get(0);
            messageTV.setText("name: " + firstUser.name + "\ncity: " + firstUser.addressModel.city);
            showToast("parsing successful");
        } catch (Exception e) {
            e.printStackTrace();
            showToast("parsing failed");
        }
    }

    public void getUserRequest() {

        progress = new ProgressDialog(this);
        progress.setMessage("Loading");
        progress.show();

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://jsonplaceholder.typicode.com/users";
        Log.e("URL", url);
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        progress.dismiss();
                        Log.e("Response", response);
                        parseResponse(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        progress.dismiss();
                        Log.e("ERROR","error => "+error.toString());
                        showToast("Sorry! Something went wrong.");
                    }
                }
        );
        queue.add(postRequest);

    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
