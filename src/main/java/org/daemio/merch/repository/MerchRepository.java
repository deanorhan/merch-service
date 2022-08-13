package org.daemio.merch.repository;

import org.daemio.merch.domain.Merch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchRepository extends JpaRepository<Merch, Integer> {
    
}
