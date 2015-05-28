package com.example.cravisundaram.customlist2;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import com.example.cravisundaram.adapters.CustomBaseAdapter;
import com.example.cravisundaram.rows.RowItem;

public class MainActivity extends Activity implements
        OnItemClickListener {

    public static final String[] titles = new String[] { "Aravind",
            "Divyam", "Kartik", "Aditya","Udith","Meena" };

    public static final String[] descriptions = new String[] {
            "Friend",
            "Friend",
            "Friend",
            "Family",
            "Family",
            "Family"};

    public static final Integer[] images = { R.drawable.straw,
            R.drawable.banana, R.drawable.orange, R.drawable.mixed,R.drawable.watermelon,R.drawable.grape };

    ListView listView;
    List<RowItem> rowItems;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < titles.length; i++) {
            RowItem item = new RowItem(images[i], titles[i], descriptions[i]);
            rowItems.add(item);
        }

        listView = (ListView) findViewById(R.id.list);
        CustomBaseAdapter adapter = new CustomBaseAdapter(this, rowItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Item " + (position + 1) + ": " + rowItems.get(position),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}