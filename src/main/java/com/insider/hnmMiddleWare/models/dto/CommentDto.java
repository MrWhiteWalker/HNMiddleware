package com.insider.hnmMiddleWare.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by raman on 22/06/20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
  private String text;
  private String userId;
  private int userHnAge;
}
