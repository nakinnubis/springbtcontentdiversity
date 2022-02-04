package com.cosmos.contentdiversity.Helper;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Data {
    @JsonProperty("blogPostId")
    public int getBlogPostId() {
        return this.blogPostId; }
    public void setBlogPostId(int blogPostId) {
        this.blogPostId = blogPostId; }
    int blogPostId;
    @JsonProperty("blogSiteId")
    public int getBlogSiteId() {
        return this.blogSiteId; }
    public void setBlogSiteId(int blogSiteId) {
        this.blogSiteId = blogSiteId; }
    int blogSiteId;
    String blogAuthor;
    String blogLocation;
    @JsonProperty("date")
    public Date getDate() {
        return this.date; }
    public void setDate(Date date) {
        this.date = date; }
    Date date;
    @JsonProperty("topicSummary")
    public Object getTopicSummary() {
        return this.topicSummary; }
    public void setTopicSummary(Object topicSummary) {
        this.topicSummary = topicSummary; }
    Object topicSummary;
    @JsonProperty("lda")
    public ArrayList<Lda> getLda() {
        return this.lda; }
    public void setLda(ArrayList<Lda> lda) {
        this.lda = lda; }
    ArrayList<Lda> lda;
    @JsonProperty("topKeyWords")
    public Hashtable<String, ArrayList<HashMap<String, Double>>> getTopKeyWords() {
        return this.topKeyWords; }
    public void setTopKeyWords(Hashtable<String, ArrayList<HashMap<String, Double>>> topKeyWords) {
        this.topKeyWords = topKeyWords; }
    Hashtable<String, ArrayList<HashMap<String, Double>>> topKeyWords;
    @JsonProperty("document")
    public Object getDocument() {
        return this.document; }
    public void setDocument(Object document) {
        this.document = document; }
    Object document;
    @JsonProperty("theta")
    public double[] getTheta() {
        return this.theta; }
    public void setTheta(double[] theta) {
        this.theta = theta; }
    double[] theta;
    public String blogger;
    public String location;
}
