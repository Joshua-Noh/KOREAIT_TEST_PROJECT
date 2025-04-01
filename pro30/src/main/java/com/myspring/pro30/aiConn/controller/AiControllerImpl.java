package com.myspring.pro30.aiConn.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.codehaus.jackson.map.ObjectMapper;

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
            System.out.println("responseList : " + responseList);
            // JSP에서 사용할 데이터 추가
            mav.addObject("dataList", responseList);
			
        } catch (Exception e) {
            e.printStackTrace();
        }
		
        return mav;
    }
	
}
