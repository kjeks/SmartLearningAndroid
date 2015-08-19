package ErrorTests;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by randomguy on 30.07.2015.
 */
public class AdditionErrorTests {

    private ArrayList<Integer> digitsInAnswer = new ArrayList<>();
    private ArrayList<Integer> digitsInUserAnswer = new ArrayList<>();
    char questionType;
    boolean ofByOne;
    boolean ofByTen;

    public AdditionErrorTests(int correctAnswer, int userAnswer, char questionType){
        this.questionType=questionType;
        digitsInAnswer = decomposeToDigits(correctAnswer);
        digitsInUserAnswer=decomposeToDigits(userAnswer);
        ofByOne = ofByOneError();
        ofByTen = ofByTenError();
        Log.v("errors", "ofByOneError "+ ofByOne);
        Log.v("errors", "ofByTenError" + ofByTen);

    }
    private boolean ofByTenError() {
        if(digitsInAnswer.size()== digitsInUserAnswer.size()){
            int tensUserDigit= digitsInUserAnswer.get(digitsInUserAnswer.size()-1);
            int tensCorrectDigit = digitsInAnswer.get(digitsInAnswer.size()-1);
            if(tensCorrectDigit-1 == tensUserDigit || tensCorrectDigit+1 == tensCorrectDigit){
                return true;
            }

        }
        return false;
    }

    private boolean ofByOneError() {
        if(digitsInAnswer.size()==digitsInUserAnswer.size()){
            int lastUserDigit= digitsInUserAnswer.get(digitsInUserAnswer.size()-1);
            int lastCorrectDigit = digitsInAnswer.get(digitsInAnswer.size()-1);
            Log.v("errors", "user " + lastUserDigit + "correct " + lastCorrectDigit);

            if(lastCorrectDigit-1 == lastUserDigit || lastCorrectDigit+1==lastUserDigit){
                return true;
            }
        }
        return false;
    }

    private ArrayList<Integer> decomposeToDigits(int number) {
        String numberAsString = Integer.toString(number);
        ArrayList<Integer> listOfDigits = new ArrayList<>();
        for(int a=0; a<numberAsString.length(); a++){
            listOfDigits.add(Character.getNumericValue(numberAsString.charAt(a)));
        }
        return  listOfDigits;
    }

}
