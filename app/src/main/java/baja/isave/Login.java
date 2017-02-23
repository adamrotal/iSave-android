package baja.isave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


//    public void login (View view) {
//        if (username.getText().toString().equals("username") && password.getText().toString().equals("passwordd")) {
//
//        }
//    }

    public void goRegister (View view){
        Intent i = new Intent(Login.this,Register.class);
        startActivity(i);
//        finish();
    }

    public void loginPressed (View view){
        Intent i = new Intent(Login.this,myClock.class);
        startActivity(i);
//        finish();
    }

}
