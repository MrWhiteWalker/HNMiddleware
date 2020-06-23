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
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private String username;
  private int userAgeInYears;
  //TO KEEP TRACK OF WHEN THIS DATA WAS FETCHED FROM THE SERVER
  private Instant fetchTime;
}
