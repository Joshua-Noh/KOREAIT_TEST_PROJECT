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
		
        // REST API ȣ��
        //String url = "http://localhost:5000/api/data";
        String url = "https://kduaro124.pythonanywhere.com/api/data";
        
        RestTemplate restTemplate = new RestTemplate();
        
        ModelAndView mav = new ModelAndView("/ai/data-view");
        try {
        	  // Flask �������� JSON �����͸� List<Map<String, Object>>�� ��������
            List<Map<String, Object>> responseList = restTemplate.getForObject(url, List.class);

            // ��� ���
            System.out.println("dataList : " + responseList);
            // JSP���� ����� ������ �߰�
            mav.addObject("dataList", responseList);
			
        } catch (Exception e) {
            e.printStackTrace();
        }
		
        return mav;
    }
	
	@RequestMapping(value= "/aiConn/testList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView fetchListData(HttpServletRequest request) {

	    String[] cities = request.getParameterValues("cities"); // form���� �Ѿ�� ��

	    // Flask API URL
	    String url = "https://kduaro124.pythonanywhere.com/api/listData";

	    // ���� ����Ʈ �����
	    List<String> cityList = (cities != null) ? Arrays.asList(cities) : Arrays.asList("����", "�뱸");

	    // ��û ���� ����
	    Map<String, Object> requestMap = new HashMap<String, Object>();
	    requestMap.put("cities", cityList);

	    // Http ��û ��� ����
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    // HttpEntity ����
	    HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(requestMap, headers);

	    // RestTemplate ����
	    RestTemplate restTemplate = new RestTemplate();

	    ModelAndView mav = new ModelAndView("/ai/data-view");

	    try {
	        // POST ��û �� ���� ó��
	        ResponseEntity<List> responseEntity = restTemplate.exchange(
	            url,
	            HttpMethod.POST,
	            entity,
	            List.class
	        );

	        List<Map<String, Object>> responseList = responseEntity.getBody();

	        // ��� ���
	        System.out.println("responseList : " + responseList);

	        // JSP�� ������ ����
	        mav.addObject("responseList", responseList);
	        
	        // GET ��û: ���� ��Ʈ�ѷ� �޼��� ���� ȣ��
            List<Map<String, Object>> getResponse = fetchBasicData(); 
            mav.addObject("dataList", getResponse);	        

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return mav;
	}
	
	// ���ο��� ȣ���� �� �ִ� GET ��û ó�� �޼���
    public List<Map<String, Object>> fetchBasicData() {
        //String getUrl = "http://localhost:5000/api/data";
        String getUrl = "https://kduaro124.pythonanywhere.com/api/data";
        
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(getUrl, List.class);
    }
	
}
