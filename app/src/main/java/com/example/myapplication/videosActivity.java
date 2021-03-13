package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

public class videosActivity extends AppCompatActivity {
    VideoView videoView;
    ListView listView;
    ArrayList<String> videoList;
    ArrayAdapter adapter;
    Context context;

    ArrayList prgmName;

    public static int [] prgmImages={R.drawable.in,R.drawable.listen,R.drawable.speaking,R.drawable.read,R.drawable.write};
    public static String [] prgmNameList={"Introduction to IELTS","Listening skills","Speaking skills","Reading skills","Writing skills"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        context=this;
        videoView=findViewById(R.id.videoview);
        listView=findViewById(R.id.lvideo);
        listView.setAdapter(new CustomListAdapter(this, prgmNameList,prgmImages));


       /* videoList= new ArrayList<>();
        videoList.add("Introduction to IELTS");
        videoList.add("Listening skills");
        videoList.add("Speaking skills");
        videoList.add("Reading skills");
        videoList.add("Writing skills");*/

      //  adapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,videoList);
     //   listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                       // Uri uri1=Uri.parse("https://firebasestorage.googleapis.com/v0/b/trialapplication-5871e.appspot.com/o/video%2FIntroductionone.mp4?alt=media&token=60b93ed1-a4ca-4b49-af87-ea10e9ebb4de");
                        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.introductionone));

                        break;
                    case 1:
                        //Uri uri=Uri.parse("https://firebasestorage.googleapis.com/v0/b/trialapplication-5871e.appspot.com/o/video%2FListeningskillstwo.mp4?alt=media&token=0bff9e03-b635-4f58-83ee-230fee1ec904");
                        //videoView.setVideoURI(uri);
                       videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.listeningskillstwo));
                        break;
                    case 2:
                       // Uri uri2=Uri.parse("https://firebasestorage.googleapis.com/v0/b/trialapplication-5871e.appspot.com/o/video%2Fspeakingskillthree.mp4?alt=media&token=d1413ecd-9125-4a28-a05a-31f7360c0dcb");
                        //videoView.setVideoURI(uri2);
                       videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.speakingskillthree));
                        break;
                    case 3:
                       // Uri uri3=Uri.parse("https://firebasestorage.googleapis.com/v0/b/trialapplication-5871e.appspot.com/o/video%2FReadingskills.mp4?alt=media&token=73f21cbe-eda6-4c41-be99-9976df742a2d");
                       // videoView.setVideoURI(uri3);
                        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.read));
                        break;
                    case 4:
                        //Uri uri4=Uri.parse("https://firebasestorage.googleapis.com/v0/b/trialapplication-5871e.appspot.com/o/video%2FWriting.mp4?alt=media&token=8c9578dd-e287-4b53-8457-00bc50150b4a");
                       // videoView.setVideoURI(uri4);
                        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.write));
                        break;
                    default:
                        break;
                }

              videoView.setMediaController(new MediaController(videosActivity.this));
                videoView.requestFocus();
                videoView.start();
            }
        });

    }
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/


}