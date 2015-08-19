package Classes;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by randomguy on 04.07.2015.
 */
public abstract class Question {

    protected abstract String generateEquation();

    protected abstract void generateEquationParts(int numberOfParts, int numberOfDigits);

    public abstract String getQuestionText();

    public abstract char getType();

    protected abstract void createSuitableDifficulty(int userLevel);


    public abstract int getAnswer();


    public abstract void analyseMistake(int userAnswer);
}
