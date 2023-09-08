package com.icthh.xm.tmf.ms.customer.repository;

import com.icthh.xm.commons.permission.access.repository.ResourceRepository;
import com.icthh.xm.tmf.ms.customer.domain.ExampleEntityFirst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ExampleEntityFirst entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExampleEntityFirstRepository
    extends JpaRepository<ExampleEntityFirst, Long>, JpaSpecificationExecutor<ExampleEntityFirst>, ResourceRepository {}
