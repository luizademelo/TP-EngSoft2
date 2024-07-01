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
import com.election.view.ReadAndPrintPresidential;
import com.election.view.ReadAndPrintUDepartment;

public class SecondRoundTest {
 
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
        Vote vote6 = new Vote("valid", candidate1);
        ElectionController.voteList.add(vote6);

        ElectionController.finishElection();
        List<Candidate> winners = ElectionController.getResults();
        Candidate winner = winners.get(0);

        assertEquals(winner.getName(), "Lula");
        assertEquals(winner.getVoteCount(), 4);
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
        Vote vote6 = new Vote("valid", candidate1);
        ElectionController.voteList.add(vote6);

        ElectionController.finishElection();
        List<Candidate> winners = ElectionController.getResults();
        Candidate winner = winners.get(0);

        assertEquals(winner.getName(), "Pedro Olmo");
        assertEquals(winner.getVoteCount(), 4);
        assertEquals(winners.size(), 1);

    }

 }