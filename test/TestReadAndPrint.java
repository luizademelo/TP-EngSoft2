import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

import com.election.entity.Candidate;
import com.election.entity.CertifiedProfessional;
import com.election.entity.Voter;
import com.election.view.ReadAndPrint;
import com.election.view.ReadAndPrintMunicipal;
import com.election.view.ReadAndPrintPresidential;

public class TestReadAndPrint {

    @Test
    public void testVoters() {
        ReadAndPrint.loadVoters();
        Voter voter = ReadAndPrint.VoterMap.get("123456789012");
        assertNotNull(voter);
    }

    @Test
    public void testLoadProfessionals() {
        ReadAndPrint.loadProfessionals();
        CertifiedProfessional professional = ReadAndPrint.CertifiedMap.get("cert");
        assertNotNull(professional);
    }

    @Test
    public void testLoadPresidentialCandidates() {
        ReadAndPrintPresidential.loadCandidates();
        Candidate candidate = ReadAndPrint.CandidateMap.get(13);
        assertEquals(candidate.getName(), "Lula");
    }

    @Test
    public void testLoadDeputyCandidates() {
        ReadAndPrintPresidential.loadCandidates();
        Candidate candidate = ReadAndPrint.CandidateMap.get(1312);
        assertEquals(candidate.getName(), "Reginaldo");
    }

    @Test
    public void testLoadMunicipalCandidates() {
        ReadAndPrintMunicipal.loadCandidates();
        Candidate candidate = ReadAndPrint.CandidateMap.get(55);
        assertEquals(candidate.getName(), "Kalil");
    }

    @Test
    public void testTotalVoters() {
        ReadAndPrint.loadVoters();
        int totalVoters = ReadAndPrint.VoterMap.size();
        assertEquals(totalVoters, 501); 
    }

    @Test
    public void testLoadVotersTwice() {
        ReadAndPrint.loadVoters();
        int totalVoters = ReadAndPrint.VoterMap.size();
        ReadAndPrint.loadVoters();
        int totalVotersAfterSecondLoad = ReadAndPrint.VoterMap.size();
        assertEquals(totalVoters, totalVotersAfterSecondLoad);
    }

    @Test
    public void testNonExistentVoter() {
        ReadAndPrint.loadVoters();
        Voter voter = ReadAndPrint.VoterMap.get("nonexistent");
        assertNull(voter);
    }
}
