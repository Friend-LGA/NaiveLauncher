package com.lga.naivelauncher;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowInsets;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SearchView;

final class SettingsView extends LinearLayout {
  @NonNull Rect mWindowInsets = new Rect(0, 0, 0, 0);

  public SettingsView(Context context) {
    this(context, null);
  }

  public SettingsView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SettingsView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    setOnApplyWindowInsetsListener(new OnApplyWindowInsetsListener() {
      @Override
      public WindowInsets onApplyWindowInsets(View view, WindowInsets insets) {
        view.setPadding(
          view.getPaddingLeft() + insets.getSystemWindowInsetLeft(),
          view.getPaddingTop() + insets.getSystemWindowInsetTop(),
          view.getPaddingRight() + insets.getSystemWindowInsetRight(),
          view.getPaddingBottom());

        mWindowInsets = new Rect(
          insets.getSystemWindowInsetLeft(),
          insets.getSystemWindowInsetTop(),
          insets.getSystemWindowInsetRight(),
          insets.getSystemWindowInsetBottom());

        final View listView = findViewById(R.id.settingsListView);

        listView.setPadding(
          listView.getPaddingLeft(),
          listView.getPaddingTop(),
          listView.getPaddingRight(),
          listView.getPaddingBottom() + insets.getSystemWindowInsetBottom());

        return insets;
      }
    });
  }

  @NonNull
  Rect getWindowInsets() {
    return mWindowInsets;
  }
}
