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
import com.election.view.ReadAndPrintUDepartment;

public class ElectionTest {
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
}
