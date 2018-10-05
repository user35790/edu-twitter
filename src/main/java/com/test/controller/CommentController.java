package com.test.controller;

import com.test.dao.CommentDAO;
import com.test.model.Comment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentDAO commentDAO;

    public CommentController(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    // TODO: проверить поля

    @RequestMapping("/new")
    public void create(@RequestParam(name = "id_creator") int idCreator,
                       @RequestParam(name = "id_tweet") int idTweet,
                       @RequestParam(name = "text") String text){
        commentDAO.create(new Comment(idCreator, idTweet, text));
    }

    @RequestMapping("/delete")
    public void delete(@RequestParam(name = "id") int id){
        commentDAO.delete(id);
    }

    @RequestMapping("/update_text")
    public void updateText(@RequestParam(name = "id") int id,
                           @RequestParam(name = "text") String text){
        commentDAO.updateText(id, text);
    }

    // TODO: организовать привязку к пользователю
    @RequestMapping("/add_like")
    public void updateAddLike(@RequestParam(name = "id") int id){
        commentDAO.addLike(id);
    }
    @RequestMapping("/cancel_like")
    public void updateCancelLike(@RequestParam(name = "id") int id){
        commentDAO.cancelLike(id);
    }
}
