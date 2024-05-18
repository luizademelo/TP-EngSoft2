import com.election.controller.*;
import com.election.enums.ElectionRoundEnum;
import com.election.view.*;

public class Main {
    public static void main(String [] args){
        String electionPassword = "password";

        ElectionController.initializeElection(electionPassword);

        // Startar todos os eleitores e profissionais
        ReadAndPrint.loadVoters();
        ReadAndPrint.loadProfessionals();
        ReadAndPrint.preElectionMenu();

        switch (ElectionController.currentElection.getElectionType()){
            case "PRESIDENTIAL" -> {
                if (ElectionController.currentElection.getRound().equals(ElectionRoundEnum.FIRST_ROUND.name())){
                    ReadAndPrintPresidential.loadCandidates();
                    PresidentialElectionController.startMenu();
                }else{
                    ReadAndPrintPresidential.loadCandidatesSecondRound();
                    PresidentialElectionController.startMenu();
                }
            }

            case "MUNICIPAL" -> {
                if (ElectionController.currentElection.getRound().equals(ElectionRoundEnum.FIRST_ROUND.name())){
                    ReadAndPrintMunicipal.loadCandidates();
                    MunipalElectionController.startMenu();
                }else{
                    ReadAndPrintMunicipal.loadCandidatesSecondRound();
                    MunipalElectionController.startMenu();
                }
            }

            case "STATE" -> {
                if (ElectionController.currentElection.getRound().equals(ElectionRoundEnum.FIRST_ROUND.name())){
                    ReadAndPrintState.loadCandidates();
                    StateElectionController.startMenu();
                }else{
                    ReadAndPrintState.loadCandidatesSecondRound();
                    StateElectionController.startMenu();
                }
            }

            case "UNIVERSITY" -> {
                if (ElectionController.currentElection.getRound().equals(ElectionRoundEnum.FIRST_ROUND.name())){
                    ReadAndPrintUDepartment.loadCandidates();
                    UDepartmentElectionController.startMenu();
                }else{
                    ReadAndPrintUDepartment.loadCandidatesSecondRound();
                    UDepartmentElectionController.startMenu();
                }
            }
        }
    }
}