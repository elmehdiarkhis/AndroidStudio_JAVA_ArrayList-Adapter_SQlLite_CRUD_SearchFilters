package com.example.myadapterplayer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.net.CookieHandler;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //variables globale
    private ListView lstPlayers;
    private ImageView imageAlert;
    private Context ctx;
    //--
    private Button btnAjouter;
    private EditText txtNom;
    private EditText txtPrenom;
    private EditText txtEquipe;
    private EditText txtPoste;
    //--
    private Button btnUpdate;
    private EditText txtNomUpdate;
    private EditText txtPrenomUpdate;
    private EditText txtEquipeUpdate;
    private EditText txtPosteUpdate;

    //--Search
    private Button btnSearch;
    private RadioButton radNom;
    private RadioButton radPrenom;
    private RadioButton radEquipe;
    private EditText txtvalueSearch;
    private Button btnReset;

    //Sort-Reset
    private Button btnSort;
    private Button btnResetTwo;


    ArrayList<PlayerC> myArray= new ArrayList<PlayerC>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.ctx = this;

        try{
            // Views
            lstPlayers = (ListView) findViewById(R.id.lstPlayers_ID);
            imageAlert = (ImageView) findViewById(R.id.imageAlert_ID);


            //-ADD==================================================
            btnAjouter = (Button) findViewById(R.id.btnAjouter_main_ID);
            txtNom = (EditText) findViewById(R.id.txtNom_main_ID);
            txtPrenom = (EditText) findViewById(R.id.txtPrenom_main_ID);
            txtEquipe = (EditText) findViewById(R.id.txtEquipe_main_ID);
            txtPoste = (EditText) findViewById(R.id.txtEquipe_main_ID);
            //-========================================================

            //-Search==================================================
            btnSearch = (Button) findViewById(R.id.btnSearch_ID);
            radNom = (RadioButton) findViewById(R.id.radNom_ID);
            radPrenom = (RadioButton) findViewById(R.id.radPrenom_ID);
            radEquipe = (RadioButton) findViewById(R.id.radEquipe_ID);
            txtvalueSearch = (EditText) findViewById(R.id.txtvalueSearch_ID);
            btnReset = (Button) findViewById(R.id.btnReset_ID);
            //-========================================================

            //Sort-Reset==================================================
            btnSort = (Button) findViewById(R.id.btnSort_ID);
            btnResetTwo = (Button) findViewById(R.id.btnResetTwo_ID);
            //-========================================================




//            //DataBase
//            mySqlDataBase myDataBase = new mySqlDataBase(getApplication());
//            myDataBase.connectToDataBase();
//
//            //Array
//            //myDataBase.addOnePlayer("test","test","test","test",R.drawable.mstr);
//            myArray  = myDataBase.selectAllPlayers();

            //Adapter
            myAdapterC adapter = new myAdapterC
                    (this,R.layout.one_listview_ligne_layout,myArray);
            lstPlayers.setAdapter(adapter);


            //ADD
            btnAjouter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String nom = txtNom.getText().toString();
                    String prenom  = txtPrenom.getText().toString();
                    String equipe = txtEquipe.getText().toString();
                    String poste = txtPoste.getText().toString();

//                    myDataBase.addOnePlayer(nom,prenom,equipe,poste,R.drawable.mstr);

                    //Array-------------------------
                    myArray.add(new PlayerC(nom,prenom,equipe,poste,R.drawable.mstr));
                    adapter.notifyDataSetChanged();
                    //------------------------------
                }
            });

            //Delete
            lstPlayers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    String nom = myArray.get(position).getNom();
                    String prenom = myArray.get(position).getPrenom();

