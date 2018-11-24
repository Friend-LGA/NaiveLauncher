package com.lga.naivelauncher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class AppsDrawerDataLoader extends AsyncTask<Void, Void, String> {
  private @NonNull final PackageManager mPackageManager;
  private @NonNull final Listener mListener;
  private @NonNull final List<AppInfo> mAppInfoList = new ArrayList<>();;

  interface Listener {
    void onFinished(@NonNull final List<AppInfo> appInfoList);
  }

  AppsDrawerDataLoader(@NonNull final PackageManager packageManager,
                       @NonNull final Listener listener) {
    mPackageManager = packageManager;
    mListener = listener;
  }

  @Override
  @NonNull
  protected String doInBackground(Void... Params) {
    final Intent intent = new Intent(Intent.ACTION_MAIN, null);
    intent.addCategory(Intent.CATEGORY_LAUNCHER);

    final List<ResolveInfo> appsList = mPackageManager.queryIntentActivities(intent, 0);

    for (final ResolveInfo resolveInfo : appsList) {
      final CharSequence label = resolveInfo.loadLabel(mPackageManager);
      final CharSequence packageName = resolveInfo.activityInfo.packageName;
      final Drawable icon = resolveInfo.activityInfo.loadIcon(mPackageManager);
      final AppInfo appInfo = new AppInfo(label, packageName, icon);

      mAppInfoList.add(appInfo);
    }

    Collections.sort(mAppInfoList);

    return "Success";
  }

  @Override
  protected void onPostExecute(final String result) {
    super.onPostExecute(result);
    mListener.onFinished(mAppInfoList);
  }
}
