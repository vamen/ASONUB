package com.example.vivek.asonub;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.vivek.asonub.Adapter.ViewPagerAdapter;
import com.example.vivek.asonub.Fragments.NotesFragment;
import com.example.vivek.asonub.Fragments.NotifcationFragment;
import com.example.vivek.asonub.Fragments.TaskFragment;
import com.malinskiy.materialicons.IconDrawable;
import com.malinskiy.materialicons.Iconify;

public class SubjectDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectdetails);
        overridePendingTransition(R.animator.transition_in_from_right, R.animator.transition_out_to_left);
        Toolbar toolbar = (Toolbar) findViewById(R.id.chatActbar);
        toolbar.setTitle("Subject");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TaskFragment(), "TASK");
        adapter.addFragment(new NotesFragment(), "NOTES");
        adapter.addFragment(new NotifcationFragment(), "Notification");
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(new IconDrawable(this, Iconify.IconValue.zmdi_assignment).colorRes(R.color.colorAccent).sizeDp(20));
        tabLayout.getTabAt(1).setIcon(new IconDrawable(this, Iconify.IconValue.zmdi_copy).colorRes(R.color.colorAccent).sizeDp(20));
        tabLayout.getTabAt(2).setIcon(new IconDrawable(this, Iconify.IconValue.zmdi_notifications).colorRes(R.color.colorAccent).sizeDp(20));

        //tabLayout.addTab(tabLayout.newTab().setText("Task").setIcon(new IconDrawable(this, Iconify.IconValue.zmdi_assignment).colorRes(R.color.colorAccent).sizeDp(20)));
        //tabLayout.addTab(tabLayout.newTab().setText("Notes").setIcon(new IconDrawable(this, Iconify.IconValue.zmdi_copy).colorRes(R.color.colorAccent).sizeDp(20)));
        //tabLayout.addTab(tabLayout.newTab().setText("Notification").setIcon(new IconDrawable(this, Iconify.IconValue.zmdi_notifications).colorRes(R.color.colorAccent).sizeDp(20)));

        //tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
        //  @Override
        //  public void onTabSelected(TabLayout.Tab tab) {
        //    if (tabLayout.getSelectedTabPosition() == 0) {
        //      Toast.makeText(SubjectDetailsActivity.this, "Tab " + tabLayout.getSelectedTabPosition(), Toast.LENGTH_LONG).show();
        //} else if (tabLayout.getSelectedTabPosition() == 1) {
        // Toast.makeText(SubjectDetailsActivity.this, "Tab " + tabLayout.getSelectedTabPosition(), Toast.LENGTH_LONG).show();
        // } else if (tabLayout.getSelectedTabPosition() == 2) {
        //    Toast.makeText(SubjectDetailsActivity.this, "Tab " + tabLayout.getSelectedTabPosition(), Toast.LENGTH_LONG).show();
        //} else if (tabLayout.getSelectedTabPosition() == 3) {
        //   Toast.makeText(SubjectDetailsActivity.this, "Tab " + tabLayout.getSelectedTabPosition(), Toast.LENGTH_LONG).show();
        // } else if (tabLayout.getSelectedTabPosition() == 4) {
        //     Toast.makeText(SubjectDetailsActivity.this, "Tab " + tabLayout.getSelectedTabPosition(), Toast.LENGTH_LONG).show();
        //   }
        //}

        //@Override
        //public void onTabUnselected(TabLayout.Tab tab) {

        //}

//            @Override
        //          public void onTabReselected(TabLayout.Tab tab) {

//            }
        //      });*/


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.animator.transition_in_from_left, R.animator.transition_out_to_right);

    }
}
