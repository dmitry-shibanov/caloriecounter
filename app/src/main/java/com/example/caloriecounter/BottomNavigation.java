package com.example.caloriecounter;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.MenuItem;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class BottomNavigation extends AppCompatActivity {
    private TextView mTextMessage;
    private FragmentManager fragmentManager;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    fragmentManager.findFragmentByTag(TagsFragment.Calculate.name());
//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

//    public static void attachFragment ( int fragmentHolderLayoutId, Fragment fragment, Context context, String tag ) {
//
//
//        FragmentManager manager = ( (AppCompatActivity) context ).getSupportFragmentManager ();
//        FragmentTransaction ft = manager.beginTransaction ();
//
//        if (manager.findFragmentByTag ( tag ) == null) { // No fragment in backStack with same tag..
//            ft.add ( fragmentHolderLayoutId, fragment, tag );
//            ft.addToBackStack ( tag );
//            ft.commit ();
//        }
//        else {
//            ft.show ( manager.findFragmentByTag ( tag ) ).commit ();
//        }
//    }

//    private void replaceFragment (Fragment fragment){
//        String backStateName = fragment.getClass().getName();
//
//        FragmentManager manager = getSupportFragmentManager();
//        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);
//
//        if (!fragmentPopped){ //fragment not in back stack, create it.
//            FragmentTransaction ft = manager.beginTransaction();
//            ft.replace(R.id.content_frame, fragment);
//            ft.addToBackStack(backStateName);
//            ft.commit();
//        }
//    }

    Fragment changeLayout(TagsFragment tag) {
        List<TagsFragment> tags = Arrays.asList(TagsFragment.Calculate, TagsFragment.Food, TagsFragment.PersonStatistics);
        tags.remove(tag);

//        for (TagsFragment item:
//             tags) {
        int index = fragmentManager.getBackStackEntryCount() - 1;
        FragmentManager.BackStackEntry backEntry = fragmentManager.getBackStackEntryAt(index);
        String tagName = backEntry.getName();
        Fragment fragment = fragmentManager.findFragmentByTag(tagName);
        if (fragment == null) {
        }
        return fragment;
//        fragmentTransaction.addToBackStack(tag);
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        fragmentManager = getSupportFragmentManager();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}


//public class AuthenticatedMainActivity extends Activity implements FragmentManager.OnBackStackChangedListener{
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        .............
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction().add(R.id.frame_container,fragment, "First").addToBackStack(null).commit();
//    }
//
//    private void switchFragment(Fragment fragment){
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.frame_container, fragment).addToBackStack("Tag").commit();
//    }
//
//    @Override
//    public void onBackStackChanged() {
//        FragmentManager fragmentManager = getFragmentManager();
//
//        System.out.println("@Class: SummaryUser : onBackStackChanged "
//                + fragmentManager.getBackStackEntryCount());
//
//        int count = fragmentManager.getBackStackEntryCount();
//
//        // when a fragment come from another the status will be zero
//        if(count == 0){
//
//            System.out.println("again loading user data");
//
//            // reload the page if user saved the profile data
//
//            if(!objPublicDelegate.checkNetworkStatus()){
//
//                objPublicDelegate.showAlertDialog("Warning"
//                        , "Please check your internet connection");
//
//            }else {
//
//                objLoadingDialog.show("Refreshing data...");
//
//                mNetworkMaster.runUserSummaryAsync();
//            }
//
//            // IMPORTANT: remove the current fragment from stack to avoid new instance
//            fragmentManager.removeOnBackStackChangedListener(this);
//
//        }// end if
//    }
//}


//https://stackoverflow.com/questions/18305945/how-to-resume-fragment-from-backstack-if-exists



//Step 2: When you call the another fragment add this method:
//
//        String backStateName = this.getClass().getName();
//
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.addOnBackStackChangedListener(this);
//
//        Fragment fragmentGraph = new GraphFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("graphTag",  view.getTag().toString());
//        fragmentGraph.setArguments(bundle);
//
//        fragmentManager.beginTransaction()
//        .replace(R.id.content_frame, fragmentGraph)
//        .addToBackStack(backStateName)
//        .commit();
