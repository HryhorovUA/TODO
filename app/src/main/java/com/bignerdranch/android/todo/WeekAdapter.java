package com.bignerdranch.android.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.name_of_day);
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
    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
