package com.icthh.xm.tmf.ms.customer.repository;

import com.icthh.xm.tmf.ms.customer.domain.CustomerCharacteristicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CustomerEntityRepository extends JpaRepository<CustomerCharacteristicEntity, Long> {

    Collection<CustomerCharacteristicEntity> findAllByCustomerIdAndKeyIn(Long customerId, Collection<String> keys);

    Optional<CustomerCharacteristicEntity> findByCustomerIdAndKey(Long customerId, String key);
}
