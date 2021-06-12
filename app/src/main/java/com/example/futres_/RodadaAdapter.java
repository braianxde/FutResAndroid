package com.example.futres_;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.futres_.Objetos.RodadasRel;

import java.util.List;

public class RodadaAdapter extends ArrayAdapter<RodadasRel> {

    private int resourceLayout;
    private Context mContext;

    public RodadaAdapter(Context context, int resource, List<RodadasRel> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        RodadasRel p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.textSpinner);

            if (tt1 != null) {
                tt1.setText(p.getId());
            }
        }

        return v;
    }
}