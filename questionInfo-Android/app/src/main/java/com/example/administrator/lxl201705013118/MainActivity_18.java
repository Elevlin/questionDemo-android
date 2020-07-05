package com.example.administrator.lxl201705013118;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import javax.xml.transform.Result;


public class MainActivity_18 extends AppCompatActivity {

    private EditText zh;
    private EditText mm;
    private EditText js;
    private Button login;
    private Button zc;
    private Button cz;
    private Button tc;
    result r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_18);

        zh = (EditText) findViewById(R.id.yhm);

        mm = (EditText) findViewById(R.id.mmk);

        js = (EditText) findViewById(R.id.jsk);
        js.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder jsAlert= new AlertDialog.Builder(MainActivity_18.this);
                jsAlert.setTitle("角色选择").setSingleChoiceItems(new String[]{"教师", "学生", "管理员"}, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            js.setText("教师");
                        }
                        else if (which == 1) {
                            js.setText("学生");
                        }else if (which == 2){
                            js.setText("管理员");
                        }
                    }
                }).setPositiveButton("确定",null).create().show();
            }
        });

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        zc = (Button) findViewById(R.id.zc);
        zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               zc();
            }
        });
        cz = (Button) findViewById(R.id.cz);
        cz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zh.setText("");
                mm.setText("");
                js.setText("");

            }
        });

        tc = (Button) findViewById(R.id.tc);
        tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void zc() {
        Intent zc = new Intent();
        zc.setClass(MainActivity_18.this,ZcActivity.class);
        startActivity(zc);
    }

    private void login() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url=getString(R.string.serverurl)+"users/getLogin?userName="+zh.getText().toString()+"&pwd="+mm.getText().toString()+"&power=1&kfz=jim";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String js = new String(bytes, "utf-8");
                    r = JsonParse.getLoginResult(js);
                    if(r==null)
                    {
                        Toast.makeText(MainActivity_18.this,"登录失败",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if(r.isFalge()) {
                            AlertDialog dialog = new AlertDialog.Builder(MainActivity_18.this)
                                    .setTitle("登陆提示")
                                    .setMessage("欢迎" + zh.getText() + "课程问题管理系统")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent=new Intent(MainActivity_18.this,NewsActivity.class);
                                            intent.putExtra("realName",r.getResult().getRealName());
                                            intent.putExtra("userName",r.getResult().getUserName());
                                            startActivity(intent);
                                        }
                                    })
                                    .create();
                            dialog.show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity_18.this,"登录失败        "+r.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }


                }catch (Exception ex)
                {
                    Toast.makeText(MainActivity_18.this,"访问错误"+ex.getMessage(),Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(int i,Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(MainActivity_18.this,"访问服务失败",Toast.LENGTH_LONG).show();
            }
        });
    }
}
