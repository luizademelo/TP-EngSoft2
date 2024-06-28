import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import com.election.controller.ElectionController;
import com.election.entity.Candidate;
import com.election.entity.Vote;
import com.election.enums.RoleEnum;
import com.election.helper.MajorityVoteHelper;
import com.election.helper.VoteInterfaceHelper;
import com.election.helper.WeightedVoteHelper;
import com.election.view.ReadAndPrint;

public class VoteHelperTest {

    @Test
    public void testMajorityVoteHelper() {
        ElectionController.candidatesList = new ArrayList<>();
        Candidate candidate = new Candidate("Lula", 13, RoleEnum.PRESIDENT.name());
        ReadAndPrint.CandidateMap.put(13, candidate);
        List<Vote> l = new ArrayList<>();
        l.add(new Vote("valid", candidate));
        l.add(new Vote("valid", candidate));
        MajorityVoteHelper helper = new MajorityVoteHelper();
        helper.countVotes(l);
        assertEquals(ElectionController.candidatesList.get(0).getVoteCount(), 2);
    }

    @Test
    public void testWeightedVoteHelper() {
        ElectionController.candidatesList = new ArrayList<>();
        Candidate candidate = new Candidate("Lula", 13, RoleEnum.PRESIDENT.name());
        ReadAndPrint.CandidateMap.put(13, candidate);
        List<Vote> l = new ArrayList<>();
        Vote voteNotWeighted = new Vote("valid", candidate);
        Vote voteWeighted = new Vote("valid", candidate);
        voteWeighted.setWeight(3);
        l.add(voteNotWeighted);
        l.add(voteWeighted);
        WeightedVoteHelper helper = new WeightedVoteHelper();
        helper.countVotes(l);
        Collections.sort(ElectionController.candidatesList,
                Comparator.comparingInt(Candidate::getVoteCount).reversed());
        assertEquals(ElectionController.candidatesList.get(0).getVoteCount(), 4);
    }

}
