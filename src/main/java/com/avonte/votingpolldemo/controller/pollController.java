package com.avonte.votingpolldemo.controller;

import com.avonte.votingpolldemo.Poll;
import com.avonte.votingpolldemo.repository.PollRepository;
import org.hibernate.service.spi.InjectService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import javax.persistence.Id;
import javax.xml.ws.Response;
import java.net.URI;
import java.util.Optional;

@RestController
//@CrossOrigin("*")
public class pollController {

    @Inject
    private PollRepository pollRepository;

    //GET ALL POLLS METHOD
    @RequestMapping(value ="/polls", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls(){
        Iterable<Poll> allPolls = pollRepository.findAll();
        return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);
    }

    //CREATE POLL
    @RequestMapping(value ="/polls", method = RequestMethod.POST)
    public ResponseEntity<?> createPoll(@RequestBody Poll poll){
        poll = pollRepository.save(poll);

        // Set the location header for the newly created resource (201)
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();

        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //GET A INDIVIDUAL POLL (FOUND THE ERROR PG.65)
    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long pollId){
//        Poll poll = pollRepository.findById(pollId).orElse(null);
        Optional<Poll> poll = pollRepository.findById(pollId);
        return new ResponseEntity<>(poll, HttpStatus.OK);
    }

    //UPDATE POLL
    @RequestMapping(value="/polls/{pollId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId){
         Poll poll1 = pollRepository.save(poll);
         return new ResponseEntity<>(HttpStatus.OK);
    }

    //DELETE POLL
    @RequestMapping(value="/polls/{pollId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId){
        pollRepository.deleteById(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
