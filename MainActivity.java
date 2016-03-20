package com.example.computer.yogaapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button LoginButton;
    Button MyPoses;
    Button sequences;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try{
            SQLiteDatabase yogaDB = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            yogaDB.execSQL("CREATE TABLE IF NOT EXISTS userInfo (name VARCHAR, username VARCHAR, password VARCHAR)");//Does all the database info go in the same place?
            yogaDB.execSQL("INSERT INTO userInfo (name, age) VALUES 'Hope','hopealbers', 'yoga'"); //Next step is getting the name, username and password from the textedit
            //extracting the data from yogaDB
            Cursor c = yogaDB.rawQuery("SELECT * FROM userInfo", null); //star means slecting veverything in the database
            //we need to get the individal columns by index so we assign them
            int nameIndex = c.getColumnIndex("name");
            int usernameIndex = c.getColumnIndex("username");
            int passwordIndex = c.getColumnIndex("password");

            //move cursor to the first result
            c.moveToFirst(); //starts with first entry in database
            while(c != null)
            {
                Log.i("name", c.getString(nameIndex)); //puts the name in a log
                Log.i("username", c.getString(usernameIndex));
                Log.i("password", c.getString(passwordIndex));

                c.moveToNext();
            }


            yogaDB.execSQL("CREATE TABLE IF NOT EXISTS generalPoses (englishName VARCHAR, sanskritName VARCHAR, picture BLOB, description VARCHAR)"); //initializing a picture?
                //do we need to specify the size of the VARCHAR?
            yogaDB.execSQL("INSERT INTO generaPoses (englishName, sanskritName, picture, description) VALUES 'downward-dog','adho mukha shvanasana', downward-dog.jpg, 'This is a downward dog.' ");
                            //Next step is getting the name, username and password from the textedit
            Cursor c2 = yogaDB.rawQuery("SELECT * FROM generalPoses", null);
            int englishNameIndex = c2.getColumnIndex("englishName");
            int sanskritNameIndex = c2.getColumnIndex("sanskritName");
            int pictureIndex = c2.getColumnIndex("picture");
            int descriptionIndex = c2.getColumnIndex("description");
            c2.moveToFirst(); //starts with first entry in database
            while(c2 != null)
            {
                Log.i("englishName", c2.getString(englishNameIndex)); //puts the name in a log
                Log.i("sanskritName", c2.getString(sanskritNameIndex));
                Log.i("picture", c2.getString(pictureIndex));
                Log.i("description", c2.getString(descriptionIndex));

                c2.moveToNext();
            }



            yogaDB.execSQL("CREATE TABLE IF NOT EXISTS generalSequences (name VARCHAR, username VARCHAR, password VARCHAR)"); //initializing a long description
            yogaDB.execSQL("CREATE TABLE IF NOT EXISTS privatePoses (name VARCHAR, username VARCHAR, password VARCHAR)");
            yogaDB.execSQL("CREATE TABLE IF NOT EXISTS privateSequences (name VARCHAR, username VARCHAR, password VARCHAR)");




        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        LoginButton = (Button)findViewById(R.id.Login);
        LoginButton.setOnClickListener(this);

        MyPoses = (Button)findViewById(R.id.MyPoses);
        MyPoses.setOnClickListener(this);

        sequences = (Button)findViewById(R.id.sequences);
        sequences.setOnClickListener(this);

        register= (Button)findViewById(R.id.register);
        register.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onClick(View v) {
        switch(v.getId())
        {


            case R.id.Login:
                startActivity(new Intent(this, Login.class));
                break;

            case R.id.MyPoses:
                startActivity(new Intent(this, MyPoses.class));
                break;

            case R.id.sequences:
                startActivity(new Intent(this, MySequences.class));
                break;

            case R.id.register:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }
}
