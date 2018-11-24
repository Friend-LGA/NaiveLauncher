package com.lga.naivelauncher;

import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

final class Utils {
  static private @NonNull Rect mWindowInsets = new Rect(0, 0, 0, 0);

  /**
   * This method converts dp unit to equivalent pixels, depending on device density.
   *
   * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
   * @param metrics Device specific display metrics
   * @return A int value to represent px equivalent to dp depending on device density
   */
  static int convertDpToPixels(final float dp, @NonNull final DisplayMetrics metrics) {
    return (int) (dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
  }

  /**
   * This method converts device specific pixels to density independent pixels.
   *
   * @param px A value in px (pixels) unit. Which we need to convert into db
   * @param metrics Device specific display metrics
   * @return A float value to represent dp equivalent to px value
   */
  static float convertPixelsToDp(final int px, @NonNull final DisplayMetrics metrics) {
    return px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
  }

  static Point getDisplaySize(@NonNull final Display display) {
    Point size = new Point();
    display.getSize(size);
    return size;
  }

  static void setStatusBarColor(@NonNull final View decorView, final boolean isLight) {
    int flags = decorView.getSystemUiVisibility();

    if (isLight) {
      flags &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
    } else {
      flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
    }

    decorView.setSystemUiVisibility(flags);
  }

  static void setNavigationBarColor(@NonNull final View decorView, final boolean isLight) {
    int flags = decorView.getSystemUiVisibility();

    if (isLight) {
      flags &= ~View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
    } else {
      flags |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
    }

    decorView.setSystemUiVisibility(flags);
  }

  static void setFullscreenLayout(@NonNull final Window window) {
    window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

    window.getDecorView().setSystemUiVisibility(
      View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
      View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
      View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
  }

  static void extendPadding(@NonNull final View view,
                            final int left,
                            final int top,
                            final int right,
                            final int bottom) {
    view.setPadding(
      view.getPaddingLeft() + left,
      view.getPaddingTop() + top,
      view.getPaddingRight() + right,
      view.getPaddingBottom() + bottom);
  }
}
