package com.primewebtech.darts.gallery;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.primewebtech.darts.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {
    private static final String TAG = GalleryActivity.class.getSimpleName();
    private File pictureDirectory;
    private List<File> sortedFiles;
    private RecyclerView mRecyclerView;
    private SimpleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        pictureDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "/Darts/");
        Util.getHeaderIndexes(pictureDirectory);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mAdapter = new SimpleAdapter(this);
        List<SectionedGridRecyclerViewAdapter.Section> sections =
                new ArrayList<SectionedGridRecyclerViewAdapter.Section>();
        //Sections
        /*
        Need to implement a top level algorithm here which generates the correct index to insert
        the title corresponding to some date range. For example "Today" can be inserting at 0 only
        if it was found that there are images which were taken today. Otherwise the next ranges
        should be changed and follow a similar principle of only being inserted if images are found
        in that range.

        We have the adapter handling a generic layout of N images which is not concerned with the
        placement of title. Not sure if that is a correct approach.
        psuedo code:

        TODAY:
        if (imagesTakenToday()) {
          insert @ 0
        }
        LASTWEEK:
        int lastWeekIndex = getLastWeekIndex()
        insert @ lastWeekIndex
        LASTMONTH:
        int lastMonthIndex = getLastMonthIndex()
        for month in months:
          int monthIndex = getMonthIndex(month)
          insert @ monthIndex

        ... keep going how far back? after a certain point we could just show the remaining pictures.

         */
        sections.add(new SectionedGridRecyclerViewAdapter.Section(0,"Today"));
        sections.add(new SectionedGridRecyclerViewAdapter.Section(5,"Last Week"));
        sections.add(new SectionedGridRecyclerViewAdapter.Section(12,"Last Month"));
        sections.add(new SectionedGridRecyclerViewAdapter.Section(14,"April"));
        sections.add(new SectionedGridRecyclerViewAdapter.Section(20,"March"));

        //Add your adapter to the sectionAdapter
        SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
        SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                SectionedGridRecyclerViewAdapter(this,R.layout.section,R.id.section_text,mRecyclerView,mAdapter);
        mSectionedAdapter.setSections(sections.toArray(dummy));
        mRecyclerView.setAdapter(mSectionedAdapter);


    }



}
