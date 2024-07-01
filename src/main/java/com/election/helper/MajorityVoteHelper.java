package com.election.helper;

import com.election.controller.ElectionController;
import com.election.entity.Candidate;
import com.election.entity.Vote;
import java.util.List;
import java.util.Map;
import static com.election.view.ReadAndPrint.CandidateMap;

public class MajorityVoteHelper implements VoteInterfaceHelper {
    @Override
    public void countVotes(List<Vote> votes) {
        for (Map.Entry<Integer, Candidate> candidate : CandidateMap.entrySet()) {
            long numberOfVotes = votes.stream()
                    .filter(vote -> vote.getCandidate() != null && vote.getCandidate().equals(candidate.getValue()))
                    .count();
            candidate.getValue().setVoteCount((int) numberOfVotes);
            ElectionController.candidatesList.add(candidate.getValue());
        }
    }
}
