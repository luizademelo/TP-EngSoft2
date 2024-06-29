import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.election.controller.ElectionController;
import com.election.enums.ElectionTypeEnum;

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

}
