import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.election.entity.Voter;
import com.election.view.ReadAndPrint;

public class TestReadAndPrint {

    @Test
    public void testVoters() {
        ReadAndPrint.loadVoters();
        Voter voter = ReadAndPrint.VoterMap.get("123456789012");
        assertNotNull(voter);
    }
}
