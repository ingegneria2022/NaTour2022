// Generated by view binder compiler. Do not edit!
package com.example.natour2122fe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import com.example.natour2122fe.R;
import java.lang.NullPointerException;
import java.lang.Override;

public final class ActivityRuteDetailsScrBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  private ActivityRuteDetailsScrBinding(@NonNull CoordinatorLayout rootView) {
    this.rootView = rootView;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRuteDetailsScrBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRuteDetailsScrBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_rute_details_scr, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRuteDetailsScrBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    return new ActivityRuteDetailsScrBinding((CoordinatorLayout) rootView);
  }
}
