package com.bignerdranch.android.todo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainFragment extends Fragment {
    private static String TAG = "MainFragment";
    private static String EXTRA = "DAY_OF_WEEK";

    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;
    private DaysController mDaysController;

    public void addNoticeDay(int position, String notice) {
        mDaysController.getDay(position).addNotice(notice);
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_days);

        mDaysController = DaysController.getInstance();

        WeekAdapter weekAdapter = new WeekAdapter(mDaysController.getDays());

        mRecyclerView.setAdapter(weekAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mFloatingActionButton = (FloatingActionButton) v.findViewById(R.id.fab_main);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddingActivity.class);
                startActivity(intent);

            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((WeekAdapter)mRecyclerView.getAdapter()).updateWeek(mDaysController.getDays());
    }

    public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.ViewHolder>  {

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            public TextView nameTextView;
            public TextView dateTextView;

            public ViewHolder(View itemView) {
                super(itemView);

                nameTextView = (TextView) itemView.findViewById(R.id.name_of_day);
                dateTextView = (TextView) itemView.findViewById(R.id.date_of_day);
                itemView.setOnClickListener((View.OnClickListener) this);
            }

            @Override
            public void onClick(View v) {
                Intent intent = DetailedDayActivity.newIntent(getActivity(), getAdapterPosition());
                startActivity(intent);
            }
        }

        private Day[] weekDays;

        public WeekAdapter(Day[] week) {
            weekDays = week;
        }

        public void updateWeek(Day[] week) {
            weekDays = week;
            notifyDataSetChanged();
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

        public Day getDayByPosition(int position) {
            return weekDays[position];
        }

        @Override
        public int getItemCount() {
            return weekDays.length;
        }
    }
}