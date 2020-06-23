package com.insider.hnmMiddleWare.controllers;

import com.insider.hnmMiddleWare.models.dto.CommentDto;
import com.insider.hnmMiddleWare.models.dto.StoryDto;
import com.insider.hnmMiddleWare.services.internal.IStoryService;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by raman on 12/06/20
 */
@RestController
@RequestMapping("/api/v1/")
@Api("Main Controller")
public class MainController {


  private final IStoryService storyService;

  @Autowired
  public MainController(IStoryService storyService) {
    this.storyService = storyService;
  }


/*  @GetMapping("get_item/{id}")
  public ResponseEntity<Item> getItem(@PathVariable("id") long id) {
    Optional<Item> itemOptional = hackerNewsService.getItem(id);
    return itemOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }*/

  @GetMapping("top-stories")
  public ResponseEntity<List<StoryDto>> getTopStories() {
    return ResponseEntity.ok(storyService.getTopStories());
  }

  @GetMapping("/comments")
  public ResponseEntity<List<CommentDto>> getComments(@RequestParam("story_id") long storyId) {
    return ResponseEntity.ok(storyService.getTopComments(storyId));
  }

  @GetMapping("past-stories")
  public ResponseEntity<List<StoryDto>> getPastStories() {
    return ResponseEntity.ok(storyService.getPastStories());
  }
}
