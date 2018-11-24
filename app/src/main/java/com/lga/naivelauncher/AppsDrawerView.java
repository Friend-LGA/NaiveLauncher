package com.lga.naivelauncher;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowInsets;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.SearchView;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

final class AppsDrawerView extends ConstraintLayout {
  private int mDefaultSearchViewTopMargin;
  private Rect mDefaultGridViewPadding;

  public AppsDrawerView(Context context) {
    this(context, null);
  }

  public AppsDrawerView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public AppsDrawerView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

//    setOnApplyWindowInsetsListener(new OnApplyWindowInsetsListener() {
//      @Override
//      public WindowInsets onApplyWindowInsets(@NonNull final View view,
//                                              @NonNull final WindowInsets insets) {
//        final int orientation = getResources().getConfiguration().orientation;
//        int viewWidth;
//
//        if (orientation == ORIENTATION_LANDSCAPE) {
//          viewWidth = Math.max(view.getMeasuredWidth(), view.getMeasuredHeight());
//        } else {
//          viewWidth = Math.min(view.getMeasuredWidth(), view.getMeasuredHeight());
//        }
//
//        calculateSearchViewLayout(viewWidth, insets);
//        calculateGridViewLayout(viewWidth, insets);
//
//        return insets;
//      }
//    });

//    final AppsDrawerGridView gridView = findViewById(R.id.appsDrawerGridView);

//    gridView.addOnLayoutChangeListener(new OnLayoutChangeListener() {
//      @Override
//      public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//        gridView.calculateScrollBarViewLayout();
//      }
//    });
//
//    gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
//      public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        // TODO Auto-generated method stub
//      }
//
//      public void onScrollStateChanged(AbsListView view, int scrollState) {
//        // TODO Auto-generated method stub
//      }
//    });
  }

//  private void calculateSearchViewLayout(final int parentViewWidth, @NonNull final WindowInsets insets) {
//    final SearchView searchView = findViewById(R.id.appsDrawerSearchView);
//    final LayoutParams params = (LayoutParams)searchView.getLayoutParams();
//
//    if (mDefaultSearchViewTopMargin == 0) {
//      mDefaultSearchViewTopMargin = params.topMargin;
//    }
//
//    final int availableWidth =
//      parentViewWidth - (insets.getSystemWindowInsetLeft() + insets.getSystemWindowInsetRight());
//
//    final int searchViewWidth = (int)(availableWidth * 0.9);
//    final int searchViewMargin = (availableWidth - searchViewWidth) / 2;
//
//    params.topMargin =
//      mDefaultSearchViewTopMargin + insets.getSystemWindowInsetTop();
//
//    params.leftMargin =
//      insets.getSystemWindowInsetLeft() + searchViewMargin;
//
//    params.rightMargin =
//      insets.getSystemWindowInsetRight() + searchViewMargin;
//
//    searchView.setLayoutParams(params);
//  }
//
//  private void calculateGridViewLayout(final int parentViewWidth, @NonNull final WindowInsets insets) {
//    final AppsDrawerGridView gridView = findViewById(R.id.appsDrawerGridView);
//
//    if (mDefaultGridViewPadding == null) {
//      mDefaultGridViewPadding = new Rect(
//        gridView.getPaddingLeft(),
//        gridView.getPaddingTop(),
//        gridView.getPaddingRight(),
//        gridView.getPaddingBottom()
//      );
//    }
//
//    gridView.setWindowInsets(new Rect(
//      insets.getSystemWindowInsetLeft(),
//      insets.getSystemWindowInsetTop(),
//      insets.getSystemWindowInsetRight(),
//      insets.getSystemWindowInsetBottom()
//    ));
//
//    final int iconWidth = Utils.convertDpToPixels(48, getResources().getDisplayMetrics());
//    final int expectedColumnWidth = (int) (iconWidth * 1.6); // Magic Number !!!
//    final int expectedPadding = (expectedColumnWidth - iconWidth) / 2;
//
//    final int widthWithPadding = parentViewWidth -
//      (insets.getSystemWindowInsetLeft() + insets.getSystemWindowInsetRight()) -
//      (expectedPadding * 2);
//
//    final int numberOfColumns = widthWithPadding / expectedColumnWidth;
//    final int columnWidth = (parentViewWidth + iconWidth) / (numberOfColumns + 1);
//    final int padding = (columnWidth - iconWidth) / 2;
//
//    gridView.setNumColumns(numberOfColumns);
//
//    gridView.setPadding(
//      mDefaultGridViewPadding.left + insets.getSystemWindowInsetLeft() + padding,
//      mDefaultGridViewPadding.top + insets.getSystemWindowInsetTop(),
//      mDefaultGridViewPadding.right + insets.getSystemWindowInsetRight() + padding,
//      mDefaultGridViewPadding.bottom + insets.getSystemWindowInsetBottom()
//    );
//  }
}
