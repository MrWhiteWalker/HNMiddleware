package com.insider.hnmMiddleWare.utils;

import com.insider.hnmMiddleWare.models.dto.CommentDto;
import com.insider.hnmMiddleWare.models.dto.StoryDto;
import com.insider.hnmMiddleWare.models.dto.UserDto;
import com.insider.hnmMiddleWare.models.external.Item;
import com.insider.hnmMiddleWare.models.external.User;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by raman on 22/06/20
 */
public class ConvertorUtil {

  public static StoryDto convertItemToStoryDto(Item item) {
    return StoryDto.builder()
        .id(item.getId())
        .title(item.getTitle())
        .url(item.getUrl())
        .score(item.getScore())
        .submittedBy(item.getBy())
        .submitTime(item.getTime())
        .build();
  }

  public static CommentDto convertItemToCommentDto(Item item, UserDto user) {
    return CommentDto.builder()
        .text(item.getText())
        .userId(user.getUsername())
        .userHnAge(user.getUserAgeInYears())
        .build();
  }

  public static UserDto convertUserToDto(User user) {
    return UserDto.builder()
        .username(user.getId())
        .fetchTime(Instant.now())
        .userAgeInYears((int) ChronoUnit.YEARS.between(LocalDateTime.now(), LocalDateTime.now().plus(Duration.between(user.getCreated(), Instant.now()))))
        .build();
  }
}
