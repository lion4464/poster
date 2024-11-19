package com.postcreater.poster.post;

import com.postcreater.poster.exceptions.AccessDeniedException;
import com.postcreater.poster.exceptions.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PostService {
    PostResponse createPost(CreatePostRequest request);
    Page<PostResponse> getAllPosts(Pageable pageable);
    PostResponse getPost(UUID postId) throws DataNotFoundException;
    PostResponse updatePost(UUID postId, UpdatePostRequest request) throws DataNotFoundException, AccessDeniedException;
    void deletePost(UUID postId) throws DataNotFoundException, AccessDeniedException;

}
