package com.icthh.xm.tmf.ms.customer.service;

import com.icthh.xm.tmf.ms.customer.service.dto.ExampleEntityFirstDto;
import com.icthh.xm.tmf.ms.customer.domain.ExampleEntityFirst;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ExampleEntityFirst}.
 */
public interface ExampleEntityFirstService {

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be {@literal null}.
     * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    boolean existsById(Long id);

    /**
     * Save a exampleEntityFirst.
     *
     * @param exampleEntityFirstDto the entity to save.
     * @return the persisted entity.
     */
    ExampleEntityFirstDto save(ExampleEntityFirstDto exampleEntityFirstDto);

    /**
     * Updates a exampleEntityFirst.
     *
     * @param exampleEntityFirstDto the entity to update.
     * @return the persisted entity.
     */
    ExampleEntityFirstDto update(ExampleEntityFirstDto exampleEntityFirstDto);

    /**
     * Partially updates a exampleEntityFirst.
     *
     * @param exampleEntityFirstDto the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ExampleEntityFirstDto> partialUpdate(ExampleEntityFirstDto exampleEntityFirstDto);

    /**
     * Get all the exampleEntityFirsts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ExampleEntityFirstDto> findAll(Pageable pageable);

    /**
     * Get the "id" exampleEntityFirst.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ExampleEntityFirstDto> findOne(Long id);

    /**
     * Delete the "id" exampleEntityFirst.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
