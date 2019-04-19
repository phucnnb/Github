package com.example.loginbygg;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.sign_in_button)
    SignInButton signInButton;

    @BindView(R.id.btnSignOut)
    Button btnSignOut;

    @BindView(R.id.imageProfile)
    ImageView imageProfile;

    @BindView(R.id.txtName)
    TextView txtName;

    @BindView(R.id.txtGmail)
    TextView txtGmail;

    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN = 1;
    private GoogleSignInAccount account = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // yêu cầu ng dùng cung cấp thông tin cơ bản
        // Email,Tên , hình ảnh
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);




        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
                account = GoogleSignIn.getLastSignedInAccount(MainActivity.this);
                updateUI(account);
            }
        });

        //
        account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleSignInClient.signOut()
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(),"đã thoát",Toast.LENGTH_SHORT).show();
                                btnSignOut.setVisibility(View.GONE);
                                txtName.setVisibility(View.GONE);
                                txtGmail.setVisibility(View.GONE);
                                imageProfile.setVisibility(View.GONE);
                                signInButton.setVisibility(View.VISIBLE);
                                txtGmail.setText("");
                                txtName.setText("");
                                imageProfile.setImageBitmap(null);
                            }
                        });
            }
        });
    }

    private void updateUI(GoogleSignInAccount account) {
        if(account != null){
            Log.d("AAA",account.toString());
            btnSignOut.setVisibility(View.VISIBLE);
            txtName.setVisibility(View.VISIBLE);
            txtGmail.setVisibility(View.VISIBLE);
            imageProfile.setVisibility(View.VISIBLE);
            signInButton.setVisibility(View.GONE);
        }else {
            Log.d("AAA","chưa đăng nhập");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            txtName.setText(account.getDisplayName());
            txtGmail.setText(account.getEmail());
            Picasso.with(this).load(account.getPhotoUrl()).resize(100,100).into(imageProfile);
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("AAA", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        btnSignOut.setVisibility(View.GONE);
                        txtName.setVisibility(View.GONE);
                        txtGmail.setVisibility(View.GONE);
                        imageProfile.setVisibility(View.GONE);
                        signInButton.setVisibility(View.VISIBLE);
                        txtGmail.setText("");
                        txtName.setText("");
                        imageProfile.setImageBitmap(null);
                    }
                });
    }
}
