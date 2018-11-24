package com.lga.naivelauncher;

import android.app.Activity;
import android.database.DataSetObserver;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.view.View.VISIBLE;

public class AppsDrawerFragment extends Fragment {
  private @NonNull List<AppInfo> mAppInfoList = new ArrayList<>();
  private @Nullable AppsDrawerGridViewAdapter mGridViewAdapter;
  private int mDefaultSearchViewTopMargin;
  private @Nullable Rect mDefaultGridViewPadding;
  private @Nullable WindowInsets mWindowInsets;

  @Override
  public void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(@NonNull final LayoutInflater inflater,
                           @Nullable final ViewGroup container,
                           @Nullable final Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    final View view = inflater.inflate(R.layout.fragment_apps_drawer, container, false);
    final SearchView searchView = view.findViewById(R.id.appsDrawerSearchView);
    final AppsDrawerGridView gridView = view.findViewById(R.id.appsDrawerGridView);
    final View scrollBarView = view.findViewById(R.id.appsDrawerScrollBarView);

    mGridViewAdapter = new AppsDrawerGridViewAdapter(gridView.getContext(), mAppInfoList);
    gridView.setAdapter(mGridViewAdapter);
    gridView.setScrollBarView(view.findViewById(R.id.appsDrawerScrollBarView));

    view.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
      @Override
      public WindowInsets onApplyWindowInsets(@NonNull final View view,
                                              @NonNull final WindowInsets insets) {
        mWindowInsets = insets;
        final int orientation = getResources().getConfiguration().orientation;
        int viewWidth;

        if (orientation == ORIENTATION_LANDSCAPE) {
          viewWidth = Math.max(view.getMeasuredWidth(), view.getMeasuredHeight());
        } else {
          viewWidth = Math.min(view.getMeasuredWidth(), view.getMeasuredHeight());
        }

        calculateSearchViewLayout(searchView, viewWidth, insets);
        calculateGridViewLayout(gridView, scrollBarView, viewWidth, insets);

        return insets;
      }
    });

//    gridView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//      @Override
//      public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//        if (mWindowInsets != null) {
//          calculateScrollBarViewLayout(
//            scrollBarView,
//            mWindowInsets,
//            gridView.getAdapter(),
//            gridView.getMeasuredWidth(),
//            gridView.getMeasuredHeight(),
//            gridView.getColumnWidth(),
//            gridView.getNumColumns(),
//            gridView.getPaddingTop(),
//            gridView.getPaddingBottom()
//          );
//        }
//      }
//    });

