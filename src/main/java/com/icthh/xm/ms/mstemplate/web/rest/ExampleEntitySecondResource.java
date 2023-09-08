package com.icthh.xm.ms.mstemplate.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.icthh.xm.commons.permission.annotation.PrivilegeDescription;
import com.icthh.xm.ms.mstemplate.service.ExampleEntitySecondQueryService;
import com.icthh.xm.ms.mstemplate.service.ExampleEntitySecondService;
import com.icthh.xm.ms.mstemplate.service.criteria.ExampleEntitySecondCriteria;
import com.icthh.xm.ms.mstemplate.service.dto.ExampleEntitySecondDto;
import com.icthh.xm.ms.mstemplate.web.rest.errors.BadRequestAlertException;
import com.icthh.xm.ms.mstemplate.web.rest.utils.ResponseContentUtils;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

/**
 * REST controller for managing {@link com.icthh.xm.ms.mstemplate.domain.ExampleEntitySecond}.
 */
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExampleEntitySecondResource {

    private static final String ENTITY_NAME = "mstemplateExampleEntitySecond";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExampleEntitySecondService exampleEntitySecondService;

    private final ExampleEntitySecondQueryService exampleEntitySecondQueryService;

    /**
     * {@code POST  /example-entity-seconds} : Create a new exampleEntitySecond.
     *
     * @param exampleEntitySecondDto the exampleEntitySecondDto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new exampleEntitySecondDto, or with status {@code 400 (Bad Request)} if the exampleEntitySecond has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Timed
    @PostMapping("/example-entity-seconds")
    @PreAuthorize("hasPermission({'exampleEntitySecond': #exampleEntitySecondDto}, 'EXAMPLE_ENTITY_SECOND.CREATE')")
    @PrivilegeDescription("Privilege to create a new example entity second")
    public ResponseEntity<ExampleEntitySecondDto> createExampleEntitySecond(@RequestBody ExampleEntitySecondDto exampleEntitySecondDto)
        throws URISyntaxException {
        if (exampleEntitySecondDto.getId() != null) {
            throw new BadRequestAlertException("A new exampleEntitySecond cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExampleEntitySecondDto result = exampleEntitySecondService.save(exampleEntitySecondDto);
        return ResponseEntity
            .created(new URI("/api/example-entity-seconds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /example-entity-seconds/:id} : Updates an existing exampleEntitySecond.
     *
     * @param id the id of the exampleEntitySecondDto to save.
     * @param exampleEntitySecondDto the exampleEntitySecondDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated exampleEntitySecondDto,
     * or with status {@code 400 (Bad Request)} if the exampleEntitySecondDto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the exampleEntitySecondDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Timed
    @PutMapping("/example-entity-seconds/{id}")
    @PreAuthorize("hasPermission({'exampleEntitySecond': #exampleEntitySecondDto, 'id': #id}, 'EXAMPLE_ENTITY_SECOND.UPDATE')")
    @PrivilegeDescription("Privilege to update example entity second")
    public ResponseEntity<ExampleEntitySecondDto> updateExampleEntitySecond(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ExampleEntitySecondDto exampleEntitySecondDto
    ) throws URISyntaxException {
        if (exampleEntitySecondDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, exampleEntitySecondDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!exampleEntitySecondService.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ExampleEntitySecondDto result = exampleEntitySecondService.update(exampleEntitySecondDto);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, exampleEntitySecondDto.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /example-entity-seconds/:id} : Partial updates given fields of an existing exampleEntitySecond, field will ignore if it is null
     *
     * @param id the id of the exampleEntitySecondDto to save.
     * @param exampleEntitySecondDto the exampleEntitySecondDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated exampleEntitySecondDto,
     * or with status {@code 400 (Bad Request)} if the exampleEntitySecondDto is not valid,
     * or with status {@code 404 (Not Found)} if the exampleEntitySecondDto is not found,
     * or with status {@code 500 (Internal Server Error)} if the exampleEntitySecondDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Timed
    @PreAuthorize("hasPermission({'exampleEntitySecond': #exampleEntitySecondDto, 'id': #id}, 'EXAMPLE_ENTITY_SECOND.PARTIAL_UPDATE')")
    @PrivilegeDescription("Privilege to partial update example entity second")
    @PatchMapping(value = "/example-entity-seconds/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ExampleEntitySecondDto> partialUpdateExampleEntitySecond(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ExampleEntitySecondDto exampleEntitySecondDto
    ) throws URISyntaxException {
        if (exampleEntitySecondDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, exampleEntitySecondDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!exampleEntitySecondService.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ExampleEntitySecondDto> result = exampleEntitySecondService.partialUpdate(exampleEntitySecondDto);

        return ResponseContentUtils.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, exampleEntitySecondDto.getId().toString())
        );
    }

    /**
     * {@code GET  /example-entity-seconds} : get all the exampleEntitySeconds.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of exampleEntitySeconds in body.
     */
    @Timed
    @GetMapping("/example-entity-seconds")
    public ResponseEntity<List<ExampleEntitySecondDto>> getAllExampleEntitySeconds(
        ExampleEntitySecondCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        Page<ExampleEntitySecondDto> page = exampleEntitySecondQueryService.findByCriteria(criteria, pageable, null);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /example-entity-seconds/count} : count all the exampleEntitySeconds.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @Timed
    @GetMapping("/example-entity-seconds/count")
    public ResponseEntity<Long> countExampleEntitySeconds(ExampleEntitySecondCriteria criteria) {
        return ResponseEntity.ok().body(exampleEntitySecondQueryService.countByCriteria(criteria, null));
    }

    /**
     * {@code GET  /example-entity-seconds/:id} : get the "id" exampleEntitySecond.
     *
     * @param id the id of the exampleEntitySecondDto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the exampleEntitySecondDto, or with status {@code 404 (Not Found)}.
     */
    @Timed
    @GetMapping("/example-entity-seconds/{id}")
    @PostAuthorize("hasPermission({'returnObject': returnObject.body}, 'EXAMPLE_ENTITY_SECOND.GET_LIST.ITEM')")
    @PrivilegeDescription("Privilege to get the example entity second by id")
    public ResponseEntity<ExampleEntitySecondDto> getExampleEntitySecond(@PathVariable Long id) {
        Optional<ExampleEntitySecondDto> exampleEntitySecondDto = exampleEntitySecondService.findOne(id);
        return ResponseContentUtils.wrapOrNotFound(exampleEntitySecondDto);
    }

    /**
     * {@code DELETE  /example-entity-seconds/:id} : delete the "id" exampleEntitySecond.
     *
     * @param id the id of the exampleEntitySecondDto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Timed
    @DeleteMapping("/example-entity-seconds/{id}")
    @PreAuthorize("hasPermission({'id': #id}, 'exampleEntitySecond', 'EXAMPLE_ENTITY_SECOND.DELETE')")
    @PrivilegeDescription("Privilege to delete the example entity second by id")
    public ResponseEntity<Void> deleteExampleEntitySecond(@PathVariable Long id) {
        exampleEntitySecondService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
