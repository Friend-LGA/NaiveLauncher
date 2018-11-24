package com.lga.naivelauncher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SettingsActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);

    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

    getWindow().getDecorView().setSystemUiVisibility(
      View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

    Utils.setStatusBarColor(getWindow().getDecorView(), true);
    Utils.setNavigationBarColor(getWindow().getDecorView(), false);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    ActionBar actionBar = getSupportActionBar();

    if (actionBar != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    ListView listView = findViewById(R.id.settingsListView);

    ArrayAdapter<String> adapter = new ArrayAdapter<>(
      this,
      android.R.layout.simple_list_item_1,
      getResources().getStringArray(R.array.settings_headers)
    );

    listView.setAdapter(adapter);

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final SettingsView settingsView = findViewById(R.id.settingsView);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        final SettingsGeneralFragment fragment = new SettingsGeneralFragment();
        fragment.setWindowInsets(settingsView.getWindowInsets());
        fragmentTransaction.add(R.id.fragmentsContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
      }
    });
  }

  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      FragmentManager fragmentManager = getSupportFragmentManager();

      if (fragmentManager.getBackStackEntryCount() > 0) {
        fragmentManager.popBackStack();
        setTitle("Settings");
        return true;
      }
    }

    return super.onOptionsItemSelected(item);
  }
}
