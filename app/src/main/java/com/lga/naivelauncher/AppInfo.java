package com.lga.naivelauncher;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

final class AppInfo implements Comparable<AppInfo> {
  private @NonNull final CharSequence mLabel;
  private @NonNull final CharSequence mPackageName;
  private @NonNull final Drawable mIcon;

  AppInfo(@NonNull final CharSequence label,
          @NonNull final CharSequence packageName,
          @NonNull final Drawable icon) {
    mLabel = label;
    mPackageName = packageName;
    mIcon = icon;
  }

  @NonNull
  CharSequence getLabel() {
    return mLabel;
  }

  @NonNull
  CharSequence getPackageName() {
    return mPackageName;
  }

  @NonNull
  Drawable getIcon() {
    return mIcon;
  }

  @Override
  public int compareTo(final AppInfo other) {
    return mLabel.toString().compareTo(other.mLabel.toString());
  }
}
