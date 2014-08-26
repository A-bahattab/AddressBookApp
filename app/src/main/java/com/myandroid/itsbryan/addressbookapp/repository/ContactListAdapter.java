package com.myandroid.itsbryan.addressbookapp.repository;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myandroid.itsbryan.addressbookapp.R;
import com.myandroid.itsbryan.addressbookapp.domain.Contact;
import com.myandroid.itsbryan.addressbookapp.repository.Impl.DataSourceDAOImpl;

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
    DataSourceDAO dao;

    public ContactListAdapter(Context context){
        this.context = context;
        dao = new DataSourceDAOImpl(context);
        contactList = dao.getAllContactsList();
    }

    @Override
    public int getCount() {
        return contactList.size();
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
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;

        if (view == null){
            view = layoutInflater.inflate(R.layout.contact_row, null);

            viewHolder = new ViewHolder();
            viewHolder.nameTV = (TextView) view.findViewById(R.id.lv_name);
            viewHolder.surnameTV = (TextView) view.findViewById(R.id.lv_surname);
            viewHolder.numberTV = (TextView) view.findViewById(R.id.lv_number);
            viewHolder.idTV = (TextView) view.findViewById(R.id.lv_id);

            Contact current = contactList.get(i);
            Log.d("ID: " , " INSIDE GET VIEW METHOD");
            try {
                viewHolder.nameTV.setText(current.getName() + " " + current.getSurname());
                viewHolder.surnameTV.setText(current.getSurname());
                viewHolder.numberTV.setText(current.getPhoneNumber());
                viewHolder.idTV.setText(String.valueOf(current.getId()));
            }
            catch (Exception ex)
            {
            }

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
        public TextView idTV;
    }
}
