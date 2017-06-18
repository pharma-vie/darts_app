package com.primewebtech.darts.statistics.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.primewebtech.darts.R;
import com.primewebtech.darts.database.ScoreDatabase;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by benebsworth on 18/6/17.
 */

public class StatsHundredFragment extends Fragment {

    private static final String TAG = StatsHundredFragment.class.getSimpleName();
    public int[] mStatsRow100 = {
            R.id.row_100_d,
            R.id.row_100_w,
            R.id.row_100_m,
    };
    public int[] mStatsRow140 = {
            R.id.row_140_d,
            R.id.row_140_w,
            R.id.row_140_m,
    };
    public int[] mStatsRow180 = {
            R.id.row_180_d,
            R.id.row_180_w,
            R.id.row_180_m,
    };
    public ArrayList<int[]> mStatsRows;
    int[] pegValues = {
        100,
        140,
        180,
    };
    private String type;
    public static StatsHundredFragment newInstance() {
        StatsHundredFragment statsHundredFragment = new StatsHundredFragment();
        Bundle args = new Bundle();
        args.putString("type", "one");
        statsHundredFragment.setArguments(args);
        return statsHundredFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getString("type");
    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stats_hundred, container, false);

        mStatsRows = new ArrayList<>();
        mStatsRows.add(mStatsRow100);
        mStatsRows.add(mStatsRow140);
        mStatsRows.add(mStatsRow180);
        int index = 0;
        for ( int[] statRow : mStatsRows) {
            TextView statsScoreDay = (TextView) rootView.findViewById(statRow[0]);
            TextView statsScoreWeek = (TextView) rootView.findViewById(statRow[1]);
            TextView statsScoreMonth = (TextView) rootView.findViewById(statRow[2]);
            int dailyScore = ScoreDatabase.mStatsHundredDoa.getTotalPegCountDay(pegValues[index]);
            int weeklyScore = ScoreDatabase.mStatsHundredDoa.getTotalPegCountWeek(pegValues[index]);
            int monthlyScore = ScoreDatabase.mStatsHundredDoa.getTotalPegCountMonth(pegValues[index]);
            if (dailyScore > 1000) {

                statsScoreDay.setTextSize(12);

            }
            if (weeklyScore > 1000) {
                statsScoreWeek.setTextSize(12);

            }
            if (monthlyScore > 1000) {
                statsScoreMonth.setTextSize(12);
            }
            //Set Day element:
            statsScoreDay.setText(String.format(Locale.getDefault(),"%d", dailyScore));
            statsScoreDay.setTextColor(Color.BLACK);
            statsScoreDay.setBackground(
                    getResources().getDrawable(R.drawable.peg_stats_score_background_white));
            //Set Week element:
            statsScoreWeek.setText(String.format(Locale.getDefault(),"%d",
                    weeklyScore));
            statsScoreWeek.setBackground(
                    getResources().getDrawable(R.drawable.peg_stats_score_background_white));
            statsScoreWeek.setTextColor(Color.BLACK);
            //Set Month element:
            statsScoreMonth.setText(String.format(Locale.getDefault(),"%d",monthlyScore ));
            statsScoreMonth.setBackground(
                    getResources().getDrawable(R.drawable.peg_stats_score_background_white));
            statsScoreMonth.setTextColor(Color.BLACK);
            index++;
        }
        return rootView;


    }


}