package com.bignerdranch.android.todo;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Calendar;

public class AddingFragment extends Fragment {
    private static final String DIALOG_DATE = "DIALOG_DATE";
    Button mChoiceButton;
    Calendar mCalendar = Calendar.getInstance();
    DatePickerDialog mDatePickerDialog;

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
                mDatePickerDialog.show();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            initDatePicker();
        }
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initDatePicker() {
        mDatePickerDialog = new DatePickerDialog(getContext());
        mDatePickerDialog.getDatePicker().setMinDate(mCalendar.getTimeInMillis());
        mCalendar.add(Calendar.DAY_OF_YEAR, 6);
        mDatePickerDialog.getDatePicker().setMaxDate(mCalendar.getTimeInMillis());
        mDatePickerDialog.setOnDateSetListener((view, year, month, dayOfMonth) -> {
            mCalendar.set(Calendar.MONTH, month);
            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            switch (mCalendar.get(Calendar.DAY_OF_WEEK)) {
                case Calendar.SUNDAY:
                    Toast.makeText(getActivity(), "SUNDAY", Toast.LENGTH_SHORT).show();
                    break;
                case Calendar.MONDAY:
                    Toast.makeText(getActivity(), "MONDAY", Toast.LENGTH_SHORT).show();
                    break;
                case Calendar.TUESDAY:
                    Toast.makeText(getActivity(), "TUESDAY", Toast.LENGTH_SHORT).show();
                    break;
                case Calendar.WEDNESDAY:
                    Toast.makeText(getActivity(), "WEDNESDAY", Toast.LENGTH_SHORT).show();
                    break;
                case Calendar.THURSDAY:
                    Toast.makeText(getActivity(), "THURSDAY", Toast.LENGTH_SHORT).show();
                    break;
                case Calendar.FRIDAY:
                    Toast.makeText(getActivity(), "FRIDAY", Toast.LENGTH_SHORT).show();
                    break;
                case Calendar.SATURDAY:
                    Toast.makeText(getActivity(), "SATURDAY", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

}
