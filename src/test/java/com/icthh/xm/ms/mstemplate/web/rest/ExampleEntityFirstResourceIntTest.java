package com.icthh.xm.ms.mstemplate.web.rest;

import com.icthh.xm.commons.i18n.error.web.ExceptionTranslator;
import com.icthh.xm.commons.security.XmAuthenticationContextHolder;
import com.icthh.xm.commons.tenant.TenantContextHolder;
import com.icthh.xm.commons.tenant.TenantContextUtils;
import com.icthh.xm.lep.api.LepManager;
import com.icthh.xm.ms.mstemplate.AbstractSpringBootTest;
import com.icthh.xm.ms.mstemplate.domain.ExampleEntityFirst;
import com.icthh.xm.ms.mstemplate.domain.ExampleEntitySecond;
import com.icthh.xm.ms.mstemplate.repository.ExampleEntityFirstRepository;
import com.icthh.xm.ms.mstemplate.service.dto.ExampleEntityFirstDto;
import com.icthh.xm.ms.mstemplate.service.mapper.ExampleEntityFirstMapper;
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
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

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
 * Integration tests for the {@link ExampleEntityFirstResource} REST controller.
 */
@WithMockUser(authorities = {"SUPER-ADMIN"})
public class ExampleEntityFirstResourceIntTest extends AbstractSpringBootTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/example-entity-firsts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ExampleEntityFirstRepository exampleEntityFirstRepository;

    @Autowired
    private ExampleEntityFirstMapper exampleEntityFirstMapper;

    @Autowired
    private EntityManager em;

    private MockMvc restExampleEntityFirstMockMvc;

    @Autowired
    private TenantContextHolder tenantContextHolder;

    @Autowired
    private XmAuthenticationContextHolder authContextHolder;

    @Autowired
    private LepManager lepManager;

    @Autowired
    private ExampleEntityFirstResource exampleEntityFirstResource;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private FilterChainProxy springSecurityFilter;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;


    private ExampleEntityFirst exampleEntityFirst;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExampleEntityFirst createEntity(EntityManager em) {
        ExampleEntityFirst exampleEntityFirst = new ExampleEntityFirst().setName(DEFAULT_NAME);
        return exampleEntityFirst;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExampleEntityFirst createUpdatedEntity(EntityManager em) {
        ExampleEntityFirst exampleEntityFirst = new ExampleEntityFirst().setName(UPDATED_NAME);
        return exampleEntityFirst;
    }

    @BeforeEach
    public void initTest() {
        exampleEntityFirst = createEntity(em);
    }

    @BeforeTransaction
    public void beforeTransaction() {
        TenantContextUtils.setTenant(tenantContextHolder, "RESINTTEST");
    }

    @SneakyThrows
    @BeforeEach
    public void setup() {
        this.restExampleEntityFirstMockMvc = MockMvcBuilders.standaloneSetup(exampleEntityFirstResource)
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
    public void createExampleEntityFirst() throws Exception {
        int databaseSizeBeforeCreate = exampleEntityFirstRepository.findAll().size();
        // Create the ExampleEntityFirst
        ExampleEntityFirstDto exampleEntityFirstDto = exampleEntityFirstMapper.toDto(exampleEntityFirst);
        restExampleEntityFirstMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(exampleEntityFirstDto))
            )
            .andExpect(status().isCreated());

        // Validate the ExampleEntityFirst in the database
        List<ExampleEntityFirst> exampleEntityFirstList = exampleEntityFirstRepository.findAll();
        assertThat(exampleEntityFirstList).hasSize(databaseSizeBeforeCreate + 1);
        ExampleEntityFirst testExampleEntityFirst = exampleEntityFirstList.get(exampleEntityFirstList.size() - 1);
        assertThat(testExampleEntityFirst.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createExampleEntityFirstWithExistingId() throws Exception {
        // Create the ExampleEntityFirst with an existing ID
        exampleEntityFirst.setId(1L);
        ExampleEntityFirstDto exampleEntityFirstDto = exampleEntityFirstMapper.toDto(exampleEntityFirst);

        int databaseSizeBeforeCreate = exampleEntityFirstRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restExampleEntityFirstMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(exampleEntityFirstDto))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExampleEntityFirst in the database
        List<ExampleEntityFirst> exampleEntityFirstList = exampleEntityFirstRepository.findAll();
        assertThat(exampleEntityFirstList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllExampleEntityFirsts() throws Exception {
        // Initialize the database
        exampleEntityFirstRepository.saveAndFlush(exampleEntityFirst);

        // Get all the exampleEntityFirstList
        restExampleEntityFirstMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(exampleEntityFirst.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void getExampleEntityFirst() throws Exception {
        // Initialize the database
        exampleEntityFirstRepository.saveAndFlush(exampleEntityFirst);

        // Get the exampleEntityFirst
        restExampleEntityFirstMockMvc
            .perform(get(ENTITY_API_URL_ID, exampleEntityFirst.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(exampleEntityFirst.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getExampleEntityFirstsByIdFiltering() throws Exception {
        // Initialize the database
        exampleEntityFirstRepository.saveAndFlush(exampleEntityFirst);

        Long id = exampleEntityFirst.getId();

        defaultExampleEntityFirstShouldBeFound("id.equals=" + id);
        defaultExampleEntityFirstShouldNotBeFound("id.notEquals=" + id);

        defaultExampleEntityFirstShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultExampleEntityFirstShouldNotBeFound("id.greaterThan=" + id);

        defaultExampleEntityFirstShouldBeFound("id.lessThanOrEqual=" + id);
        defaultExampleEntityFirstShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    public void getAllExampleEntityFirstsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        exampleEntityFirstRepository.saveAndFlush(exampleEntityFirst);

        // Get all the exampleEntityFirstList where name equals to DEFAULT_NAME
        defaultExampleEntityFirstShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the exampleEntityFirstList where name equals to UPDATED_NAME
        defaultExampleEntityFirstShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllExampleEntityFirstsByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        exampleEntityFirstRepository.saveAndFlush(exampleEntityFirst);

        // Get all the exampleEntityFirstList where name not equals to DEFAULT_NAME
        defaultExampleEntityFirstShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the exampleEntityFirstList where name not equals to UPDATED_NAME
        defaultExampleEntityFirstShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllExampleEntityFirstsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        exampleEntityFirstRepository.saveAndFlush(exampleEntityFirst);

        // Get all the exampleEntityFirstList where name in DEFAULT_NAME or UPDATED_NAME
        defaultExampleEntityFirstShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the exampleEntityFirstList where name equals to UPDATED_NAME
        defaultExampleEntityFirstShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllExampleEntityFirstsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        exampleEntityFirstRepository.saveAndFlush(exampleEntityFirst);

        // Get all the exampleEntityFirstList where name is not null
        defaultExampleEntityFirstShouldBeFound("name.specified=true");

        // Get all the exampleEntityFirstList where name is null
        defaultExampleEntityFirstShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllExampleEntityFirstsByNameContainsSomething() throws Exception {
        // Initialize the database
        exampleEntityFirstRepository.saveAndFlush(exampleEntityFirst);

        // Get all the exampleEntityFirstList where name contains DEFAULT_NAME
        defaultExampleEntityFirstShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the exampleEntityFirstList where name contains UPDATED_NAME
        defaultExampleEntityFirstShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllExampleEntityFirstsByNameNotContainsSomething() throws Exception {
        // Initialize the database
        exampleEntityFirstRepository.saveAndFlush(exampleEntityFirst);

        // Get all the exampleEntityFirstList where name does not contain DEFAULT_NAME
        defaultExampleEntityFirstShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the exampleEntityFirstList where name does not contain UPDATED_NAME
        defaultExampleEntityFirstShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllExampleEntityFirstsByExampleEntitySecondIsEqualToSomething() throws Exception {
        // Initialize the database
        exampleEntityFirstRepository.saveAndFlush(exampleEntityFirst);
        ExampleEntitySecond exampleEntitySecond;
        if (TestUtil.findAll(em, ExampleEntitySecond.class).isEmpty()) {
            exampleEntitySecond = ExampleEntitySecondResourceIntTest.createEntity(em);
            em.persist(exampleEntitySecond);
            em.flush();
        } else {
            exampleEntitySecond = TestUtil.findAll(em, ExampleEntitySecond.class).get(0);
        }
        em.persist(exampleEntitySecond);
        em.flush();
        exampleEntityFirst.setExampleEntitySecond(exampleEntitySecond);
        exampleEntityFirstRepository.saveAndFlush(exampleEntityFirst);
        Long exampleEntitySecondId = exampleEntitySecond.getId();

        // Get all the exampleEntityFirstList where exampleEntitySecond equals to exampleEntitySecondId
        defaultExampleEntityFirstShouldBeFound("exampleEntitySecondId.equals=" + exampleEntitySecondId);

        // Get all the exampleEntityFirstList where exampleEntitySecond equals to (exampleEntitySecondId + 1)
        defaultExampleEntityFirstShouldNotBeFound("exampleEntitySecondId.equals=" + (exampleEntitySecondId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultExampleEntityFirstShouldBeFound(String filter) throws Exception {
        restExampleEntityFirstMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(exampleEntityFirst.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));

        // Check, that the count call also returns 1
        restExampleEntityFirstMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultExampleEntityFirstShouldNotBeFound(String filter) throws Exception {
        restExampleEntityFirstMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restExampleEntityFirstMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingExampleEntityFirst() throws Exception {
        // Get the exampleEntityFirst
        restExampleEntityFirstMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void putNewExampleEntityFirst() throws Exception {
        // Initialize the database
        exampleEntityFirstRepository.saveAndFlush(exampleEntityFirst);

        int databaseSizeBeforeUpdate = exampleEntityFirstRepository.findAll().size();

        // Update the exampleEntityFirst
        ExampleEntityFirst updatedExampleEntityFirst = exampleEntityFirstRepository.findById(exampleEntityFirst.getId()).get();
        // Disconnect from session so that the updates on updatedExampleEntityFirst are not directly saved in db
        em.detach(updatedExampleEntityFirst);
        updatedExampleEntityFirst.setName(UPDATED_NAME);
        ExampleEntityFirstDto exampleEntityFirstDto = exampleEntityFirstMapper.toDto(updatedExampleEntityFirst);

        restExampleEntityFirstMockMvc
            .perform(
                put(ENTITY_API_URL_ID, exampleEntityFirstDto.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(exampleEntityFirstDto))
            )
            .andExpect(status().isOk());

        // Validate the ExampleEntityFirst in the database
        List<ExampleEntityFirst> exampleEntityFirstList = exampleEntityFirstRepository.findAll();
        assertThat(exampleEntityFirstList).hasSize(databaseSizeBeforeUpdate);
        ExampleEntityFirst testExampleEntityFirst = exampleEntityFirstList.get(exampleEntityFirstList.size() - 1);
        assertThat(testExampleEntityFirst.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void putNonExistingExampleEntityFirst() throws Exception {
        int databaseSizeBeforeUpdate = exampleEntityFirstRepository.findAll().size();
        exampleEntityFirst.setId(count.incrementAndGet());

        // Create the ExampleEntityFirst
        ExampleEntityFirstDto exampleEntityFirstDto = exampleEntityFirstMapper.toDto(exampleEntityFirst);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExampleEntityFirstMockMvc
            .perform(
                put(ENTITY_API_URL_ID, exampleEntityFirstDto.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(exampleEntityFirstDto))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExampleEntityFirst in the database
        List<ExampleEntityFirst> exampleEntityFirstList = exampleEntityFirstRepository.findAll();
        assertThat(exampleEntityFirstList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void putWithIdMismatchExampleEntityFirst() throws Exception {
        int databaseSizeBeforeUpdate = exampleEntityFirstRepository.findAll().size();
        exampleEntityFirst.setId(count.incrementAndGet());

        // Create the ExampleEntityFirst
        ExampleEntityFirstDto exampleEntityFirstDto = exampleEntityFirstMapper.toDto(exampleEntityFirst);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExampleEntityFirstMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(exampleEntityFirstDto))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExampleEntityFirst in the database
        List<ExampleEntityFirst> exampleEntityFirstList = exampleEntityFirstRepository.findAll();
        assertThat(exampleEntityFirstList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void putWithMissingIdPathParamExampleEntityFirst() throws Exception {
        int databaseSizeBeforeUpdate = exampleEntityFirstRepository.findAll().size();
        exampleEntityFirst.setId(count.incrementAndGet());

        // Create the ExampleEntityFirst
        ExampleEntityFirstDto exampleEntityFirstDto = exampleEntityFirstMapper.toDto(exampleEntityFirst);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExampleEntityFirstMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(exampleEntityFirstDto))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ExampleEntityFirst in the database
        List<ExampleEntityFirst> exampleEntityFirstList = exampleEntityFirstRepository.findAll();
        assertThat(exampleEntityFirstList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void partialUpdateExampleEntityFirstWithPatch() throws Exception {
        // Initialize the database
        exampleEntityFirstRepository.saveAndFlush(exampleEntityFirst);

        int databaseSizeBeforeUpdate = exampleEntityFirstRepository.findAll().size();

        // Update the exampleEntityFirst using partial update
        ExampleEntityFirst partialUpdatedExampleEntityFirst = new ExampleEntityFirst();
        partialUpdatedExampleEntityFirst.setId(exampleEntityFirst.getId());

        partialUpdatedExampleEntityFirst.setName(UPDATED_NAME);

        restExampleEntityFirstMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedExampleEntityFirst.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedExampleEntityFirst))
            )
            .andExpect(status().isOk());

        // Validate the ExampleEntityFirst in the database
        List<ExampleEntityFirst> exampleEntityFirstList = exampleEntityFirstRepository.findAll();
        assertThat(exampleEntityFirstList).hasSize(databaseSizeBeforeUpdate);
        ExampleEntityFirst testExampleEntityFirst = exampleEntityFirstList.get(exampleEntityFirstList.size() - 1);
        assertThat(testExampleEntityFirst.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void fullUpdateExampleEntityFirstWithPatch() throws Exception {
        // Initialize the database
        exampleEntityFirstRepository.saveAndFlush(exampleEntityFirst);

        int databaseSizeBeforeUpdate = exampleEntityFirstRepository.findAll().size();

        // Update the exampleEntityFirst using partial update
        ExampleEntityFirst partialUpdatedExampleEntityFirst = new ExampleEntityFirst();
        partialUpdatedExampleEntityFirst.setId(exampleEntityFirst.getId());

        partialUpdatedExampleEntityFirst.setName(UPDATED_NAME);

        restExampleEntityFirstMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedExampleEntityFirst.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedExampleEntityFirst))
            )
            .andExpect(status().isOk());

        // Validate the ExampleEntityFirst in the database
        List<ExampleEntityFirst> exampleEntityFirstList = exampleEntityFirstRepository.findAll();
        assertThat(exampleEntityFirstList).hasSize(databaseSizeBeforeUpdate);
        ExampleEntityFirst testExampleEntityFirst = exampleEntityFirstList.get(exampleEntityFirstList.size() - 1);
        assertThat(testExampleEntityFirst.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void patchNonExistingExampleEntityFirst() throws Exception {
        int databaseSizeBeforeUpdate = exampleEntityFirstRepository.findAll().size();
        exampleEntityFirst.setId(count.incrementAndGet());

        // Create the ExampleEntityFirst
        ExampleEntityFirstDto exampleEntityFirstDto = exampleEntityFirstMapper.toDto(exampleEntityFirst);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExampleEntityFirstMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, exampleEntityFirstDto.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(exampleEntityFirstDto))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExampleEntityFirst in the database
        List<ExampleEntityFirst> exampleEntityFirstList = exampleEntityFirstRepository.findAll();
        assertThat(exampleEntityFirstList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void patchWithIdMismatchExampleEntityFirst() throws Exception {
        int databaseSizeBeforeUpdate = exampleEntityFirstRepository.findAll().size();
        exampleEntityFirst.setId(count.incrementAndGet());

        // Create the ExampleEntityFirst
        ExampleEntityFirstDto exampleEntityFirstDto = exampleEntityFirstMapper.toDto(exampleEntityFirst);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExampleEntityFirstMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(exampleEntityFirstDto))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExampleEntityFirst in the database
        List<ExampleEntityFirst> exampleEntityFirstList = exampleEntityFirstRepository.findAll();
        assertThat(exampleEntityFirstList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void patchWithMissingIdPathParamExampleEntityFirst() throws Exception {
        int databaseSizeBeforeUpdate = exampleEntityFirstRepository.findAll().size();
        exampleEntityFirst.setId(count.incrementAndGet());

        // Create the ExampleEntityFirst
        ExampleEntityFirstDto exampleEntityFirstDto = exampleEntityFirstMapper.toDto(exampleEntityFirst);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExampleEntityFirstMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(exampleEntityFirstDto))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ExampleEntityFirst in the database
        List<ExampleEntityFirst> exampleEntityFirstList = exampleEntityFirstRepository.findAll();
        assertThat(exampleEntityFirstList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExampleEntityFirst() throws Exception {
        // Initialize the database
        exampleEntityFirstRepository.saveAndFlush(exampleEntityFirst);

        int databaseSizeBeforeDelete = exampleEntityFirstRepository.findAll().size();

        // Delete the exampleEntityFirst
        restExampleEntityFirstMockMvc
            .perform(delete(ENTITY_API_URL_ID, exampleEntityFirst.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ExampleEntityFirst> exampleEntityFirstList = exampleEntityFirstRepository.findAll();
        assertThat(exampleEntityFirstList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
