package com.example.loginbyfb;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.imageProfile)
    ProfilePictureView imageProfile;

    @BindView(R.id.btnLogin)
    LoginButton btnLogin;

    @BindView(R.id.btnDangXuat)
    Button btnDangXuat;

    @BindView(R.id.txtName)
    TextView txtName;

    @BindView(R.id.txtEmail)
    TextView txtEmail;

    @BindView(R.id.txtFirstName)
    TextView txtFirstName;

    @BindView(R.id.btnChucNang)
    Button btnChucNang;

    private CallbackManager callbackManager;
    private String name,firsrname,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //cách lấy keyHash
        /*try {
            PackageInfo info = null;
            try {
                info = getPackageManager().getPackageInfo(
                        "com.example.loginbyfb",
                        PackageManager.GET_SIGNATURES);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (NoSuchAlgorithmException e) {

        }*/


        btnChucNang.setVisibility(View.INVISIBLE);
        btnDangXuat.setVisibility(View.INVISIBLE);
        txtEmail.setVisibility(View.INVISIBLE);
        txtName.setVisibility(View.INVISIBLE);
        txtFirstName.setVisibility(View.INVISIBLE);

        btnLogin.setReadPermissions(Arrays.asList("public_profile","email"));
        setBtnLogin();
        setBtnLogout();
        setBtnChucNang();

    }

    private void setBtnChucNang() {
        btnChucNang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ChucNang.class);
                startActivity(intent);
            }
        });
    }

    private void setBtnLogout() {
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                btnChucNang.setVisibility(View.INVISIBLE);
                btnDangXuat.setVisibility(View.INVISIBLE);
                txtEmail.setVisibility(View.INVISIBLE);
                txtName.setVisibility(View.INVISIBLE);
                txtFirstName.setVisibility(View.INVISIBLE);
                txtEmail.setText("");
                txtName.setText("");
                txtFirstName.setText("");

                imageProfile.setProfileId(null);
                btnLogin.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setBtnLogin() {
        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                btnLogin.setVisibility(View.INVISIBLE);
                btnChucNang.setVisibility(View.VISIBLE);
                btnDangXuat.setVisibility(View.VISIBLE);
                txtEmail.setVisibility(View.VISIBLE);
                txtName.setVisibility(View.VISIBLE);
                txtFirstName.setVisibility(View.VISIBLE);
                result();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void result() {
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("AAA",object.toString());
                Log.d("AAA",response.getJSONObject().toString());

                try {
                    name = object.getString("name");
                    email = object.getString("email");
                    firsrname = object.getString("first_name");

                    Log.d("AAA",name + email + firsrname);
                    Log.d("AAA","AAAAAAAA");
                    imageProfile.setProfileId(Profile.getCurrentProfile().getId());

                    txtEmail.setText(email);
                    txtFirstName.setText(firsrname);
                    txtName.setText(name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields","name,email,first_name");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        LoginManager.getInstance().logOut();
        super.onStart();
    }
}
