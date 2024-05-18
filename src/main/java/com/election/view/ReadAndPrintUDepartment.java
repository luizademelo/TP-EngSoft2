package com.election.view;

import com.election.controller.ElectionController;
import com.election.controller.UDepartmentElectionController;
import com.election.entity.Candidate;
import com.election.entity.UVoter;
import com.election.entity.Voter;
import com.election.enums.ElectionStatusEnum;
import com.election.enums.RoleEnum;
import com.election.enums.VoterRoleEnum;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import com.election.entity.Candidate;
import com.election.enums.RoleEnum; 
import static java.lang.System.exit;

public class ReadAndPrintUDepartment extends ReadAndPrint{

    public static int showMenu(){
        print("Escolha uma opção:\n");
        print("(1) Entrar (Eleitor)");
        print("(2) Entrar (Organização)");
        print("(0) Fechar aplicação");
        return readInt();
    }

    public static void showVoterMenu(){
        print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
        print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
        if (ElectionController.currentElection.getStatus().equals(ElectionStatusEnum.NOT_INITIALIZED.name())) {
            print("A eleição ainda não foi inicializada, verifique com um funcionário responsável");
            return;
        }
        if (ElectionController.currentElection.getStatus().equals(ElectionStatusEnum.FINISHED.name())) {
            print("A eleição já foi encerrada, verifique com um funcionário responsável");
            return;
        }
        UVoter voter = ReadAndPrintUDepartment.getVoter();
        if (voter == null)
            return;
        print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");

        print("Vamos começar!\n");
        print(
                "OBS:\n- A partir de agora caso você queira votar nulo você deve usar um numero composto de 0 (00 e 0000)\n- A partir de agora caso você queira votar branco você deve escrever br\n");
        print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");

        print("\nInsira seu voto para chefe de departamento:\n\n");
         if (UDepartmentElectionController.voteChief(voter))
           print("Voto para chefe de departamento registrado com sucesso");
         print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");

    }

    public static void loadCandidates() {
        try{
            Path filePath = Paths.get("src/main/resources/universityCandidates.txt");
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                var candidateData = line.split(",");
                ReadAndPrint.CandidateMap.put(Integer.valueOf(candidateData[0]),
                        new Candidate.Builder()
                                .electoralNumber(Integer.parseInt(candidateData[0]))
                                .name(candidateData[1])
                                .role(RoleEnum.DEPARTMENT_CHIEF.name())
                                .build());
            }
        } catch (Exception e){
            print("Erro na inicialização dos dados");
            exit(1);
        }
    }

    public static UVoter getVoter() {
        print("Insira sua matricula:");
        String electoralCard = readString();
        Voter voter = VoterMap.get(electoralCard);
        if (voter == null) {
            print("Eleitor não encontrado, por favor confirme se a entrada está correta e tente novamente");
        } else {
            UVoter uVoter = new UVoter(voter.getElectoralCard(), voter.getName(), voter.getState());
            print("Olá, você é " + uVoter.name + " de " + uVoter.state + "?\n");
            print("(1) Sim\n(2) Não");
            int command = readInt();
            if (command == 1){
                print("\nInforme seu cargo\n(1) Professor\n(2) Aluno\n(3) Funcionario tercerio");
                command = readInt();
                switch (command) {
                    case 1:
                        uVoter.role = VoterRoleEnum.PROFESSOR;
                        break;
                    case 2:
                        uVoter.role = VoterRoleEnum.STUDENT;
                        break;
                    case 3:
                        uVoter.role = VoterRoleEnum.HELPER;
                        break;
                }
                return uVoter;
            }
            else if (command == 2)
                print("Ok, você será redirecionado para o menu inicial");
            else {
                print("Entrada inválida, tente novamente");
                return getVoter();
            }
        }
        return null;
    }

    public static void loadCandidatesSecondRound() {
        try{
            int codigo1, codigo2;
            print("Vamos começar!\n");
            print("\nInsira o codigo do primeiro candidato:\n\n");
            codigo1 = readInt();
            print("\nInsira o codigo do segundo candidato:\n\n");
            codigo2 = readInt();
            Path filePath = Paths.get("src/main/resources/universityCandidates.txt");
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                var candidateData = line.split(",");
                if (Integer.parseInt(candidateData[0]) == codigo1 || Integer.parseInt(candidateData[0]) == codigo2){
                    ReadAndPrint.CandidateMap.put(Integer.valueOf(candidateData[0]),
                            new Candidate.Builder()
                                    .electoralNumber(Integer.parseInt(candidateData[0]))
                                    .name(candidateData[1])
                                    .role(RoleEnum.DEPARTMENT_CHIEF.name())
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
