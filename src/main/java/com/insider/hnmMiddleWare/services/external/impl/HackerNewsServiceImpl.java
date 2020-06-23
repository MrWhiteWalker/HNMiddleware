package com.insider.hnmMiddleWare.services.external.impl;

import com.insider.hnmMiddleWare.enums.ItemType;
import com.insider.hnmMiddleWare.models.dto.StoryDto;
import com.insider.hnmMiddleWare.models.external.Item;
import com.insider.hnmMiddleWare.models.external.Item.SortByScoreDesc;
import com.insider.hnmMiddleWare.models.external.User;
import com.insider.hnmMiddleWare.services.external.IHackerNewsService;
import com.insider.hnmMiddleWare.utils.ConvertorUtil;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by raman on 12/06/20
 */
@Service
@Slf4j
public class HackerNewsServiceImpl implements IHackerNewsService {

  private RestTemplate restTemplate = new RestTemplate();
  @Value("${hackerNews.baseUrl}")
  private String baseUrl;
  @Value("${hackerNews.relativeUrl.item}")
  private String getItemApiUrl;
  @Value("${hackerNews.relativeUrl.user}")
  private String getUserApiUrl;
  @Value("${hackerNews.relativeUrl.topItems}")
  private String getTopItemsApiUrl;
  @Value("${hackerNews.topItems.limit}")
  private int topItemsLimit;


  @Override
  public Item getItem(long id) {
    // log.info("getting item with id:{}", id);
    ResponseEntity<Item> responseEntity = restTemplate.getForEntity(baseUrl + String.format(getItemApiUrl, id), Item.class);
    if (responseEntity.getStatusCode().is2xxSuccessful()) {
      if (responseEntity.getBody() != null) {
        return responseEntity.getBody();
      } else {
        throw new IllegalArgumentException("Item not found");
      }
    } else {
      throw new IllegalArgumentException("API call unsuccessful");
    }
  }

  @Override
  public User getUser(String username) {
    ResponseEntity<User> responseEntity = restTemplate.getForEntity(baseUrl + String.format(getUserApiUrl, username), User.class);
    if (responseEntity.getStatusCode().is2xxSuccessful()) {
      if (responseEntity.getBody() != null) {
        return responseEntity.getBody();
      } else {
        throw new IllegalArgumentException("User not found");
      }
    } else {
      throw new IllegalArgumentException("API call unsuccessful");
    }  }

  @Override
  public List<StoryDto> fetchTopStories() {
    ResponseEntity<List<Long>> responseEntity = restTemplate.exchange(baseUrl + getTopItemsApiUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Long>>() {
    });
    if (responseEntity.getStatusCode().is2xxSuccessful()) {

      List<Item> topItems = responseEntity.getBody().stream()
          .map(this::getItem)
          .filter(x -> ItemType.story.equals(x.getType()))
          .sorted(new SortByScoreDesc())
          .collect(Collectors.toList());
      if (CollectionUtils.size(topItems) > topItemsLimit) {
        return topItems.subList(0, topItemsLimit).stream().map(ConvertorUtil::convertItemToStoryDto).collect(Collectors.toList());
      } else {
        return topItems.stream().map(ConvertorUtil::convertItemToStoryDto).collect(Collectors.toList());
      }
    } else {
      throw new IllegalArgumentException("API call unsuccessful");
    }
  }
}
