package com.cosmos.contentdiversity.controller;

import com.cosmos.contentdiversity.Helper.TopicModelResponse;
import com.cosmos.contentdiversity.Helper.TopicModellingHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("topicmodelling")
public class TopicModellingController {
    @GetMapping("chordDiagram")
    public void chordDiagram(){
        try {
            getTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getTest() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://144.167.35.135:9011/api/v1/dashboard/trackers/312/topic-distribution?dateStart=2018-01-01&dateEnd=2020-01-02")
                .method("GET", null)
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjb3Ntb2dyYXBoZXJzQGdtYWlsLmNvbSIsImV4cCI6MTY0Mzc4NjUwOX0.ujTdnMJiZg5umHz-FPf_BAN50mqJr9LA_sDDa31Ftl-Dsc92A1Jpd8L_TOPHfSXSNXuVn7nLepd2e0QuxMO4xQ")
                .build();
        ResponseBody response = client.newCall(request).execute().body();
        ObjectMapper objectMapper = new ObjectMapper();
        TopicModelResponse responseMapper = objectMapper.readValue(response != null ? response.string() : null, TopicModelResponse.class);
        TopicModellingHelper topicModellingHelper = new TopicModellingHelper();
var test  = topicModellingHelper.getMatrix(responseMapper.getData());
    }
}
