package com.election.entity;

public class StateElection extends Election {

    protected int totalGovernorValidVotes = 0;
    protected int totalGovernorWhiteVotes = 0;
    protected  int totalGovernorNullVotes = 0;

    public StateElection(String electionType) {
        super(electionType);
    }
}
