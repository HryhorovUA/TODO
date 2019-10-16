package com.bignerdranch.android.todo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    //private RecyclerView mRecyclerView;
    private static String TAG = "MainFragment";

    Day[] mDays = new Day[7];

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container, false);
        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_days);


        mDays = Day.createWeek();


        WeekAdapter weekAdapter = new WeekAdapter(mDays);


        mRecyclerView.setAdapter(weekAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }

    public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.ViewHolder>  {

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            public TextView nameTextView;
            public TextView dateTextView;

            public ViewHolder(View itemView) {
                super(itemView);

                nameTextView = (TextView) itemView.findViewById(R.id.name_of_day);
                dateTextView = (TextView) itemView.findViewById(R.id.date_of_day);
                nameTextView.setOnClickListener((View.OnClickListener) this);
            }

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailedDay.class);
                startActivity(intent);
            }
        }



        private Day[] weekDays = new Day[7];

        public WeekAdapter(Day[] week) {
            for (int i = 0; i < 7; i++) {
                weekDays[i] = week[i];
            }
        }

        @Override
        public WeekAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View dayView = inflater.inflate(R.layout.simple_day, parent, false);

            ViewHolder viewHolder = new ViewHolder(dayView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(WeekAdapter.ViewHolder viewHolder, int position) {
            Day day = weekDays[position];

            TextView textView = viewHolder.nameTextView;
            textView.setText(day.getDayOfTheWeek());
            TextView dateView = viewHolder.dateTextView;
            dateView.setText(day.getDateOfTheWeek());
        }

        @Override
        public int getItemCount() {
            return 7;
        }
    }
}
