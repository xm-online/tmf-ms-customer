package com.icthh.xm.ms.mstemplate.service.impl;

import com.icthh.xm.ms.mstemplate.domain.ExampleEntityFirst;
import com.icthh.xm.ms.mstemplate.repository.ExampleEntityFirstRepository;
import com.icthh.xm.ms.mstemplate.service.ExampleEntityFirstService;
import com.icthh.xm.ms.mstemplate.service.dto.ExampleEntityFirstDto;
import com.icthh.xm.ms.mstemplate.service.mapper.ExampleEntityFirstMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ExampleEntityFirst}.
 */
@Slf4j
@Service
@Transactional
public class ExampleEntityFirstServiceImpl implements ExampleEntityFirstService {

    private final ExampleEntityFirstRepository exampleEntityFirstRepository;

    private final ExampleEntityFirstMapper exampleEntityFirstMapper;

    public ExampleEntityFirstServiceImpl(
        ExampleEntityFirstRepository exampleEntityFirstRepository,
        ExampleEntityFirstMapper exampleEntityFirstMapper
    ) {
        this.exampleEntityFirstRepository = exampleEntityFirstRepository;
        this.exampleEntityFirstMapper = exampleEntityFirstMapper;
    }

    @Override
    public boolean existsById(Long id) {
        return exampleEntityFirstRepository.existsById(id);
    }

    @Override
    public ExampleEntityFirstDto save(ExampleEntityFirstDto exampleEntityFirstDto) {
        ExampleEntityFirst exampleEntityFirst = exampleEntityFirstMapper.toEntity(exampleEntityFirstDto);
        exampleEntityFirst = exampleEntityFirstRepository.save(exampleEntityFirst);
        return exampleEntityFirstMapper.toDto(exampleEntityFirst);
    }

    @Override
    public ExampleEntityFirstDto update(ExampleEntityFirstDto exampleEntityFirstDto) {
        ExampleEntityFirst exampleEntityFirst = exampleEntityFirstMapper.toEntity(exampleEntityFirstDto);
        exampleEntityFirst = exampleEntityFirstRepository.save(exampleEntityFirst);
        return exampleEntityFirstMapper.toDto(exampleEntityFirst);
    }

    @Override
    public Optional<ExampleEntityFirstDto> partialUpdate(ExampleEntityFirstDto exampleEntityFirstDto) {
        return exampleEntityFirstRepository
            .findById(exampleEntityFirstDto.getId())
            .map(existingExampleEntityFirst -> {
                exampleEntityFirstMapper.partialUpdate(existingExampleEntityFirst, exampleEntityFirstDto);

                return existingExampleEntityFirst;
            })
            .map(exampleEntityFirstRepository::save)
            .map(exampleEntityFirstMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ExampleEntityFirstDto> findAll(Pageable pageable) {
        return exampleEntityFirstRepository.findAll(pageable).map(exampleEntityFirstMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExampleEntityFirstDto> findOne(Long id) {
        return exampleEntityFirstRepository.findById(id).map(exampleEntityFirstMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        exampleEntityFirstRepository.deleteById(id);
    }
}
