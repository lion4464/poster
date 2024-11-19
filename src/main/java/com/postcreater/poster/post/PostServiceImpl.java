package com.postcreater.poster.post;


import com.postcreater.poster.exceptions.AccessDeniedException;
import com.postcreater.poster.exceptions.DataNotFoundException;
import com.postcreater.poster.users.UserEntity;
import com.postcreater.poster.utils.SecurityContextHolderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final SecurityContextHolderUtils securityContextHolderUtils;
    @Transactional
    public PostResponse createPost(CreatePostRequest request) {
        UserEntity author = getCurrentUser();
        PostEntity post = postMapper.toEntity(request, author);
        post = postRepository.save(post);
        return postMapper.toResponse(post, author.getId());
    }

    @Transactional(readOnly = true)
    public Page<PostResponse> getAllPosts(Pageable pageable) {
        UUID currentUserId = getCurrentUserId();
        return postRepository.findAllByOrderByCreatedDateDesc(pageable)
                .map(post -> postMapper.toResponse(post, currentUserId));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(UUID postId) throws DataNotFoundException {
        UUID currentUserId = getCurrentUserId();
        PostEntity post = getPostById(postId);
        return postMapper.toResponse(post, currentUserId);
    }

    @Transactional
    public PostResponse updatePost(UUID postId, UpdatePostRequest request) throws DataNotFoundException, AccessDeniedException {
        PostEntity post = getPostById(postId);
        checkPostAuthor(post);

        postMapper.updateEntity(post, request);
        post = postRepository.save(post);

        return postMapper.toResponse(post, getCurrentUserId());
    }

    @Transactional
    public void deletePost(UUID postId) throws DataNotFoundException, AccessDeniedException {
        PostEntity post = getPostById(postId);
        checkPostAuthor(post);
        postRepository.delete(post);
    }

    private PostEntity getPostById(UUID postId) throws DataNotFoundException {
        return postRepository.findById(postId)
                .orElseThrow(() -> new DataNotFoundException("Post not found with id: " + postId));
    }

    private void checkPostAuthor(PostEntity post) throws AccessDeniedException {
        UUID currentUserId = getCurrentUserId();
        if (!post.getAuthor().getId().equals(currentUserId)) {
            throw new AccessDeniedException("You can only modify your own posts");
        }
    }

    private UserEntity getCurrentUser() {
        return securityContextHolderUtils.getCurrentUser().getUser();
    }

    private UUID getCurrentUserId() {
        return getCurrentUser().getId();
    }
}