//                    myDataBase.deletePlayer(nom,prenom);

                    //Array-------------------------
                    myArray.remove(position);
                    adapter.notifyDataSetChanged();
                    //------------------------------

                    return false;
                }
            });

            //UPDATE
            lstPlayers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //------------------------------ALERT DIALOGUE COSTUM----------------------------------------
                    AlertDialog.Builder alert = new AlertDialog.Builder(ctx);

                    View myView = LayoutInflater.from(ctx).inflate(R.layout.alert_update,null);
                    alert.setView(myView);
                    btnUpdate = (Button) myView.findViewById(R.id.btnUpdate_update_ID);
                    txtNomUpdate = (EditText) myView.findViewById(R.id.txtNom_update_ID);
                    txtPrenomUpdate = (EditText) myView.findViewById(R.id.txtPrenom_update_ID);
                    txtEquipeUpdate = (EditText) myView.findViewById(R.id.txtEquipe_update_ID);
                    txtPosteUpdate = (EditText) myView.findViewById(R.id.txtPoste_update_ID);

                    //Afficher les anciennes information dans les EDIT TEXT
                    txtNomUpdate.setText(myArray.get(position).getNom());
                    txtPrenomUpdate.setText(myArray.get(position).getPrenom());
                    txtEquipeUpdate.setText(myArray.get(position).getEquipe());
                    txtPosteUpdate.setText(myArray.get(position).getPoste());



                    //Modifier les information
                    btnUpdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //Recuperer les nouvelle Valeurs des Object du COTUM ALERTDIALOGUE------
                            String nom = txtNomUpdate.getText().toString();
                            String prenom  = txtPrenomUpdate.getText().toString();
                            String equipe = txtEquipeUpdate.getText().toString();
                            String poste = txtPosteUpdate.getText().toString();
                            //-------------------------------------------------------------


//                            myDataBase.UpdatePlayer(nom,prenom,equipe,poste,myArray.get(position).getNom(),myArray.get(position).getPrenom());


                            //Array-------------
                            //myArray.set(position,new PlayerC(nom,prenom,equipe,poste,R.drawable.mstr));
                            myArray.get(position).setNom(nom);
                            myArray.get(position).setPrenom(prenom);
                            myArray.get(position).setEquipe(equipe);
                            myArray.get(position).setPoste(poste);
                            adapter.notifyDataSetChanged();

                        }
                    });

                    alert.create();
                    alert.show();
                    //------------------------------ALERT DIALOGUE COSTUM----------------------------------------
                }
            });

            //SEARCH=====================================================================
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //get the Search value
                    String valueSearch = txtvalueSearch.getText().toString();

                    ArrayList<PlayerC> myNewArray = new ArrayList<PlayerC>();

                    if(radNom.isChecked()){

                        for(int i=0;i<myArray.size();i++){
                                if(myArray.get(i).getNom().equals(valueSearch)){
                                    myNewArray.add(myArray.get(i));
                                }
                        }

                    }else if(radPrenom.isChecked()){

                        for(int i=0;i<myArray.size();i++){
                            if(myArray.get(i).getPrenom().equals(valueSearch)){
                                myNewArray.add(myArray.get(i));
                            }
                        }

                    }else if (radEquipe.isChecked()){

                        for(int i=0;i<myArray.size();i++){
                            if(myArray.get(i).getEquipe().equals(valueSearch)){
                                myNewArray.add(myArray.get(i));
                            }
                        }
                    }

                    //Change Array dans l'Adapter
                    myAdapterC adapter = new myAdapterC
                            (ctx,R.layout.one_listview_ligne_layout,myNewArray);
                    lstPlayers.setAdapter(adapter);

                }
            });

            //RESET AFTER SEARCH
            btnReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Reset l'Adapter aves l'Array principale
                    myAdapterC adapter = new myAdapterC
                            (ctx,R.layout.one_listview_ligne_layout,myArray);
                    lstPlayers.setAdapter(adapter);
                }
            });
            //=================================================================================================


            //SORT=========================================================================================
            btnSort.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ArrayList<PlayerC> myNewArray = new ArrayList<PlayerC>();

                    myNewArray=myArray;

                    PlayerC temp;
                    for (int i=0;i<myNewArray.size();i++){
                        for (int j=i;j<myNewArray.size();j++){
                            if(myNewArray.get(i).getImg()>myNewArray.get(j).getImg()){
                                temp = myNewArray.get(i);
                                myNewArray.set(i,myNewArray.get(j));
                                myNewArray.set(j,temp);
                            }
                        }
                    }

                    //Change Array dans l'Adapter
                    myAdapterC adapter = new myAdapterC
                            (ctx,R.layout.one_listview_ligne_layout,myNewArray);
                    lstPlayers.setAdapter(adapter);

                }
            });

            //RESET AFTER SORT
            btnResetTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Reset l'Adapter aves l'Array principale
                    myAdapterC adapter = new myAdapterC
                            (ctx,R.layout.one_listview_ligne_layout,myArray);
                    lstPlayers.setAdapter(adapter);
                }
            });
            //=================================================================================================




        }catch(Exception ex){
            Log.i("mytag",ex.getMessage());
        }


    }

}





