package com.insider.hnmMiddleWare.models.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Instant;
import lombok.Data;

/**
 * Created by raman on 22/06/20
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
  private String id;
  private Instant created;
}
