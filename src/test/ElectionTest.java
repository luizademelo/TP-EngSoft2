import com.election.entity.Candidate;
import com.election.entity.Vote;
import com.election.view.ReadAndPrint;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.election.view.ReadAndPrint.CandidateMap;

public class ElectionTest {
    @Test
    public void teste1() throws IOException, URISyntaxException {
        Map<Integer, Candidate> CandidateMap = new HashMap<>();
        List<Vote> voteList = new ArrayList<>();
        List<Candidate> candidateRanking = new ArrayList<>();
        Path filePath = Paths.get("src/main/resources/presidentialCandidates.txt");
        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines) {
            var candidateData = line.split(",");
            CandidateMap.put(Integer.valueOf(candidateData[0]),
                    new Candidate.Builder()
                            .electoralNumber(Integer.parseInt(candidateData[0]))
                            .name(candidateData[1])
                            .build());
        }

        Vote vote1 = new Vote("valid", CandidateMap.get(13));
        Vote vote2 = new Vote("valid", CandidateMap.get(13));
        Vote vote3 = new Vote("valid", CandidateMap.get(13));
        Vote vote4 = new Vote("valid", CandidateMap.get(7070));

        voteList.add(vote1);
        voteList.add(vote2);
        voteList.add(vote3);
        voteList.add(vote4);

        for (Map.Entry<Integer, Candidate> candidate : CandidateMap.entrySet()){
            long numberOfVotes = voteList.stream()
                    .filter(vote -> vote.getCandidate().equals(candidate.getValue()))
                    .count();
            candidate.getValue().setVoteCount((int) numberOfVotes);
            candidateRanking.add(candidate.getValue());
        }
        candidateRanking.sort((c1, c2) -> Integer.compare(c2.getVoteCount(), c1.getVoteCount()));

    }
}
