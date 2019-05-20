package com.avonte.votingpolldemo.controller;

import com.avonte.votingpolldemo.Vote;
import com.avonte.votingpolldemo.dto.OptionCount;
import com.avonte.votingpolldemo.dto.VoteResult;
import com.avonte.votingpolldemo.repository.VoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@RestController
public class computeResultController {

    @Inject
    private VoteRepository voteRepository;

    @RequestMapping(value="/computeresult", method= RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);

        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<Long, OptionCount>();
        for(Vote x : allVotes){

            OptionCount optionCount = tempMap.get(x.getOption().getId());
            if (optionCount == null){
                optionCount = new OptionCount();
                optionCount.setOptionId(x.getOption().getId());
                tempMap.put(x.getOption().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount()+1);
        }

        voteResult.setTotalVotes(totalVotes);
        voteResult.setResults(tempMap.values());

        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }
}




































//GQ is QG
