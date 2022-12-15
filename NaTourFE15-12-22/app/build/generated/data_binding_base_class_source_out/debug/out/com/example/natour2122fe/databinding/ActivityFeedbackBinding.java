// Generated by view binder compiler. Do not edit!
package com.example.natour2122fe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.natour2122fe.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityFeedbackBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnInsFeedback;

  @NonNull
  public final RecyclerView feedbackListRecyclerView;

  @NonNull
  public final ImageView imageView3;

  @NonNull
  public final ToolbarBinding include4;

  @NonNull
  public final TextView textView21;

  @NonNull
  public final TextView tvPathwayName;

  private ActivityFeedbackBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button btnInsFeedback, @NonNull RecyclerView feedbackListRecyclerView,
      @NonNull ImageView imageView3, @NonNull ToolbarBinding include4, @NonNull TextView textView21,
      @NonNull TextView tvPathwayName) {
    this.rootView = rootView;
    this.btnInsFeedback = btnInsFeedback;
    this.feedbackListRecyclerView = feedbackListRecyclerView;
    this.imageView3 = imageView3;
    this.include4 = include4;
    this.textView21 = textView21;
    this.tvPathwayName = tvPathwayName;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityFeedbackBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityFeedbackBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_feedback, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityFeedbackBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnInsFeedback;
      Button btnInsFeedback = ViewBindings.findChildViewById(rootView, id);
      if (btnInsFeedback == null) {
        break missingId;
      }

      id = R.id.feedbackList_recyclerView;
      RecyclerView feedbackListRecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (feedbackListRecyclerView == null) {
        break missingId;
      }

      id = R.id.imageView3;
      ImageView imageView3 = ViewBindings.findChildViewById(rootView, id);
      if (imageView3 == null) {
        break missingId;
      }

      id = R.id.include4;
      View include4 = ViewBindings.findChildViewById(rootView, id);
      if (include4 == null) {
        break missingId;
      }
      ToolbarBinding binding_include4 = ToolbarBinding.bind(include4);

      id = R.id.textView21;
      TextView textView21 = ViewBindings.findChildViewById(rootView, id);
      if (textView21 == null) {
        break missingId;
      }

      id = R.id.tvPathwayName;
      TextView tvPathwayName = ViewBindings.findChildViewById(rootView, id);
      if (tvPathwayName == null) {
        break missingId;
      }

      return new ActivityFeedbackBinding((ConstraintLayout) rootView, btnInsFeedback,
          feedbackListRecyclerView, imageView3, binding_include4, textView21, tvPathwayName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
