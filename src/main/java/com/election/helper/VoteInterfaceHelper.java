package com.election.helper;

import com.election.entity.Vote;

import java.util.List;

public interface VoteInterfaceHelper {
    void countVotes(List<Vote> votes);
}