package Classes;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by randomguy on 20.07.2015.
 */
public class Multiplication extends Question{

    private String questionText;
    private int answer;
    private char type= '*';
    private ArrayList<Integer> equationParts = new ArrayList<>();

    public Multiplication(String userLevelString){
        answer = 0;
        int userLevel = Integer.parseInt(userLevelString);
        createSuitableDifficulty(userLevel);

    }
    @Override
    protected String generateEquation() {
        String equation= "";
        for(int a=0; a<equationParts.size(); a++){
            if(a==equationParts.size()-1){
                equation = equation + Integer.toString(equationParts.get(a));

            }
            else{
                equation = equation + Integer.toString(equationParts.get(a))+ " * ";
            }

        }
        Log.v("test", "equation " + equation);
        Log.v("test", "answer" + answer);
        return equation;
    }

    @Override
    protected void generateEquationParts(int numberOfParts, int numberOfDigits) {
        for(int a=0; a<numberOfParts; a++){
            int newPart= (int)(Math.random()*(Math.pow(10, numberOfDigits)));
            if(a==0){
                answer = newPart;
            }
            else{
                answer = answer * newPart;
            }
            equationParts.add(newPart);
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
        questionText = generateEquation();
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
