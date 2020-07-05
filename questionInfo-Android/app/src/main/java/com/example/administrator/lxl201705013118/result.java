package com.example.administrator.lxl201705013118;

public class result {
    private   boolean Falge;
    private  String Msg;
    private  int ErorrNum;
    private  userInfo Result;

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

    public userInfo getResult() {
        return Result;
    }

    public void setResult(userInfo result) {
        Result = result;
    }

}

