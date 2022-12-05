package com.example.natour2122fe.login;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.example.natour2122fe.HomeActivity;
import com.example.natour2122fe.MainActivity;

import java.util.ArrayList;
import java.util.Locale;


public class AmplifyCognito {
    private final Context context;

    public AmplifyCognito(Context context) {
        this.context = context;
    }

    public void signUp(String name, String surname, String email, String username, String password) {
        ArrayList<AuthUserAttribute> attributes = new ArrayList<>();
        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.email(), email));
        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.name(), name));
        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.familyName(), surname));
        AuthSignUpOptions options = AuthSignUpOptions.builder()
                .userAttributes(attributes)
                .build();

        Amplify.Auth.signUp(username, password, options,
                result -> {
                    Log.i("AmplifyQuickstart", result.toString());

                    loadConfirm(username);
                },
                /*errori di inserimento dati (es: lunghezza password)*/
                error -> {
                    Log.e("AmplifyQuickstart", error.toString());
                    loadPopUpSignError(error.getMessage());
                });

    }


    public void confirm(String username, String code) {
        Amplify.Auth.confirmSignUp(username, code,
                result -> {
                    Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
                    loadLogin();
                },
                /*errori di inserimento dati (es: lunghezza password)*/
                error -> {
                    Log.e("AmplifyQuickstart", error.toString());
                    loadPopUpSignError(error.getMessage());
                });

    }

    public void signIn(String username, String password) {
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                context.getApplicationContext(),
                "us-east-1:f2c9ed92-81a1-4b3c-a0ed-c47477b647ee", // ID pool di identità
                Regions.US_EAST_1 // Regione
        );
        Amplify.Auth.signIn(username, password,
                result -> {
                    Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete");
                    if (adminVerify(username)) {
                        //pagina amministratore
                        Log.i("adminVerify","true");
                        loadAdminPage(username);
                    }else{
                        Log.i("adminVerify","false");
                        loadHome(username);
                    }


                },
                /*errori di inserimento dati (es: lunghezza password)*/
                error -> {
                    Log.e("AmplifyQuickstart", error.toString());
                    String userNotConfirm="user not confirmed ";
                    String err= error.toString().toLowerCase();
                    if(err.contains(userNotConfirm)){
                        loadConfirm(username);
                    }else{
                        loadPopUpSignError(error.getMessage());
                    }
                });

    }

    public void signOut() {
        Amplify.Auth.signOut(
                AuthSignOutOptions.builder().globalSignOut(true).build(),
                () -> {
                    Log.i("AuthQuickstart", "Signed out globally");
                    loadMain();
                },
                /*errori di inserimento dati (es: lunghezza password)*/
                error -> {
                    Log.e("AmplifyQuickstart", error.toString());
                    loadPopUpSignError(error.getMessage());
                });
    }


    public void userAttributes (String username){
        Amplify.Auth.fetchUserAttributes(
                attributes -> {
                        Log.i("AuthDemo", "User attributes = " + attributes.toString());
                        String email = attributes.get(4).getValue();
                        String surname = attributes.get(3).getValue();
                        String name = attributes.get(2).getValue();
                        // photo = attributes.get(5).toString();

                        loadUser(email, username, name, surname);
                    }
                ,
                error -> Log.e("AuthDemo", "Failed to fetch user attributes.", error)
        );
    }

    private void loadUser(String email, String username, String name, String surname){
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra("email", email);
        intent.putExtra("username", username);
        intent.putExtra("name", name);
        intent.putExtra("surname",surname);
        //intent.putExtra("photo", photo);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void loadPopUpSignError(String error) {
        Intent intent = new Intent(context, popUpErrorActivity.class);
        intent.putExtra("errorSign", error);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }


    private void loadConfirm(String username) {
        Intent intent = new Intent(context, ConfirmActivity.class);
        intent.putExtra("username", username);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    public void loadAdminPage(String username) {
        Intent intent = new Intent(context, AdminActivity.class);
        intent.putExtra("username", username);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public void loadLogin() {
        Intent intent = new Intent(context, SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public void loadMain() {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void loadSignup() {
        Intent intent = new Intent(context, SignUpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void loadHome(String username) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra("username", username);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    public boolean adminVerify(String username) {

        boolean adminOK = false;
            if ("supremo".equalsIgnoreCase(username)) {
                adminOK = true;
            }else{
                if("giuseppe01".equalsIgnoreCase(username)){
                    adminOK = true;
                }else{
                    if("roberto".equalsIgnoreCase(username)){
                        adminOK = true;
                    }else{
                        if("gianluca".equalsIgnoreCase(username)){
                            adminOK = true;
                        }
                    }
                }
        }
        return adminOK;
    }

}

