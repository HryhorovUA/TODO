package com.bignerdranch.android.todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class AddingFragment extends Fragment {
    private static final String DIALOG_DATE = "DIALOG_DATE";
    Button mChoiceButton;

    public static AddingFragment newInstance() {
        return new AddingFragment();
    }

    @Override
    public View  onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.adding_fragment, container, false);

        mChoiceButton = (Button) v.findViewById(R.id.date_button_choice);
        mChoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.show(manager, DIALOG_DATE);
            }
        });

        return v;
    }

}
