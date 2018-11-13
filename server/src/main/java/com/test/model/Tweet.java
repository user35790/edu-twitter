package com.test.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @NotBlank(message = "Message cannot be empty")
    @Length(max = 2048, message = "Message too long (more than 2048Kb)")
    private String text;

    private String filename;

    private Date date;

    private int likes;

    @ManyToMany
    @JoinTable(
            name = "tweets_likes",
            joinColumns = { @JoinColumn(name = "tweet_id")},
            inverseJoinColumns = { @JoinColumn(name = "user_id")}
    )
    private Set<User> likesOf = new HashSet<>();

    public Tweet() {
    }

    public Tweet(User author, String text) {
        this.author = author;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Set<User> getLikesOf() {
        return likesOf;
    }

    public void setLikesOf(Set<User> likesOf) {
        this.likesOf = likesOf;
    }
}
