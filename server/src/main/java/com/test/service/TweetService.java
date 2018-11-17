package com.test.service;

import com.test.model.Tweet;
import com.test.model.User;
import com.test.repos.TweetRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class TweetService {

    private final static Logger LOGGER = LoggerFactory.getLogger(TweetService.class);

    private final TweetRepo tweetRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public TweetService(TweetRepo tweetRepo) {
        this.tweetRepo = tweetRepo;
    }

    private void addFileTweet(MultipartFile file, Tweet tweet) {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            boolean mkdirResult = uploadDir.mkdirs();
            if (!mkdirResult){
                LOGGER.error("mkdir error");
            }
        }
        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + file.getOriginalFilename();
        try {
            file.transferTo(new File(uploadPath + "/" + resultFilename));
        } catch (IOException e) {
            LOGGER.error("", e);
        }
        tweet.setFilename(resultFilename);
    }

    public void deleteTweet(Integer tweetId) {
        Tweet tweet = tweetRepo.findTopById(tweetId);
        if (tweet.getFilename() != null && !tweet.getFilename().isEmpty()) {
            File file = new File(uploadPath + "/" + tweet.getFilename());
            boolean result = file.delete();
            if (!result) {
                LOGGER.error("Delete file error. File:{}", file.toString());
            }
        }
        tweetRepo.deleteById(tweetId);
    }

    public boolean editTweet(Tweet tweet, MultipartFile file, BindingResult bindingResult, Model model) {
        // check valid
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ServiceUtil.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("tweet", tweet);
            LOGGER.warn("Edit tweet #{} fail", tweet.getId());
            return false;
        }

        String originalFile = tweetRepo.findTopById(tweet.getId()).getFilename();
        if (!file.isEmpty()) {
            if (originalFile != null && !originalFile.isEmpty()) {
                File fileOrig = new File(uploadPath + "/" + originalFile);
                boolean result = fileOrig.delete();
                if (!result) {
                    LOGGER.error("Delete file error. File:{}", fileOrig.toString());
                }
            }
            addFileTweet(file, tweet);
        } else {
            if (originalFile != null && !originalFile.isEmpty()) {
                tweet.setFilename(tweetRepo.findTopById(tweet.getId()).getFilename());
            }
        }

        tweet.setAuthor(tweetRepo.findTopById(tweet.getId()).getAuthor());
        tweetRepo.save(tweet);
        return true;
    }

    public boolean addTweet(Tweet tweet, User author, MultipartFile file, BindingResult bindingResult, Model model) {
        // check valid
        if (bindingResult.hasErrors()) {
            LOGGER.warn("Errors in valid tweet add");
            Map<String, String> errorMap = ServiceUtil.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            model.addAttribute("tweet", tweet);
            LOGGER.warn("Add tweet from user @{} fail", author.getUsername());
            return false;
        }

        tweet.setAuthor(author);

        if (!file.isEmpty()) {
            addFileTweet(file, tweet);
        }

        model.addAttribute("tweet", null);
        tweetRepo.save(tweet);
        return true;
    }

}