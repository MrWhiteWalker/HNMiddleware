package com.insider.hnmMiddleWare.services.external;

import com.insider.hnmMiddleWare.models.dto.StoryDto;
import com.insider.hnmMiddleWare.models.external.Item;
import com.insider.hnmMiddleWare.models.external.User;
import java.util.List;

/**
 * Created by raman on 12/06/20
 */
public interface IHackerNewsService {

  Item getItem(long id);
  User getUser(String username);
  List<StoryDto> fetchTopStories();
}
