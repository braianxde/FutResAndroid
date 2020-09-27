package com.example.futres_;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.futres_.Objetos.Partida;

import java.util.ArrayList;

public class PartidaAdapter extends ArrayAdapter<Partida> {
    private Context mContext;
    private int mResourse;

    public PartidaAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Partida> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResourse = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResourse, parent, false);

        ImageView immandante = convertView.findViewById(R.id.imMandante);
        ImageView imvisitante = convertView.findViewById(R.id.imVisitante);
        TextView txtdtpartida = convertView.findViewById(R.id.txtData);
        TextView txtgolMand = convertView.findViewById(R.id.txtGol_Mandante);
        TextView txtgolVis = convertView.findViewById(R.id.txtGol_Visitante);
        TextView txtnomeMand = convertView.findViewById(R.id.txtNome_mandante);
        TextView txtnomeVis = convertView.findViewById(R.id.txtNome_visitante);

        immandante.setImageResource(getItem(position).getImag_mandante());
        imvisitante.setImageResource(getItem(position).getImag_visitante());
        txtdtpartida.setText(getItem(position).getData_hora());
        txtgolMand.setText(getItem(position).getGols_mandante());
        txtgolVis.setText(getItem(position).getGols_visitante());
        txtnomeMand.setText(getItem(position).getNome_mandante());
        txtnomeVis.setText(getItem(position).getNome_visitante());

        return convertView;
    }
}
