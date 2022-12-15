// Generated by view binder compiler. Do not edit!
package com.example.natour2122fe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.natour2122fe.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityInterestPointsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnInsPoint;

  @NonNull
  public final TextView textView20;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final ToolbarBinding tool;

  @NonNull
  public final TextView tvPathwayName;

  private ActivityInterestPointsBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button btnInsPoint, @NonNull TextView textView20, @NonNull TextView textView3,
      @NonNull ToolbarBinding tool, @NonNull TextView tvPathwayName) {
    this.rootView = rootView;
    this.btnInsPoint = btnInsPoint;
    this.textView20 = textView20;
    this.textView3 = textView3;
    this.tool = tool;
    this.tvPathwayName = tvPathwayName;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityInterestPointsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityInterestPointsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_interest_points, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityInterestPointsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnInsPoint;
      Button btnInsPoint = ViewBindings.findChildViewById(rootView, id);
      if (btnInsPoint == null) {
        break missingId;
      }

      id = R.id.textView20;
      TextView textView20 = ViewBindings.findChildViewById(rootView, id);
      if (textView20 == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      id = R.id.tool;
      View tool = ViewBindings.findChildViewById(rootView, id);
      if (tool == null) {
        break missingId;
      }
      ToolbarBinding binding_tool = ToolbarBinding.bind(tool);

      id = R.id.tvPathwayName;
      TextView tvPathwayName = ViewBindings.findChildViewById(rootView, id);
      if (tvPathwayName == null) {
        break missingId;
      }

      return new ActivityInterestPointsBinding((ConstraintLayout) rootView, btnInsPoint, textView20,
          textView3, binding_tool, tvPathwayName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
