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
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.natour2122fe.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityInsPhotoBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnInsPhotoPathway;

  @NonNull
  public final ImageView loadPhoto;

  @NonNull
  public final ImageView photoView;

  @NonNull
  public final TextView tvInsPhoto;

  private ActivityInsPhotoBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button btnInsPhotoPathway, @NonNull ImageView loadPhoto,
      @NonNull ImageView photoView, @NonNull TextView tvInsPhoto) {
    this.rootView = rootView;
    this.btnInsPhotoPathway = btnInsPhotoPathway;
    this.loadPhoto = loadPhoto;
    this.photoView = photoView;
    this.tvInsPhoto = tvInsPhoto;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityInsPhotoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityInsPhotoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_ins_photo, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityInsPhotoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnInsPhotoPathway;
      Button btnInsPhotoPathway = ViewBindings.findChildViewById(rootView, id);
      if (btnInsPhotoPathway == null) {
        break missingId;
      }

      id = R.id.loadPhoto;
      ImageView loadPhoto = ViewBindings.findChildViewById(rootView, id);
      if (loadPhoto == null) {
        break missingId;
      }

      id = R.id.photoView;
      ImageView photoView = ViewBindings.findChildViewById(rootView, id);
      if (photoView == null) {
        break missingId;
      }

      id = R.id.tvInsPhoto;
      TextView tvInsPhoto = ViewBindings.findChildViewById(rootView, id);
      if (tvInsPhoto == null) {
        break missingId;
      }

      return new ActivityInsPhotoBinding((ConstraintLayout) rootView, btnInsPhotoPathway, loadPhoto,
          photoView, tvInsPhoto);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
