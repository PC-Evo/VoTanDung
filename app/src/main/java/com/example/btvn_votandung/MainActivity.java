package com.example.btvn_votandung;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

FloatingActionButton fab;
DrawerLayout drawerLayout;
    BottomNavigationView bttomnavigation;



    @SuppressLint("RestrictedApi")


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        fab = findViewById(R.id.fab);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);

        }

        replaceFragment(new HomeFragment());

        bttomnavigation = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        //bottomNavigationView.setBackground(null);
        bttomnavigation.setOnItemSelectedListener(item -> {

            MenuItem ite = item;
            if (item.getItemId() == R.id.home) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.shorts) {
                replaceFragment(new ShortsFragment());
            } else if (item.getItemId() == R.id.subscriptions) {
                replaceFragment(new SubscriptionsFragment());
            } else if (item.getItemId() == R.id.library) {
                replaceFragment(new LibraryFragment());
            }
            return true;
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
            }
        });

    }
///
private  void replaceFragment(Fragment fragment) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.fragment_layout, fragment);
    fragmentTransaction.commit();
}

    private void showBottomDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottonsheetlayout);

        LinearLayout videoLayout = dialog.findViewById(R.id.layoutVideo);
        LinearLayout shortsLayout = dialog.findViewById(R.id.layoutShorts);
        LinearLayout liveLayout = dialog.findViewById(R.id.layoutLive);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);

        videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(MainActivity.this,"Upload a Video is clicked",Toast.LENGTH_SHORT).show();

            }
        });

        shortsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(MainActivity.this,"Create a short is Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        liveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(MainActivity.this,"Go live is Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

}