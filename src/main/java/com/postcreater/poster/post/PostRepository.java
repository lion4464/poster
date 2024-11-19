package com.postcreater.poster.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, UUID> {
    Page<PostEntity> findAllByOrderByCreatedDateDesc(Pageable pageable);
    Page<PostEntity> findAllByAuthorIdOrderByCreatedDateDesc(UUID authorId, Pageable pageable);
}