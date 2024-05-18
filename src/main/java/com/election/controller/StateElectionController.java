package com.election.controller;

import com.election.entity.Candidate;
import com.election.view.ReadAndPrint;
import com.election.view.ReadAndPrintState;
import com.election.entity.Vote;
import com.election.entity.Voter;
import com.election.enums.RoleEnum;

import java.util.ArrayList;
import java.util.List;

import static com.election.view.ReadAndPrint.print;

public class StateElectionController {
    public static List<Candidate> candidateRankingGovernor = new ArrayList<>();
    public static List<Candidate> candidateRankingState = new ArrayList<>();

    public static void startMenu() {
        try {
            boolean menuOn = true;
            while (menuOn) {
                int command = ReadAndPrintState.showMenu();
                switch (command) {
                    case 1 -> voterMenu();
                    case 2 -> ReadAndPrint.certifiedProfessionalMenu();
                    case 0 -> menuOn = false;
                    default -> print("Comando inválido\n");
                }
            }
        } catch (Exception e) {
            print("Erro inesperado\n");
            e.printStackTrace(); 
        }
    }
    
    private static void voterMenu() {
        ReadAndPrintState.showVoterMenu();
    }

    public static boolean voteGovernor(Voter voter){
        Vote governorVote = ReadAndPrint.readVote();
        ElectionController.voteList.add(governorVote); 
        voter.alreadyVoted = true; 
        return true;
    }

    public static void computeVotes(){
        List<Candidate> governmental = new ArrayList<>(ElectionController.candidatesList.stream()
                .filter(candidate -> candidate.getRole().equals(RoleEnum.GOVERNOR.name()))
                .toList()); 
        governmental.sort((c1,c2) -> Integer.compare(c2.getVoteCount(), c1.getVoteCount())); 
        candidateRankingGovernor = governmental; 
        // List<Candidate> state = new ArrayList<>(ElectionController.candidatesList.stream()
        //         .filter(candidate -> candidate.getRole().equals(RoleEnum.STATE_DEPUTY.name()))
        //         .toList());
        // state.sort((c1,c2) -> Integer.compare(c2.getVoteCount(), c1.getVoteCount()));
        // candidateRankingState = state;  
    }

    public static List<Candidate> getWinners(){
        List<Candidate> result = new ArrayList<>(); 
        result.add(candidateRankingGovernor.get(0)); 
        //result.add(candidateRankingState.get(0)); 
        return result; 
    }

}
