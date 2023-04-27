package com.example.ti.ble.sensortag;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ti.ble.common.TaggingView;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class TagListActivity extends MainActivity {
    ArrayList<String> array;
    ArrayAdapter<String> adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_list);

        ListView listView = (ListView) findViewById(R.id.tag_list_lv);
        Button file_btn = (Button)findViewById(R.id.tag_list_file_btn);
        Button refresh_btn = (Button)findViewById(R.id.tag_list_refresh_btn);


        Intent intent = getIntent();
        array = (ArrayList<String>) intent.getSerializableExtra("data_list");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
        listView.setAdapter(adapter);


        StringBuffer sb = new StringBuffer();
        sb.append("Sensor Data\n");

        for(int i=0;i<array.size();i++){
            sb.append(array.get(i));
            sb.append("\n");
        }
        final String result = sb.toString();

        file_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeFile(result);
                Toast.makeText(getApplicationContext(), "파일이 저장되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        refresh_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog(view);
            }
        });
    }

    public class Object implements Serializable {
    }

    public void fileStorage(String str) {

        String fileName = "tagging.txt";
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        DataOutputStream dos = null;

        try{
            fos = openFileOutput(fileName, MODE_PRIVATE);
            bos = new BufferedOutputStream(fos);
            dos = new DataOutputStream(bos);
            dos.writeUTF(str);
            dos.flush();
        }catch (FileNotFoundException f){
            return;
        }catch (IOException e) {

        }finally {
            try{
                if (dos != null) dos.close();
                if (bos != null) bos.close();
                if (fos != null) fos.close();
            }catch (IOException e) {}
        }
    }
    public void writeFile(String str) {

        //외부 저장소(External Storage)가 마운트(인식) 되었을 때 동작
//getExternalStorageState() 함수를 통해 외부저장장치가 Mount 되어 있는지를 확인
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //다운로드 폴더에 "tagging.txt" 이름으로 txt 파일 저장
            //Environment.DIRECTORY_DOWNLOADS - 기기의 기본 다운로드 폴더
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), "tagging" + ".txt");
            try {
                FileWriter fw = new FileWriter(file, false);
                fw.write(str);
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();

        }
    }

    public void dialog(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("파일 삭제");
        builder.setMessage("삭제 하시겠습니까?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((SensorTagMovementProfile)SensorTagMovementProfile.context_sensortag_mov).TList.clear();
                ((DeviceActivity)DeviceActivity.context_device).list.clear();
                ((TaggingView)TaggingView.context_help).LabelTimeList.clear();
                ((TaggingView)TaggingView.context_help).LabelList.clear();
                array.clear();
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancle", null);
        builder.show();
    }
}
