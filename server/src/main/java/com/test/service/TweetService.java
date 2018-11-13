package com.test.service;

import com.test.model.Tweet;
import com.test.repos.TweetRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class TweetService {

    private final TweetRepo tweetRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public TweetService(TweetRepo tweetRepo) {
        this.tweetRepo = tweetRepo;
    }

    public void addFileTweet(MultipartFile file, Tweet tweet) {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            System.out.println("where is fucking dir, bro");
        }
        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + file.getOriginalFilename();

        try {
            file.transferTo(new File(uploadPath + "/" + resultFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        tweet.setFilename(resultFilename);

        tweetRepo.save(tweet);
    }

    public void deleteTweet(Integer tweetId) {
        Tweet tweet = tweetRepo.findTopById(tweetId);
        File file = new File(uploadPath + "/" + tweet.getFilename());
        file.delete();
        tweetRepo.deleteById(tweetId);
    }

    private boolean deleteFile(String filename) {
        File file = new File(uploadPath + "/" + filename);
        return file.delete();
    }

    public void editTweet(Tweet tweetT, MultipartFile filename) {
        Tweet tweet = tweetRepo.findTopById(tweetT.getId());
        if (!tweet.getText().equals(tweetT.getText())) {
            tweet.setText(tweetT.getText());
        }

        if (!filename.isEmpty()){
            addFileTweet(filename,tweet);
        } else {

            tweetRepo.save(tweet);
        }
    }
}