package com.election.helper;

import com.election.controller.ElectionController;
import com.election.entity.Candidate;
import com.election.entity.Vote;

import java.util.List;
import java.util.Map;

import static com.election.controller.ElectionController.candidatesList;
import static com.election.view.ReadAndPrint.CandidateMap;

public class WeightedVoteHelper implements VoteInterfaceHelper{
    public static final int VOTE_WEIGHT = 3;
    @Override
    public void countVotes(List<Vote> votes) {
        for (Map.Entry<Integer, Candidate> candidate : CandidateMap.entrySet()){
            long numberOfVotesProfessors = ElectionController.voteList.stream()
                    .filter(vote -> vote.getCandidate().equals(candidate.getValue()))
                    .filter(vote -> vote.getWeight() == VOTE_WEIGHT)
                    .count();
            long numberOfVotesOthers = ElectionController.voteList.stream()
                    .filter(vote -> vote.getCandidate().equals(candidate.getValue()))
                    .filter(vote -> vote.getWeight() == 1)
                    .count();
            candidate.getValue().setVoteCount((int) (numberOfVotesOthers + (numberOfVotesProfessors * VOTE_WEIGHT)));
            candidatesList.add(candidate.getValue());
        }
    }
}
