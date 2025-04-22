package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {
  @Autowired // 게시글 리파지터리 주입
  private ArticleRepository articleRepository;

  public List<Article> index(){
    return articleRepository.findAll();
  }

  public Article show(@PathVariable Long id){
    return articleRepository.findById(id).orElse(null);
  }

  public Article create(@RequestBody ArticleForm dto){
    // 1. DTO 를 엔티티로 변환
    Article article = dto.toEntity();
    return articleRepository.save(article);
  }

  public Article update(@PathVariable Long id, @RequestBody ArticleForm dto){
    // 1. DTO -> 엔티티 변환
    Article article = dto.toEntity();
    log.info("id:: {}, article: {}", id, article.toString());
    // 2. 타깃 조회
    Article target = articleRepository.findById(id).orElse(null);
    // 3. 잘못된 요청 처리
    if (target == null || id != article.getId()){
      // 400 잘못된 응답!
      log.info("잘못된 요청: id:: {}, article: {}", id, article.toString());
    }
    // 4. 업데이터 및 정상 응답처리
    Article updated = articleRepository.save(article);
    return updated;
  }

  public Article delete(@PathVariable Long id){
    //1. 삭제할 대상 찾기
    Article target = articleRepository.findById(id).orElse(null);
    //2. 잘못된 요청 처리
    if (target == null){
      return null;
    }
    //3. 삭제하기
    articleRepository.delete(target);
    return target;
  }

  @Transactional
  public List<Article> createArticles(List<ArticleForm> dtos) {
    // 1. dto 묶음을 엔티티 묶음으로 변환하기 (Stream 형식)
//    List<Article> articleList = dtos.stream()
//        .map(dto -> dto.toEntity())
//        .collect(Collectors.toList());
    // 1. * for 문 형식
    List<Article> articleList = new ArrayList<>();
    for (int i=0; i< dtos.size(); i++){
      ArticleForm dto = dtos.get(i);
      Article entity = dto.toEntity();
      articleList.add(entity);
    }

    // 2. 엔티티 묶음을 DB에 저장하기
//    articleList.stream()
//        .forEach(article -> articleRepository.save(article));
    // 2. for문 형식
    for(int i=0; i < articleList.size(); i++){
      Article article = articleList.get(i);
      articleRepository.save(article);
    }

    // 3. 강제 예외 발생시키기
    //articleRepository.findById(-1L)
    //   .orElseThrow(() -> new IllegalArgumentException("결제 실패!"));

    // 4. 결과값 반환하기
    return articleList;
  }
}
