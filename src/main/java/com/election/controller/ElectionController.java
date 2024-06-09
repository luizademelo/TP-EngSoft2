package com.election.controller;

import com.election.entity.*;
import com.election.enums.ElectionRoundEnum;
import com.election.helper.MajorityVoteHelper;
import com.election.helper.VoteInterfaceHelper;
import com.election.helper.WeightedVoteHelper;
import com.election.view.ReadAndPrint;
import com.election.view.ReadAndPrintMunicipal;
import com.election.view.ReadAndPrintPresidential;
import com.election.view.ReadAndPrintState;
import com.election.view.ReadAndPrintUDepartment;

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

    public static void createElectionMenu(String electionPassword) {
        try {
            while (!exit) {
                printElectionMenu();
                int command = readInt();
                if (command == 0)
                    exit(1);

                String electionType = getElectionType(command);
                if (electionType == "Invalida") {
                    print("Tipo inválido de eleição");
                    exit(1);
                }

                createElection(electionPassword, electionType);
                exit = true;
            }
        } catch (Exception e) {
            print("Erro inesperado");
        }
    }

    private static void printElectionMenu() {
        print("Selecione o tipo de eleicao desejada:\n");
        print("(1) Presidencial");
        print("(2) Estadual");
        print("(3) Municipal");
        print("(4) Universitaria");
        print("(0) Fechar Aplicacao");
    }

    private static String getElectionType(int command) {
        return switch (command) {
            case 1 -> "Presidencial";
            case 2 -> "Estadual";
            case 3 -> "Municipal";
            case 4 -> "Universitaria";
            default -> "Invalida";
        };
    }

    public static void createElection(String electionPassword, String electionType) {
        if (electionType.equalsIgnoreCase("presidencial")) {
            currentElection = new PresidentialElection("PRESIDENTIAL");
        } else if (electionType.equalsIgnoreCase("municipal")) {
            currentElection = new MunicipalElection("MUNICIPAL");
        } else if (electionType.equalsIgnoreCase("estadual")) {
            currentElection = new StateElection("STATE");
        } else if (electionType.equalsIgnoreCase("universitaria")) {
            currentElection = new UDepartamentElection("UNIVERSITY");
        } else {
            throw new IllegalArgumentException("Tipo de eleição inválido.");
        }
    }

    public static void setElectionRoundMenu() {
        print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
        print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
        print("Escolha uma opção de turno:\n");
        print("(1) Primeiro Turno");
        print("(2) Segundo turno");
        int round = readInt();
        if (round == 1)
            currentElection.setRound("FIRST_ROUND");
        else if (round == 2)
            currentElection.setRound("SECOND_ROUND");
        else {
            print("Opção invalida\n");
        }
    }

    private static void setCountingStrategy(String electionType) {
        switch (electionType) {
            case "PRESIDENTIAL", "STATE", "MUNICIPAL" -> {
                countingStrategy = new MajorityVoteHelper();
            }
            case "UNIVERSITY" -> {
                countingStrategy = new WeightedVoteHelper();
            }
        }
    }

    public static void initializePresidentialElection() {
        if (currentElection.getRound().equals(ElectionRoundEnum.FIRST_ROUND.name())) {
            ReadAndPrintPresidential.loadCandidates();
            PresidentialElectionController.startMenu();
        } else {
            ReadAndPrintPresidential.loadCandidatesSecondRound();
            PresidentialElectionController.startMenu();
        }
    }

    public static void initializeMunicipalEletion() {
        if (currentElection.getRound().equals(ElectionRoundEnum.FIRST_ROUND.name())) {
            ReadAndPrintMunicipal.loadCandidates();
            MunipalElectionController.startMenu();
        } else {
            ReadAndPrintMunicipal.loadCandidatesSecondRound();
            MunipalElectionController.startMenu();
        }
    }

    public static void initializeStateElection() {
        if (currentElection.getRound().equals(ElectionRoundEnum.FIRST_ROUND.name())) {
            ReadAndPrintState.loadCandidates();
            StateElectionController.startMenu();
        } else {
            ReadAndPrintState.loadCandidatesSecondRound();
            StateElectionController.startMenu();
        }
    }

    public static void initializeUniversityElection() {
        if (currentElection.getRound().equals(ElectionRoundEnum.FIRST_ROUND.name())) {
            ReadAndPrintUDepartment.loadCandidates();
            UDepartmentElectionController.startMenu();
        } else {
            ReadAndPrintUDepartment.loadCandidatesSecondRound();
            UDepartmentElectionController.startMenu();
        }
    }

    public static void finishElection() {
        currentElection.setStatus("FINISHED");
        setCountingStrategy(currentElection.getElectionType());
        countingStrategy.countVotes(voteList);
        computeRanking();
    }

    private static void computeRanking() {
        switch (currentElection.getElectionType()) {
            case "PRESIDENTIAL" -> {
                PresidentialElectionController.computeVotes();
            }
            case "STATE" -> {
                StateElectionController.computeVotes();
            }
            case "MUNICIPAL" -> {
                MunipalElectionController.computeVotes();
            }
            case "UNIVERSITY" -> {
                UDepartmentElectionController.computeVotes();
            }
        }
    }

    public static List<Candidate> getResults() {
        List<Candidate> result = new ArrayList<>();
        switch (currentElection.getElectionType()) {
            case "PRESIDENTIAL" -> {
                result = PresidentialElectionController.getWinners();
            }
            case "STATE" -> {
                result = StateElectionController.getWinners();
            }
            case "MUNICIPAL" -> {
                result = MunipalElectionController.getWinners();
            }
            case "UNIVERSITY" -> {
                result = UDepartmentElectionController.getWinners();
            }
        }
        return result;
    }

    public static int getValidVotes() {
        return currentElection.getValidVotes();
    }

    public static int getNullVotes() {
        return currentElection.getNullVotes();
    }

    public static int getWhiteVotes() {
        return currentElection.getWhiteVotes();
    }
}
