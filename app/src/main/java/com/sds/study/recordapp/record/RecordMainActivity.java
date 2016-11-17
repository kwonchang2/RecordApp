package com.sds.study.recordapp.record;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.sds.study.recordapp.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wbhlkc0 on 2016-11-17.
 */

public class RecordMainActivity extends AppCompatActivity {
    String TAG;
    MediaRecorder recorder;

    static final int REQUEST_RECORD_PERMISSION =1;
    static final int REQUEST_WRITE_PERMISSION =2;
    Boolean flag=false;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getName();
        setContentView(R.layout.record_main);

        init();
    }

    public void init(){
        recorder = new MediaRecorder();//미리 메모리에 올려두자.

    }

    /*저장 파일 구하기*/
    public String getSaveFile(){
        File dir =new File( Environment.getExternalStorageDirectory(),"IoT_record");
        //녹음 파일이 만들어질때 그 파일 명은 보통 시스템이 정한다.
        //현재 시간을 구하자.
        Date d = new Date();
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS").format(d);
        Log.d(TAG,"현재 시간은 ? " + currentTime);

        File saveFile = new File(dir,currentTime+".mp4");

        return saveFile.getAbsolutePath();
    }

    /*녹음 파일 화면 띄우기*/
    public void showList(){
     Intent intent = new Intent(this,FileListActivity.class);
        startActivity(intent);
    }

    public void startRecord(){
        if(flag==true){
            try {
                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                recorder.setOutputFile(getSaveFile());
                recorder.prepare();
                recorder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            recorder.stop();
            recorder.reset(); //재녹음을 위한 초기화

            /*녹음이 완료된 화면을 보여주자!!!*/
            showList();
        }
    }


    public void btnClick(View view){
        ImageView img = (ImageView)findViewById(R.id.img);

        if(flag==false){
            flag=true;
            img.setImageResource(R.drawable.play);
        }else{
            flag=false;
            img.setImageResource(R.drawable.stop);
        }

        //각종 권한을 체크하자.
        int writePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int recordPermission = ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO);

        if(writePermission == PackageManager.PERMISSION_DENIED || recordPermission == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO},REQUEST_RECORD_PERMISSION);
        }else{
            startRecord();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        Log.d(TAG,"requestCode:"+requestCode+"  ,grantResults"+grantResults[0]+"  ,grantResults"+grantResults[1]);
        switch (requestCode){
            case REQUEST_RECORD_PERMISSION:
                if(permissions.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"앱사용을 위해서는 쓰기 권한을 주셔야 합니다.",Toast.LENGTH_SHORT).show();
                }else if(permissions.length>0&&grantResults[1]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"앱사용을 위해서는 오디오 권한을 주셔야 합니다.",Toast.LENGTH_SHORT).show();
                }

        }
    }
}
