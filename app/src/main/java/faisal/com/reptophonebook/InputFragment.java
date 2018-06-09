package faisal.com.reptophonebook;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class InputFragment extends Fragment {
    View view;

    private EditText nameText;
    private EditText numberText;
    private Button btnAdd;
    private SearchView searchView;
    private ListView listView;
    private List<Item> itemList;
    MyAdapter adapter;
    private Context context;
    private DatabaseHelper dbHelper;
    private Button refreshButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.inputlayout,container,false);

        nameText=view.findViewById(R.id.editName);
        numberText=view.findViewById(R.id.editNumber);
        btnAdd=view.findViewById(R.id.addBtn);
        listView=view.findViewById(R.id.listView);
        itemList=new ArrayList<>();
        context=getActivity().getBaseContext();
        adapter=new MyAdapter(context,itemList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        dbHelper=new DatabaseHelper(context);
        refreshButton=view.findViewById(R.id.refresh);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshDataView();
            }
        });
        refreshDataView();
      listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
          @Override
          public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int postion, long l) {



              new AlertDialog.Builder(getActivity()).setTitle("Warning!!").setMessage("Choose Any Option").setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      dbHelper.deleteData(itemList.get(postion).getId());
                      refreshDataView();
                  }
              }).setNegativeButton("Call ", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      Intent intent=new Intent(Intent.ACTION_CALL);
                      intent.setData(Uri.parse("tel:"+itemList.get(postion).getNumber()));
                      startActivity(intent);
                  }
              }).setNeutralButton("Edit", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      Intent intent=new Intent(context,EditActivity.class);
                      intent.putExtra("name",itemList.get(postion).getName());
                      intent.putExtra("number",itemList.get(postion).getNumber());
                      intent.putExtra("id",""+itemList.get(postion).getId());

                      startActivity(intent);
                  }
              }).show();
              return false;
          }
      });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();
                String number=numberText.getText().toString();
                Item item=new Item(name,number);
               // itemList.add(item);
                dbHelper.insert(item);

                Toast.makeText(context,"One data inserted!",Toast.LENGTH_LONG).show();
                refreshDataView();
            }
        });
        return view;
    }
    public void refreshDataView()
    {
        itemList.clear();
        for(Item it: dbHelper.getAllData())
        {
            itemList.add(it);

        }
        adapter.notifyDataSetChanged();
    }

}
