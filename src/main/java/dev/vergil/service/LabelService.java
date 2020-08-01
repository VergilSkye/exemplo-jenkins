package dev.vergil.service;

import dev.vergil.domain.Label;
import dev.vergil.repository.LabelRepository;
import dev.vergil.service.dto.LabelDTO;
import dev.vergil.service.mapper.LabelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Label}.
 */
@Service
@Transactional
public class LabelService {

    private final Logger log = LoggerFactory.getLogger(LabelService.class);

    private final LabelRepository labelRepository;

    private final LabelMapper labelMapper;

    public LabelService(LabelRepository labelRepository, LabelMapper labelMapper) {
        this.labelRepository = labelRepository;
        this.labelMapper = labelMapper;
    }

    /**
     * Save a label.
     *
     * @param labelDTO the entity to save.
     * @return the persisted entity.
     */
    public LabelDTO save(LabelDTO labelDTO) {
        log.debug("Request to save Label : {}", labelDTO);
        Label label = labelMapper.toEntity(labelDTO);
        label = labelRepository.save(label);
        return labelMapper.toDto(label);
    }

    /**
     * Get all the labels.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<LabelDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Labels");
        return labelRepository.findAll(pageable)
            .map(labelMapper::toDto);
    }


    /**
     * Get one label by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LabelDTO> findOne(Long id) {
        log.debug("Request to get Label : {}", id);
        return labelRepository.findById(id)
            .map(labelMapper::toDto);
    }

    /**
     * Delete the label by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Label : {}", id);
        labelRepository.deleteById(id);
    }
}
