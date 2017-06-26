package eskool.com.eskoolapp.Fees;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eskool.com.eskoolapp.Home.ParentHome;
import eskool.com.eskoolapp.Profile.TeacherFragmentOne;
import eskool.com.eskoolapp.Profile.TeacherFragmentThree;
import eskool.com.eskoolapp.Profile.TeacherFrgmntTwo;
import eskool.com.eskoolapp.Profile.TeacherProfile;
import eskool.com.eskoolapp.R;
import eskool.com.eskoolapp.User;

/**
 * Created by user on 6/23/2017.
 */

public class FeesFrgmnt2 extends Fragment {
    Toolbar toolbar;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public FeesFrgmnt2() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fees_fragment2, container, false);

        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.tool_bar);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Jan"));
        tabLayout.addTab(tabLayout.newTab().setText("Feb"));
        tabLayout.addTab(tabLayout.newTab().setText("March"));
        tabLayout.addTab(tabLayout.newTab().setText("April"));
        tabLayout.addTab(tabLayout.newTab().setText("May"));
        tabLayout.addTab(tabLayout.newTab().setText("June"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final PagerAdapter adapter = new PagerAdapter
                (getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.setupWithViewPager(viewPager);


        return view;
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;
        private String[] tabTitles = new String[]{"Jan","Feb","March","April","May","June"};

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                   one tab1 = new one();
                    return tab1;
                case 1:
                    one tab2 = new one();
                    return tab2;
                case 2:
                    one tab3 = new one();
                    return tab3;
                case 3:
                    one tab4 = new one();
                    return tab4;
                case 4:
                    one tab5 = new one();
                    return tab5;
                case 5:
                    one tab6 = new one();
                    return tab6;
                case 6:
                    one tab7 = new one();
                    return tab7;

                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Fees");
        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }

}
