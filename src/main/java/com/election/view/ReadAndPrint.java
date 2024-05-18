package com.election.view;

import com.election.controller.ElectionController;
import com.election.entity.Candidate;
import com.election.entity.CertifiedProfessional;
import com.election.entity.Vote;
import com.election.entity.Voter;
import com.election.enums.ElectionStatusEnum;


import java.io.BufferedReader;
import java.nio.file.Paths;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Files;
import java.util.Objects;

import static java.lang.System.exit;

public class ReadAndPrint {

    protected static final Map<String, Voter> VoterMap = new HashMap<>();
    public static final Map<Integer, Candidate> CandidateMap = new HashMap<>();
    private static final Map<String, CertifiedProfessional> CertifiedMap = new HashMap<>();
    private static final BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
    
    public static void print(String output) {
        System.out.println(output);
    }
    
    public static String readString() {
        try {
            return scanner.readLine();
        } catch (Exception e) {
            print("\nErro na leitura de entrada, digite novamente");
            return readString();
        }
    }

    public static int readInt() {
        try {
            return Integer.parseInt(readString());
        } catch (Exception e) {
            print("\nErro na leitura de entrada, digite novamente");
            return readInt();
        }
    }

    public static void preElectionMenu(){
        print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
        print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
        print("Escolha uma opção de turno:\n");
        print("(1) Primeiro Turno");
        print("(2) Segundo turno");
        int round = readInt();
        if (round == 1) ElectionController.currentElection.setRound("FIRST_ROUND");
        else if (round == 2) ElectionController.currentElection.setRound("SECOND_ROUND");
        else{
            print("Opção invalida\n");
        }
    }

    public static void loadVoters() {
        try {
            Path filePath = Paths.get("src/main/resources/voterLoad.txt");
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                var voterData = line.split(",");
                VoterMap.put(voterData[0],
                        new Voter.Builder()
                                .electoralCard(voterData[0])
                                .name(voterData[1])
                                .state(voterData[2])
                                .build());
            }
        } catch (Exception e) {
            print("Erro na inicialização dos dados de eleitores");
            exit(1);
        }
    }

    public static void loadProfessionals() {
        try{
            Path filePath = Paths.get("src/main/resources/certifiedProfessionals.txt");
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                var professionalData = line.split(",");
                CertifiedMap.put(professionalData[0],
                        new CertifiedProfessional.Builder()
                                .user(professionalData[0])
                                .password(professionalData[1])
                                .build());
            }

        } catch (Exception e){
            print("Erro na inicialização dos dados");
            exit(1);
        }
    }

    public static Voter getVoter() {
        print("Insira seu título de eleitor:");
        String electoralCard = readString();
        Voter voter = VoterMap.get(electoralCard);
        if (voter == null) {
            print("Eleitor não encontrado, por favor confirme se a entrada está correta e tente novamente");
        } else {
            print("Olá, você é " + voter.name + " de " + voter.state + "?\n");
            print("(1) Sim\n(2) Não");
            int command = readInt();
            if (command == 1)
                return voter;
            else if (command == 2)
                print("Ok, você será redirecionado para o menu inicial");
            else {
                print("Entrada inválida, tente novamente");
                return getVoter();
            }
        }
        return null;
    }

    public static Vote readVote(){
        print("\nInsira o número do candidato:\nEx.: 13\nOU 0 para nulo OU br para branco\n");
        String candidateNumber = readString();
        Vote vote;
        switch (candidateNumber) {
            case "br" -> {
                vote = new Vote(candidateNumber);
                return vote;
            }
            case "0" -> {
                vote = new Vote("null");
                return vote;
            }
            default -> {
                Candidate candidate = CandidateMap.get(Integer.parseInt(candidateNumber));
                if (candidate == null) {
                    print("Candidato não encontrado, por favor confirme se a entrada está correta e tente novamente");
                    readVote();
                } else {
                    vote = new Vote("valid", candidate);
                    return vote;
                }
            }
        }
        return vote = new Vote("null");
    }

    public static void certifiedProfessionalMenu(){
        int command;
        String user, password;
        print("\nFazer login (1)\nSair (2)\n");
        command = readInt();
        boolean state = true;
        if (command == 1){
            while (state){
                print("\nInsira o usuario:\n");
                user = readString();
                print("\nInsira a senha:\n");
                password = readString();
                CertifiedProfessional professional = CertifiedMap.get(user);
                if (professional == null || !professional.getPassword().equals(password)){
                    print("\nUsuario ou senha Incorretos! Digite novamente.:\n");
                }else {
                    if (ElectionController.currentElection.getStatus().equals(ElectionStatusEnum.NOT_INITIALIZED.name())){
                        print("\nIniciar Sessão (1)\nSair (2)\n");
                        command = readInt();
                        if (command == 1 ){
                            ElectionController.currentElection.setStatus("RUNNING");
                            print("\nSessão iniciada com Sucesso!\n");
                            state = false;
                        }
                        else if(command == 2){
                            state = false;
                        }
                    }
                    else if (ElectionController.currentElection.getStatus().equals(ElectionStatusEnum.RUNNING.name())){
                        print("\nFinalizar Sessão (1)\nSair (2)\n");
                        command = readInt();
                        if (command == 1 ){
                            ElectionController.finishElection();
                            print("\nSessão finalizada com Sucesso!\n");
                            state = false;
                        }
                        else if(command == 2){
                            state = false;
                        }
                    }else if(ElectionController.currentElection.getStatus().equals(ElectionStatusEnum.FINISHED.name())){
                        print("\nVer Resultados (1)\nSair (2)\n"); 
                        command = readInt(); 
                        if(command == 1){
                            showResults();
                            state = false;
                        }
                        else if(command == 2){
                            state = false; 
                        }
                    }
                }
            }
        }
    }

    public static void showResults(){
        // print("Resultado da Eleicao:");
        // print("Votos validos: " + ElectionController.getValidVotes());
        // print("Votos nulos: " + ElectionController.getNullVotes());
        // print("Votos brancos: " +  ElectionController.getWhiteVotes()); 
        // print("\n"); 

        List<Candidate> winners = ElectionController.getResults(); 
        for(Candidate candidate : winners){
            print("O vencedor para o cargo de " + candidate.getRole() + " foi"); 
            print(candidate.toString()); 
        }
    }


}
