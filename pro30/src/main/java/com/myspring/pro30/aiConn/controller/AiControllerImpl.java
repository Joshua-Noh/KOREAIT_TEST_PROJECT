package com.myspring.pro30.aiConn.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AiControllerImpl implements AiController{
	
	@Override
	@RequestMapping(value= "/aiConn/test.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView fetchData() {
		
        // REST API 호출
        String url = "http://localhost:5000/api/data";
        RestTemplate restTemplate = new RestTemplate();
        
        ModelAndView mav = new ModelAndView("/ai/data-view");
        try {
        	  // Flask 서버에서 JSON 데이터를 List<Map<String, Object>>로 가져오기
            List<Map<String, Object>> responseList = restTemplate.getForObject(url, List.class);

            // 결과 출력
            System.out.println("dataList : " + responseList);
            // JSP에서 사용할 데이터 추가
            mav.addObject("dataList", responseList);
			
        } catch (Exception e) {
            e.printStackTrace();
        }
		
        return mav;
    }
	
	@RequestMapping(value= "/aiConn/testList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView fetchListData(HttpServletRequest request) {

	    String[] cities = request.getParameterValues("cities"); // form에서 넘어온 값

	    // Flask API URL
	    String url = "http://localhost:5000/api/listData";

	    // 도시 리스트 만들기
	    List<String> cityList = (cities != null) ? Arrays.asList(cities) : Arrays.asList("서울", "대구");

	    // 요청 본문 생성
	    Map<String, Object> requestMap = new HashMap<String, Object>();
	    requestMap.put("cities", cityList);

	    // Http 요청 헤더 설정
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    // HttpEntity 생성
	    HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(requestMap, headers);

	    // RestTemplate 설정
	    RestTemplate restTemplate = new RestTemplate();

	    ModelAndView mav = new ModelAndView("/ai/data-view");

	    try {
	        // POST 요청 및 응답 처리
	        ResponseEntity<List> responseEntity = restTemplate.exchange(
	            url,
	            HttpMethod.POST,
	            entity,
	            List.class
	        );

	        List<Map<String, Object>> responseList = responseEntity.getBody();

	        // 결과 출력
	        System.out.println("responseList : " + responseList);

	        // JSP로 데이터 전달
	        mav.addObject("responseList", responseList);
	        
	        // GET 요청: 같은 컨트롤러 메서드 직접 호출
            List<Map<String, Object>> getResponse = fetchBasicData(); 
            mav.addObject("dataList", getResponse);	        

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return mav;
	}
	
	// 내부에서 호출할 수 있는 GET 요청 처리 메서드
    public List<Map<String, Object>> fetchBasicData() {
        String getUrl = "http://localhost:5000/api/data";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(getUrl, List.class);
    }
	
}
