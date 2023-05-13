package com.example.myadapterplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class myAdapterC extends ArrayAdapter<PlayerC> {

    //props------
    private Context context;
    private int res;
    private ArrayList<PlayerC> myArray;

    //Constructeur
    public myAdapterC(@NonNull Context _context, int _res, @NonNull List<PlayerC> _objects) {
        super(_context, _res, _objects);
        context = _context;
        res = _res;
        myArray = new ArrayList<PlayerC>();
        myArray = (ArrayList<PlayerC>) _objects;
    }

    //Overide de la fonction getView
    @NonNull
    @Override
    public View getView(int _position, @Nullable View _convertView, @NonNull ViewGroup _parent) {

        //recuperer le costum_layout
        _convertView = LayoutInflater.from(context).inflate(res,_parent,false);
        //Recuperer les Views
        ImageView img = (ImageView) _convertView.findViewById(R.id.img_ID);
        TextView txtNom = (TextView) _convertView.findViewById(R.id.txtNom_ID);
        TextView txtPrenom = (TextView) _convertView.findViewById(R.id.txtPrenom_ID);
        TextView txtEquipe = (TextView) _convertView.findViewById(R.id.txtEquipe_ID);
        TextView txtPoste = (TextView) _convertView.findViewById(R.id.txtPoste_ID);


        img.setImageResource(myArray.get(_position).getImg());
        txtNom.setText(myArray.get(_position).getNom());
        txtPrenom.setText(myArray.get(_position).getPrenom());
        txtEquipe.setText(myArray.get(_position).getEquipe());
        txtPoste.setText(myArray.get(_position).getPoste());

        return _convertView;
    }
}
