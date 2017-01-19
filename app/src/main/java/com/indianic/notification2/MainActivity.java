package com.indianic.notification2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.iid.FirebaseInstanceId;
import com.indianic.notification.R;

import me.leolin.shortcutbadger.ShortcutBadger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.e("aaa", "TTTTocken: " + token);

        ((EditText)findViewById(R.id.edit)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("Text",i+" "+i1+" "+i2);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ((Button)findViewById(R.id.btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((EditText)findViewById(R.id.edit)).setError("Please set Valid Password, Like A-Z,a-z,0-9");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        NotificationApp.getNotificationApp().setCountZero();
        ShortcutBadger.removeCount(this); //for 1.1.4+
    }
}
