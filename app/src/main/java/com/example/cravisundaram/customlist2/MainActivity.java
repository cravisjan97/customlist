package com.example.cravisundaram.customlist2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
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

    Button b,b2;
    EditText e;
    int flag=1;
    private CustomBaseAdapter adapter;
    boolean s=true;


    public static final Integer[] images = { R.drawable.straw,
            R.drawable.banana, R.drawable.orange, R.drawable.mixed,R.drawable.watermelon,R.drawable.grape };

    ListView listView;
    List<RowItem> rowItems;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=(Button)findViewById(R.id.button);
        e=(EditText)findViewById(R.id.editText);
        b2=(Button)findViewById(R.id.button2);

        rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < titles.length; i++) {
            RowItem item = new RowItem(images[i], titles[i], descriptions[i]);
            rowItems.add(item);
        }

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomBaseAdapter(this, rowItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg=e.getText().toString();
                flag=1;
                if(msg.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please enter a string.",
                            Toast.LENGTH_LONG).show();

                }
                else {
                    for (int i = 0; i < titles.length; ++i)
                    {
                        if (msg.equals(titles[i])) {
                            flag=0;
                            Toast.makeText(getApplicationContext(), "Contact Found:" + titles[i] + descriptions[i],
                                    Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                    if(flag==1)
                    {
                        Toast.makeText(getApplicationContext(), "Missing",
                                Toast.LENGTH_LONG).show();

                    }
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s==true) {
                    s = false;

                    Collections.sort(rowItems, new Comparator() {
                        @Override
                        public int compare(Object lhs, Object rhs) {
                            RowItem r1 = (RowItem) lhs;
                            RowItem r2 = (RowItem) rhs;
                            return r1.getTitle().compareToIgnoreCase(r2.getTitle());
                        }
                    });
                    listView.invalidateViews();
                }
                else if(s==false)
                {
                    s=true;
                    Collections.sort(rowItems, new Comparator() {
                        @Override
                        public int compare(Object lhs, Object rhs) {
                            RowItem r1 = (RowItem) lhs;
                            RowItem r2 = (RowItem) rhs;
                            return r2.getTitle().compareToIgnoreCase(r1.getTitle());
                        }
                    });
                    listView.invalidateViews();

                }
            }
        });
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