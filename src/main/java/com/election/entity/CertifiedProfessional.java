package com.election.entity;
public class CertifiedProfessional {

    private final String user;
    private final String password;

    public static class Builder {
        private String user;
        private String password;

        public Builder user(String user) {
            this.user = user;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public CertifiedProfessional build() {
            if (user == null)
                throw new IllegalArgumentException("user mustn't be null");

            if (user.isEmpty())
                throw new IllegalArgumentException("user mustn't be empty");

            if (password == null)
                throw new IllegalArgumentException("password mustn't be null");

            if (password.isEmpty())
                throw new IllegalArgumentException("password mustn't be empty");

            return new CertifiedProfessional(
                    this.user,
                    this.password);
        }
    }

    public String getPassword(){ return this.password;}

    public CertifiedProfessional(
            String user,
            String password) {
        this.user = user;
        this.password = password;
    }
}
