package com.insider.hnmMiddleWare.services.internal.impl;

import static org.mockito.ArgumentMatchers.anyLong;

import com.insider.hnmMiddleWare.models.dto.CommentDto;
import com.insider.hnmMiddleWare.models.dto.StoryDto;
import com.insider.hnmMiddleWare.models.external.Item;
import com.insider.hnmMiddleWare.services.external.IHackerNewsService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by raman on 23/06/20
 */
@RunWith(MockitoJUnitRunner.class)
public class StoryServiceImplTest {

  @Mock
  private IHackerNewsService hackerNewsService;
  private StoryServiceImpl service;

  @Before
  public void setUp() {
    service = new StoryServiceImpl(hackerNewsService);
  }

  @Test
  public void getTopStories() {
    Mockito.when(hackerNewsService.fetchTopStories()).thenReturn(new ArrayList<>());
    service.getTopStories();
    service.getTopStories();

  }

  @Test
  public void getTopComments() {
    Item item = new Item();
    item.setKids(new ArrayList<>());
    item.getKids().add(1L);
    item.getKids().add(2L);
    item.getKids().add(3L);

    Item kid1 = new Item();
    kid1.setKids(new ArrayList<>());
    Item kid2 = new Item();
    kid2.setKids(new ArrayList<>());
    Item kid3 = new Item();
    kid3.setKids(new ArrayList<>());
    kid3.getKids().add(4L);
    Item kid4 = new Item();
    kid1.setKids(new ArrayList<>());
    Mockito.when(hackerNewsService.getItem(12L)).thenReturn(item);
    Mockito.when(hackerNewsService.getItem(1L)).thenReturn(kid1);
    Mockito.when(hackerNewsService.getItem(2L)).thenReturn(kid2);
    Mockito.when(hackerNewsService.getItem(3L)).thenReturn(kid3);
    Mockito.when(hackerNewsService.getItem(4L)).thenReturn(kid4);
    service.getTopComments(12L);
  }

  @Test
  public void getPastStories() {
    service.getPastStories();
  }
}