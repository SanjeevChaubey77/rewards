package com.san.cha.test;

import org.springframework.data.repository.CrudRepository;

public interface RewardRepository extends CrudRepository<Customer,Long> {
    public Customer findByCustomerId(Long customerId);
}
