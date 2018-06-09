package faisal.com.reptophonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "myData", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String sql="create table table1 (id integer primary key autoincrement, name text, number text)";
       db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql="drop table if exists table1";
        db.execSQL(sql);;
        onCreate(db);
    }

    public void insert(Item item)
    {
        SQLiteDatabase database=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",item.getName());
        values.put("number",item.getNumber());
        database.insert("table1",null,values);
    }
public List<Item> getAllData()
{
    List<Item> items=new ArrayList<>();
    SQLiteDatabase database=getReadableDatabase();
    String sql="select * from table1";
    Cursor res=database.rawQuery(sql,null);
    if(res!=null)
    {
        while(res.moveToNext())
        {
            int id=res.getInt(0);
            String name=res.getString(1);
            String number=res.getString(2);
            Item item=new Item(id,name,number);
            items.add(item);

        }

    }

    return items;
}
public void deleteData(int id)
{
    SQLiteDatabase database=getWritableDatabase();
    database.delete("table1","id=?",new String[] {Integer.toString(id)});

}
    public void updateData(Item item)
    {
        SQLiteDatabase database=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",item.getName());
        values.put("number",item.getNumber());
        database.update("table1",values,"id=?",new String[] {Integer.toString(item.getId())});

    }
}
