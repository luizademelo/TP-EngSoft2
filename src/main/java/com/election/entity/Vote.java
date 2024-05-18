package com.election.entity;

public class Vote {
    private final String type;
    private Candidate candidate;
    private int weight = 1;

    public Vote(String type){
        this.type = type;
    }
    public Vote(String type, Candidate candidate){
        this.type = type;
        this.candidate = candidate;
    }

    public Candidate getCandidate(){
        return this.candidate; 
    }
    public String getType() { return this.type; }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getWeight() {
        return this.weight;
    }
}
