package com.election.entity;

import com.election.enums.VoterRoleEnum;

public class Voter {
    protected final String electoralCard;

    public final String name;

    public final String state;
    public  boolean alreadyVoted = false;

    public static class Builder {
        private String electoralCard;
        private String name;
        private String state;

        public Builder electoralCard(String electoralCard) {
            this.electoralCard = electoralCard;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Voter build() {
            if (electoralCard == null)
                throw new IllegalArgumentException("electoralCard mustn't be null");

            if (electoralCard.isEmpty())
                throw new IllegalArgumentException("electoralCard mustn't be empty");

            if (name == null)
                throw new IllegalArgumentException("name mustn't be null");

            if (name.isEmpty())
                throw new IllegalArgumentException("name mustn't be empty");

            if (state == null)
                throw new IllegalArgumentException("state mustn't be null");

            if (state.isEmpty())
                throw new IllegalArgumentException("state mustn't be empty");

            return new Voter(electoralCard, name, state);
        }
    }

    protected Voter(String electoralCard, String name, String state) {
        this.electoralCard = electoralCard;
        this.name = name;
        this.state = state;
    }

    public String getElectoralCard(){
        return this.electoralCard;
    }
    public String getName() {
        return name;
    }
    public String getState() {
        return state;
    }
}
