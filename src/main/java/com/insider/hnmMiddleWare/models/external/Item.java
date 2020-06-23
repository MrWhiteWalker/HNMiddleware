package com.insider.hnmMiddleWare.models.external;

import com.insider.hnmMiddleWare.enums.ItemType;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import lombok.Data;

/**
 * Created by raman on 12/06/20
 */
@Data
public class Item {

  private long id;
  private ItemType type;
  private String by;
  private boolean deleted;
  private Instant time;
  private String text;
  private boolean dead;
  private Long parent;
  private Long poll;
  private List<Long> kids;
  private String url;
  private int score;
  private String title;
  private List<Long> parts;
  private Integer descendants;


  public static class SortByDescendantsDesc implements Comparator<Item> {

    public int compare(Item a, Item b) {
      return b.descendants - a.descendants;
    }
  }

  public static class SortByScoreDesc implements Comparator<Item> {

    public int compare(Item a, Item b) {
      return b.score - a.score;
    }
  }
}
