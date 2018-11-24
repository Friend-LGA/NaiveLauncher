package com.lga.naivelauncher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DesktopFragment extends Fragment {

  @Override
  public void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  @Nullable
  public View onCreateView(@NonNull final LayoutInflater inflater,
                           @Nullable final ViewGroup container,
                           @Nullable final Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    final View view = inflater.inflate(R.layout.fragment_desktop, container, false);
    final Button button = view.findViewById(R.id.appsDrawerButton);

    button.setOnClickListener(new View.OnClickListener() {
      public void onClick(@NonNull final View view) {
        final MainActivity activity = (MainActivity) getActivity();

        if (activity != null) {
          activity.showAppsDrawerFragment();
        }
      }
    });

    return view;
  }
}
