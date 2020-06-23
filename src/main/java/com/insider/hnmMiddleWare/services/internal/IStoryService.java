package com.insider.hnmMiddleWare.services.internal;

import com.insider.hnmMiddleWare.models.dto.CommentDto;
import com.insider.hnmMiddleWare.models.dto.StoryDto;
import java.util.List;

/**
 * Created by raman on 22/06/20
 */
public interface IStoryService {

  List<StoryDto> getTopStories();

  List<CommentDto> getTopComments(long storyId);

  List<StoryDto> getPastStories();
}
