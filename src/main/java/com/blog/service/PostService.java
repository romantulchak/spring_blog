package com.blog.service;

import com.blog.model.Post;
import java.util.List;


public interface PostService {

    Post addPost(Post post);
    Post getByIdAndName(long id, String name);
    void delete(long id);
    Post editPost(Post post);
    List<Post> getByName(String name);
    List<Post> getPosts();
}
