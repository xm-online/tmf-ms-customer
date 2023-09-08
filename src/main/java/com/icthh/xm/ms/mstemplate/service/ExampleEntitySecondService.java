package com.icthh.xm.ms.mstemplate.service;

import com.icthh.xm.ms.mstemplate.service.dto.ExampleEntitySecondDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.icthh.xm.ms.mstemplate.domain.ExampleEntitySecond}.
 */
public interface ExampleEntitySecondService {

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be {@literal null}.
     * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    boolean existsById(Long id);

    /**
     * Save a exampleEntitySecond.
     *
     * @param exampleEntitySecondDto the entity to save.
     * @return the persisted entity.
     */
    ExampleEntitySecondDto save(ExampleEntitySecondDto exampleEntitySecondDto);

    /**
     * Updates a exampleEntitySecond.
     *
     * @param exampleEntitySecondDto the entity to update.
     * @return the persisted entity.
     */
    ExampleEntitySecondDto update(ExampleEntitySecondDto exampleEntitySecondDto);

    /**
     * Partially updates a exampleEntitySecond.
     *
     * @param exampleEntitySecondDto the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ExampleEntitySecondDto> partialUpdate(ExampleEntitySecondDto exampleEntitySecondDto);

    /**
     * Get all the exampleEntitySeconds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ExampleEntitySecondDto> findAll(Pageable pageable);

    /**
     * Get the "id" exampleEntitySecond.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ExampleEntitySecondDto> findOne(Long id);

    /**
     * Delete the "id" exampleEntitySecond.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
