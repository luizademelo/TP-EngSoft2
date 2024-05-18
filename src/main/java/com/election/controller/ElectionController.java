package com.election.controller;

import com.election.entity.*;
import com.election.helper.MajorityVoteHelper;
import com.election.helper.VoteInterfaceHelper;
import com.election.helper.WeightedVoteHelper;
import com.election.view.ReadAndPrint;
import com.election.view.ReadAndPrintPresidential;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.election.view.ReadAndPrint.*;
import static java.lang.System.exit;


public class ElectionController {
    public static List<Candidate> candidatesList = new ArrayList<>();
    public static List<Vote> voteList = new ArrayList<>();
    static boolean exit = false;
    private static VoteInterfaceHelper countingStrategy;
    public static Election currentElection;
    public static void initializeElection(String electionPassword){
        try{

            while(!exit){
                print("Selecione o tipo de eleicao desejada:\n");
                print("(1) Presidencial");
                print("(2) Estadual");
                print("(3) Municipal");
                print("(4) Universitaria");
                print("(0) Fechar Aplicacao");
                int command = readInt();
                switch (command) {
                    case 1 -> {
                        createElection(electionPassword, "Presidencial");
                        exit = true;
                    }
                    case 2 -> {
                        createElection(electionPassword, "Estadual");
                        exit = true;
                    }
                    case 3 -> {
                        createElection(electionPassword, "Municipal");
                        exit = true;
                    }
                    case 4 -> {
                        createElection(electionPassword, "Universitaria");
                        exit = true;
                    }
                    case 0 -> exit(1);
                }
            }
        }catch(Exception e){
            print("Erro inesperado");
        }

    }

    private static void createElection(String electionPassword, String electionType) {
        if (electionType.equalsIgnoreCase("presidencial")) {
            currentElection =  new PresidentialElection("PRESIDENTIAL");
        } else if (electionType.equalsIgnoreCase("municipal")) {
            currentElection = new MunicipalElection("MUNICIPAL");
        } else if (electionType.equalsIgnoreCase("estadual")) {
            currentElection = new StateElection("STATE");
        }else if (electionType.equalsIgnoreCase("universitaria")) {
            currentElection = new UDepartamentElection("UNIVERSITY");
        }
        else {
            throw new IllegalArgumentException("Tipo de eleição inválido.");
        }
    }

    private static void setCountingStrategy(String electionType){
        switch (electionType) {
            case "PRESIDENTIAL", "STATE", "MUNICIPAL" -> {
                countingStrategy = new MajorityVoteHelper();
            }
            case "UNIVERSITY" -> {
                countingStrategy = new WeightedVoteHelper();
            }
        }
    }

    public static void finishElection(){
        currentElection.setStatus("FINISHED");
        setCountingStrategy(currentElection.getElectionType());
        countingStrategy.countVotes(voteList);
        computeRanking();
    }

    private static void computeRanking(){
        switch(currentElection.getElectionType()){
            case "PRESIDENTIAL" -> {PresidentialElectionController.computeVotes();}
            case "STATE" -> {StateElectionController.computeVotes();}
            case "MUNICIPAL" -> {MunipalElectionController.computeVotes();}
            case "UNIVERSITY" -> {UDepartmentElectionController.computeVotes();}
        }
    }

    public static List<Candidate> getResults(){
        List<Candidate> result = new ArrayList<>(); 
        switch(currentElection.getElectionType()){
            case "PRESIDENTIAL" -> {result = PresidentialElectionController.getWinners();}
            case "STATE" -> {result = StateElectionController.getWinners();}
            case "MUNICIPAL" -> {result = MunipalElectionController.getWinners();}
            case "UNIVERSITY" -> {result = UDepartmentElectionController.getWinners();}
        }
        return result; 
    }

    public static int getValidVotes(){return currentElection.getValidVotes();}
    public static int getNullVotes(){return currentElection.getNullVotes();}
    public static int getWhiteVotes(){return currentElection.getWhiteVotes();}
}
