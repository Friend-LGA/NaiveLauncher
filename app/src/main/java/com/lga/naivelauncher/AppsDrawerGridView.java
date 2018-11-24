package com.lga.naivelauncher;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

final class AppsDrawerGridView extends GridView {
  @NonNull Rect mWindowInsets = new Rect(0, 0, 0, 0);
  @Nullable View mScrollBarView;

  public AppsDrawerGridView(Context context) {
    this(context, null);
  }

  public AppsDrawerGridView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public AppsDrawerGridView(Context context, AttributeSet attrs, int defStyleAttr) {
    this(context, attrs, defStyleAttr, 0);
  }

  public AppsDrawerGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

//  void onDataChanged() {
//    calculateScrollBarViewLayout();
//  }

//  @Override
//  public void setAdapter(@NonNull final ListAdapter adapter) {
//    super.setAdapter(adapter);
//    calculateScrollBarViewLayout();
//  }

  void setScrollBarView(@NonNull final View view) {
    mScrollBarView = view;
  }

  void setWindowInsets(@NonNull final Rect windowInsets) {
    mWindowInsets = windowInsets;
  }

//  void calculateScrollBarViewLayout() {
//    ListAdapter adapter = getAdapter();
//
//    if (mScrollBarView == null || adapter == null) {
//      return;
//    }
//
//    final View itemView = adapter.getView(0, null, this);
//
//    if (itemView == null) {
//      return;
//    }
//
//    itemView.measure(getColumnWidth(), 0);
//
//    final ConstraintLayout.LayoutParams params =
//      (ConstraintLayout.LayoutParams)mScrollBarView.getLayoutParams();
//    final int height = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
//    final int itemHeight = itemView.getMeasuredHeight();
//    final int itemsCount = adapter.getCount();
//    final int columnsCount = getNumColumns();
//    final int contentHeight =
//      (itemsCount / columnsCount + (itemsCount % columnsCount == 0 ? 0 : 1)) * itemHeight;
//    final float heightToContent = (float)height / (float)contentHeight;
//    final int scrollBarHeight = (int)(height * heightToContent);
//
//    mScrollBarView.setX(getMeasuredWidth() - mWindowInsets.right - params.width * 3);
//    mScrollBarView.setY(mWindowInsets.top);
//    mScrollBarView.setVisibility(VISIBLE);
//
//    params.height = 500;
//
//    mScrollBarView.setLayoutParams(params);
//  }

//  @Override
//  protected void layoutChildren() {
//    super.layoutChildren();
//    calculateScrollBarViewLayout();
//  }
//
//  @Override
//  protected void onLayout(boolean changed, int l, int t, int r, int b) {
//    super.onLayout(changed, l, t, r, b);
//    calculateScrollBarViewLayout();
//  }
//
//  @Override
//  public void updateViewLayout(View view, ViewGroup.LayoutParams params) {
//    super.updateViewLayout(view, params);
//    calculateScrollBarViewLayout();
//  }
}
