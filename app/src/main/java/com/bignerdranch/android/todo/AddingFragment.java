package com.bignerdranch.android.todo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

public class AddingFragment extends Fragment {
    private static final String TAG = "AddingFragment";

    private static final String DIALOG_DATE = "DIALOG_DATE";
    private static final String DIALOG_TIME = "DIALOG_TIME";
    private Button mChoiceDayButton;
    private Button mChoiceTimeButton;
    private Button mConfirmButton;
    private Calendar mCalendar = Calendar.getInstance();
    private DatePickerDialog mDatePickerDialog;
    private TimePickerDialog mTimePickerDialog;
    private EditText mEditTextNotice;

    private String getNotice;

    private int positionOfDay;
    private int currentHour;
    private int currentMinute;

    public static AddingFragment newInstance() {
        return new AddingFragment();
    }

    @Override
    public View  onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.adding_fragment, container, false);

        mChoiceDayButton = (Button) v.findViewById(R.id.date_button_choice);
        mChoiceDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog.show();
            }
        });

        mChoiceTimeButton = (Button) v.findViewById(R.id.time_button_choice);
        mChoiceTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                FragmentManager manager = getFragmentManager();
//                TimePickerFragment dialog = new TimePickerFragment();
//                dialog.show(manager, DIALOG_TIME);

                Calendar mCurrentTime = Calendar.getInstance();
                int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mCurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        currentHour = hourOfDay;
                        currentMinute = minute;
                    }
                }, hour, minute, true);

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        mEditTextNotice= (EditText) v.findViewById(R.id.edit_notice);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            initDatePicker();
        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            initTimePicker();
//        }

        mConfirmButton = (Button) v.findViewById(R.id.confirm_button);
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNotice = (String) mEditTextNotice.getText().toString();
                Log.i(TAG, "Working-0");
                DaysController daysController = DaysController.getInstance();
                Log.i(TAG, "Working-010");
                Day day = daysController.getDay(positionOfDay);
                Log.i(TAG, "Working-1");
                day.addNotice(getNotice, currentHour, currentMinute);
                Log.i(TAG, "Working-2");
            }
        });

        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int currentDay = (int) calendar.get(Calendar.DAY_OF_WEEK);

        mDatePickerDialog = new DatePickerDialog(getContext());
        mDatePickerDialog.getDatePicker().setMinDate(mCalendar.getTimeInMillis());
        mCalendar.add(Calendar.DAY_OF_YEAR, 6);
        mDatePickerDialog.getDatePicker().setMaxDate(mCalendar.getTimeInMillis());
        mDatePickerDialog.setOnDateSetListener((view, year, month, dayOfMonth) -> {
            mCalendar.set(Calendar.MONTH, month);
            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            int clicked = mCalendar.get(Calendar.DAY_OF_WEEK);

            //Log.i(TAG, Integer.toString(currentDay));
            int dayPosition;

            if (clicked < currentDay) {
                dayPosition = 7 - currentDay + clicked;
            } else if (clicked > currentDay) {
                dayPosition = clicked - currentDay;
            } else {
                dayPosition = 0;
            }

            positionOfDay = dayPosition;
        });

    }

//    @RequiresApi(api = Build.VERSION_CODES.N)
//    private void initTimePicker() {
//        Calendar calendar = Calendar.getInstance();
//        int currentTimeHour = (int) calendar.get(Calendar.HOUR_OF_DAY);
//        int currentTimeMinute = (int) calendar.get(Calendar.MINUTE);
//
//        Log.i(TAG, "!!!!!!!!!!!!!");
//
//        mTimePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                Log.i(TAG, "Time = " + Integer.toString(hourOfDay) + ":" + Integer.toString(minute) );
//            }
//        }, 0, 0, true);
//
//    }
}
