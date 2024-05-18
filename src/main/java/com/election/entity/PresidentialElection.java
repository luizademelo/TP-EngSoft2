package com.election.entity;

public class PresidentialElection extends Election {

    protected int totalPresidentialValidVotes = 0;
    protected int totalPresidentialWhiteVotes = 0;
    protected  int totalPresidentialNullVotes = 0;
    protected int totalFederalValidVotes = 0;
    protected int totalFederalWhiteVotes = 0;
    protected  int totalFederalNullVotes = 0;


    public PresidentialElection(String electionType) {
        super(electionType);
    }

}
