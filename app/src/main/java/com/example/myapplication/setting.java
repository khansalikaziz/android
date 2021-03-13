package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class setting extends AppCompatActivity {
    CircleImageView img;
    Button back;
    TextView name,email,about;
    private Context context;
    private TextView url;
    private FirebaseDatabase database ;
    private DatabaseReference mReference ;
    private DatabaseReference childReference ;
    String customerid;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        img=findViewById(R.id.profile_image);
        auth = FirebaseAuth.getInstance();
        back=findViewById(R.id.button5);
        name=findViewById(R.id.username);
        email=findViewById(R.id.useremail);
        about=findViewById(R.id.userabout);
        context=this;
        customerid = auth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        mReference = database.getReference("Users");
        childReference = mReference.child("Customers").child(customerid);

    }
    @Override
    protected void onStart() {
        super.onStart();
        childReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String message = dataSnapshot.child("image").getValue(String.class);
                Picasso.get().load(message).into(img);
                String nameuser=dataSnapshot.child("name").getValue(String.class);
                name.setText(nameuser);
                String emailuser=dataSnapshot.child("email").getValue(String.class);
                email.setText(emailuser);
                String aboutuser=dataSnapshot.child("about").getValue(String.class);
                about.setText(aboutuser);
              /*  Picasso.with(context)
                        .load(message)
                        .into(img);*/
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}