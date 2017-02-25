package baja.isave;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    public static final String PREFS_NAME = "user";
    public static final String KEY_ID_ARDUINO = "idArduino";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences user = getSharedPreferences(PREFS_NAME, 0);
        String idArduino = user.getString(KEY_ID_ARDUINO,"");

        if(idArduino.equals("")) {
            setContentView(R.layout.activity_register);
        } else {
            Intent i = new Intent(Register.this,myClock.class);
            startActivity(i);
            finish();
        }
    }

    public void goLogin(View view) {
        Intent i = new Intent (Register.this,Login.class);
        startActivity(i);
    }

    public void registerPressed (View view){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://afternoon-atoll-74920.herokuapp.com/api/android/register";


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equals("0")) {
                            Context context = getApplicationContext();
                            CharSequence text = "Username doesn't unique";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        } else {
                            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString(KEY_ID_ARDUINO, response);

                            // Commit the edits!
                            editor.commit();
                            Intent i = new Intent(Register.this,myClock.class);
                            startActivity(i);
                            finish();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Context context = getApplicationContext();
                        CharSequence text = "Error is Occurs";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
        ){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                EditText mEditUsername = (EditText)findViewById(R.id.username);
                EditText mEditPassword = (EditText)findViewById(R.id.password);
                EditText mEditName = (EditText) findViewById(R.id.name);
                EditText mEditIDArduino = (EditText)findViewById(R.id.idArduino);
                SharedPreferences user = getSharedPreferences(PREFS_NAME, 0);

                params.put("username", mEditUsername.getText().toString());
                params.put("password", mEditPassword.getText().toString());
                params.put("name", mEditName.getText().toString());
                params.put("idArduino", mEditIDArduino.getText().toString());
                params.put("token", user.getString(KEY_TOKEN, "1"));

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");

                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}
