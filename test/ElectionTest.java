import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.election.controller.ElectionController;
import com.election.controller.UDepartmentElectionController;
import com.election.entity.Candidate;
import com.election.entity.UVoter;
import com.election.entity.Vote;
import com.election.entity.Voter;
import com.election.view.ReadAndPrint;
import com.election.view.ReadAndPrintMunicipal;
import com.election.view.ReadAndPrintPresidential;
import com.election.view.ReadAndPrintState;
import com.election.view.ReadAndPrintUDepartment;

public class ElectionTest {

    @Test
    public void testPresidentialElection() {
        ElectionController.createElection("password", "presidencial");
        ReadAndPrint.loadVotersAndProfessionals();
        ElectionController.currentElection.setRound("FIRST_ROUND");
        ReadAndPrintPresidential.loadCandidates();
        ElectionController.currentElection.setStatus("RUNNING");
        Candidate candidate1 = ReadAndPrint.CandidateMap.get(13);
        Vote vote1 = new Vote("valid", candidate1);
        ElectionController.voteList.add(vote1);
        Candidate candidate2 = ReadAndPrint.CandidateMap.get(1212);
        Vote vote2 = new Vote("valid", candidate2);
        ElectionController.voteList.add(vote2);
        ElectionController.finishElection();
        List<Candidate> winners = ElectionController.getResults();
        Candidate c1 = winners.get(0);
        Candidate c2 = winners.get(1);
        assertEquals(c1.getName(), "Lula");
        assertEquals(c2.getName(), "Duda Salabert");

    }

    @Test
    public void testMunicipalElection() {
        ElectionController.createElection("password", "municipal");
        ReadAndPrint.loadVotersAndProfessionals();
        ElectionController.currentElection.setRound("FIRST_ROUND");
        ReadAndPrintMunicipal.loadCandidates();
        ElectionController.currentElection.setStatus("RUNNING");
        Candidate candidate = ReadAndPrint.CandidateMap.get(35);
        Vote vote = new Vote("valid", candidate);
        ElectionController.voteList.add(vote);
        ElectionController.finishElection();
        List<Candidate> winners = ElectionController.getResults();
        Candidate c = winners.get(0);
        assertEquals(c.getName(), "Edmar");
    }

    @Test
    public void testStateElection() {
        ElectionController.createElection("password", "estadual");
        ReadAndPrint.loadVotersAndProfessionals();
        ElectionController.currentElection.setRound("FIRST_ROUND");
        ReadAndPrintState.loadCandidates();
        ElectionController.currentElection.setStatus("RUNNING");
        Candidate candidate = ReadAndPrint.CandidateMap.get(15456);
        Vote vote = new Vote("valid", candidate);
        ElectionController.voteList.add(vote);
        ElectionController.finishElection();
        List<Candidate> winners = ElectionController.getResults();
        Candidate c = winners.get(0);
        assertEquals(c.getName(), "Ivo");
    }

    @Test
    public void testUdepartmentElection() {
        ElectionController.createElection("password", "universitaria");
        ReadAndPrint.loadVotersAndProfessionals();
        ElectionController.currentElection.setRound("FIRST_ROUND");
        ReadAndPrintUDepartment.loadCandidates();
        ElectionController.currentElection.setStatus("RUNNING");
        Candidate candidate = ReadAndPrint.CandidateMap.get(Integer.parseInt("2"));
        Vote vote = new Vote("valid", candidate);
        ElectionController.voteList.add(vote);
        ElectionController.finishElection();
        List<Candidate> winners = ElectionController.getResults();
        Candidate c = winners.get(0);
        assertEquals(c.getName(), "Pedro Olmo");
    }

    @Test
    public void testSecondRoundPresidentialElection() {

        ElectionController.createElection("password", "presidencial");
        ReadAndPrint.loadVotersAndProfessionals();
        ElectionController.currentElection.setRound("SECOND_ROUND");
        ReadAndPrintPresidential.loadCandidates();
        ElectionController.currentElection.setStatus("RUNNING");

        Candidate candidate1 = ReadAndPrint.CandidateMap.get(13);
        Candidate candidate2 = ReadAndPrint.CandidateMap.get(22);

        Vote vote1 = new Vote("valid", candidate1);
        ElectionController.voteList.add(vote1);
        Vote vote2 = new Vote("valid", candidate2);
        ElectionController.voteList.add(vote2);
        Vote vote3 = new Vote("valid", candidate2);
        ElectionController.voteList.add(vote3);
        Vote vote4 = new Vote("valid", candidate1);
        ElectionController.voteList.add(vote4);
        Vote vote5 = new Vote("valid", candidate1);
        ElectionController.voteList.add(vote5);

        ElectionController.finishElection();
        List<Candidate> winners = ElectionController.getResults();
        Candidate winner = winners.get(0);

        assertEquals(winner.getName(), "Lula");
        assertEquals(winner.getVoteCount(), 3);
        assertEquals(winners.size(), 1);
    }

    @Test
    public void testSecondRoundUdepartmentElection() {

        ElectionController.createElection("password", "universitaria");
        ReadAndPrint.loadVotersAndProfessionals();
        ElectionController.currentElection.setRound("SECOND_ROUND");
        ReadAndPrintUDepartment.loadCandidates();
        ElectionController.currentElection.setStatus("RUNNING");

        Candidate candidate1 = ReadAndPrint.CandidateMap.get(Integer.parseInt("3"));
        Candidate candidate2 = ReadAndPrint.CandidateMap.get(Integer.parseInt("2"));

        Vote vote1 = new Vote("valid", candidate2);
        ElectionController.voteList.add(vote1);
        Vote vote2 = new Vote("valid", candidate1);
        ElectionController.voteList.add(vote2);
        Vote vote3 = new Vote("valid", candidate2);
        ElectionController.voteList.add(vote3);
        Vote vote4 = new Vote("valid", candidate2);
        ElectionController.voteList.add(vote4);
        Vote vote5 = new Vote("valid", candidate2);
        ElectionController.voteList.add(vote5);

        ElectionController.finishElection();
        List<Candidate> winners = ElectionController.getResults();
        Candidate winner = winners.get(0);

        assertEquals(winner.getName(), "Pedro Olmo");
        assertEquals(winner.getVoteCount(), 4);
        assertEquals(winners.size(), 1);

    }
}
