package com.election.controller;

import com.election.entity.UVoter;
import com.election.enums.VoterRoleEnum;
import com.election.view.ReadAndPrint;
import com.election.view.ReadAndPrintUDepartment;
import com.election.entity.Candidate;
import com.election.entity.Vote;
import com.election.entity.Voter;
import com.election.enums.RoleEnum;

import static com.election.view.ReadAndPrint.print;

import java.util.ArrayList; 
import java.util.List; 

public class UDepartmentElectionController {

    public static final int WEIGHT = 3;
    private static List<Candidate> candidateRankingDepartmental = new ArrayList<>();

    public static void startMenu() {
        try {
            boolean menuOn = true;
            while (menuOn) {
                int command = ReadAndPrintUDepartment.showMenu();
                switch (command) {
                    case 1 -> voterMenu();
                    case 2 -> ReadAndPrint.certifiedProfessionalMenu();
                    case 0 -> menuOn = false;
                    default -> print("Comando inválido\n");
                }
            }
        } catch (Exception e) {
            print("Erro inesperado\n");
        }
    }
    
    private static void voterMenu() {
        ReadAndPrintUDepartment.showVoterMenu();
    }

    public static boolean voteChief(UVoter voter){
        Vote chiefVote = ReadAndPrint.readVote();
        if (voter.getRole().equals(VoterRoleEnum.PROFESSOR.name())){
            chiefVote.setWeight(WEIGHT);
        }
        ElectionController.voteList.add(chiefVote); 
        voter.alreadyVoted = true; 
        return true;
    }

    public static void computeVotes(){
        List<Candidate> departmental = new ArrayList<>(ElectionController.candidatesList.stream()
                .filter(candidate -> candidate.getRole().equals(RoleEnum.DEPARTMENT_CHIEF.name()))
                .toList()); 
        departmental.sort((c1,c2) -> Integer.compare(c2.getVoteCount(), c1.getVoteCount()));
        candidateRankingDepartmental = departmental; 
    }

    public static List<Candidate> getWinners(){
        List<Candidate> result = new ArrayList<>(); 
        result.add(candidateRankingDepartmental.get(0)); 
        return result; 
    }

}
