package com.lga.naivelauncher;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

final class AppsDrawerGridViewAdapter extends ArrayAdapter<AppInfo> {
  private @NonNull final LayoutInflater mLayoutInflater;

  AppsDrawerGridViewAdapter(@NonNull final Context context,
                            @NonNull final List<AppInfo> appInfoList) {
    super(context, R.layout.item_app, appInfoList);

    mLayoutInflater = LayoutInflater.from(context);
  }

  @Override
  @NonNull
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    if (convertView == null) {
      convertView = mLayoutInflater.inflate(R.layout.item_app, parent, false);
    }

    final AppInfo appInfo = getItem(position);

    if (appInfo != null) {
      final ImageView appIcon = convertView.findViewById(R.id.appIcon);
      final TextView appLabel = convertView.findViewById(R.id.appLabel);

      appIcon.setImageDrawable(appInfo.getIcon());
      appLabel.setText(appInfo.getLabel());
    }

    return convertView;
  }
}
