package faisal.com.reptophonebook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
private EditText inputUserId;
private EditText inpUserPassword;
private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lgoin);
        inputUserId=findViewById(R.id.inpUser);
        inpUserPassword=findViewById(R.id.inpPassword);
        inputUserId=findViewById(R.id.inpPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId=inputUserId.getText().toString().trim();
                String userPass=inpUserPassword.getText().toString().trim();
                Toast.makeText(getBaseContext(),"User: "+userId+" Pass: "+userPass,Toast.LENGTH_LONG).show();
            }
        });
    }
}
