package com.election.entity;

import com.election.enums.RoleEnum;

public class Candidate {
    private final int electoralNumber;
    private int voteCount;  
    private final String name;
    private final RoleEnum role;

    public Candidate(String name, int electoralNumber, String role) {
        this.name = name;
        this.electoralNumber = electoralNumber;
        this.voteCount = 0;
        this.role = RoleEnum.valueOf(role);
    }

    public static class Builder {
        private int electoralNumber;
        private String name;
        private String role;

        public Candidate.Builder electoralNumber(int electoralNumber) {
            this.electoralNumber = electoralNumber;
            return this;
        }

        public Candidate.Builder name(String name) {
            this.name = name;
            return this;
        }
        public Candidate.Builder role(String role) {
            this.role = role;
            return this;
        }

        public Candidate build() {
            if (name == null)
                throw new IllegalArgumentException("name mustn't be null");

            if (name.isEmpty())
                throw new IllegalArgumentException("name mustn't be empty");

            return new Candidate(
                    this.name,
                    this.electoralNumber,
                    this.role);
        }
    }
    
    public int getVoteCount(){
        return this.voteCount; 
    }

    public String getRole() {return this.role.name();}

    public void setVoteCount(Integer voteCount){
        this.voteCount = voteCount;
    }
    @Override
    public String toString(){
        return ("Candidato: " + name + " \nVotos: " + this.voteCount); 
    }

}
