package faisal.com.reptophonebook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends Fragment {
    View view;
    private Button signInButton;
    private EditText userNameEdit;
    private EditText numberEdit;

    SharedPreferences preferences;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_lgoin,container,false);
       // return super.onCreateView(inflater, container, savedInstanceState);
        signInButton=view.findViewById(R.id.btnLogin);
        userNameEdit=view.findViewById(R.id.inpUser);
        numberEdit=view.findViewById(R.id.inpPassword);
        preferences=getActivity().getBaseContext().getSharedPreferences("myData", Context.MODE_PRIVATE);
        String existUserName=preferences.getString("userName",null);
        String existPassword=preferences.getString("password",null);

        if(existUserName!=null&&existPassword!=null) {
            Intent intent=new Intent(getActivity(),MainNavDrawer.class);
            startActivity(intent);
        }

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName=userNameEdit.getText().toString();
                String password=numberEdit.getText().toString();
                if(userName.equals("smac")&&password.equals("123456"))
                {
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("userName",userName);
                    editor.putString("password",password);
                    editor.commit();
                    Intent intent=new Intent(getActivity(),MainNavDrawer.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getActivity().getBaseContext(),"Username or Password Incorrect!!",Toast.LENGTH_LONG).show();
                }

            }
        });
        return view;
    }
}
