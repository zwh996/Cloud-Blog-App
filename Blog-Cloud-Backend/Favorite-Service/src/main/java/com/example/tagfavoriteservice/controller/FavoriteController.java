package com.example.tagfavoriteservice.controller;

import com.example.tagfavoriteservice.entity.FavoriteEntity;
import com.example.tagfavoriteservice.service.FavoriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorite")
@Slf4j
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @PostMapping("/add-favorite")
    public ResponseEntity addToFavorite(@RequestBody FavoriteEntity favorite) {
        favoriteService.insert(favorite);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/favorited")
    public ResponseEntity isFavorited(@RequestBody FavoriteEntity favorite) {
        if (favoriteService.isFavorited(favorite)) {
            return new ResponseEntity(true, HttpStatus.EXPECTATION_FAILED);
        } else {
            return new ResponseEntity(false, HttpStatus.OK);
        }
    }

    @PostMapping("/cancel")
    public ResponseEntity cancel(@RequestBody FavoriteEntity favorite) {
        favoriteService.cancel(favorite);
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/get-favorite/{userId}")
    public ResponseEntity getFavorite(@PathVariable Integer userId) {
        log.info("userId:{}", userId);
        return new ResponseEntity(favoriteService.favoriteArticle(userId), HttpStatus.OK);
    }
}

