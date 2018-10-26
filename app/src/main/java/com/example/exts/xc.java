package com.example.exts;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class xc extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private String[] listMenu;
    private int layout;

    public xc(Context context, String[] listMenu) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listMenu = listMenu;
        this.layout = layout;
    }

    @Override
    public int getCount()
    {
        return listMenu.length;
    }

    @Override
    public Object getItem(int postion)
    {
        return listMenu[postion];
    }

    @Override
    public long getItemId(int postion)
    {
        return postion;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        if(view == null)
        {
            view = inflater.inflate(R.layout.list_custom, null);
        }
        TextView listText = (TextView) view.findViewById(R.id.list_text);
        listText.setText(listMenu[position]);
        return view;
    }
}
