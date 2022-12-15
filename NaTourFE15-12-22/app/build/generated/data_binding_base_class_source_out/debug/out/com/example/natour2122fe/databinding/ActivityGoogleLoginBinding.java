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

public final class ActivityGoogleLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView benvenuto;

  @NonNull
  public final Button btnSignOutGoogle;

  @NonNull
  public final ToolbarBinding include2;

  @NonNull
  public final ImageView ivPersonFoto;

  @NonNull
  public final ConstraintLayout relativeLayout;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextView tvEmail;

  @NonNull
  public final TextView tvFamilyName;

  @NonNull
  public final TextView tvId;

  @NonNull
  public final TextView tvName;

  private ActivityGoogleLoginBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView benvenuto, @NonNull Button btnSignOutGoogle,
      @NonNull ToolbarBinding include2, @NonNull ImageView ivPersonFoto,
      @NonNull ConstraintLayout relativeLayout, @NonNull TextView textView4,
      @NonNull TextView tvEmail, @NonNull TextView tvFamilyName, @NonNull TextView tvId,
      @NonNull TextView tvName) {
    this.rootView = rootView;
    this.benvenuto = benvenuto;
    this.btnSignOutGoogle = btnSignOutGoogle;
    this.include2 = include2;
    this.ivPersonFoto = ivPersonFoto;
    this.relativeLayout = relativeLayout;
    this.textView4 = textView4;
    this.tvEmail = tvEmail;
    this.tvFamilyName = tvFamilyName;
    this.tvId = tvId;
    this.tvName = tvName;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityGoogleLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityGoogleLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_google_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityGoogleLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.benvenuto;
      TextView benvenuto = ViewBindings.findChildViewById(rootView, id);
      if (benvenuto == null) {
        break missingId;
      }

      id = R.id.btnSignOutGoogle;
      Button btnSignOutGoogle = ViewBindings.findChildViewById(rootView, id);
      if (btnSignOutGoogle == null) {
        break missingId;
      }

      id = R.id.include2;
      View include2 = ViewBindings.findChildViewById(rootView, id);
      if (include2 == null) {
        break missingId;
      }
      ToolbarBinding binding_include2 = ToolbarBinding.bind(include2);

      id = R.id.ivPersonFoto;
      ImageView ivPersonFoto = ViewBindings.findChildViewById(rootView, id);
      if (ivPersonFoto == null) {
        break missingId;
      }

      ConstraintLayout relativeLayout = (ConstraintLayout) rootView;

      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      id = R.id.tvEmail;
      TextView tvEmail = ViewBindings.findChildViewById(rootView, id);
      if (tvEmail == null) {
        break missingId;
      }

      id = R.id.tvFamilyName;
      TextView tvFamilyName = ViewBindings.findChildViewById(rootView, id);
      if (tvFamilyName == null) {
        break missingId;
      }

      id = R.id.tvId;
      TextView tvId = ViewBindings.findChildViewById(rootView, id);
      if (tvId == null) {
        break missingId;
      }

      id = R.id.tvName;
      TextView tvName = ViewBindings.findChildViewById(rootView, id);
      if (tvName == null) {
        break missingId;
      }

      return new ActivityGoogleLoginBinding((ConstraintLayout) rootView, benvenuto,
          btnSignOutGoogle, binding_include2, ivPersonFoto, relativeLayout, textView4, tvEmail,
          tvFamilyName, tvId, tvName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}