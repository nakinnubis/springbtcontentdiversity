package com.cosmos.contentdiversity.controller;


import com.cosmos.contentdiversity.Computations.NoveltyTransienceResonance;
import com.cosmos.contentdiversity.DataModel.BlogPost;
import com.cosmos.contentdiversity.DataModel.TransienceNoveltyResonance;
import com.cosmos.contentdiversity.Response.Datum;
import com.cosmos.contentdiversity.Response.Lda;
import com.cosmos.contentdiversity.Response.TopicModelResponse;
import com.cosmos.contentdiversity.TopicDistributionDataModel.LdaTopicDistribution;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("blogcontentdiveristy")
public class BlogContentDivsersityController {
    ArrayList<BlogPost> blogpostsArray = new ArrayList<BlogPost>();
    @GetMapping("blog")
    public void blogcontent(){
        try {
            getTopicDistribution();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

private  void getTopicDistribution() throws IOException {
    OkHttpClient client = new OkHttpClient().newBuilder()
            .build();
    //&size=500
    Request request = new Request.Builder()
            .url("http://144.167.35.135:9011/api/v1/dashboard/trackers/428/topic-distribution?dateStart=2017-01-01&dateEnd=2018-01-01&size=200000")
            .method("GET", null)
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjb3Ntb2dyYXBoZXJzQGdtYWlsLmNvbSIsImV4cCI6MTY0NDczNjkzMH0.EYhgeJvMrxTgV60Y2nFqfMxtNw0uifqzjHxUFrfs59vg5EAfCIDtEIbEpfUTYzROxiMOsoSyXbSboVx8eD2v5w")
            .build();
    //LdaTopicDistribution
    ResponseBody response = client.newCall(request).execute().body();
    ObjectMapper objectMapper = new ObjectMapper();
    TopicModelResponse responseMapper = objectMapper.readValue(response != null ? response.string() : null, TopicModelResponse.class);

    //This conversion back and forth may not be need after you have added the theta field to the topic distribution
    //You can add the comparator to the class level and use this to have the theta in the computation of the theta that is stored in the topic distribution database
    //
    var mappedData = responseMapper.getData().stream().map(a -> {
        var ldaa = new LdaTopicDistribution();
        ldaa.theta = a.getLda().stream().sorted().mapToDouble(Lda::getV2).toArray();
        ldaa.date = String.valueOf(a.getDate());
        ldaa.blogPostId= String.valueOf(a.getBlogPostId());
        ldaa.blogSiteId = String.valueOf(a.getBlogSiteId());
        return ldaa;
    }).toList();
    generateContentDiversity(mappedData);

}
public void generateContentDiversity(List<LdaTopicDistribution> topicDistribution){
    NoveltyTransienceResonance noveltyTransienceResonance = new NoveltyTransienceResonance();
    ArrayList _noveltyTransienceResonance = noveltyTransienceResonance.main(topicDistribution);
    JsonArray result = (JsonArray) new Gson().toJsonTree(_noveltyTransienceResonance,
            new TypeToken<List<TransienceNoveltyResonance>>() {
            }.getType());
    System.out.println(result.toString());
    var r = result;
}
}
