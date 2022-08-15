package org.daemio.merch.repository;

import org.daemio.merch.domain.Merch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MerchRepository extends JpaRepository<Merch, Integer>, JpaSpecificationExecutor<Merch> {

}
