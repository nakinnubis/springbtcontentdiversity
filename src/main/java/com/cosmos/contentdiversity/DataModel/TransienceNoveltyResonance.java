package com.cosmos.contentdiversity.DataModel;

import java.util.ArrayList;

public class TransienceNoveltyResonance {
    private ArrayList<Novelty> novelties;
    private  ArrayList<Transience> transiences;
    private   ArrayList<Resonance> resonances;

    public  ArrayList<Novelty> getNovelties() {
        return novelties;
    }

    public void setNovelties(ArrayList<Novelty> novelties) {
        this.novelties = novelties;
    }

    public   ArrayList<Transience> getTransiences() {
        return transiences;
    }

    public void setTransiences( ArrayList<Transience> transiences) {
        this.transiences = transiences;
    }

    public ArrayList<Resonance> getResonances() {
        return resonances;
    }

    public void setResonances(ArrayList<Resonance> resonances) {
        this.resonances = resonances;
    }
}