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
        setContentView(R.layout.activity_login);
    }

    EditText username = (EditText)findViewById(R.id.username);
    EditText password = (EditText)findViewById(R.id.password);

    public void login (View view) {
        if (username.getText().toString().equals("username") && password.getText().toString().equals("passwordd")) {

        }
    }

    public void goRegister (View view){
        Intent i = new Intent(Login.this,Register.class);
        startActivity(i);
    }
}
