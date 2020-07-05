package com.example.administrator.lxl201705013118;

import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.http.Header;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class ZcActivity extends AppCompatActivity {

    Button btnReg;
    Button btnClose;
    EditText et_no;
    EditText et_userName;
    EditText et_realName;
    EditText et_zh;
    EditText et_pwd;
    EditText et_pwd1;
    result r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zc);
        init();
    }

    private void init() {
        btnReg=(Button)findViewById(R.id.button);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reg();
            }
        });

        btnClose=(Button)findViewById(R.id.button2);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        et_no=(EditText)findViewById(R.id.etNo);
        et_userName=(EditText)findViewById(R.id.etUserName);
        et_realName=(EditText)findViewById(R.id.etRealName);
        et_zh=(EditText)findViewById(R.id.etZh);
        et_pwd=(EditText)findViewById(R.id.etPwd);
        et_pwd1=(EditText)findViewById(R.id.etPwd1);

    }

    private void reg() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = getString(R.string.serverurl)+"users/getReg?userName="+et_userName.getText()+"&pwd="+et_pwd.getText()+"&power=1&no="+et_no.getText()+"&realname="+et_realName.getText()+"&kfz=jim&zh="+et_zh.getText()+"&bz1=&bz2=&bz3=&bz4=&bz5=&bz6=&bz7=";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String json = new String(bytes, "utf-8");
                    r=JsonParse.getLoginResult(json);
                    if(r.isFalge())
                    {
                        if (et_pwd.getText().toString() == et_pwd1.getText().toString()){
                            Toast.makeText(ZcActivity.this,"Successed!",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                    else
                    {
                        Toast.makeText(ZcActivity.this,"注册失败,"+r.getMsg(),Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    Toast.makeText(ZcActivity.this,"访问错误"+e.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(ZcActivity.this,"访问服务失败",Toast.LENGTH_LONG).show();

            }
        });

    }
}
