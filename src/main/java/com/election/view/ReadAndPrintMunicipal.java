package com.election.view;

import com.election.controller.ElectionController;
import com.election.controller.MunipalElectionController;
import com.election.entity.Candidate;
import com.election.entity.Voter;
import com.election.enums.RoleEnum; 
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.System.exit;
import com.election.enums.ElectionStatusEnum;

public class ReadAndPrintMunicipal extends ReadAndPrint{

    public static int showMenu(){
        print("Escolha uma opção:\n");
        print("(1) Entrar (Eleitor)");
        print("(2) Entrar (TSE)");
        print("(0) Fechar aplicação");
        return readInt();
    }

    public static void showVoterMenu(){
        print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
        print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
        if (ElectionController.currentElection.getStatus().equals(ElectionStatusEnum.NOT_INITIALIZED.name())) {
            print("A eleição ainda não foi inicializada, verifique com um funcionário do TSE");
            return;
        }
        if (ElectionController.currentElection.getStatus().equals(ElectionStatusEnum.FINISHED.name())) {
            print("A eleição ja foi encerrada, verifique com um funcionário do TSE");
            return;
        }
        Voter voter = ReadAndPrint.getVoter();
        if (voter == null)
            return;
        print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");

        print("Vamos começar!\n");
        print(
                "OBS:\n- A partir de agora caso você queira votar nulo você deve usar um numero composto de 0 (00 e 0000)\n- A partir de agora caso você queira votar branco você deve escrever br\n");
        print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");

        print("\nInsira seu voto para prefeito:\n\n");
         if (MunipalElectionController.voteMayor(voter))
           print("Voto para prefeito registrado com sucesso");
         print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");

    }

    public static void loadCandidates() {
        try{
            Path filePath = Paths.get("src/main/resources/municipalCandidates.txt");
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                var candidateData = line.split(",");
                ReadAndPrint.CandidateMap.put(Integer.valueOf(candidateData[0]),
                        new Candidate.Builder()
                                .electoralNumber(Integer.parseInt(candidateData[0]))
                                .name(candidateData[1])
                                .role(RoleEnum.MAYOR.name())
                                .build());
            }
        } catch (Exception e){
            print("Erro na inicialização dos dados");
            exit(1);
        }
    }

    public static void loadCandidatesSecondRound() {
        try{
            int codigo1, codigo2;
            print("Vamos começar!\n");
            print("\nInsira o codigo do primeiro candidato:\n\n");
            codigo1 = readInt();
            print("\nInsira o codigo do segundo candidato:\n\n");
            codigo2 = readInt();
            Path filePath = Paths.get("src/main/resources/municipalCandidates.txt");
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                var candidateData = line.split(",");
                if (Integer.parseInt(candidateData[0]) == codigo1 || Integer.parseInt(candidateData[0]) == codigo2){
                    ReadAndPrint.CandidateMap.put(Integer.valueOf(candidateData[0]),
                            new Candidate.Builder()
                                    .electoralNumber(Integer.parseInt(candidateData[0]))
                                    .name(candidateData[1])
                                    .role(RoleEnum.MAYOR.name())
                                    .build());
                }
            }
            if (CandidateMap.size() != 2){
                print("Candidatos Inválidos! Digite novamente");
                loadCandidatesSecondRound();
            }

        } catch (Exception e){
            print("Erro na inicialização dos dados");
            exit(1);
        }
    }
}
