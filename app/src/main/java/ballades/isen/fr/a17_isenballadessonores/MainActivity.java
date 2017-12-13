package ballades.isen.fr.a17_isenballadessonores;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ballades.isen.fr.a17_isenballadessonores.dummy.DummyContent;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
        , HomeFragment.OnFragmentInteractionListener
        , MapFragment.OnFragmentInteractionListener
,BalladeFragment.OnListFragmentInteractionListener {

    private static final String TAG = "MainActivity" ;
    // tableau des fragments
    Fragment[] allFrags = new Fragment[3];
    ViewPager mViewPager; // gestionnaire de pages (Pager)
    FragsPagerAdapter fragsPagerAdapter; // adapteur des fragments au Pager : classe interne

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        allFrags[0] = HomeFragment.newInstance("bla", "blabla");
        allFrags[1] = MapFragment.newInstance("bla", "blabla");
        allFrags[2] = BalladeFragment.newInstance(1);
        setContentView(R.layout.activity_main);

        fragsPagerAdapter = new FragsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
      //  mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mViewPager.setPageTransformer(true, new DepthPageTransformer());

        mViewPager.setAdapter(fragsPagerAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (mViewPager.getCurrentItem() == 0) {
                // If the user is currently looking at the first step, allow the system to handle the         // Back button. This calls finish() on this activity and pops the back stack.
                super.onBackPressed();
            } else {            // Otherwise, select the previous step.
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);       }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Log.d(TAG, "I Click");
        Intent it = new Intent(MainActivity.this, BalladeInfoActivity.class);
        startActivity(it);
    }

    /**
     * adapteur minimaliste pour les fragments
     */
    public class FragsPagerAdapter extends FragmentStatePagerAdapter {
        public FragsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return allFrags[i];
        }

        @Override
        public int getCount() {
            return allFrags.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position==0)
                return getResources().getString(R.string.tabHome);
            else if (position==1)
                return getResources().getString(R.string.tabMap);
            else if (position==2)
                return getResources().getString(R.string.tabList);
            else
                return "ERROR" ;
        }
    }
}

