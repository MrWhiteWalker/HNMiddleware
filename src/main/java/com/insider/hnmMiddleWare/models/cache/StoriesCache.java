package com.insider.hnmMiddleWare.models.cache;

import com.insider.hnmMiddleWare.models.dto.StoryDto;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by raman on 22/06/20
 */
public class StoriesCache {
  public static List<StoryDto> topStories;
  public static Map<Long, StoryDto> pastStories =  new HashMap<>();
  public static Instant fetchTime;
}
