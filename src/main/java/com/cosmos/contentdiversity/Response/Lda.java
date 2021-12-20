package com.cosmos.contentdiversity.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Comparator;

// // version 2.11.1
//  // version 2.11.1
/*
Root root = om.readValue(myJsonString), Root.class); */
public class Lda implements Comparable<Lda> {
    @JsonProperty("topicNumber")
    public int getTopicNumber() {
        return this.topicNumber;
    }

    public void setTopicNumber(int topicNumber) {
        this.topicNumber = topicNumber;
    }

    int topicNumber;

    @JsonProperty("v2")
    public double getV2() {
        return this.v2;
    }

    public void setV2(double v2) {
        this.v2 = v2;
    }

    double v2;


    @Override
    public int compareTo(@NotNull Lda o) {
        return this.getTopicNumber() - o.getTopicNumber();
    }
}
