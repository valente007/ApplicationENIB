package com.enib.applicationenib.pasutilisé;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.enib.applicationenib.R;


/**
 * Created by Valentin on 12/12/2015.
 */
public class Home extends Fragment
{
    ExpandableListView explvlist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_notes,container,false);



        explvlist = (ExpandableListView) rootView.findViewById(R.id.expandable_list_notes);
        explvlist.setAdapter(new ParentLevel());

        return rootView;
    }

    public class ParentLevel extends BaseExpandableListAdapter
    {

        @Override
        public Object getChild(int arg0, int arg1)
        {
            return arg1;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition)
        {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent)
        {
            CustExpListview SecondLevelexplv = new CustExpListview((Context)getContext());
            SecondLevelexplv.setAdapter(new SecondLevelAdapter());
            SecondLevelexplv.setGroupIndicator(null);
            return SecondLevelexplv;
        }

        @Override
        public int getChildrenCount(int groupPosition)
        {
            return 3;
        }

        @Override
        public Object getGroup(int groupPosition)
        {
            return groupPosition;
        }

        @Override
        public int getGroupCount()
        {
            return 5;
        }

        @Override
        public long getGroupId(int groupPosition)
        {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent)
        {
            TextView tv = new TextView(getActivity());
            tv.setText("->FirstLevel");
            tv.setBackgroundColor(Color.BLUE);
            tv.setPadding(10, 7, 7, 7);

            return tv;
        }

        @Override
        public boolean hasStableIds()
        {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition)
        {
            return true;
        }
    }

    public class CustExpListview extends ExpandableListView
    {

        int intGroupPosition, intChildPosition, intGroupid;

        public CustExpListview(Context context)
        {
            super(context);
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
        {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(960, MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(600, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public class SecondLevelAdapter extends BaseExpandableListAdapter
    {

        @Override
        public Object getChild(int groupPosition, int childPosition)
        {
            return childPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition)
        {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent)
        {
            TextView tv = new TextView(getActivity());
            tv.setText("child");
            tv.setPadding(15, 5, 5, 5);
            tv.setBackgroundColor(Color.YELLOW);
            tv.setLayoutParams(new ListView.LayoutParams(AbsListView.LayoutParams.FILL_PARENT, AbsListView.LayoutParams.FILL_PARENT));
            return tv;
        }

        @Override
        public int getChildrenCount(int groupPosition)
        {
            return 5;
        }

        @Override
        public Object getGroup(int groupPosition)
        {
            return groupPosition;
        }

        @Override
        public int getGroupCount()
        {
            return 1;
        }

        @Override
        public long getGroupId(int groupPosition)
        {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent)
        {
            TextView tv = new TextView(getActivity());
            tv.setText("-->Second Level");
            tv.setPadding(12, 7, 7, 7);
            tv.setBackgroundColor(Color.RED);

            return tv;
        }

        @Override
        public boolean hasStableIds() {
            // TODO Auto-generated method stub
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return true;
        }

    }
}