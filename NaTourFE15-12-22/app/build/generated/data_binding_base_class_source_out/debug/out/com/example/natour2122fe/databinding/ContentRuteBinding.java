// Generated by view binder compiler. Do not edit!
package com.example.natour2122fe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.natour2122fe.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ContentRuteBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final Button btnFeedback;

  @NonNull
  public final Button btnInsInterestingPoints;

  @NonNull
  public final Button btnPhotoPathway;

  @NonNull
  public final Button btnReportPathway;

  @NonNull
  public final TextView etPathwayDescription;

  @NonNull
  public final TextView etPathwayDifficulty;

  @NonNull
  public final TextView etPathwayDuration;

  @NonNull
  public final TextView etPathwayName;

  @NonNull
  public final ToolbarBinding include3;

  @NonNull
  public final TextView textView5;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final TextView tvPathwaySignaling;

  private ContentRuteBinding(@NonNull CoordinatorLayout rootView, @NonNull Button btnFeedback,
      @NonNull Button btnInsInterestingPoints, @NonNull Button btnPhotoPathway,
      @NonNull Button btnReportPathway, @NonNull TextView etPathwayDescription,
      @NonNull TextView etPathwayDifficulty, @NonNull TextView etPathwayDuration,
      @NonNull TextView etPathwayName, @NonNull ToolbarBinding include3,
      @NonNull TextView textView5, @NonNull TextView textView6, @NonNull TextView textView7,
      @NonNull TextView tvPathwaySignaling) {
    this.rootView = rootView;
    this.btnFeedback = btnFeedback;
    this.btnInsInterestingPoints = btnInsInterestingPoints;
    this.btnPhotoPathway = btnPhotoPathway;
    this.btnReportPathway = btnReportPathway;
    this.etPathwayDescription = etPathwayDescription;
    this.etPathwayDifficulty = etPathwayDifficulty;
    this.etPathwayDuration = etPathwayDuration;
    this.etPathwayName = etPathwayName;
    this.include3 = include3;
    this.textView5 = textView5;
    this.textView6 = textView6;
    this.textView7 = textView7;
    this.tvPathwaySignaling = tvPathwaySignaling;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ContentRuteBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ContentRuteBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.content_rute, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ContentRuteBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnFeedback;
      Button btnFeedback = ViewBindings.findChildViewById(rootView, id);
      if (btnFeedback == null) {
        break missingId;
      }

      id = R.id.btnInsInterestingPoints;
      Button btnInsInterestingPoints = ViewBindings.findChildViewById(rootView, id);
      if (btnInsInterestingPoints == null) {
        break missingId;
      }

      id = R.id.btnPhotoPathway;
      Button btnPhotoPathway = ViewBindings.findChildViewById(rootView, id);
      if (btnPhotoPathway == null) {
        break missingId;
      }

      id = R.id.btnReportPathway;
      Button btnReportPathway = ViewBindings.findChildViewById(rootView, id);
      if (btnReportPathway == null) {
        break missingId;
      }

      id = R.id.etPathwayDescription;
      TextView etPathwayDescription = ViewBindings.findChildViewById(rootView, id);
      if (etPathwayDescription == null) {
        break missingId;
      }

      id = R.id.etPathwayDifficulty;
      TextView etPathwayDifficulty = ViewBindings.findChildViewById(rootView, id);
      if (etPathwayDifficulty == null) {
        break missingId;
      }

      id = R.id.etPathwayDuration;
      TextView etPathwayDuration = ViewBindings.findChildViewById(rootView, id);
      if (etPathwayDuration == null) {
        break missingId;
      }

      id = R.id.etPathwayName;
      TextView etPathwayName = ViewBindings.findChildViewById(rootView, id);
      if (etPathwayName == null) {
        break missingId;
      }

      id = R.id.include3;
      View include3 = ViewBindings.findChildViewById(rootView, id);
      if (include3 == null) {
        break missingId;
      }
      ToolbarBinding binding_include3 = ToolbarBinding.bind(include3);

      id = R.id.textView5;
      TextView textView5 = ViewBindings.findChildViewById(rootView, id);
      if (textView5 == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = ViewBindings.findChildViewById(rootView, id);
      if (textView7 == null) {
        break missingId;
      }

      id = R.id.tvPathwaySignaling;
      TextView tvPathwaySignaling = ViewBindings.findChildViewById(rootView, id);
      if (tvPathwaySignaling == null) {
        break missingId;
      }

      return new ContentRuteBinding((CoordinatorLayout) rootView, btnFeedback,
          btnInsInterestingPoints, btnPhotoPathway, btnReportPathway, etPathwayDescription,
          etPathwayDifficulty, etPathwayDuration, etPathwayName, binding_include3, textView5,
          textView6, textView7, tvPathwaySignaling);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}