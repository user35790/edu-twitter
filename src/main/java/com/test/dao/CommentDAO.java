package com.test.dao;

import com.test.model.Comment;

public interface CommentDAO {

    void create(Comment comment);

    void delete(int id);

    void updateText(int id, String text);

    void addLike(int id);

    void cancelLike(int id);
}
