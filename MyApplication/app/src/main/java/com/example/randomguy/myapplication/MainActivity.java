package com.example.randomguy.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Database.FeedReaderContract;

import static android.app.PendingIntent.getActivity;


public class MainActivity extends ActionBarActivity {
    SQLiteDatabase db;
    ContentValues values;
    @Override

    protected void onStop(){
        super.onStop();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       Button toAddition = (Button) findViewById(R.id.toAddition);
       Button toSubstraction = (Button) findViewById((R.id.toSubstraction));
       Button toMultiplication = (Button) findViewById(R.id.toMultiplication);
       Button toDivision = (Button) findViewById(R.id.toDivision);
       Button newUser = (Button)findViewById((R.id.newUser));
       Button toGame = (Button)findViewById((R.id.toGame));
       final Intent toQuestionsIntent;
       toQuestionsIntent = new Intent(this, QuestionActivity.class);
       final Intent toGameIntent;
       toGameIntent = new Intent(this, GameActivity.class);
       FeedReaderContract contract = new FeedReaderContract();
       final FeedReaderContract.FeedReaderDbHelper dbHelper = contract.new FeedReaderDbHelper(this);

       db = dbHelper.getWritableDatabase();

        values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID, "player");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ADDITION, "0");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBSTRACTION, "0");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_MULITPLICATION, "0");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DIVISION, "0");

        /*db.insert(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                null,
                values);
        db.update(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                values,
                null,
                null
        );*/

        newUser.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        updatePlayer();


                    }
                }
        );
        toGame.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        startActivity(toGameIntent);


                    }
                }
        );


        toAddition.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                      toQuestionsIntent.putExtra("typeOfQuestion", '+');
                      startActivity(toQuestionsIntent);

                    }
                }
        );

        toSubstraction.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        toQuestionsIntent.putExtra("typeOfQuestion", '-');
                        startActivity(toQuestionsIntent);

                    }
                }
        );
        toMultiplication.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        toQuestionsIntent.putExtra("typeOfQuestion", '*');


                        startActivity(toQuestionsIntent);

                    }
                }
        );
        toDivision.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        toQuestionsIntent.putExtra("typeOfQuestion", '/');
                        startActivity(toQuestionsIntent);
                    }
                }
        );


    }
    private void updatePlayer(){
        db.update(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                values,
                null,
                null
        );
    }
}




