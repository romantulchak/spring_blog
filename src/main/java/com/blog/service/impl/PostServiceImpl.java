package com.blog.service.impl;

import com.blog.model.Post;
import com.blog.repository.PostRepository;
import com.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;



    @Override
    public Post addPost(Post post){
        Post postToAdd = postRepository.saveAndFlush(post);
        return postToAdd;


    }

    @Override
    public void delete(long id){
        postRepository.deleteById(id);
    }
    @Override
    public Post editPost(Post post){
        Post post1 = postRepository.saveAndFlush(post);
        return post1;
    }
    public List<Post> getPosts(){
        List<Post> posts = postRepository.findAllByOrderByIdDesc();
        return posts;
    }
    @Override
    public Post getByIdAndName (long id, String name){
        Post post = postRepository.findByIdAndName(id, name);
        if (post !=null){
            return post;
        }else {

        }

        return null;

    }
    @Override
    public List<Post> getByName(String name) {
        Post post = postRepository.findByName(name);
        List<Post> posts = new ArrayList<>();
        if (post != null) {
            posts.add(post);
        }else {
            posts = postRepository.findAll();
        }
        return posts;
    }
}
