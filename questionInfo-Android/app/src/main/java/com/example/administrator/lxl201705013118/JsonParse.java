package com.example.administrator.lxl201705013118;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonParse {
    public static result getLoginResult(String json)
    {
        Gson gson=new Gson();
        result r=gson.fromJson(json,result.class);
        return  r;
    }
    public static questionResult getQuestionResult(String json)
    {
        Gson gson=new Gson();
        questionResult r=gson.fromJson(json,questionResult.class);
        return  r;
    }
    public static List<QuestionInfo> getQuestiongList(String json) {
        //使用gson库解析JSON数据
        Gson gson = new Gson();
        //创建一个TypeToken的匿名子类对象，并调用对象的getType()方法
        Type listType = new TypeToken<List<QuestionInfo>>() {
        }.getType();
        //把获取到的信息集合存到newsInfos中
        List<QuestionInfo> questionList = gson.fromJson(json, listType);
        return questionList;
    }

}
