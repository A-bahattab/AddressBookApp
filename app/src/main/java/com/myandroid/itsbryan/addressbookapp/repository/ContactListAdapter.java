package com.myandroid.itsbryan.addressbookapp.repository;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myandroid.itsbryan.addressbookapp.R;
import com.myandroid.itsbryan.addressbookapp.domain.Contact;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by its.Bryan on 8/20/2014.
 */
public class ContactListAdapter extends BaseAdapter {
    private Context context;
    private List<Contact> contactList = new ArrayList<Contact>();
    private LayoutInflater layoutInflater;

    public ContactListAdapter(Context context, List<Contact> list, LayoutInflater layoutInflater){
        this.context = context;
        this.contactList = list;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return contactList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return contactList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null){
            view = layoutInflater.inflate(R.layout.contact_row, null);

            viewHolder = new ViewHolder();
            viewHolder.nameTV = (TextView) view.findViewById(R.id.lv_name);
            viewHolder.surnameTV = (TextView) view.findViewById(R.id.lv_surname);
            viewHolder.numberTV = (TextView) view.findViewById(R.id.lv_number);

            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)view.getTag();
        }
        return view;
    }

    private static class ViewHolder {
        public TextView nameTV;
        public TextView surnameTV;
        public TextView numberTV;
    }
}
