package com.lga.naivelauncher;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

final class AppsDrawerController {
  private @NonNull final FragmentManager mFragmentManager;
  private @NonNull final View mDecorView;
  private @NonNull final List<AppInfo> mAppInfoList = new ArrayList<>();
  private @Nullable AppsDrawerFragment mAppsDrawerFragment;

  AppsDrawerController(@NonNull final FragmentManager fragmentManager,
                       @NonNull final PackageManager packageManager,
                       @NonNull final View decorView) {
    mFragmentManager = fragmentManager;
    mDecorView = decorView;

    final AppsDrawerDataLoader.Listener dataLoaderListener = new AppsDrawerDataLoader.Listener() {
      public void onFinished(@NonNull final List<AppInfo> appInfoList) {
        mAppInfoList.addAll(appInfoList);

        if (mAppsDrawerFragment != null) {
          mAppsDrawerFragment.reloadAppsGridView();
        }
      }
    };

    final AppsDrawerDataLoader dataLoader =
      new AppsDrawerDataLoader(packageManager, dataLoaderListener);
    dataLoader.execute();
  }

  void showFragment() {
    if (isShowing()) {
      return;
    }

    mAppsDrawerFragment = new AppsDrawerFragment();
    mAppsDrawerFragment.setAppInfoList(mAppInfoList);
    final FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
    fragmentTransaction.add(R.id.mainView, mAppsDrawerFragment);
    fragmentTransaction.commit();

    Utils.setStatusBarColor(mDecorView, false);
    Utils.setNavigationBarColor(mDecorView, false);
  }

  void hideFragment() {
    if (!isShowing()) {
      return;
    }

    final FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
    fragmentTransaction.remove(mAppsDrawerFragment);
    fragmentTransaction.commit();
    mAppsDrawerFragment = null;
  }

  boolean isShowing() {
    return mAppsDrawerFragment != null && mAppsDrawerFragment.isAdded();
  }
}
