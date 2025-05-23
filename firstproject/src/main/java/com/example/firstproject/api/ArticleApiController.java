package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
  @Autowired // 게시글 리파지터리 주입
  private ArticleService articleService;
  // GET
  @GetMapping("/api/articles")
  public List<Article> index(){
    return articleService.index();
  }
  @GetMapping("/api/articles/{id}")
  public Article show(@PathVariable Long id){
    return articleService.show(id);
  }

  // POST
  @PostMapping("/api/articles")
  public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
    Article created = articleService.create(dto);
    return (created != null) ?
        ResponseEntity.status(HttpStatus.OK).body(created) :
        ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }

  // PATCH 수정
  @PatchMapping("/api/articles/{id}")
  public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
    Article updated = articleService.update(id, dto);
    return ResponseEntity.status(HttpStatus.OK).body(updated);
  }

  // DELETE
  @DeleteMapping("/api/articles/{id}")
  public ResponseEntity<Article> delete(@PathVariable Long id){
    Article updated = articleService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  // 트랜잭션 처리
  @PostMapping("/api/transaction-test")
  public ResponseEntity<List<Article>> transactionTest(@RequestBody
                                                       List<ArticleForm> dtos) {
    List<Article> createdList = articleService.createArticles(dtos);
    return (createdList != null) ?
        ResponseEntity.status(HttpStatus.OK).body(createdList) :
        ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }

}
