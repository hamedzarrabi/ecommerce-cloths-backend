package com.hami.Service.impl.blogImpl;

import com.hami.DTO.blogDto.BlogDto;
import com.hami.Entity.blog.Blog;
import com.hami.Exception.ResourceNotFoundException;
import com.hami.Repository.blog.BlogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService implements com.hami.Service.blog.BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public BlogDto createBlog(BlogDto blogDto) {
        // convert DTO to entity
        Blog blog = mapToEntity(blogDto);
        Blog newBlog = blogRepository.save(blog);

        // convert Entity to DTO
        BlogDto blogResponse = mapToDTO(newBlog);
        return blogResponse;

    }

    @Override
    public BlogDto editBlog(BlogDto blogDto, Long id) {
        // convert DTO to entity
        Blog blog = mapToEntity(blogDto);
        blog.setId(id);
        Blog newBlog = blogRepository.save(blog);

        // convert Entity to DTO
        BlogDto blogResponse = mapToDTO(newBlog);
        return blogResponse;
    }

    @Override
    public void deleteBlog(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Blog", "id", id));
        blogRepository.delete(blog);
    }

    @Override
    public List<Blog> findAllBlog() {
        return blogRepository.findAll();
    }

    @Override
    public Optional<Blog> findBlogById(Long blogId) {
        return blogRepository.findById(blogId);
    }

    private BlogDto mapToDTO(Blog blog) {
        BlogDto blogDto = mapper.map(blog, BlogDto.class);
        return blogDto;
    }

    private Blog mapToEntity(BlogDto blogDto) {
        Blog blog = mapper.map(blogDto, Blog.class);
        return blog;
    }
}
