package Classes;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.randomguy.myapplication.R;

/**
 * Created by randomguy on 04.08.2015.
 */
public class AnswerFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_numbers, container, false);

        return view;
     }


}
