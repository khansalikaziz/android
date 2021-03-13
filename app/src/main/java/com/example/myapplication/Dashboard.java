package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Flow;

import co.apptailor.googlesignin.RNGoogleSigninModule;

public class Dashboard extends AppCompatActivity {
    EditText secretCodeBox;
    Button joinBtn, shareBtn;
    String cid;
    FirebaseAuth auth;
    ProgressDialog dialog;
    DatabaseReference customersDatabaseRef;
    public void hit(View v){
        startActivity(new Intent(Dashboard.this,setting.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dialog = new ProgressDialog(this);
        dialog.setMessage("you will be signout from the app");
        dialog.setTitle("Sign out");
        secretCodeBox = findViewById(R.id.codeBox);
        joinBtn = findViewById(R.id.joinBtn);
        shareBtn = findViewById(R.id.shareBtn);
        auth = FirebaseAuth.getInstance();

        URL serverURL;

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.home:
                                Toast.makeText(Dashboard.this, "Home", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.history:
                                startActivity(new Intent(Dashboard.this,gettingvideomodule.class));
                                Toast.makeText(Dashboard.this, "Home", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.profile:
                                startActivity(new Intent(Dashboard.this,Scrollview.class));
                                Toast.makeText(Dashboard.this, "Home", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.setting:
                                startActivity(new Intent(Dashboard.this,setting.class));
                                break;
                            case R.id.logout:
                                dialog.show();
                                Toast.makeText(Dashboard.this, "you are Logout", Toast.LENGTH_SHORT).show();

                                //auth.getInstance().signOut();
                                final Timer timer=new Timer();
                                timer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                       dialog.dismiss();
                                       timer.cancel();
                                        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                                        SharedPreferences.Editor editor=preferences.edit();
                                        editor.putString("remember","false");
                                        editor.apply();
                                        finish();
                                        //startActivity(new Intent(Dashboard.this,LoginActivity.class));
                                    }
                                },2000);
                                //startActivity(new Intent(Dashboard.this,LoginActivity.class));
                                break;
                        }
                        return false;
                    }
                });
       /* cid = auth.getCurrentUser().getUid();
        customersDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(cid);
        customersDatabaseRef.setValue(true);
        DatabaseReference refname=customersDatabaseRef.child("secretcode");
        refname.setValue(secretCodeBox);*/


        try {
            serverURL = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions =
                    new JitsiMeetConferenceOptions.Builder()
                            .setServerURL(serverURL)
                            .setWelcomePageEnabled(false)
                            .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }





        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(secretCodeBox.getText().toString())
                        .setWelcomePageEnabled(false)
                        .build();

                JitsiMeetActivity.launch(Dashboard.this, options);
               /* cid = auth.getCurrentUser().getUid();
                customersDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(cid);
                customersDatabaseRef.setValue(true);
                DatabaseReference refname=customersDatabaseRef.child("secretcode");
                refname.setValue(secretCodeBox);*/
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {

            case R.id.home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.history:
                startActivity(new Intent(Dashboard.this,gettingvideomodule.class));
                Toast.makeText(this, "Access video", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.setting:
                startActivity(new Intent(Dashboard.this,setting.class));
                Toast.makeText(this, "Use profile", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout:
                final Timer timer=new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        timer.cancel();
                        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("remember","false");
                        editor.apply();
                        finish();
                        //startActivity(new Intent(Dashboard.this,LoginActivity.class));
                    }
                },2000);
                Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.profile:
                startActivity(new Intent(Dashboard.this,Scrollview.class));
                Toast.makeText(this, "User profile", Toast.LENGTH_SHORT).show();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}