package com.icthh.xm.ms.mstemplate.service.impl;

import com.icthh.xm.ms.mstemplate.domain.ExampleEntitySecond;
import com.icthh.xm.ms.mstemplate.repository.ExampleEntitySecondRepository;
import com.icthh.xm.ms.mstemplate.service.ExampleEntitySecondService;
import com.icthh.xm.ms.mstemplate.service.dto.ExampleEntitySecondDto;
import com.icthh.xm.ms.mstemplate.service.mapper.ExampleEntitySecondMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ExampleEntitySecond}.
 */
@Slf4j
@Service
@Transactional
public class ExampleEntitySecondServiceImpl implements ExampleEntitySecondService {

    private final ExampleEntitySecondRepository exampleEntitySecondRepository;

    private final ExampleEntitySecondMapper exampleEntitySecondMapper;

    public ExampleEntitySecondServiceImpl(
        ExampleEntitySecondRepository exampleEntitySecondRepository,
        ExampleEntitySecondMapper exampleEntitySecondMapper
    ) {
        this.exampleEntitySecondRepository = exampleEntitySecondRepository;
        this.exampleEntitySecondMapper = exampleEntitySecondMapper;
    }

    @Override
    public boolean existsById(Long id) {
        return exampleEntitySecondRepository.existsById(id);
    }

    @Override
    public ExampleEntitySecondDto save(ExampleEntitySecondDto exampleEntitySecondDto) {
        ExampleEntitySecond exampleEntitySecond = exampleEntitySecondMapper.toEntity(exampleEntitySecondDto);
        exampleEntitySecond = exampleEntitySecondRepository.save(exampleEntitySecond);
        return exampleEntitySecondMapper.toDto(exampleEntitySecond);
    }

    @Override
    public ExampleEntitySecondDto update(ExampleEntitySecondDto exampleEntitySecondDto) {
        ExampleEntitySecond exampleEntitySecond = exampleEntitySecondMapper.toEntity(exampleEntitySecondDto);
        exampleEntitySecond = exampleEntitySecondRepository.save(exampleEntitySecond);
        return exampleEntitySecondMapper.toDto(exampleEntitySecond);
    }

    @Override
    public Optional<ExampleEntitySecondDto> partialUpdate(ExampleEntitySecondDto exampleEntitySecondDto) {

        return exampleEntitySecondRepository
            .findById(exampleEntitySecondDto.getId())
            .map(existingExampleEntitySecond -> {
                exampleEntitySecondMapper.partialUpdate(existingExampleEntitySecond, exampleEntitySecondDto);

                return existingExampleEntitySecond;
            })
            .map(exampleEntitySecondRepository::save)
            .map(exampleEntitySecondMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ExampleEntitySecondDto> findAll(Pageable pageable) {
        return exampleEntitySecondRepository.findAll(pageable).map(exampleEntitySecondMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExampleEntitySecondDto> findOne(Long id) {
        return exampleEntitySecondRepository.findById(id).map(exampleEntitySecondMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        exampleEntitySecondRepository.deleteById(id);
    }
}
