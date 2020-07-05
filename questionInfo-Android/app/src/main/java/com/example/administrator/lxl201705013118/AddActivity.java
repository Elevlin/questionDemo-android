package com.example.administrator.lxl201705013118;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

public class AddActivity extends AppCompatActivity {
    EditText et_no;
    EditText et_realName;
    EditText et_zh;
    EditText et_title;
    EditText et_content;
    EditText et_zs;
    Button btnSumit;
    questionResult r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
    }

    private void init() {
        et_no = (EditText) findViewById(R.id.etNo);
        et_realName = (EditText) findViewById(R.id.etRealName);
        et_zh = (EditText) findViewById(R.id.etZH);
        et_title = (EditText) findViewById(R.id.etTitle);
        et_content = (EditText) findViewById(R.id.etContent);
        et_zs = (EditText) findViewById(R.id.etzs);
        btnSumit=(Button)findViewById(R.id.button);
        btnSumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSumit();
            }
        });
    }

    private void btnSumit() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = getString(R.string.serverurl)+"question/getQuestionAdd?no="+et_no.getText().toString()+"&realname="+et_realName.getText().toString()+"&zh="+et_zh.getText().toString()+"&title="+et_title.getText().toString()+"&Content="+et_content.getText().toString()+"&zs="+et_zs.getText().toString()+"&kfz=jim&bz1=&bz2=&bz3=&bz4=&bz5=&bz6=&bz7=";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String json = new String(bytes,"utf-8");
                    r = JsonParse.getQuestionResult(json);
                    if (r.isFalge()){
                        Intent intent=new Intent();
                        setResult(1,intent);
                        finish();

                    }else{}
                }catch (Exception e){
                    Toast.makeText(AddActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(AddActivity.this,"访问服务失败",Toast.LENGTH_LONG).show();
            }
        });
    }
}
