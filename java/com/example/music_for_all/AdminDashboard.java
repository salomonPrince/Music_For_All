package com.example.music_for_all;

import com.example.music_for_all.R;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class AdminDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        int id = item.getItemId();

        if (id == R.id.nav_create_music_genre) {
            transaction.replace(R.id.fragment_container, new CreateMusicGenreFragment());
            transaction.commit();
        } else if (id == R.id.nav_create_update_song) {
            transaction.replace(R.id.fragment_container, new CreateUpdateSongFragment());
            transaction.commit();
        } else if (id == R.id.nav_set_favorite) {
            transaction.replace(R.id.fragment_container, new SetFavoriteMusicFragment());
            transaction.commit();
        } else if (id == R.id.nav_set_download) {
            transaction.replace(R.id.fragment_container, new SetDownloadMusicFragment());
            transaction.commit();
        } else if (id == R.id.nav_edit_admin_profile) {
            transaction.replace(R.id.fragment_container, new EditAdminProfileFragment());
            transaction.commit();
        } else if (id == R.id.nav_add_remove_song) {
            transaction.replace(R.id.fragment_container, new AddRemoveSongFragment());
            transaction.commit();
        } else if (id == R.id.nav_create_music_share) {
            transaction.replace(R.id.fragment_container, new CreateMusicToShareFragment());
            transaction.commit();
        } else if (id == R.id.nav_create_artist) {
            transaction.replace(R.id.fragment_container, new CreateArtistFragment());
            transaction.commit();
        } else if (id == R.id.nav_user_engagement) {
            transaction.replace(R.id.fragment_container, new UserEngagementFragment());
            transaction.commit();
        } else if (id == R.id.nav_feedback) {
            transaction.replace(R.id.fragment_container, new FeedbackSupportFragment());
            transaction.commit();
        } else if (id == R.id.nav_set_new_music) {
            transaction.replace(R.id.fragment_container, new SetNewMusicFragment());
            transaction.commit();
        } else if (id == R.id.nav_logout_admin) {
            transaction.replace(R.id.fragment_container, new LogoutAdminFragment());
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
