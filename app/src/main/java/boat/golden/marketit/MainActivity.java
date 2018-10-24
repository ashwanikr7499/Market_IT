package boat.golden.marketit;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.firebase.FirebaseApp;

import boat.golden.marketit.fragments.Fav;
import boat.golden.marketit.fragments.Home;
import boat.golden.marketit.fragments.Nearby;
import boat.golden.marketit.fragments.Orders;
import boat.golden.marketit.helpers.BottomNavigationViewHelper;


public class MainActivity extends AppCompatActivity
{
    Fragment fragment;
    Menu localmenu;
    //BottomNavigationView navview;
    AHBottomNavigation navview;
    SharedPreferences sharedPreferences;
    String currentstatus;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);
        navview=findViewById(R.id.bottom_navigation);
        navview.setColored(true);
        //navview.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navview.setOnTabSelectedListener(mOnBottomNavigationTabSelectedListener);



        navview.addItem(new AHBottomNavigationItem("Home",R.drawable.ic_home_black_24dp));
        navview.addItem(new AHBottomNavigationItem("Fav",R.drawable.ic_favorite_black_24dp));
        navview.addItem(new AHBottomNavigationItem("Orders",R.drawable.ic_room_service_black_24dp));
        navview.addItem(new AHBottomNavigationItem("Near By",R.drawable.ic_location_on_black_24dp));
        navview.setCurrentItem(0);

        //navview.setBehaviorTranslationEnabled(false);
        navview.setTranslucentNavigationEnabled(true);
        navview.setDefaultBackgroundColor(Color.WHITE);
        navview.setAccentColor(fetchColor(R.color.accentcolor));
        navview.setInactiveColor(fetchColor(R.color.inactivecolor));


        navview.setColoredModeColors(Color.WHITE,fetchColor(R.color.bottomtabitemresting));

        sharedPreferences=this.getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        currentstatus=sharedPreferences.getString("islogin","false");
        if (currentstatus.equals("false"))
        {
            Intent i=new Intent(this,login.class);
            startActivity(i);
        }
        fragment=new Home();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();


    }

    private int fetchColor(@ColorRes int color) {
        return ContextCompat.getColor(this, color);
    }


    private AHBottomNavigation.OnTabSelectedListener mOnBottomNavigationTabSelectedListener = new AHBottomNavigation.OnTabSelectedListener() {
        @Override
        public boolean onTabSelected(int position, boolean wasSelected) {
            switch (position) {
                case 0:
                    fragment=new Home();

                    break;
                case 1:

                    fragment=new Fav();
                    break;

                case 2:
                    fragment=new Orders();
                    break;

                case 3:
                    fragment=new Nearby();
                    break;
            }
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.orderss:
                    fragment=new Orders();
                    break;

                case R.id.homee:

                    fragment=new Home();
                    break;
                case R.id.favv:

                    fragment=new Fav();
                    break;

                case R.id.nearbyy:
                    fragment=new Nearby();
                    break;
            }
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        if (!currentstatus.equals("false")){
            menu.findItem(R.id.logout).setTitle("Login");
        }
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.about:
                Toast.makeText(MainActivity.this,"About",Toast.LENGTH_SHORT).show();

                   Intent intent = new Intent(MainActivity.this, About.class);
                    startActivity(intent);


                break;
            case R.id.help:
                Toast.makeText(MainActivity.this,"Ohhh You need Help",Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                Toast.makeText(MainActivity.this,"Log Out",Toast.LENGTH_SHORT).show();
                break;
            case R.id.seller:
                startActivity(new Intent(MainActivity.this,SellerMainActivity.class));
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder buildd = new AlertDialog.Builder(MainActivity.this);

        buildd.setMessage("Do You Want To Exit");
        buildd.setTitle("Market It");
        buildd.setCancelable(true);
        buildd.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog fgi=buildd.create();
        fgi.show();
    }
}