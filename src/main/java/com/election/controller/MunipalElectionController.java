package com.election.controller;

import com.election.entity.Candidate;
import com.election.entity.Election;
import com.election.view.ReadAndPrint;
import com.election.view.ReadAndPrintMunicipal;
import com.election.entity.Vote;
import com.election.entity.Voter;
import com.election.enums.ElectionRoundEnum;
import com.election.enums.RoleEnum; 
import static com.election.controller.ElectionController.*;

import java.util.ArrayList;
import java.util.List; 

import static com.election.view.ReadAndPrint.*; 

public class MunipalElectionController {

    private static List<Candidate> candidateRankingMayoral = new ArrayList<>(); 
    private static List<Candidate> candidateRankingCouncilor = new ArrayList<>(); 

    public static void startMenu() {
        try {
            boolean menuOn = true;
            while (menuOn) {
                int command = ReadAndPrintMunicipal.showMenu();
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
        ReadAndPrintMunicipal.showVoterMenu();
    }
    
    public static boolean voteMayor(Voter voter){
        Vote mayorVote = ReadAndPrint.readVote();
        ElectionController.voteList.add(mayorVote); 
        voter.alreadyVoted = true; 
        return true;
    }

    public static void computeVotes(){
        List<Candidate> mayoral = new ArrayList<>(ElectionController.candidatesList.stream()
                .filter(candidate -> candidate.getRole().equals(RoleEnum.MAYOR.name()))
                .toList());  
        mayoral.sort((c1,c2) -> Integer.compare(c2.getVoteCount(), c1.getVoteCount())); 
        candidateRankingMayoral = mayoral; 

        // List<Candidate> councilor = new ArrayList<>(ElectionController.candidatesList.stream()
        //         .filter(candidate -> candidate.getRole().equals(RoleEnum.CITY_COUNCILOR.name()))
        //         .toList()); 
        // councilor.sort((c1,c2) -> Integer.compare(c2.getVoteCount(), c1.getVoteCount())); 
        // candidateRankingCouncilor = councilor; 
    }

    public static List<Candidate> getWinners(){
        List<Candidate> result = new ArrayList<>(); 
        result.add(candidateRankingMayoral.get(0)); 
        //result.add(candidateRankingCouncilor.get(0)); 
        return result; 
    }

}
