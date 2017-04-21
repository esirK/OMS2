package com.janta.esir.oms;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.janta.esir.oms.fragments.AboutUsFragment;
import com.janta.esir.oms.fragments.BrekoFragment;
import com.janta.esir.oms.fragments.HelpFragment;
import com.janta.esir.oms.fragments.OrdersFragment;
import com.janta.esir.oms.fragments.ProfileFragment;
import com.janta.esir.oms.fragments.SettingsFragment;
import com.janta.esir.oms.fragments.TabsFragment;
import com.janta.esir.oms.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.personal_nav);

        /**
        * Set Up The header_nav
        * */
        View hView=mNavigationView.inflateHeaderView(R.layout.header_nav_content);
        final RelativeLayout relativeLayout=(RelativeLayout)hView.findViewById(R.id.header);
        CircleImageView profile_pic=(CircleImageView)hView.findViewById(R.id.profile_pic);
        TextView profile_name=(TextView)hView.findViewById(R.id.profile_name);
        TextView profile_mail=(TextView)hView.findViewById(R.id.profile_email);

        Glide.with(this).load(R.drawable.esir).asBitmap().placeholder(R.drawable.rice).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    relativeLayout.setBackground(drawable);
                }
            }
        });

//        Glide.with(this).load(R.drawable.esir).asBitmap().into(profile_pic);
//        profile_pic.setImageResource(R.drawable.njunge);
        Glide.with(this).load(R.drawable.esir).asBitmap().into(profile_pic);
        profile_name.setText("Esir Kings Waitina");
        profile_mail.setText("esirkings@gmail.com");

        /**
         * inflate the very first fragment
         * The TabFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabsFragment()).commit();

        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                if (menuItem.getItemId() == R.id.home) {
                    Intent restartMe=new Intent(getApplicationContext(),MainActivity.class);
                    restartMe.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(restartMe);
                    finish();
                }

                if (menuItem.getItemId() == R.id.profile) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView,new ProfileFragment()).commit();

                }

                if (menuItem.getItemId() == R.id.orders) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new OrdersFragment()).commit();
                }
                if (menuItem.getItemId() == R.id.settings) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new SettingsFragment()).commit();
                }
                if (menuItem.getItemId() == R.id.about_us) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new AboutUsFragment()).commit();
                }
                if (menuItem.getItemId() == R.id.help) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new HelpFragment()).commit();
                }
                return false;
            }

        });
        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();
    }
}
