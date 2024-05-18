package com.election.entity;

import com.election.enums.*;

public class Election {
    protected int totalValidVotes = 0;
    protected int totalNullVotes = 0;
    protected int totalWhiteVotes = 0;

    protected ElectionTypeEnum electionType;
    protected ElectionStatusEnum electionStatus;
    protected ElectionRoundEnum electionRound;
    protected Election(String electionType) {
        this.electionStatus = ElectionStatusEnum.NOT_INITIALIZED;
        this.electionType = ElectionTypeEnum.valueOf(electionType);
    }
    public String getStatus() {
        return this.electionStatus.name();
    }
    public String getElectionType(){
        return this.electionType.name();
    }
    public void setStatus(String status){
        this.electionStatus = ElectionStatusEnum.valueOf(status);
    }

    public int getValidVotes(){ return this.totalValidVotes; } 
    public int getNullVotes(){ return this.totalNullVotes; }
    public int getWhiteVotes(){ return this.totalWhiteVotes; }
  
    public void setRound(String round){
        this.electionRound = ElectionRoundEnum.valueOf(round);
    }

    public String getRound() { return this.electionRound.name(); }

}
