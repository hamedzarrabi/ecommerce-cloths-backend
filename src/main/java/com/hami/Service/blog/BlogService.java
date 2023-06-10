package com.hami.Service.blog;

import com.hami.DTO.blogDto.BlogDto;
import com.hami.Entity.blog.Blog;

import java.util.List;
import java.util.Optional;


public interface BlogService {
    public BlogDto createBlog(BlogDto blogDto);
    public BlogDto editBlog(BlogDto blogDto, Long id);
    public void deleteBlog(Long id);
    public List<Blog> findAllBlog();
    public Optional<Blog> findBlogById(Long blogId);
}
