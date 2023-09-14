package com.icthh.xm.tmf.ms.customer.repository;

import com.icthh.xm.tmf.ms.customer.domain.CustomerCharacteristicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CustomerCharacteristicRepository extends JpaSpecificationExecutor<CustomerCharacteristicEntity>,
    JpaRepository<CustomerCharacteristicEntity, Long> {

    Collection<CustomerCharacteristicEntity> findAllByCustomerIdAndKeyIn(String customerId, Collection<String> keys);

    Optional<CustomerCharacteristicEntity> findByCustomerIdAndKey(String customerId, String key);

    void deleteByCustomerIdAndKey(String customerId, String key);
}
