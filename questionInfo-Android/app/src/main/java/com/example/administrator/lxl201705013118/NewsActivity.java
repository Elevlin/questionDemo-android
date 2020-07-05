package com.example.administrator.lxl201705013118;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;


public class NewsActivity extends AppCompatActivity {
    private LinearLayout loading;
    private ListView lvNews;
    private List<QuestionInfo> questionlist;
    private TextView tv_title;
    private TextView tv_description;
    private TextView tv_type;
    private QuestionInfo questioninfo;
    private  TextView tv_no;
    private TextView tv_realName;
    private  TextView tv_zh;
    Button btn_sumit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        init();
    }

    private void init() {
        loading = (LinearLayout) findViewById(R.id.loading);
        lvNews = (ListView) findViewById(R.id.lv_news);
        tv_no=(TextView) findViewById(R.id.tvNo);
        tv_realName=(TextView) findViewById(R.id.tvRealName);
        tv_zh=(TextView) findViewById(R.id.tvzuhao);
        btn_sumit=(Button)findViewById(R.id.btnSubmit);
        btn_sumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewsActivity.this,AddActivity.class);
                intent.putExtra("RealName",tv_realName.getText().toString());
                intent.putExtra("no",tv_no.getText().toString());
                intent.putExtra("zh",tv_zh.getText().toString());
                startActivityForResult(intent,11);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==10)
        {
            if(resultCode==1){
                tv_no.setText(data.getStringExtra("no"));
                tv_realName.setText(data.getStringExtra("realName"));
                tv_zh.setText(data.getStringExtra("zh"));
            }
            if(resultCode==0) {

                finish();
            }
        }
        else  if(requestCode==11)
        {
            fillData();
        }
    }

    private void fillData() {
        //创建AsyncHttpClient实例
        AsyncHttpClient client = new AsyncHttpClient();
        //使用GET方式请求
        String url=getString(R.string.serverurl)+"question/GetList?kfz=jim";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, org.apache.http.Header[] headers, byte[] bytes) {
                //请求成功

                try {
                    String json = new String(bytes, "utf-8");
                    questionlist = JsonParse.getQuestiongList(json);
                    if (questionlist == null) {
                        Toast.makeText(NewsActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
                    } else {
                        //更新界面
                        loading.setVisibility(View.VISIBLE);
                        lvNews.setAdapter(new NewsAdapter());
                        loading.setVisibility(View.INVISIBLE);
                        lvNews.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(int i, org.apache.http.Header[] headers, byte[] bytes, Throwable throwable) {
            }
        });

        }
    //ListView适配器
    private class NewsAdapter extends BaseAdapter {
        //listview的item数
        @Override
        public int getCount() {
            return questionlist.size();
        }

        //得到listview条目视图
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(NewsActivity.this, R.layout.list_item, null);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_description = (TextView) view.findViewById(R.id.tv_description);
            tv_type = (TextView) view.findViewById(R.id.tv_type);
            questioninfo = questionlist.get(position);
            // Log.i("dd",""+newsInfo.getIcon());
            //设置新闻标题
            tv_title.setText(questioninfo.getTitle());
            //设置新闻描述
            tv_description.setText(questioninfo.getContent());

            return view;
        }

        //条目对象
        @Override
        public Object getItem(int position) {
            return null;
        }

        //条目id
        @Override
        public long getItemId(int position) {
            return 0;
        }
    }

}
