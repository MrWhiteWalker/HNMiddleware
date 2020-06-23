package com.insider.hnmMiddleWare.models.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by raman on 22/06/20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoryDto {

  private long id;
  private String title;
  private String url;
  private int score;
  private String submittedBy;
  private Instant submitTime;

}
