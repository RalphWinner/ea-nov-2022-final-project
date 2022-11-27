package com.ralph.integration;

import com.ralph.service.CommentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MessageListener {

  @Autowired
  private CommentService commentService;

  @RabbitListener(queues = "mediaQueue")
  public void receiveMessage(String message) {
        commentService.deleteAllByMediaId(Long.parseLong(message));
  }
}