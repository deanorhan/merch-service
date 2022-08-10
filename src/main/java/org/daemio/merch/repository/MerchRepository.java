package org.daemio.merch.repository;

import org.daemio.merch.model.Merch;
import org.springframework.data.repository.CrudRepository;

public interface MerchRepository extends CrudRepository<Merch, Integer> {
    
}
