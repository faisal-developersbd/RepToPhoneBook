package faisal.com.reptophonebook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
private EditText nameText;
private TextView idText;
private EditText numnberText;
private Button btnUpdate;
DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        nameText=findViewById(R.id.editName);
        numnberText=findViewById(R.id.editNumber);
        btnUpdate=findViewById(R.id.btnUpdate);
        idText=findViewById(R.id.idText);
        db=new DatabaseHelper(getBaseContext());
        idText.setText(getIntent().getStringExtra("id"));
        nameText.setText(getIntent().getStringExtra("name"));
        numnberText.setText(getIntent().getStringExtra("number"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Settings Activity");
    }

    public void updateMethod(View view) {
        int id=Integer.parseInt(""+idText.getText().toString());
        String name=nameText.getText().toString();
        String number=numnberText.getText().toString();
        db.updateData(new Item(id,name,number));
        //finish();
        Toast.makeText(getBaseContext(),"Data updated successfully!",Toast.LENGTH_LONG).show();
    }

}
