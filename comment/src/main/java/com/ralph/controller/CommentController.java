package com.ralph.controller;

import com.ralph.dto.CommentDTO;
import com.ralph.entity.Comment;
import com.ralph.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    @Autowired
    public CommentService commentService;

    @ApiOperation(value = "Get All Comments", notes = "to get all the comments from the system")
    @GetMapping
    public List<CommentDTO> findAll(){
        return commentService.findAll();
    }

    @ApiOperation(value = "add a comment", notes = "for any Media (Movie or TV serie)")
    @PostMapping("/{mediaId}")
    public ResponseEntity<String> save(@RequestBody Comment comment, @PathVariable Long mediaId){
        return new ResponseEntity<>(commentService.save(comment, mediaId), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all Comments", notes = "for a specific Media (Movie or TV serie)")
    @GetMapping("/filter/media/{id}")
    public ResponseEntity<List<CommentDTO>> findAllByMediaId(@PathVariable Long id){
        return new ResponseEntity<>(commentService.findAllById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Edit a comment", notes = "for any Media (Movie or TV serie)")
    @PutMapping("/{Id}")
    public ResponseEntity<String> update(@PathVariable Long Id, @RequestBody Comment comment){
        return new ResponseEntity<>(commentService.update(Id, comment), HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    @ApiOperation(value = "Delete a comment", notes = "Delete any Comment")
    public ResponseEntity<String> delete(@PathVariable Long Id){
        return new ResponseEntity<>(commentService.delete(Id), HttpStatus.OK);
    }

}
