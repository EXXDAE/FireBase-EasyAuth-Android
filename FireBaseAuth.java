package com.pollux.pry;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    EditText email_t, pass_t;

    Button login_b, registr_b;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email_t = (EditText) findViewById(R.id.email_text);
        pass_t = (EditText) findViewById(R.id.pass_text);

        login_b = (Button) findViewById(R.id.login_button);
        registr_b = (Button) findViewById(R.id.regist_button);


        firebaseAuth = FirebaseAuth.getInstance();

        login_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = email_t.getText().toString().trim();
                String password = pass_t.getText().toString().trim();


                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity.this, "Почта не введена", Toast.LENGTH_LONG); // Email empty
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Пароль не введен", Toast.LENGTH_LONG); // Password empty
                    return;
                }


                if(password.length()<6) {
                    Toast.makeText(MainActivity.this, "Пароль короткий", Toast.LENGTH_LONG); // Password short

                }

                firebaseAuth.createUserWithEmailAndPassword(email, password) // String
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information

                                    Toast.makeText(MainActivity.this, "Успешно", Toast.LENGTH_LONG); // Successful


                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(MainActivity.this, "Неуспешно", Toast.LENGTH_LONG); // Unsuccessful


                                }

                                // ...
                            }
                        });

            }
        });

    }
}
