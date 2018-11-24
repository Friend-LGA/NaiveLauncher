package com.lga.naivelauncher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.WindowManager;

public final class MainActivity extends FragmentActivity {
  @Nullable DesktopController mDesktopController;
  @Nullable AppsDrawerController mAppsDrawerController;
  @Nullable GestureDetector mGestureDetector;

  @Override
  protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Utils.setFullscreenLayout(getWindow());

    mDesktopController = new DesktopController(
      getSupportFragmentManager(),
      getWindow().getDecorView()
    );

    mAppsDrawerController = new AppsDrawerController(
      getSupportFragmentManager(),
      getPackageManager(),
      getWindow().getDecorView()
    );

    mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
      public void onLongPress(MotionEvent event) {
        final Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
      }
    });

    if (savedInstanceState == null) {
      showDesktopFragment();
    }
  }

  void showDesktopFragment() {
    if (mDesktopController == null || mAppsDrawerController == null) {
      return;
    }

    mDesktopController.showFragment();
    mAppsDrawerController.hideFragment();

    getWindow().setStatusBarColor(getResources().getColor(R.color.transparent));
    getWindow().setNavigationBarColor(getResources().getColor(R.color.transparent));
  }

  void showAppsDrawerFragment() {
    if (mDesktopController == null || mAppsDrawerController == null) {
      return;
    }

    mDesktopController.hideFragment();
    mAppsDrawerController.showFragment();

    getWindow().setStatusBarColor(getResources().getColor(R.color.system_bar_white_transparent));
    getWindow().setNavigationBarColor(getResources().getColor(R.color.system_bar_white_transparent));
  }

  @Override
  public void onBackPressed() {
    if (mAppsDrawerController != null && mAppsDrawerController.isShowing()) {
      showDesktopFragment();
    } else {
      super.onBackPressed();
    }
  }

  public boolean onTouchEvent(MotionEvent event) {
    if (mGestureDetector == null) {
      return false;
    }

    return mGestureDetector.onTouchEvent(event);
  }
}
