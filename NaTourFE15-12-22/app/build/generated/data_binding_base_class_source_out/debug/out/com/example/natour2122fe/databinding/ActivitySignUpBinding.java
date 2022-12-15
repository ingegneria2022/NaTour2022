// Generated by view binder compiler. Do not edit!
package com.example.natour2122fe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.natour2122fe.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySignUpBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnLoadPhoto;

  @NonNull
  public final Button btnSignin;

  @NonNull
  public final Button btnSignup;

  @NonNull
  public final EditText etEmail;

  @NonNull
  public final EditText etEmailBis;

  @NonNull
  public final EditText etName;

  @NonNull
  public final EditText etPassword;

  @NonNull
  public final EditText etPasswordBis;

  @NonNull
  public final EditText etSurname;

  @NonNull
  public final EditText etUsername;

  @NonNull
  public final ImageView ivPersonPhoto;

  private ActivitySignUpBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnLoadPhoto,
      @NonNull Button btnSignin, @NonNull Button btnSignup, @NonNull EditText etEmail,
      @NonNull EditText etEmailBis, @NonNull EditText etName, @NonNull EditText etPassword,
      @NonNull EditText etPasswordBis, @NonNull EditText etSurname, @NonNull EditText etUsername,
      @NonNull ImageView ivPersonPhoto) {
    this.rootView = rootView;
    this.btnLoadPhoto = btnLoadPhoto;
    this.btnSignin = btnSignin;
    this.btnSignup = btnSignup;
    this.etEmail = etEmail;
    this.etEmailBis = etEmailBis;
    this.etName = etName;
    this.etPassword = etPassword;
    this.etPasswordBis = etPasswordBis;
    this.etSurname = etSurname;
    this.etUsername = etUsername;
    this.ivPersonPhoto = ivPersonPhoto;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySignUpBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySignUpBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_sign_up, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySignUpBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnLoadPhoto;
      Button btnLoadPhoto = ViewBindings.findChildViewById(rootView, id);
      if (btnLoadPhoto == null) {
        break missingId;
      }

      id = R.id.btnSignin;
      Button btnSignin = ViewBindings.findChildViewById(rootView, id);
      if (btnSignin == null) {
        break missingId;
      }

      id = R.id.btnSignup;
      Button btnSignup = ViewBindings.findChildViewById(rootView, id);
      if (btnSignup == null) {
        break missingId;
      }

      id = R.id.etEmail;
      EditText etEmail = ViewBindings.findChildViewById(rootView, id);
      if (etEmail == null) {
        break missingId;
      }

      id = R.id.etEmailBis;
      EditText etEmailBis = ViewBindings.findChildViewById(rootView, id);
      if (etEmailBis == null) {
        break missingId;
      }

      id = R.id.etName;
      EditText etName = ViewBindings.findChildViewById(rootView, id);
      if (etName == null) {
        break missingId;
      }

      id = R.id.etPassword;
      EditText etPassword = ViewBindings.findChildViewById(rootView, id);
      if (etPassword == null) {
        break missingId;
      }

      id = R.id.etPasswordBis;
      EditText etPasswordBis = ViewBindings.findChildViewById(rootView, id);
      if (etPasswordBis == null) {
        break missingId;
      }

      id = R.id.etSurname;
      EditText etSurname = ViewBindings.findChildViewById(rootView, id);
      if (etSurname == null) {
        break missingId;
      }

      id = R.id.etUsername;
      EditText etUsername = ViewBindings.findChildViewById(rootView, id);
      if (etUsername == null) {
        break missingId;
      }

      id = R.id.ivPersonPhoto;
      ImageView ivPersonPhoto = ViewBindings.findChildViewById(rootView, id);
      if (ivPersonPhoto == null) {
        break missingId;
      }

      return new ActivitySignUpBinding((ConstraintLayout) rootView, btnLoadPhoto, btnSignin,
          btnSignup, etEmail, etEmailBis, etName, etPassword, etPasswordBis, etSurname, etUsername,
          ivPersonPhoto);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}