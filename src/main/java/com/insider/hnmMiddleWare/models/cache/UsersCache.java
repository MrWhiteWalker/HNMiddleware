package com.insider.hnmMiddleWare.models.cache;

import com.insider.hnmMiddleWare.models.dto.UserDto;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by raman on 22/06/20
 */
public class UsersCache {
  public static Map<String, UserDto> data = new HashMap<>();
}
