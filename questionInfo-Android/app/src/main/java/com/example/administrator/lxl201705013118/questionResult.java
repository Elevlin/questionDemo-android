package com.example.administrator.lxl201705013118;

public class questionResult {
    private   boolean Falge;
    private  String Msg;
    private  int ErorrNum;
    private  QuestionInfo Result;

    public boolean isFalge() {
        return Falge;
    }

    public void setFalge(boolean falge) {
        Falge = falge;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public int getErorrNum() {
        return ErorrNum;
    }

    public void setErorrNum(int erorrNum) {
        ErorrNum = erorrNum;
    }

    public QuestionInfo getResult() {
        return Result;
    }

    public void setResult(QuestionInfo result) {
        Result = result;
    }


}
