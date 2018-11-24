package com.lga.naivelauncher;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public final class SettingsGeneralFragment extends PreferenceFragmentCompat {
  @NonNull Rect mWindowInsets = new Rect(0, 0, 0, 0);

  @Override
  public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    setPreferencesFromResource(R.xml.pref_general, rootKey);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final View view = super.onCreateView(inflater, container, savedInstanceState);

    if (view != null) {
      view.setBackgroundColor(getResources().getColor(R.color.white));

      final View listView = getListView();

      listView.setPadding(
        listView.getPaddingLeft(),
        listView.getPaddingTop(),
        listView.getPaddingRight(),
        listView.getPaddingBottom() + mWindowInsets.bottom);
    }

    return view;
  }

  @Override
  public void onResume() {
    super.onResume();

    Activity activity = getActivity();

    if (activity != null) {
      activity.setTitle("Test");
    }
  }

  void setWindowInsets(@NonNull final Rect windowInsets) {
    mWindowInsets = windowInsets;
  }

//  @Override
//  public void setPreferenceScreen(PreferenceScreen preferenceScreen) {
//    super.setPreferenceScreen(preferenceScreen);
//
//    if (preferenceScreen != null) {
//      for (int i = 0; i < preferenceScreen.getPreferenceCount(); i++)
//        preferenceScreen.getPreference(i).setIconSpaceReserved(false);
//    }
//  }
}
