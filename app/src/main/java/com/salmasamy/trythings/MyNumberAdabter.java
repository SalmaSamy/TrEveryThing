package com.salmasamy.trythings;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Salma-Pc on 3/28/2018.
 */

public class MyNumberAdabter extends ArrayAdapter<MyNumber> {

    public MyNumberAdabter(Context context, ArrayList<MyNumber> numbers){
        super(context, 0, numbers);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //check if the existing view is being reused else inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        MyNumber currentNumber = getItem(position);
        TextView word = (TextView) listItemView.findViewById(R.id.word);
        TextView number = (TextView) listItemView.findViewById(R.id.number);

        word.setText(currentNumber.getFirst());
        number.setText(currentNumber.getSecond());

        return listItemView;

    }
}
