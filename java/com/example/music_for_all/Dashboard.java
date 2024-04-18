package com.example.music_for_all;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;


/* */

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

      /*  if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MusicGenreFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_music_genre);
        } */
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        int id = item.getItemId();

        if (id==R.id.nav_music_genre){
            transaction.replace(R.id.fragment_container, new MusicGenreFragment());
            transaction.commit();
        }
        else if (id==R.id.nav_choose_music){
            transaction.replace(R.id.fragment_container,new ChooseMusicFragment());
            transaction.commit();
        }

        else if (id==R.id.nav_download){
            transaction.replace(R.id.fragment_container, new DownloadMusicFragment());
            transaction.commit();
        }
        else if (id==R.id.nav_edit_profile){
            transaction.replace(R.id.fragment_container, new EditProfileFragment());
            transaction.commit();
        }

        else if (id==R.id.nav_weather){
            transaction.replace(R.id.fragment_container, new WeatherFragment());
            transaction.commit();
        }


        else if (id==R.id.nav_follow_artist){
            transaction.replace(R.id.fragment_container, new FollowArtistsFragment());
            transaction.commit();
        }
        else if (id==R.id.nav_discover){
            transaction.replace(R.id.fragment_container, new DiscoverNewMusicFragment());
            transaction.commit();
        }
        else if (id==R.id.nav_video){
            transaction.replace(R.id.fragment_container, new VideoLibraryFragment());
            transaction.commit();
        }
        else if (id==R.id.nav_rate){
            transaction.replace(R.id.fragment_container, new RateFeedback());
            transaction.commit();
        }
        else if (id==R.id.nav_logout){
            transaction.replace(R.id.fragment_container, new LogoutFragment());
            transaction.commit();
            Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}