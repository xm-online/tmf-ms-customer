package com.icthh.xm.ms.mstemplate.web.rest;

import com.icthh.xm.commons.i18n.error.web.ExceptionTranslator;
import com.icthh.xm.commons.security.XmAuthenticationContextHolder;
import com.icthh.xm.commons.tenant.TenantContextHolder;
import com.icthh.xm.commons.tenant.TenantContextUtils;
import com.icthh.xm.lep.api.LepManager;
import com.icthh.xm.ms.mstemplate.AbstractSpringBootTest;
import com.icthh.xm.ms.mstemplate.domain.ExampleEntitySecond;
import com.icthh.xm.ms.mstemplate.repository.ExampleEntitySecondRepository;
import com.icthh.xm.ms.mstemplate.service.dto.ExampleEntitySecondDto;
import com.icthh.xm.ms.mstemplate.service.mapper.ExampleEntitySecondMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static com.icthh.xm.commons.lep.XmLepConstants.THREAD_CONTEXT_KEY_AUTH_CONTEXT;
import static com.icthh.xm.commons.lep.XmLepConstants.THREAD_CONTEXT_KEY_TENANT_CONTEXT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for the {@link ExampleEntitySecondResource} REST controller.
 */
@WithMockUser(authorities = {"SUPER-ADMIN"})
public class ExampleEntitySecondResourceIntTest extends AbstractSpringBootTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/example-entity-seconds";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ExampleEntitySecondRepository exampleEntitySecondRepository;

    @Autowired
    private ExampleEntitySecondMapper exampleEntitySecondMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private ExampleEntitySecondResource exampleEntitySecondResource;

    private MockMvc restExampleEntitySecondMockMvc;

    @Autowired
    private TenantContextHolder tenantContextHolder;

    @Autowired
    private XmAuthenticationContextHolder authContextHolder;

    @Autowired
    private LepManager lepManager;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private ExampleEntitySecond exampleEntitySecond;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExampleEntitySecond createEntity(EntityManager em) {
        ExampleEntitySecond exampleEntitySecond = new ExampleEntitySecond().setName(DEFAULT_NAME);
        return exampleEntitySecond;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExampleEntitySecond createUpdatedEntity(EntityManager em) {
        ExampleEntitySecond exampleEntitySecond = new ExampleEntitySecond().setName(UPDATED_NAME);
        return exampleEntitySecond;
    }

    @BeforeEach
    public void initTest() {
        exampleEntitySecond = createEntity(em);
    }

    @BeforeTransaction
    public void beforeTransaction() {
        TenantContextUtils.setTenant(tenantContextHolder, "RESINTTEST");
    }

    @SneakyThrows
    @BeforeEach
    public void setup() {
        this.restExampleEntitySecondMockMvc = MockMvcBuilders.standaloneSetup(exampleEntitySecondResource)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                .setControllerAdvice(exceptionTranslator)
                .setMessageConverters(jacksonMessageConverter).build();

        lepManager.beginThreadContext(ctx -> {
            ctx.setValue(THREAD_CONTEXT_KEY_TENANT_CONTEXT, tenantContextHolder.getContext());
            ctx.setValue(THREAD_CONTEXT_KEY_AUTH_CONTEXT, authContextHolder.getContext());
        });
    }

    @AfterEach
    public void tearDown() {
        lepManager.endThreadContext();
        tenantContextHolder.getPrivilegedContext().destroyCurrentContext();
    }

    @Test
    @Transactional
    public void createExampleEntitySecond() throws Exception {
        int databaseSizeBeforeCreate = exampleEntitySecondRepository.findAll().size();
        // Create the ExampleEntitySecond
        ExampleEntitySecondDto exampleEntitySecondDto = exampleEntitySecondMapper.toDto(exampleEntitySecond);
        restExampleEntitySecondMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(exampleEntitySecondDto))
            )
            .andExpect(status().isCreated());

        // Validate the ExampleEntitySecond in the database
        List<ExampleEntitySecond> exampleEntitySecondList = exampleEntitySecondRepository.findAll();
        assertThat(exampleEntitySecondList).hasSize(databaseSizeBeforeCreate + 1);
        ExampleEntitySecond testExampleEntitySecond = exampleEntitySecondList.get(exampleEntitySecondList.size() - 1);
        assertThat(testExampleEntitySecond.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createExampleEntitySecondWithExistingId() throws Exception {
        // Create the ExampleEntitySecond with an existing ID
        exampleEntitySecond.setId(1L);
        ExampleEntitySecondDto exampleEntitySecondDto = exampleEntitySecondMapper.toDto(exampleEntitySecond);

        int databaseSizeBeforeCreate = exampleEntitySecondRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restExampleEntitySecondMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(exampleEntitySecondDto))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExampleEntitySecond in the database
        List<ExampleEntitySecond> exampleEntitySecondList = exampleEntitySecondRepository.findAll();
        assertThat(exampleEntitySecondList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllExampleEntitySeconds() throws Exception {
        // Initialize the database
        exampleEntitySecondRepository.saveAndFlush(exampleEntitySecond);

        // Get all the exampleEntitySecondList
        restExampleEntitySecondMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(exampleEntitySecond.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void getExampleEntitySecond() throws Exception {
        // Initialize the database
        exampleEntitySecondRepository.saveAndFlush(exampleEntitySecond);

        // Get the exampleEntitySecond
        restExampleEntitySecondMockMvc
            .perform(get(ENTITY_API_URL_ID, exampleEntitySecond.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(exampleEntitySecond.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getExampleEntitySecondsByIdFiltering() throws Exception {
        // Initialize the database
        exampleEntitySecondRepository.saveAndFlush(exampleEntitySecond);

        Long id = exampleEntitySecond.getId();

        defaultExampleEntitySecondShouldBeFound("id.equals=" + id);
        defaultExampleEntitySecondShouldNotBeFound("id.notEquals=" + id);

        defaultExampleEntitySecondShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultExampleEntitySecondShouldNotBeFound("id.greaterThan=" + id);

        defaultExampleEntitySecondShouldBeFound("id.lessThanOrEqual=" + id);
        defaultExampleEntitySecondShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    public void getAllExampleEntitySecondsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        exampleEntitySecondRepository.saveAndFlush(exampleEntitySecond);

        // Get all the exampleEntitySecondList where name equals to DEFAULT_NAME
        defaultExampleEntitySecondShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the exampleEntitySecondList where name equals to UPDATED_NAME
        defaultExampleEntitySecondShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllExampleEntitySecondsByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        exampleEntitySecondRepository.saveAndFlush(exampleEntitySecond);

        // Get all the exampleEntitySecondList where name not equals to DEFAULT_NAME
        defaultExampleEntitySecondShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the exampleEntitySecondList where name not equals to UPDATED_NAME
        defaultExampleEntitySecondShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllExampleEntitySecondsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        exampleEntitySecondRepository.saveAndFlush(exampleEntitySecond);

        // Get all the exampleEntitySecondList where name in DEFAULT_NAME or UPDATED_NAME
        defaultExampleEntitySecondShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the exampleEntitySecondList where name equals to UPDATED_NAME
        defaultExampleEntitySecondShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllExampleEntitySecondsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        exampleEntitySecondRepository.saveAndFlush(exampleEntitySecond);

        // Get all the exampleEntitySecondList where name is not null
        defaultExampleEntitySecondShouldBeFound("name.specified=true");

        // Get all the exampleEntitySecondList where name is null
        defaultExampleEntitySecondShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllExampleEntitySecondsByNameContainsSomething() throws Exception {
        // Initialize the database
        exampleEntitySecondRepository.saveAndFlush(exampleEntitySecond);

        // Get all the exampleEntitySecondList where name contains DEFAULT_NAME
        defaultExampleEntitySecondShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the exampleEntitySecondList where name contains UPDATED_NAME
        defaultExampleEntitySecondShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllExampleEntitySecondsByNameNotContainsSomething() throws Exception {
        // Initialize the database
        exampleEntitySecondRepository.saveAndFlush(exampleEntitySecond);

        // Get all the exampleEntitySecondList where name does not contain DEFAULT_NAME
        defaultExampleEntitySecondShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the exampleEntitySecondList where name does not contain UPDATED_NAME
        defaultExampleEntitySecondShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultExampleEntitySecondShouldBeFound(String filter) throws Exception {
        restExampleEntitySecondMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(exampleEntitySecond.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));

        // Check, that the count call also returns 1
        restExampleEntitySecondMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultExampleEntitySecondShouldNotBeFound(String filter) throws Exception {
        restExampleEntitySecondMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restExampleEntitySecondMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingExampleEntitySecond() throws Exception {
        // Get the exampleEntitySecond
        restExampleEntitySecondMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void putNewExampleEntitySecond() throws Exception {
        // Initialize the database
        exampleEntitySecondRepository.saveAndFlush(exampleEntitySecond);

        int databaseSizeBeforeUpdate = exampleEntitySecondRepository.findAll().size();

        // Update the exampleEntitySecond
        ExampleEntitySecond updatedExampleEntitySecond = exampleEntitySecondRepository.findById(exampleEntitySecond.getId()).get();
        // Disconnect from session so that the updates on updatedExampleEntitySecond are not directly saved in db
        em.detach(updatedExampleEntitySecond);
        updatedExampleEntitySecond.setName(UPDATED_NAME);
        ExampleEntitySecondDto exampleEntitySecondDto = exampleEntitySecondMapper.toDto(updatedExampleEntitySecond);

        restExampleEntitySecondMockMvc
            .perform(
                put(ENTITY_API_URL_ID, exampleEntitySecondDto.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(exampleEntitySecondDto))
            )
            .andExpect(status().isOk());

        // Validate the ExampleEntitySecond in the database
        List<ExampleEntitySecond> exampleEntitySecondList = exampleEntitySecondRepository.findAll();
        assertThat(exampleEntitySecondList).hasSize(databaseSizeBeforeUpdate);
        ExampleEntitySecond testExampleEntitySecond = exampleEntitySecondList.get(exampleEntitySecondList.size() - 1);
        assertThat(testExampleEntitySecond.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void putNonExistingExampleEntitySecond() throws Exception {
        int databaseSizeBeforeUpdate = exampleEntitySecondRepository.findAll().size();
        exampleEntitySecond.setId(count.incrementAndGet());

        // Create the ExampleEntitySecond
        ExampleEntitySecondDto exampleEntitySecondDto = exampleEntitySecondMapper.toDto(exampleEntitySecond);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExampleEntitySecondMockMvc
            .perform(
                put(ENTITY_API_URL_ID, exampleEntitySecondDto.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(exampleEntitySecondDto))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExampleEntitySecond in the database
        List<ExampleEntitySecond> exampleEntitySecondList = exampleEntitySecondRepository.findAll();
        assertThat(exampleEntitySecondList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void putWithIdMismatchExampleEntitySecond() throws Exception {
        int databaseSizeBeforeUpdate = exampleEntitySecondRepository.findAll().size();
        exampleEntitySecond.setId(count.incrementAndGet());

        // Create the ExampleEntitySecond
        ExampleEntitySecondDto exampleEntitySecondDto = exampleEntitySecondMapper.toDto(exampleEntitySecond);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExampleEntitySecondMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(exampleEntitySecondDto))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExampleEntitySecond in the database
        List<ExampleEntitySecond> exampleEntitySecondList = exampleEntitySecondRepository.findAll();
        assertThat(exampleEntitySecondList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void putWithMissingIdPathParamExampleEntitySecond() throws Exception {
        int databaseSizeBeforeUpdate = exampleEntitySecondRepository.findAll().size();
        exampleEntitySecond.setId(count.incrementAndGet());

        // Create the ExampleEntitySecond
        ExampleEntitySecondDto exampleEntitySecondDto = exampleEntitySecondMapper.toDto(exampleEntitySecond);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExampleEntitySecondMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(exampleEntitySecondDto))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ExampleEntitySecond in the database
        List<ExampleEntitySecond> exampleEntitySecondList = exampleEntitySecondRepository.findAll();
        assertThat(exampleEntitySecondList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void partialUpdateExampleEntitySecondWithPatch() throws Exception {
        // Initialize the database
        exampleEntitySecondRepository.saveAndFlush(exampleEntitySecond);

        int databaseSizeBeforeUpdate = exampleEntitySecondRepository.findAll().size();

        // Update the exampleEntitySecond using partial update
        ExampleEntitySecond partialUpdatedExampleEntitySecond = new ExampleEntitySecond();
        partialUpdatedExampleEntitySecond.setId(exampleEntitySecond.getId());

        partialUpdatedExampleEntitySecond.setName(UPDATED_NAME);

        restExampleEntitySecondMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedExampleEntitySecond.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedExampleEntitySecond))
            )
            .andExpect(status().isOk());

        // Validate the ExampleEntitySecond in the database
        List<ExampleEntitySecond> exampleEntitySecondList = exampleEntitySecondRepository.findAll();
        assertThat(exampleEntitySecondList).hasSize(databaseSizeBeforeUpdate);
        ExampleEntitySecond testExampleEntitySecond = exampleEntitySecondList.get(exampleEntitySecondList.size() - 1);
        assertThat(testExampleEntitySecond.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void fullUpdateExampleEntitySecondWithPatch() throws Exception {
        // Initialize the database
        exampleEntitySecondRepository.saveAndFlush(exampleEntitySecond);

        int databaseSizeBeforeUpdate = exampleEntitySecondRepository.findAll().size();

        // Update the exampleEntitySecond using partial update
        ExampleEntitySecond partialUpdatedExampleEntitySecond = new ExampleEntitySecond();
        partialUpdatedExampleEntitySecond.setId(exampleEntitySecond.getId());

        partialUpdatedExampleEntitySecond.setName(UPDATED_NAME);

        restExampleEntitySecondMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedExampleEntitySecond.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedExampleEntitySecond))
            )
            .andExpect(status().isOk());

        // Validate the ExampleEntitySecond in the database
        List<ExampleEntitySecond> exampleEntitySecondList = exampleEntitySecondRepository.findAll();
        assertThat(exampleEntitySecondList).hasSize(databaseSizeBeforeUpdate);
        ExampleEntitySecond testExampleEntitySecond = exampleEntitySecondList.get(exampleEntitySecondList.size() - 1);
        assertThat(testExampleEntitySecond.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void patchNonExistingExampleEntitySecond() throws Exception {
        int databaseSizeBeforeUpdate = exampleEntitySecondRepository.findAll().size();
        exampleEntitySecond.setId(count.incrementAndGet());

        // Create the ExampleEntitySecond
        ExampleEntitySecondDto exampleEntitySecondDto = exampleEntitySecondMapper.toDto(exampleEntitySecond);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExampleEntitySecondMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, exampleEntitySecondDto.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(exampleEntitySecondDto))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExampleEntitySecond in the database
        List<ExampleEntitySecond> exampleEntitySecondList = exampleEntitySecondRepository.findAll();
        assertThat(exampleEntitySecondList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void patchWithIdMismatchExampleEntitySecond() throws Exception {
        int databaseSizeBeforeUpdate = exampleEntitySecondRepository.findAll().size();
        exampleEntitySecond.setId(count.incrementAndGet());

        // Create the ExampleEntitySecond
        ExampleEntitySecondDto exampleEntitySecondDto = exampleEntitySecondMapper.toDto(exampleEntitySecond);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExampleEntitySecondMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(exampleEntitySecondDto))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExampleEntitySecond in the database
        List<ExampleEntitySecond> exampleEntitySecondList = exampleEntitySecondRepository.findAll();
        assertThat(exampleEntitySecondList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void patchWithMissingIdPathParamExampleEntitySecond() throws Exception {
        int databaseSizeBeforeUpdate = exampleEntitySecondRepository.findAll().size();
        exampleEntitySecond.setId(count.incrementAndGet());

        // Create the ExampleEntitySecond
        ExampleEntitySecondDto exampleEntitySecondDto = exampleEntitySecondMapper.toDto(exampleEntitySecond);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExampleEntitySecondMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(exampleEntitySecondDto))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ExampleEntitySecond in the database
        List<ExampleEntitySecond> exampleEntitySecondList = exampleEntitySecondRepository.findAll();
        assertThat(exampleEntitySecondList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExampleEntitySecond() throws Exception {
        // Initialize the database
        exampleEntitySecondRepository.saveAndFlush(exampleEntitySecond);

        int databaseSizeBeforeDelete = exampleEntitySecondRepository.findAll().size();

        // Delete the exampleEntitySecond
        restExampleEntitySecondMockMvc
            .perform(delete(ENTITY_API_URL_ID, exampleEntitySecond.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ExampleEntitySecond> exampleEntitySecondList = exampleEntitySecondRepository.findAll();
        assertThat(exampleEntitySecondList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
