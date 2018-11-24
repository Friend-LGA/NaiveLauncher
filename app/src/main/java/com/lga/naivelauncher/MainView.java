package com.lga.naivelauncher;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.GridView;

final class MainView extends FrameLayout {
  public MainView(Context context) {
    this(context, null);
  }

  public MainView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public MainView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    setOnHierarchyChangeListener(new OnHierarchyChangeListener() {
      @Override
      public void onChildViewAdded(@NonNull final View parent, @NonNull final View child) {
        requestApplyInsets();
      }

      @Override
      public void onChildViewRemoved(@NonNull final View parent, @NonNull final View child) {
        // no-op
      }
    });
  }
}
