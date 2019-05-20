package com.avonte.votingpolldemo.repository;

import com.avonte.votingpolldemo.Poll;
import org.springframework.data.repository.CrudRepository;

public interface PollRepository extends CrudRepository<Poll, Long> {
}
