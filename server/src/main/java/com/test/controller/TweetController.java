package com.test.controller;

import com.test.dao.TweetDAO;
import com.test.model.Tweet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    private final TweetDAO tweetDAO;

    public TweetController(TweetDAO tweetDAO) {
        this.tweetDAO = tweetDAO;
    }

    // TODO: проверка полей
    // TODO: общий класс под сессию пользователя для запоминания id

    @RequestMapping("/new")
    public void create(@RequestParam(name = "text") String text,
                       @RequestParam(name = "id_creator") int idCreator){
        tweetDAO.create(new Tweet(idCreator, text));
    }

    @RequestMapping("/update_text")
    public void updateText(@RequestParam(name = "id") int id,
                           @RequestParam(name = "text") String text) {
        tweetDAO.updateText(id, text);
    }

    // TODO: организовать привязку к пользователю
    @RequestMapping("/add_like")
    public void updateAddLike(@RequestParam(name = "id") int id){
        tweetDAO.addLike(id);
    }
    @RequestMapping("/cancel_like")
    public void updateCancelLike(@RequestParam(name = "id") int id){
        tweetDAO.cancelLike(id);
    }

    @RequestMapping("/delete")
    public void delete(@RequestParam(name = "id") int id){
        tweetDAO.delete(id);
    }
}
