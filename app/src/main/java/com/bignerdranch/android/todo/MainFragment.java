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

import java.util.ArrayList;

public class MainFragment extends Fragment {
    private static String TAG = "MainFragment";
    private static String EXTRA = "DAY_OF_WEEK";

    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;
    private TextView mTextViewNotice1;
    private TextView mTextViewNotice2;
    private TextView mTextViewNotice3;
    private static DaysController sDaysController;

//    public void addNoticeDay(int position, String notice) {
//        mDaysController.getDay(position).addNotice(notice);
//    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_days);

        sDaysController = DaysController.getInstance();
        WeekAdapter weekAdapter = new WeekAdapter(sDaysController.getDays());

        mRecyclerView.setAdapter(weekAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mTextViewNotice1 = (TextView) v.findViewById(R.id.notice1);
        mTextViewNotice2 = (TextView) v.findViewById(R.id.notice2);
        mTextViewNotice3 = (TextView) v.findViewById(R.id.notice3);

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
        ((WeekAdapter)mRecyclerView.getAdapter()).updateWeek(sDaysController.getDays());
    }

    public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.ViewHolder>  {

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            public TextView nameTextView;
            public TextView dateTextView;
            public TextView notice1TextView;
            public TextView notice2TextView;
            public TextView notice3TextView;

            public ViewHolder(View itemView) {
                super(itemView);

                nameTextView = (TextView) itemView.findViewById(R.id.name_of_day);
                dateTextView = (TextView) itemView.findViewById(R.id.date_of_day);
                notice1TextView = (TextView) itemView.findViewById(R.id.notice1);
                notice2TextView = (TextView) itemView.findViewById(R.id.notice2);
                notice3TextView = (TextView) itemView.findViewById(R.id.notice3);

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
            Day day = sDaysController.getDay(position);
            TextView textView = viewHolder.nameTextView;
            textView.setText(day.getDayOfTheWeek());
            TextView dateView = viewHolder.dateTextView;
            dateView.setText(day.getDateOfTheWeek());
            TextView textViewNotice1 = viewHolder.notice1TextView;
            TextView textViewNotice2 = viewHolder.notice2TextView;
            TextView textViewNotice3 = viewHolder.notice3TextView;
            ArrayList<SimpleNotice> list = day.getListNotice();
            Log.i(TAG, day.getDayOfTheWeek() + " " + Integer.toString(list.size()));
            String notice1;
            String notice2;
            String notice3;
            if (list.size() > 0) {
                switch (list.size()) {
                    case 1:
                        notice1 = list.get(0).getDateNotice();
                        textViewNotice1.setText(notice1);
                        break;
                    case 2:
                        notice1 = list.get(0).getDateNotice();
                        notice2 = list.get(1).getDateNotice();
                        textViewNotice1.setText(notice1);
                        textViewNotice2.setText(notice2);
                        break;
                    default:
                        notice1 = list.get(0).getDateNotice();
                        notice2 = list.get(1).getDateNotice();
                        notice3 = list.get(2).getDateNotice();
                        textViewNotice1.setText(notice1);
                        textViewNotice2.setText(notice2);
                        textViewNotice3.setText(notice3);
                        break;
                }
            }
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