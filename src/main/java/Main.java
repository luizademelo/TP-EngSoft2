import com.election.controller.*;
import com.election.enums.ElectionRoundEnum;
import com.election.view.*;

public class Main {



    public static void main(String [] args){
        String electionPassword = "password";

        ElectionController.createElectionMenu(electionPassword);

        // Startar todos os eleitores e profissionais
        ReadAndPrint.loadVotersAndProfessionals();
        
        ElectionController.setElectionRoundMenu();

        switch (ElectionController.currentElection.getElectionType()){
            case "PRESIDENTIAL" -> {
                ElectionController.initializePresidentialElection();
            }

            case "MUNICIPAL" -> {
                ElectionController.initializeMunicipalEletion();
            }

            case "STATE" -> {
                ElectionController.initializeStateElection();
            }

            case "UNIVERSITY" -> {
                ElectionController.initializeUniversityElection();
            }
        }
    }
}