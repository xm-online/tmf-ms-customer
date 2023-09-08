package com.icthh.xm.ms.mstemplate.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.icthh.xm.commons.permission.annotation.PrivilegeDescription;
import com.icthh.xm.ms.mstemplate.service.ExampleEntityFirstQueryService;
import com.icthh.xm.ms.mstemplate.service.ExampleEntityFirstService;
import com.icthh.xm.ms.mstemplate.service.criteria.ExampleEntityFirstCriteria;
import com.icthh.xm.ms.mstemplate.service.dto.ExampleEntityFirstDto;
import com.icthh.xm.ms.mstemplate.web.rest.errors.BadRequestAlertException;
import com.icthh.xm.ms.mstemplate.web.rest.utils.ResponseContentUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link com.icthh.xm.ms.mstemplate.domain.ExampleEntityFirst}.
 */
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExampleEntityFirstResource {

    private static final String ENTITY_NAME = "mstemplateExampleEntityFirst";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExampleEntityFirstService exampleEntityFirstService;

    private final ExampleEntityFirstQueryService exampleEntityFirstQueryService;

    /**
     * {@code POST  /example-entity-firsts} : Create a new exampleEntityFirst.
     *
     * @param exampleEntityFirstDto the exampleEntityFirstDto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new exampleEntityFirstDto, or with status {@code 400 (Bad Request)} if the exampleEntityFirst has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Timed
    @PostMapping("/example-entity-firsts")
    @PreAuthorize("hasPermission({'exampleEntityFirstDto': #exampleEntityFirstDto}, 'EXAMPLE_ENTITY_FIRST.CREATE')")
    @PrivilegeDescription("Privilege to create a new example entity first")
    public ResponseEntity<ExampleEntityFirstDto> createExampleEntityFirst(@RequestBody ExampleEntityFirstDto exampleEntityFirstDto)
        throws URISyntaxException {
        if (exampleEntityFirstDto.getId() != null) {
            throw new BadRequestAlertException("A new exampleEntityFirst cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExampleEntityFirstDto result = exampleEntityFirstService.save(exampleEntityFirstDto);
        return ResponseEntity
            .created(new URI("/api/example-entity-firsts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /example-entity-firsts/:id} : Updates an existing exampleEntityFirst.
     *
     * @param id the id of the exampleEntityFirstDto to save.
     * @param exampleEntityFirstDto the exampleEntityFirstDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated exampleEntityFirstDto,
     * or with status {@code 400 (Bad Request)} if the exampleEntityFirstDto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the exampleEntityFirstDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Timed
    @PutMapping("/example-entity-firsts/{id}")
    @PreAuthorize("hasPermission({'exampleEntityFirstDto': #exampleEntityFirstDto, 'id': #id}, 'EXAMPLE_ENTITY_FIRST.UPDATE')")
    @PrivilegeDescription("Privilege to update example entity first")
    public ResponseEntity<ExampleEntityFirstDto> updateExampleEntityFirst(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ExampleEntityFirstDto exampleEntityFirstDto
    ) throws URISyntaxException {
        if (exampleEntityFirstDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, exampleEntityFirstDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!exampleEntityFirstService.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ExampleEntityFirstDto result = exampleEntityFirstService.update(exampleEntityFirstDto);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, exampleEntityFirstDto.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /example-entity-firsts/:id} : Partial updates given fields of an existing exampleEntityFirst, field will ignore if it is null
     *
     * @param id the id of the exampleEntityFirstDto to save.
     * @param exampleEntityFirstDto the exampleEntityFirstDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated exampleEntityFirstDto,
     * or with status {@code 400 (Bad Request)} if the exampleEntityFirstDto is not valid,
     * or with status {@code 404 (Not Found)} if the exampleEntityFirstDto is not found,
     * or with status {@code 500 (Internal Server Error)} if the exampleEntityFirstDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Timed
    @PreAuthorize("hasPermission({'exampleEntityFirstDto': #exampleEntityFirstDto, 'id': #id}, 'EXAMPLE_ENTITY_FIRST.PARTIAL_UPDATE')")
    @PrivilegeDescription("Privilege to partial update example entity first")
    @PatchMapping(value = "/example-entity-firsts/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ExampleEntityFirstDto> partialUpdateExampleEntityFirst(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ExampleEntityFirstDto exampleEntityFirstDto
    ) throws URISyntaxException {
        if (exampleEntityFirstDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, exampleEntityFirstDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!exampleEntityFirstService.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ExampleEntityFirstDto> result = exampleEntityFirstService.partialUpdate(exampleEntityFirstDto);

        return ResponseContentUtils.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, exampleEntityFirstDto.getId().toString())
        );
    }

    /**
     * {@code GET  /example-entity-firsts} : get all the exampleEntityFirsts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of exampleEntityFirsts in body.
     */
    @Timed
    @GetMapping("/example-entity-firsts")
    public ResponseEntity<List<ExampleEntityFirstDto>> getAllExampleEntityFirsts(
        ExampleEntityFirstCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        Page<ExampleEntityFirstDto> page = exampleEntityFirstQueryService.findByCriteria(criteria, pageable, null);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /example-entity-firsts/count} : count all the exampleEntityFirsts.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @Timed
    @GetMapping("/example-entity-firsts/count")
    public ResponseEntity<Long> countExampleEntityFirsts(ExampleEntityFirstCriteria criteria) {
        return ResponseEntity.ok().body(exampleEntityFirstQueryService.countByCriteria(criteria, null));
    }

    /**
     * {@code GET  /example-entity-firsts/:id} : get the "id" exampleEntityFirst.
     *
     * @param id the id of the exampleEntityFirstDto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the exampleEntityFirstDto, or with status {@code 404 (Not Found)}.
     */
    @Timed
    @GetMapping("/example-entity-firsts/{id}")
    @PostAuthorize("hasPermission({'returnObject': returnObject.body}, 'EXAMPLE_ENTITY_FIRST.GET_LIST.ITEM')")
    @PrivilegeDescription("Privilege to get the example entity first by id")
    public ResponseEntity<ExampleEntityFirstDto> getExampleEntityFirst(@PathVariable Long id) {
        Optional<ExampleEntityFirstDto> exampleEntityFirstDto = exampleEntityFirstService.findOne(id);
        return ResponseContentUtils.wrapOrNotFound(exampleEntityFirstDto);
    }

    /**
     * {@code DELETE  /example-entity-firsts/:id} : delete the "id" exampleEntityFirst.
     *
     * @param id the id of the exampleEntityFirstDto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Timed
    @DeleteMapping("/example-entity-firsts/{id}")
    @PreAuthorize("hasPermission({'id': #id}, 'exampleEntityFirst', 'EXAMPLE_ENTITY_FIRST.DELETE')")
    @PrivilegeDescription("Privilege to delete the example entity first by id")
    public ResponseEntity<Void> deleteExampleEntityFirst(@PathVariable Long id) {
        exampleEntityFirstService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
