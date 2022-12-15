// Generated by view binder compiler. Do not edit!
package com.example.natour2122fe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public final class ActivityInsFeedbackBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnInsFeedback;

  @NonNull
  public final EditText etFeedbackDescription;

  @NonNull
  public final Spinner spFeedbackStars;

  @NonNull
  public final TextView textDescription;

  @NonNull
  public final TextView textStars;

  @NonNull
  public final TextView textView13;

  private ActivityInsFeedbackBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button btnInsFeedback, @NonNull EditText etFeedbackDescription,
      @NonNull Spinner spFeedbackStars, @NonNull TextView textDescription,
      @NonNull TextView textStars, @NonNull TextView textView13) {
    this.rootView = rootView;
    this.btnInsFeedback = btnInsFeedback;
    this.etFeedbackDescription = etFeedbackDescription;
    this.spFeedbackStars = spFeedbackStars;
    this.textDescription = textDescription;
    this.textStars = textStars;
    this.textView13 = textView13;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityInsFeedbackBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityInsFeedbackBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_ins_feedback, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityInsFeedbackBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnInsFeedback;
      Button btnInsFeedback = ViewBindings.findChildViewById(rootView, id);
      if (btnInsFeedback == null) {
        break missingId;
      }

      id = R.id.etFeedbackDescription;
      EditText etFeedbackDescription = ViewBindings.findChildViewById(rootView, id);
      if (etFeedbackDescription == null) {
        break missingId;
      }

      id = R.id.spFeedbackStars;
      Spinner spFeedbackStars = ViewBindings.findChildViewById(rootView, id);
      if (spFeedbackStars == null) {
        break missingId;
      }

      id = R.id.textDescription;
      TextView textDescription = ViewBindings.findChildViewById(rootView, id);
      if (textDescription == null) {
        break missingId;
      }

      id = R.id.textStars;
      TextView textStars = ViewBindings.findChildViewById(rootView, id);
      if (textStars == null) {
        break missingId;
      }

      id = R.id.textView13;
      TextView textView13 = ViewBindings.findChildViewById(rootView, id);
      if (textView13 == null) {
        break missingId;
      }

      return new ActivityInsFeedbackBinding((ConstraintLayout) rootView, btnInsFeedback,
          etFeedbackDescription, spFeedbackStars, textDescription, textStars, textView13);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
