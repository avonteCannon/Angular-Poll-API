package com.avonte.votingpolldemo.repository;

import com.avonte.votingpolldemo.Option;
import org.springframework.data.repository.CrudRepository;

public interface OptionRepository extends CrudRepository<Option, Long> {

}
