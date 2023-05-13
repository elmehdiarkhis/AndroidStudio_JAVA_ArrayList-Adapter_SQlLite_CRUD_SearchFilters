package com.example.myadapterplayer;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class mySqlDataBase {

    private SQLiteDatabase myDataBase;
    private mySqlOpenHelper sqlHelper;
    private Context ctx;
    private final String DATABASE_NAME="BASEDONEES";
    private final int DATABASE_VERSION=1;

    //Constructor
    public mySqlDataBase(Context _ctx){
        ctx=_ctx;
        sqlHelper = new mySqlOpenHelper(ctx,DATABASE_NAME,null,DATABASE_VERSION);
    }

    //Fonction
    public void connectToDataBase(){
        myDataBase = sqlHelper.getWritableDatabase();
    }

    public void addOnePlayer(String _nom,String _prenom,String _equipe,String _poste,int _img){
        myDataBase.execSQL("INSERT INTO playersTable VALUES ('"+_nom+"','"+_prenom+"','"+_equipe+"','"+_poste+"',"+_img+");");
    }

    public void addOneTeam(String _nom,String _ville,String _pays,String _classement,int _img){
        myDataBase.execSQL("INSERT INTO equipesTable VALUES ('"+_nom+"','"+_ville+"','"+_pays+"','"+_classement+"',"+_img+");");
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public int getLastID(){
        Cursor myCursor = myDataBase.rawQuery("SELECT MAX(id) FROM equipesTable;",null);

        int idIndex = myCursor.getColumnIndex("id");
        int id=0;
        if(myCursor!=null && myCursor.moveToFirst()){
            id=myCursor.getInt(idIndex);
        }while(myCursor.moveToNext());

        return id;
    }
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public void removeOneTeam(int _id){
        myDataBase.execSQL("DELETE FROM equipesTable WHERE id="+_id+";");
    }

    public ArrayList<PlayerC> selectAllPlayers(){

        Cursor myCursor = myDataBase.rawQuery("select * from playersTable",null);

        int nomIndex = myCursor.getColumnIndex("nom");
        int prenomIndex = myCursor.getColumnIndex("prenom");
        int equipeIndex = myCursor.getColumnIndex("equipe");
        int posteIndex = myCursor.getColumnIndex("poste");
        int imgIndex = myCursor.getColumnIndex("img");

        ArrayList<PlayerC> myPlayerArray = new ArrayList<PlayerC>();
        if (myCursor!=null && myCursor.moveToFirst()){
            do{
                myPlayerArray.add(new PlayerC(myCursor.getString(nomIndex),myCursor.getString(prenomIndex),myCursor.getString(equipeIndex),myCursor.getString(posteIndex),myCursor.getInt(imgIndex)));
            }while(myCursor.moveToNext());
        }

        return myPlayerArray;
    }

    public void deletePlayer(String _nom,String _prenom){
        myDataBase.execSQL("DELETE FROM playersTable WHERE nom='"+_nom+"' AND prenom='"+_prenom+"';");
    }

    public void UpdatePlayer(String _nom,String _prenom,String _equipe,String _poste,String _nomKey,String _prenomKey){
        try{
            myDataBase.execSQL("UPDATE playersTable SET nom='"+_nom+"', prenom='"+_prenom+"', equipe='"+_equipe+"', poste='"+_poste+"' WHERE nom='"+_nomKey+"' AND prenom='"+_prenomKey+"';");
        }catch(Exception ex){
            Log.i("tag2",ex.getMessage());
        }
    }


}
