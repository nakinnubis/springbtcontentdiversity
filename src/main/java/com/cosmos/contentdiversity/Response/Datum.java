package com.cosmos.contentdiversity.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class Datum {
    @JsonProperty("blogPostId")
    public int getBlogPostId() {
        return this.blogPostId;
    }

    public void setBlogPostId(int blogPostId) {
        this.blogPostId = blogPostId;
    }

    int blogPostId;

    @JsonProperty("blogSiteId")
    public int getBlogSiteId() {
        return this.blogSiteId;
    }

    public void setBlogSiteId(int blogSiteId) {
        this.blogSiteId = blogSiteId;
    }

    int blogSiteId;

    @JsonProperty("date")
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    Date date;

    @JsonProperty("topicSummary")
    public Object getTopicSummary() {
        return this.topicSummary;
    }

    public void setTopicSummary(Object topicSummary) {
        this.topicSummary = topicSummary;
    }

    Object topicSummary;

    @JsonProperty("lda")
    public List<Lda> getLda() {
        return this.lda;
    }

    public void setLda(List<Lda> lda) {
        this.lda = lda;
    }

    List<Lda> lda;
        double[] theta;
    @JsonProperty("topKeyWords")
    public Hashtable<String, List<HashMap<String, Integer>>> getTopKeyWords() {
        return this.topKeyWords;
    }

    public void setTopKeyWords(Hashtable<String, List<HashMap<String, Integer>>> topKeyWords) {
        this.topKeyWords = topKeyWords;
    }

    Hashtable<String, List<HashMap<String, Integer>>> topKeyWords;

    @JsonProperty("document")
    public Object getDocument() {
        return this.document;
    }

    public void setDocument(Object document) {
        this.document = document;
    }

    Object document;
}
