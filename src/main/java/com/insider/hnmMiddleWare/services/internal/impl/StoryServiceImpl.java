package com.insider.hnmMiddleWare.services.internal.impl;

import com.insider.hnmMiddleWare.models.cache.StoriesCache;
import com.insider.hnmMiddleWare.models.cache.UsersCache;
import com.insider.hnmMiddleWare.models.dto.CommentDto;
import com.insider.hnmMiddleWare.models.dto.StoryDto;
import com.insider.hnmMiddleWare.models.dto.UserDto;
import com.insider.hnmMiddleWare.models.external.Item;
import com.insider.hnmMiddleWare.models.external.Item.SortByDescendantsDesc;
import com.insider.hnmMiddleWare.services.external.IHackerNewsService;
import com.insider.hnmMiddleWare.services.internal.IStoryService;
import com.insider.hnmMiddleWare.utils.ConvertorUtil;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by raman on 22/06/20
 */
@Service
public class StoryServiceImpl implements IStoryService {

  private final IHackerNewsService hackerNewsService;

  @Value("${hackerNews.cache.ttl}")
  private int cacheTime;
  @Value("${hackerNews.cache.user.ttl_days}")
  private int userCacheTime;
  @Value("${hackerNews.topComments.limit}")
  private int topCommentsLimit;

  @Autowired
  public StoryServiceImpl(IHackerNewsService hackerNewsService) {
    this.hackerNewsService = hackerNewsService;
  }

  @Override
  public List<StoryDto> getTopStories() {
    if (StoriesCache.fetchTime != null) {
      if (StoriesCache.fetchTime.isBefore(Instant.now().minus(cacheTime, ChronoUnit.MINUTES))) {
        StoriesCache.topStories = hackerNewsService.fetchTopStories();
        StoriesCache.fetchTime = Instant.now();
        StoriesCache.pastStories.putAll(StoriesCache.topStories.stream().collect(Collectors.toMap(StoryDto::getId, x -> x)));
      }
    } else {
      StoriesCache.topStories = hackerNewsService.fetchTopStories();
      StoriesCache.fetchTime = Instant.now();
      StoriesCache.pastStories.putAll(StoriesCache.topStories.stream().collect(Collectors.toMap(StoryDto::getId, x -> x)));
    }
    return StoriesCache.topStories;
  }

  @Override
  public List<CommentDto> getTopComments(long storyId) {
    Item item = hackerNewsService.getItem(storyId);
    if (item == null) {
      throw new IllegalArgumentException("Item not found");
    } else {
      List<Item> comments = item.getKids().stream().map(hackerNewsService::getItem)
          .peek(x -> x.setDescendants(getNumberOfDescendants(x)))
          .sorted(new SortByDescendantsDesc())
          .collect(Collectors.toList());

      if (CollectionUtils.size(comments) > topCommentsLimit) {
        return comments.subList(0, topCommentsLimit).stream().map(x -> ConvertorUtil.convertItemToCommentDto(x, getUser(x.getBy()))).collect(Collectors.toList());
      } else {
        return comments.stream().map(x -> ConvertorUtil.convertItemToCommentDto(x, getUser(x.getBy()))).collect(Collectors.toList());
      }
    }
  }

  @Override
  public List<StoryDto> getPastStories() {
    return new ArrayList<>(StoriesCache.pastStories.values());
  }

  private int getNumberOfDescendants(Item item) {
    if (CollectionUtils.isEmpty(item.getKids())) {
      item.setDescendants(0);
    } else {
      List<Item> kids = item.getKids().stream().map(hackerNewsService::getItem)
          .peek(x -> x.setDescendants(getNumberOfDescendants(x)))
          .collect(Collectors.toList());
      item.setDescendants(kids.stream().mapToInt(Item::getDescendants).sum());
    }
    return item.getDescendants();
  }


  private UserDto getUser(String username) {
    if (!UsersCache.data.containsKey(username) || UsersCache.data.get(username).getFetchTime().isBefore(Instant.now().minus(userCacheTime, ChronoUnit.DAYS))) {
      UsersCache.data.put(username, ConvertorUtil.convertUserToDto(hackerNewsService.getUser(username)));
    }
    return UsersCache.data.get(username);
  }
}
