package com.election.entity;

import java.util.ArrayList;
import java.util.List;

public class MunicipalElection extends Election {
    public static List<Candidate> candidateRankingMayor = new ArrayList<>();
    public static List<Candidate> candidateRankingCityCounciler = new ArrayList<>();
    protected int totalMayorValidVotes = 0;
    protected int totalMayorWhiteVotes = 0;
    protected  int totalMayorNullVotes = 0;

    public MunicipalElection(String electionType) {
        super(electionType);
    }
}
