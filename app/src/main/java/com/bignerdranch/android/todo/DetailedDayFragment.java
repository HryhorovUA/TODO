package com.bignerdranch.android.todo;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailedDayFragment extends Fragment {
    private static final String DAY_EXTRA = "DAY_EXTRA";

    private Day mDay;

    private FloatingActionButton mFloatingActionButton;
    private TextView mTextViewDate;
    private TextView mTextViewDay;
    private ActionBar mActionBar;



    public static DetailedDayFragment newInstance() {
        return new DetailedDayFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDay = (Day) getActivity()
                .getIntent()
                .getSerializableExtra(DetailedDayActivity.EXTRA_DAY);

        getActivity().setTitle(mDay.getDayOfTheWeek() + "  " + mDay.getDateOfTheWeek());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detailed_fragment, container, false);

        mFloatingActionButton = (FloatingActionButton) v.findViewById(R.id.fab_second);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddingActivity.class);
                startActivity(intent);
            }
        });

        //mTextViewDay = (TextView) v.findViewById(R.id.day_secondscreen);
        //mTextViewDay.setText(mDay.getDayOfTheWeek());

        //mTextViewDate = (TextView) v.findViewById(R.id.date_secondscreen);
        //mTextViewDate.setText(mDay.getDateOfTheWeek());


        return v;
    }


}
