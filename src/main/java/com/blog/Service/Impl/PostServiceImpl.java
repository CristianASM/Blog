package com.blog.Service.Impl;

import com.blog.Exceptions.PostNotFoundException;
import com.blog.Model.Post;
import com.blog.Repository.PostRepository;
import com.blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post newPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(()-> new PostNotFoundException("Publicación no encontrada"));
    }

    @Override
    public Post updatePost(Long id, Post post) {
        Post existingPost = postRepository.findById(id).orElseThrow(()-> new PostNotFoundException("Publicación no encontrada"));
        existingPost.setTitle(post.getTitle());
        existingPost.setBody(post.getBody());
        existingPost.setCreatedDate(post.getCreatedDate());
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.findById(id).orElseThrow(()-> new PostNotFoundException("Publicación no encontrada"));
        postRepository.deleteById(id);
    }
}
