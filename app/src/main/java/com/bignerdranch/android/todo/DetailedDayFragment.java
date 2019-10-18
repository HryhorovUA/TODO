package com.bignerdranch.android.todo;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DetailedDayFragment extends Fragment {
    private static final String TAG = "DetailedDayFragment";
    private static final String DAY_EXTRA = "DAY_EXTRA";

    private Day mDay;

    private FloatingActionButton mFloatingActionButton;
    private TextView mTextViewDate;
    private TextView mTextViewDay;
    private ActionBar mActionBar;
    private RecyclerView mRecyclerView;

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

        mRecyclerView = (RecyclerView) v.findViewById(R.id.note_list_recyclerview);

        ArrayList<SimpleNotice> simpleNoticeArrayList = DetailedDayActivity.getDay().getListNotice();

        NoticeAdapter noticeAdapter = new NoticeAdapter(simpleNoticeArrayList);
        mRecyclerView.setAdapter(noticeAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }

    public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {


        public class ViewHolder extends RecyclerView.ViewHolder  {
            private SimpleNotice mSimpleNotice;
            public TextView mTextViewOfNotice;

            public ViewHolder(View itemView) {
                super(itemView);

                mTextViewOfNotice = (TextView) itemView.findViewById(R.id.text_of_notice);
            }

            public void bind(SimpleNotice notice) {
                mSimpleNotice = notice;
            }
        }

        @Override
        public NoticeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View noticeView = inflater.inflate(R.layout.simple_notice, parent, false);

            ViewHolder viewHolder = new ViewHolder(noticeView);
            return viewHolder;
        }

        private ArrayList<SimpleNotice> noticeList = new ArrayList<SimpleNotice>();

        public NoticeAdapter(ArrayList<SimpleNotice> notices) {
            noticeList = notices;
        }

        @Override
        public void onBindViewHolder(NoticeAdapter.ViewHolder viewHolder, int position) {
            SimpleNotice notice = noticeList.get(position);
            viewHolder.bind(notice);
            TextView textView = viewHolder.mTextViewOfNotice;
            textView.setText(notice.getNotice());
            Log.i(TAG, notice.getNotice());
        }

        @Override
        public int getItemCount() {
            return noticeList.size();
        }
    }
}
