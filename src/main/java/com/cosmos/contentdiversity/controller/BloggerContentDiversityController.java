package com.cosmos.contentdiversity.controller;


import com.cosmos.contentdiversity.Computations.NoveltyTransienceResonance;
import com.cosmos.contentdiversity.DataModel.BlogPost;
import com.cosmos.contentdiversity.DataModel.TransienceNoveltyResonance;
import com.cosmos.contentdiversity.TopicDistributionDataModel.LdaTopicDistribution;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("bloggercontentdiveristy")
public class BloggerContentDiversityController {
    ArrayList<BlogPost> blogpostsArray = new ArrayList<BlogPost>();

   @GetMapping("blogger")
    public void getblogger(){
               ArrayList blogPosts = null;
        try {
            ArrayList<LdaTopicDistribution> topicDistribution = new ArrayList<>();
            NoveltyTransienceResonance noveltyTransienceResonance = new NoveltyTransienceResonance();
            ArrayList _noveltyTransienceResonance = noveltyTransienceResonance.main(topicDistribution);
            JsonArray result = (JsonArray) new Gson().toJsonTree(_noveltyTransienceResonance,
                    new TypeToken<List<TransienceNoveltyResonance>>() {
                    }.getType());

        } catch (Exception e) {
            e.printStackTrace();
        }
   }
}