//    mGridViewAdapter.registerDataSetObserver(new DataSetObserver() {
//      @Override
//      public void onChanged() {
//        gridView.onDataChanged();
//      }
//    });

    return view;
  }

  void setAppInfoList(@NonNull final List<AppInfo> appInfoList) {
    mAppInfoList = appInfoList;

    if (mGridViewAdapter != null) {
      mGridViewAdapter.clear();
      mGridViewAdapter.addAll(mAppInfoList);
      reloadAppsGridView();
    }
  }

  void reloadAppsGridView() {
    if (mGridViewAdapter == null) {
      return;
    }

    mGridViewAdapter.notifyDataSetChanged();

    final View view = getView();
    final AppsDrawerGridView gridView = view.findViewById(R.id.appsDrawerGridView);
    final View scrollBarView = view.findViewById(R.id.appsDrawerScrollBarView);

    calculateScrollBarViewLayout(
      scrollBarView,
      mWindowInsets,
      gridView.getAdapter(),
      gridView.getMeasuredWidth(),
      gridView.getMeasuredHeight(),
      gridView.getColumnWidth(),
      gridView.getNumColumns(),
      gridView.getPaddingTop(),
      gridView.getPaddingBottom()
    );
  }

  private void calculateSearchViewLayout(@NonNull final SearchView searchView, final int parentViewWidth, @NonNull final WindowInsets insets) {
    final ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams)searchView.getLayoutParams();

    if (mDefaultSearchViewTopMargin == 0) {
      mDefaultSearchViewTopMargin = params.topMargin;
    }

    final int availableWidth =
      parentViewWidth - (insets.getSystemWindowInsetLeft() + insets.getSystemWindowInsetRight());

    final int searchViewWidth = (int)(availableWidth * 0.9);
    final int searchViewMargin = (availableWidth - searchViewWidth) / 2;

    params.topMargin =
      mDefaultSearchViewTopMargin + insets.getSystemWindowInsetTop();

    params.leftMargin =
      insets.getSystemWindowInsetLeft() + searchViewMargin;

    params.rightMargin =
      insets.getSystemWindowInsetRight() + searchViewMargin;

    searchView.setLayoutParams(params);
  }

  private void calculateGridViewLayout(@NonNull final AppsDrawerGridView gridView,
                                       @NonNull final View scrollBarView,
                                       final int parentViewWidth,
                                       @NonNull final WindowInsets insets) {
    if (mDefaultGridViewPadding == null) {
      mDefaultGridViewPadding = new Rect(
        gridView.getPaddingLeft(),
        gridView.getPaddingTop(),
        gridView.getPaddingRight(),
        gridView.getPaddingBottom()
      );
    }

    gridView.setWindowInsets(new Rect(
      insets.getSystemWindowInsetLeft(),
      insets.getSystemWindowInsetTop(),
      insets.getSystemWindowInsetRight(),
      insets.getSystemWindowInsetBottom()
    ));

    final int iconWidth = Utils.convertDpToPixels(48, getResources().getDisplayMetrics());
    final int expectedColumnWidth = (int) (iconWidth * 1.6); // Magic Number !!!
    final int expectedPadding = (expectedColumnWidth - iconWidth) / 2;

    final int widthWithPadding = parentViewWidth -
      (insets.getSystemWindowInsetLeft() + insets.getSystemWindowInsetRight()) -
      (expectedPadding * 2);

    final int numberOfColumns = widthWithPadding / expectedColumnWidth;
    final int columnWidth = (parentViewWidth + iconWidth) / (numberOfColumns + 1);
    final int padding = (columnWidth - iconWidth) / 2;

    gridView.setNumColumns(numberOfColumns);

    gridView.setPadding(
      mDefaultGridViewPadding.left + insets.getSystemWindowInsetLeft() + padding,
      mDefaultGridViewPadding.top + insets.getSystemWindowInsetTop(),
      mDefaultGridViewPadding.right + insets.getSystemWindowInsetRight() + padding,
      mDefaultGridViewPadding.bottom + insets.getSystemWindowInsetBottom()
    );

//    calculateScrollBarViewLayout(
//      scrollBarView,
//      insets,
//      gridView.getAdapter(),
//      gridView.getMeasuredWidth(),
//      gridView.getMeasuredHeight(),
//      columnWidth,
//      numberOfColumns,
//      mDefaultGridViewPadding.top + insets.getSystemWindowInsetTop(),
//      mDefaultGridViewPadding.bottom + insets.getSystemWindowInsetBottom()
//    );
  }

  private void calculateScrollBarViewLayout(@NonNull final View scrollBarView,
                                            @NonNull final WindowInsets insets,
                                            @NonNull final ListAdapter gridViewAdapter,
                                            final int gridViewWidth,
                                            final int gridViewHeight,
                                            final int gridViewColumnWidth,
                                            final int gridViewNumberOfColumns,
                                            final int gridViewPaddingTop,
                                            final int gridViewPaddingBottom) {
    View itemView = getLayoutInflater().inflate(R.layout.item_app, null, false);
    itemView.measure(gridViewColumnWidth, 0);

    final ConstraintLayout.LayoutParams params =
      (ConstraintLayout.LayoutParams)scrollBarView.getLayoutParams();
    final int height = gridViewHeight - gridViewPaddingTop - gridViewPaddingBottom;
    final int itemHeight = itemView.getMeasuredHeight();
    final int itemsCount = gridViewAdapter.getCount();
    final int contentHeight =
      (itemsCount / gridViewNumberOfColumns + (itemsCount % gridViewNumberOfColumns == 0 ? 0 : 1)) * itemHeight;
    final float heightToContent = (float)height / (float)contentHeight;

    scrollBarView.setX(gridViewWidth - insets.getSystemWindowInsetRight() - params.width * 3);
    scrollBarView.setY(insets.getSystemWindowInsetTop());
    scrollBarView.setVisibility(VISIBLE);

    params.height = (int)(height * heightToContent);

    scrollBarView.setLayoutParams(params);
  }
}
