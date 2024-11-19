package com.postcreater.poster.post;

import com.postcreater.poster.users.UserEntity;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "authorUsername", source = "author.username")
    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "canEdit", expression = "java(post.getAuthor().getId().equals(currentUserId))")
    PostResponse toResponse(PostEntity post, @Context UUID currentUserId);

    default PostEntity toEntity(CreatePostRequest request, UserEntity author) {
        return PostEntity.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(author)
                .build();
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    void updateEntity(@MappingTarget PostEntity post, UpdatePostRequest request);
}