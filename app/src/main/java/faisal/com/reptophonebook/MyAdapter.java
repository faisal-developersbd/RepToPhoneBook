package faisal.com.reptophonebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Item> itemList;
    MyAdapter(Context context, List itemList)
    {
        this.context=context;
        this.itemList=itemList;
    }
    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
       if(convertView==null)
       {
           convertView=inflater.inflate(R.layout.childlayout,null);
       }
        TextView nameText=convertView.findViewById(R.id.nameText);
        TextView numberText=convertView.findViewById(R.id.numberText);
        ImageView imageView=convertView.findViewById(R.id.imageView);

        nameText.setText(itemList.get(position).getName());
        numberText.setText(itemList.get(position).getNumber());
        imageView.setImageResource(R.mipmap.ic_launcher_round);
        return convertView;
    }
}
