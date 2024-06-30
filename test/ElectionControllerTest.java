import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.election.controller.ElectionController;

public class ElectionControllerTest {

    @Test
    public void testCreatePresidentialElection() {
        ElectionController.createElection("password", "presidencial");
        assertEquals(ElectionController.currentElection.getElectionType(), "PRESIDENTIAL");
    }

    @Test
    public void testCreateMunicipalElection() {
        ElectionController.createElection("password", "municipal");
        assertEquals(ElectionController.currentElection.getElectionType(), "MUNICIPAL");
    }

    @Test
    public void testCreateStateElection() {
        ElectionController.createElection("password", "estadual");
        assertEquals(ElectionController.currentElection.getElectionType(), "STATE");
    }

    @Test
    public void testCreateUDepartmentElection() {
        ElectionController.createElection("password", "universitaria");
        assertEquals(ElectionController.currentElection.getElectionType(), "UNIVERSITY");
    }
    
    @Test
    public void testGetValidVotes() {
        ElectionController.createElection("password", "presidencial");
        int validVotes = ElectionController.getValidVotes();
        assertEquals(validVotes >= 0, true);
    }

    @Test
    public void testGetNullVotes() {
        ElectionController.createElection("password", "presidencial");
        int nullVotes = ElectionController.getNullVotes();
        assertEquals(nullVotes >= 0, true);
    }

    @Test
    public void testGetWhiteVotes() {
        ElectionController.createElection("password", "presidencial");
        int whiteVotes = ElectionController.getWhiteVotes();
        assertEquals(whiteVotes >= 0, true);
    }
}
