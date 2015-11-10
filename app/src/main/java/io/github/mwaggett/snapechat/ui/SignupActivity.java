package io.github.mwaggett.snapechat.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.github.mwaggett.snapechat.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.mwaggett.snapechat.models.User;

public class SignupActivity extends AppCompatActivity {

    @Bind(R.id.usernameText) EditText mUsernameInput;
    @Bind(R.id.passwordText) EditText mPasswordInput;
    //@Bind(R.id.emailText) EditText mEmailInput;
    @Bind(R.id.signupButton) Button mSignupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsernameInput.getText().toString().trim();
                String password = mPasswordInput.getText().toString().trim();
                //String email = mEmailInput.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) { //|| email.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    builder.setMessage("Please enter a username and password!")
                            .setTitle("Oops!")
                            .setPositiveButton("OK", null);
                    builder.create().show();
                } else {
                    User newUser = new User(username, password);
                    newUser.save(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(SignupActivity.this, ShowThumbnailsActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    }, new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                            builder.setMessage("Something went wrong!")
                                    .setTitle("Oops!")
                                    .setPositiveButton("OK", null);
                            builder.create().show();
                        }
                    });
                }
            }
        });
    }
}
