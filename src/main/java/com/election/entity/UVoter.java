package com.election.entity;

import com.election.enums.VoterRoleEnum;

public class UVoter extends Voter {

    public VoterRoleEnum role;
    public UVoter(String electoralCard, String name, String state) {
        super(electoralCard, name, state);
    }

    public String getRole(){
        return this.role.name();
    }
}
