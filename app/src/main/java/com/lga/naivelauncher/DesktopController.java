package com.lga.naivelauncher;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

final class DesktopController {
  private @NonNull final FragmentManager mFragmentManager;
  private @NonNull final View mDecorView;
  private @Nullable Fragment mDesktopFragment;

  DesktopController(@NonNull final FragmentManager fragmentManager, @NonNull final View decorView) {
    mFragmentManager = fragmentManager;
    mDecorView = decorView;
  }

  void showFragment() {
    if (isFragmentShowing()) {
      return;
    }

    mDesktopFragment = new DesktopFragment();
    final FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
    fragmentTransaction.add(R.id.mainView, mDesktopFragment);
    fragmentTransaction.commit();

    Utils.setStatusBarColor(mDecorView, true);
    Utils.setNavigationBarColor(mDecorView, true);
  }

  void hideFragment() {
    if (!isFragmentShowing()) {
      return;
    }

    final FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
    fragmentTransaction.remove(mDesktopFragment);
    fragmentTransaction.commit();
    mDesktopFragment = null;
  }

  boolean isFragmentShowing() {
    return mDesktopFragment != null && mDesktopFragment.isAdded();
  }
}
