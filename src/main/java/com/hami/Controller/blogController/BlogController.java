package com.hami.Controller.blogController;

import com.hami.DTO.blogDto.BlogDto;
import com.hami.Entity.blog.Blog;
import com.hami.Entity.user.User;
import com.hami.Service.blog.BlogService;
import com.hami.Service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Api(value = "CRUD Rest APIs for Blog resource")
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Create Blog REST API")
    @PostMapping(value = "/create/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createBlog(
            @RequestParam("title") String title,
            @RequestParam("image") MultipartFile image,
            @RequestParam("text") String text,
            @PathVariable("userId") Long userId) {

        BlogDto blog = new BlogDto();
        User user = userService.findUserById(userId);

        String imageAddress = "images/blog/" + user.getEmail();
        String originalAddressImage = "D:/Backend/E-commenrce-cloths-full-website/ecommerce-cloths-frontend/public/images/blog/" + user.getEmail();

        if (user != null) {
            blog.setTitle(title);
            blog.setImage(imageAddress + "/" + image.getOriginalFilename());
            blog.setText(text);
            blog.setUser(user);

            savePicture(image, originalAddressImage);

            BlogDto newBlog = blogService.createBlog(blog);
            return new ResponseEntity<>(newBlog, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("UserId not found", HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation(value = "Update Blog REST API")
    @PutMapping(value = "/edit/{userId}/{blogId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> editBlog(@RequestParam("title") String title,
                                      @RequestParam("image") MultipartFile image,
                                      @RequestParam("text") String text,
                                      @PathVariable("userId") Long userId,
                                      @PathVariable("blogId") Long blogId
    ) {
        Optional<Blog> blogDto = blogService.findBlogById(blogId);

        System.out.println("id:    " + blogDto.get().getId());

        BlogDto blog = new BlogDto();
        User user = userService.findUserById(userId);

        String imageAddress = "images/blog/" + user.getEmail();
        String originalAddressImage = "D:/Backend/E-commenrce-cloths-full-website/ecommerce-cloths-frontend/public/images/blog/" + user.getEmail();

        if (user != null && blogDto.get().getId() != null) {
            blog.setTitle(title);
            blog.setImage(imageAddress + "/" + image.getOriginalFilename());
            blog.setText(text);
            blog.setUser(user);

            savePicture(image, originalAddressImage);

            BlogDto newBlog = blogService.editBlog(blog, blogDto.get().getId());
            return new ResponseEntity<>(newBlog, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("UserId not found", HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Find All Blog REST API")
    @GetMapping("/findAll")
    public ResponseEntity<List<Blog>> findAll() {
        List<Blog> blog = blogService.findAllBlog();
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }
    @ApiOperation(value = "Find Blog By Id REST API")
    @GetMapping("/findById/{blogId}")
    public ResponseEntity<Blog> findBlogById(@PathVariable("blogId") Long blogId) {
        Blog blog = blogService.findBlogById(blogId).get();
        return new ResponseEntity<Blog>(blog, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Blog from id REST API")
    @DeleteMapping("/deleteBlog/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable("id") Long id) {
        blogService.deleteBlog(id);
        return new ResponseEntity<>("Deleted blog id:" + id + " Successfully.", HttpStatus.OK);
    }

    private String savePicture(MultipartFile image, String address) {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        try {
            Path path = Paths.get(address);
            Files.createDirectories(path);
            Files.copy(image.getInputStream(), path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            return "Success image save.";
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", e);
        }
    }
}
