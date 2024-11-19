package com.postcreater.poster.post;

import com.postcreater.poster.exceptions.AccessDeniedException;
import com.postcreater.poster.exceptions.DataNotFoundException;
import com.postcreater.poster.generic.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_AUTHER')")
    public ResponseEntity<ApiResponse<PostResponse>> createPost(
            @Valid @RequestBody CreatePostRequest request) {
        return ApiResponse.ok(
                postService.createPost(request),
                "Post created successfully"
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<PostResponse>>> getAllPosts(
            @PageableDefault(size = 10, sort = "createdDate") Pageable pageable) {
        return ApiResponse.ok(postService.getAllPosts(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> getPost(@PathVariable UUID id) throws DataNotFoundException {
        return ApiResponse.ok(postService.getPost(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_AUTHER')")
    public ResponseEntity<ApiResponse<PostResponse>> updatePost(
            @PathVariable UUID id,
            @Valid @RequestBody UpdatePostRequest request) throws DataNotFoundException, AccessDeniedException {
        return ApiResponse.ok(
                postService.updatePost(id, request),
                "Post updated successfully"
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_AUTHER')")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable UUID id) throws DataNotFoundException, AccessDeniedException {
        postService.deletePost(id);
        return ApiResponse.ok(null, "Post deleted successfully");
    }
}