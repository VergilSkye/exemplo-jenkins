package dev.vergil.service;

import dev.vergil.domain.Comment;
import dev.vergil.domain.User;
import dev.vergil.repository.CommentRepository;
import dev.vergil.repository.UserRepository;
import dev.vergil.security.AuthoritiesConstants;
import dev.vergil.security.SecurityUtils;
import dev.vergil.service.dto.CommentDTO;
import dev.vergil.service.mapper.CommentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Comment}.
 */
@Service
@Transactional
public class CommentService {

    private final Logger log = LoggerFactory.getLogger(CommentService.class);

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.userRepository = userRepository;
    }

    /**
     * Save a comment.
     *
     * @param commentDTO the entity to save.
     * @return the persisted entity.
     */
    public CommentDTO save(CommentDTO commentDTO) {
        log.debug("Request to save Comment : {}", commentDTO);
        Comment comment = commentMapper.toEntity(commentDTO);
        setUserOnNewComment(comment);
        comment = commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    private void setUserOnNewComment(Comment comment) {
        if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
            getUser()
                .ifPresent(e -> {
                    if (comment.getLogin() == null) {
                        comment.setLogin(e);
                    }
                });
        } else {
            getUser()
                .ifPresent(comment::setLogin);
        }

    }

    private Optional<User> getUser() {
        return SecurityUtils.getCurrentUserLogin()
            .flatMap(userRepository::findOneByLogin);
    }


    /**
     * Get all the comments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CommentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Comments");
        return commentRepository.findAll(pageable)
            .map(commentMapper::toDto);
    }


    /**
     * Get one comment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommentDTO> findOne(Long id) {
        log.debug("Request to get Comment : {}", id);
        return commentRepository.findById(id)
            .map(commentMapper::toDto);
    }

    /**
     * Delete the comment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Comment : {}", id);
        commentRepository.deleteById(id);
    }
}
