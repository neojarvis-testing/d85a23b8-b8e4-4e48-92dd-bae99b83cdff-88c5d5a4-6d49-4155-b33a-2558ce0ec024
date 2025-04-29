package com.examly.springapp.dtos;
public class ApiResponse {
  private String msg;
  public ApiResponse(String msg){ this.msg=msg;}
  public ApiResponse(){}
  public void setMsg(String msg){ this.msg=msg;}
  public String getMsg(){ return this.msg;}
}
