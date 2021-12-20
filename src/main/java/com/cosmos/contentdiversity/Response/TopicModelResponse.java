package com.cosmos.contentdiversity.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TopicModelResponse {
    @JsonProperty("data")
    public List<Datum> getData() {
        return this.data; }
    public void setData(List<Datum> data) {
        this.data = data; }
    List<Datum> data;
    @JsonProperty("extra")
    public Object getExtra() {
        return this.extra; }
    public void setExtra(Object extra) {
        this.extra = extra; }
    Object extra;
    @JsonProperty("message")
    public String getMessage() {
        return this.message; }
    public void setMessage(String message) {
        this.message = message; }
    String message;
    @JsonProperty("success")
    public boolean getSuccess() {
        return this.success; }
    public void setSuccess(boolean success) {
        this.success = success; }
    boolean success;
}




