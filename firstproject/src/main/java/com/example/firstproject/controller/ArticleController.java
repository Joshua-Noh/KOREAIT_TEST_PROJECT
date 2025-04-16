package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class ArticleController {
  @Autowired  // 스프링부트가 미리 생성해 놓은 리파지터리 객체 주입(DI)
  private ArticleRepository articleRepository;

  @GetMapping("/articles/new")
  public String newArticleForm() {
    return "articles/new";
  }

  @PostMapping("/articles/create")
  public String createArticle(ArticleForm form) {
    //System.out.println("form.toString() :: " + form.toString());
    log.info("form.toString() :: " + form.toString());
    // 1. DTO 를 엔티티로 변환
    Article article = form.toEntity();
    //System.out.println("article.toString() ::: " + article.toString());
    log.info("article.toString() ::: " + article.toString());

    // 2. 리파지터리로 엔티티를 DB에 저장
    Article saved = articleRepository.save(article);
    System.out.println("saved.toString() ::: " + saved.toString());
    log.info("saved.toString() ::: " + saved.toString());

    return "";
  }
  
  @GetMapping("/articles/{id}")
  public String show(@PathVariable Long id, Model model){  // 매개변수 id 받아 오기
    log.info("id = "+ id);
    // 1. id를 조회해 데이터 가져오기
    Optional<Article> articleEntity = Optional.ofNullable(articleRepository.findById(id).orElse(null));

    // 2. 모델에 데이터를 등록하기
    model.addAttribute("article", articleEntity);

    // 3. 뷰 페이지에 반환하기
    return "articles/show";
  }

  @GetMapping("/articles")  // 목록 조회
  public String index(Model model){
    // 1. DB에서 모든 Article 데이터 가져오기
    ArrayList<Article> articleEntityList = articleRepository.findAll();

    // 2. 가져온 Article 묶음을 모델에 등록하기
    model.addAttribute("articleList", articleEntityList);

    // 3. 사용자에게 보여 줄 뷰페이지 설정하기
    return "articles/index";
  }

}
