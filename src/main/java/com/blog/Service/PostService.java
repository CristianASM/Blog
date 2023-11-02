package com.blog.Service;

import com.blog.Model.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPost();
    Post newPost(Post post);
    Post getPost(Long id);
    Post updatePost(Long id, Post post);
    void deletePost(Long id);

}
