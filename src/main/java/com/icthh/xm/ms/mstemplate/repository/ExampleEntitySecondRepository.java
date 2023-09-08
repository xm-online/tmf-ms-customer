package com.icthh.xm.ms.mstemplate.repository;

import com.icthh.xm.commons.permission.access.repository.ResourceRepository;
import com.icthh.xm.ms.mstemplate.domain.ExampleEntitySecond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ExampleEntitySecond entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExampleEntitySecondRepository
    extends JpaRepository<ExampleEntitySecond, Long>, JpaSpecificationExecutor<ExampleEntitySecond>, ResourceRepository {}
