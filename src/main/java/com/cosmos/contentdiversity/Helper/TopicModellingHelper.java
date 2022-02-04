package com.cosmos.contentdiversity.Helper;

import java.util.*;


public class TopicModellingHelper {
    public ArrayList<ArrayList<Double>> getMatrix(ArrayList<Data> blogposts){
        final double TOPIC_THRESHOLD = 0.1;
//        final int TOPIC_NUMBER = 10;
//        final int TOPICMODEL_MAXWORDS = 30;
        final String DEFAULT_AUTHOR = "Not Provided";
        int[] bloggerMention = new int[10];
        int[] postMention = new int[10];
        String[] topBloggers = new String[10];
        double[][] blogDistributionMatrix = new double[10][10];
        Map<String, ArrayList<HashMap<String, Double>>> topics;
        var test = blogposts.stream().map(t -> t.getTopKeyWords()).toList();
        var testt =test;
        topics = getTopics(testt);
        setBlogTopicDistributionThreshold(TOPIC_THRESHOLD, topics, blogposts, DEFAULT_AUTHOR, bloggerMention, postMention, topBloggers, blogDistributionMatrix);
        ArrayList<ArrayList<Double>> _chordDiagramMatrix = getChordDiagramMatrix(topics, blogDistributionMatrix);
        return _chordDiagramMatrix;
    }
public static  Map<String, ArrayList<HashMap<String, Double>>> getTopics(List<Hashtable<String, ArrayList<HashMap<String, Double>>>> to){
    Map<String, ArrayList<HashMap<String, Double>>> topics = new HashMap<>();
    for (var t: to){
      for(var tt:t.entrySet()){
         if(topics.containsKey(tt.getKey())){
            var value = tt.getValue();
            var existingValues =  topics.get(tt.getKey());
             existingValues.addAll(value);
         }
         else {
             var value = tt.getValue();
             ArrayList<HashMap<String, Double>> db = new ArrayList<>();
             db.addAll(value);
             topics.put(tt.getKey(),db);
         }
      }
    }
        return topics;
}
    private void setBlogTopicDistributionThreshold(double topic_threshold, Map<String, ArrayList<HashMap<String, Double>>> topics, ArrayList<Data> blogposts, String default_author, int[] bloggerMention, int[] postMention, String[] topBloggers, double[][] blogDistributionMatrix) {
        for (int i = 0; i < topics.size(); i++) {
            HashMap<String, Integer> bloggers = new HashMap<>();
            HashMap<String, Integer> locationFrequency = new HashMap<>();
            String bloggerHighestTopicProb = default_author;
            double highestTopicProb = 0;
            int blogPostsInThreshold = 0;

           // blogPostsInThreshold = getBlogPostsInThreshold(topic_threshold, topics, blogposts, blogDistributionMatrix, i, bloggers, locationFrequency, highestTopicProb, blogPostsInThreshold,bloggerHighestTopicProb);
            for (var doc : blogposts) {

                if(i < doc.theta.length){
                    if (doc.theta[i] > topic_threshold) {
                        blogPostsInThreshold++;
                        // Track author whose post has highest topic probability
                        if (doc.theta[i] > highestTopicProb) {
                            highestTopicProb = doc.theta[i];
                            bloggerHighestTopicProb = doc.blogger;
                        }
                        // Track Number of Occurence for each Blogger
                        occurrenceTrackerForEachBlogs(bloggers, locationFrequency, doc);
                        // Track Topic Overlap
                        trackTopicOverlap(topic_threshold, topics, blogDistributionMatrix, i, doc);
                    }
                }

            }
            bloggerMention[i] = bloggers.size();
            postMention[i] = blogPostsInThreshold;
            topBloggers[i] = bloggerHighestTopicProb;

        }
    }
    public static void trackTopicOverlap(double TOPIC_THRESHOLD, Map<String, ArrayList<HashMap<String, Double>>> topics, double[][] blogDistributionMatrix, int i, Data doc) {
        for (int j = 0; j < topics.size(); j++) {
            if (doc.theta[j] > TOPIC_THRESHOLD) {
                blogDistributionMatrix[i][j]++;
            }
        }
    }
    private int getBlogPostsInThreshold(double topic_threshold, Map<String, ArrayList<HashMap<String, Double>>> topics, ArrayList<Data> blogposts, double[][] blogDistributionMatrix, int i, HashMap<String, Integer> bloggers, HashMap<String, Integer> locationFrequency, double highestTopicProb, int blogPostsInThreshold, String bloggerHighestTopicProb) {
        for (var doc : blogposts) {
            if(i < doc.theta.length){
                if (doc.theta[i] > topic_threshold) {
                    blogPostsInThreshold++;
                    // Track author whose post has highest topic probability
                    if (doc.theta[i] > highestTopicProb) {
                        highestTopicProb = doc.theta[i];
                        bloggerHighestTopicProb = doc.blogger;
                    }
                    // Track Number of Occurence for each Blogger
                    occurrenceTrackerForEachBlogs(bloggers, locationFrequency, doc);
                    // Track Topic Overlap
                    trackTopicOverlap(topic_threshold, topics, blogDistributionMatrix, i, doc);
                }
            }

        }
        return blogPostsInThreshold;
    }
    public void occurrenceTrackerForEachBlogs(HashMap<String, Integer> bloggers, HashMap<String, Integer> locationFrequency, Data doc) {
        if (bloggers.containsKey(doc.blogger)) {
            bloggers.replace(doc.blogger, bloggers.get(doc.blogger) + 1);
        } else {
            bloggers.put(doc.blogger, 1);
        }
        // Track Location Frequency
        if (locationFrequency.containsKey(doc.location)) {
            locationFrequency.put(doc.location, locationFrequency.get(doc.location) + 1);
        } else {
            locationFrequency.put(doc.location, 1);
        }
    }
    private ArrayList<ArrayList<Double>> getChordDiagramMatrix(Map<String, ArrayList<HashMap<String, Double>>> topics, double[][] blogDistributionMatrix) {
        ArrayList<ArrayList<Double>> _chordDiagramMatrix = new ArrayList<>();
        for (int i = 0; i < topics.keySet().size(); i++) {
            ArrayList<Double> a = new ArrayList<>(1);
            _chordDiagramMatrix.add(a);
            for (int j = 0; j < topics.keySet().size(); j++) {
                a = _chordDiagramMatrix.get(_chordDiagramMatrix.size() - 1);
                a.add(blogDistributionMatrix[i][j]);
            }

        }
        return _chordDiagramMatrix;


    }
}
