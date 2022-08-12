package org.daemio.merch.repository;

import org.daemio.merch.domain.Merch;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MerchRepository extends PagingAndSortingRepository<Merch, Integer> {
    
}
