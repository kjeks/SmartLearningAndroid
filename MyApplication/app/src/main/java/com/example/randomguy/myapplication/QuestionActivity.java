package com.example.randomguy.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import Classes.Additon;
import Classes.Division;
import Classes.Multiplication;
import Classes.Question;
import Classes.Substraction;
import Database.FeedReaderContract;

/**
 * Created by randomguy on 08.07.2015.
 */
public class QuestionActivity extends ActionBarActivity{
    String userAnswer ="";
    int correctAnswer =0;
    String addition;
    String substraction;
    String multiplication;
    String division;
    Button[] buttons = new Button[13];
    Intent wrongAnswerIntent;
    FeedReaderContract contract = new FeedReaderContract();
    final FeedReaderContract.FeedReaderDbHelper dbHelper = contract.new FeedReaderDbHelper(this);

    @Override
    protected  void onStop(){
        super.onStop();
        saveNewLevels();

    }
    private void saveNewLevels(){
        SQLiteDatabase dbWrite = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ADDITION, addition);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBSTRACTION, substraction);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_MULITPLICATION, multiplication);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DIVISION, division);


        dbWrite.update(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                values,
                null,
                null
        );

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        getUserLevels();


        wrongAnswerIntent = new Intent(this, WrongAnswerActivity.class);

        createButtonsAndQuestion();

    }
    private void startAnimation(){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fallinganim);
        buttons[0].startAnimation(animation);
    }
    private void getUserLevels(){



        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_ADDITION,
        };
        String sortOrder = FeedReaderContract.FeedEntry.COLUMN_NAME_ADDITION + "DESC";

        Cursor c = dbRead.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        ) ;
        c.moveToFirst();

        division = c.getString(
                c.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_DIVISION)
        );
        addition = c.getString(
                c.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_ADDITION)
        );
        substraction = c.getString(
                c.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBSTRACTION)

        );
        multiplication = c.getString(
                c.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_MULITPLICATION)
        );


        Log.v("query", "addition " + addition + " substraction " + substraction + " multiplication " + multiplication + " division " + division);
    }


    private void createButtonsAndQuestion(){
        final TextView answerText = (TextView)findViewById(R.id.answer);
        final TextView questionText = (TextView)findViewById(R.id.questionText);
        char typeOfQuestion = getIntent().getCharExtra("typeOfQuestion", '+');
        String userLevel="";
        final Question currentQuestion;

        switch (typeOfQuestion){
            case '+': userLevel = addition;
                currentQuestion = new Additon(addition);
                questionText.setText(currentQuestion.getQuestionText());

                break;
            case '-': userLevel = substraction;
                currentQuestion = new Substraction(substraction);
                questionText.setText(currentQuestion.getQuestionText());
                break;
            case'*': userLevel = multiplication;
                currentQuestion = new Multiplication(multiplication);
                questionText.setText(currentQuestion.getQuestionText());
                break;
            case'/': userLevel = division;
                currentQuestion = new Division(division);
                questionText.setText(currentQuestion.getQuestionText());
                break;
            default:
                currentQuestion= new Additon(addition);
                break;
        }
        correctAnswer = currentQuestion.getAnswer();

        buttons[0]= (Button)findViewById(R.id.button0);
        buttons[1]= (Button)findViewById(R.id.button1);
        buttons[2]= (Button)findViewById(R.id.button2);
        buttons[3]= (Button)findViewById(R.id.button3);
        buttons[4]= (Button)findViewById(R.id.button4);
        buttons[5]= (Button)findViewById(R.id.button5);
        buttons[6]= (Button)findViewById(R.id.button6);
        buttons[7]= (Button)findViewById(R.id.button7);
        buttons[8]= (Button)findViewById(R.id.button8);
        buttons[9]= (Button)findViewById(R.id.button9);
        buttons[10]= (Button)findViewById(R.id.eraseB);
        buttons[11]= (Button)findViewById(R.id.answerB);
        buttons[12]= (Button)findViewById(R.id.negate);


        for(int a=0; a<10; a++){
            final int answerValue = a;
            buttons[a].setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            userAnswer = userAnswer + Integer.toString(answerValue);
                            answerText.setText(userAnswer);

                        }
                    }
            );
        }


        buttons[10].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        userAnswer = "";
                        answerText.setText(userAnswer);
                        userAnswer = "";
                    }
                }
        );
        buttons[11].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Log.v("test", Integer.toString(correctAnswer));
                        Log.v("test", userAnswer);
                        if(Integer.toString(correctAnswer).equals(userAnswer)){
                            Log.v("test", "correct userAnswer");
                            createButtonsAndQuestion();
                            char questionType = currentQuestion.getType();
                            switch (questionType){
                                case '+':
                                    int valueOfInteger = Integer.valueOf(addition);
                                    addition = Integer.toString(valueOfInteger + 1);
                                    Log.v("test", addition);
                                    break;
                                case '-':
                                    valueOfInteger = Integer.valueOf(substraction);
                                    substraction = Integer.toString(valueOfInteger+1);
                                    break;
                                case '/':
                                    valueOfInteger = Integer.valueOf(division);
                                    division = Integer.toString(valueOfInteger+1);
                                    break;

                                case '*': valueOfInteger = Integer.valueOf(multiplication);
                                    multiplication = Integer.toString(valueOfInteger+1);
                                    break;
                            }
                            answerText.setText("");
                            userAnswer ="";
                        }
                        else{
                            Log.v("test", "incorrect userAnswer");

                            currentQuestion.analyseMistake(Integer.parseInt(userAnswer));
                            startActivity(wrongAnswerIntent);


                        }
                    }
                }
        );
        buttons[12].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        if(userAnswer.startsWith("-")){
                            userAnswer = userAnswer.substring(1);
                        }else{
                            userAnswer = "-" + userAnswer;
                        }
                        answerText.setText(userAnswer);
                        startAnimation();


                    }
                }
        );
    }
}