package com.blog.repository;

import com.blog.model.Post;

import com.blog.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByName(String name);
    List<Post> findAllByOrderByIdDesc();
    List<Post> findByUserOrderByIdDesc(User user);
}
