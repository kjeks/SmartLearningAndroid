package Classes;

import android.content.Intent;
import android.util.Log;

import com.example.randomguy.myapplication.QuestionActivity;
import com.example.randomguy.myapplication.WrongAnswerActivity;

import java.util.ArrayList;

import ErrorTests.AdditionErrorTests;

/**
 * Created by randomguy on 28.06.2015.
 */
public class Additon extends Question{

    private String questionText;
    private int answer;
    private char type = '+';
    private ArrayList<Integer> equationParts = new ArrayList<>();


    public Additon(String userLevelString){
        answer=0;
        int userLevel = Integer.parseInt(userLevelString);
        createSuitableDifficulty(userLevel);
    }
    @Override
    protected void createSuitableDifficulty(int userLevel) {
        if(userLevel<10){
            generateEquationParts(2,1);
        }
        else if(userLevel<20){
            generateEquationParts(3,1);
        }else if(userLevel<30){
            generateEquationParts(2,2);
        }else if(userLevel<40){
            generateEquationParts(3,2);
        }else if(userLevel<50){
            generateEquationParts(3,3);
        }
        questionText = generateEquation();

    }

    @Override
    protected void generateEquationParts(int numberOfParts, int numberOfDigits){

        Log.v("bug", "parts " + numberOfParts + "digits " + numberOfDigits);
        for(int a=0; a<numberOfParts; a++){
            int newPart= (int)(Math.random()*(Math.pow(10, numberOfDigits)));
            answer = answer + newPart;
            equationParts.add(newPart);
        }

    }
    protected String generateEquation(){
        String equation= "";
        for(int a=0; a<equationParts.size(); a++){
            if(a==equationParts.size()-1){
                equation = equation + Integer.toString(equationParts.get(a));

            }
            else{
                equation = equation + Integer.toString(equationParts.get(a))+ " + ";
            }

        }
        Log.v("test", "equation " + equation);
        Log.v("test", "answer" + answer);
        return equation;
    }


    public String getQuestionText() {
        Log.v("test", "in get QuestionText");

        return questionText;
    }

    @Override
    public char getType() {
        return type;
    }

    public int getAnswer(){
        return answer;
    }

    @Override
    public void analyseMistake(int userAnswer) {
        AdditionErrorTests errorTests= new AdditionErrorTests(userAnswer, answer, type);
    }




}
