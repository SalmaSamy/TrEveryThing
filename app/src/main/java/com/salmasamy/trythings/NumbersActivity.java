package com.salmasamy.trythings;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_adaptor);

        ArrayList<MyNumber> numbers = new ArrayList<>();
        numbers.add(new MyNumber("one","LaLa 1"));
        numbers.add(new MyNumber("two","LaLa 2"));
        numbers.add(new MyNumber("three","LaLa 3"));
        numbers.add(new MyNumber("four","LaLa 4"));
        numbers.add(new MyNumber("five","LaLa 5"));
        numbers.add(new MyNumber("six","LaLa 6"));
        numbers.add(new MyNumber("seven","LaLa 7"));
        numbers.add(new MyNumber("eight","LaLa 8"));
        numbers.add(new MyNumber("nine","LaLa 9"));
        numbers.add(new MyNumber("ten","LaLa 10"));


        MyNumberAdabter itemsAdapter = new MyNumberAdabter(this, numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
    }
}
