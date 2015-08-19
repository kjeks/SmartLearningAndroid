package Classes;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by randomguy on 20.07.2015.
 */
public class Division extends Question{
    private String questionText ="asd";
    private int answer;
    char type ='/';


    public Division(String userLevelString){
        answer=0;
        int userLevel = Integer.parseInt(userLevelString);
        createSuitableDifficulty(userLevel);
    }

    @Override
    protected String generateEquation() {
       return null;
    }

    @Override
    protected void generateEquationParts(int numberOfParts, int numberOfDigits) {
        Log.v("bug", "parts " + numberOfParts + "digits " + numberOfDigits);
        for(int a=0; a<numberOfParts; a++){

            int divisor = (int)(Math.random()*(Math.pow(10, numberOfDigits))+1);
            int dividend= divisor*((int)(Math.random()*10));

            answer = dividend/divisor;
            questionText = dividend + " / " + divisor;

        }

    }

    @Override
    public String getQuestionText() {
        return questionText;
    }

    @Override
    public char getType() {
        return type;
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

    }

    @Override
    public int getAnswer() {
        return answer;
    }
    @Override
    public void analyseMistake(int userAnswer) {
        Log.v("test", "mistake beeing analysed");
    }
}
