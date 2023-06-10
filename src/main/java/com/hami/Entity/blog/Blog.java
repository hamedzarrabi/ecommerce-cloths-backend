package com.hami.Entity.blog;

import com.hami.Entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blogs", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private String image;

    @Column(name = "create_blog", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createBlog;

    @Column(name = "update_blog")
    @UpdateTimestamp
    private Date updateBlog;

    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
