package com.spring.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spring.bean.ContactFormVO;
import com.utils.JMail;


@Controller
public class ContactFormController {

	private static List<ContactFormVO> contactFormList; 
	
	private Gson gson;
	static {
		contactFormList = new ArrayList<>();
		ContactFormVO contactFormVO = new ContactFormVO();
		contactFormVO.setName("mpi");
		contactFormVO.setBir(Date.valueOf(LocalDate.now()));
		contactFormVO.setArds("新竹縣");
		contactFormVO.setEmail("mpi@gmail.com");
		contactFormVO.setGender(1);
		contactFormVO.setTel("0912345678");
		contactFormVO.setIssue("初始化內容");
		contactFormList.add(contactFormVO);
	}
	
	@PostConstruct
	public void init() {
		System.out.println("init");
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	}
	
	
	@RequestMapping("add")
	@ResponseBody
	public String addContact(@RequestBody String message) {
		ContactFormVO contactFormVO = gson.fromJson(message, ContactFormVO.class);
		JMail.Send_mail(contactFormVO.getEmail(), "送出成功", "你的信件已送出成功");
		contactFormList.add(contactFormVO);
		contactFormList.forEach(System.out::println);
		return "listall";
	}
	
	@RequestMapping(value = "listall",method = RequestMethod.GET)
	public String listAll(Model model) {
		return "listAll";
	}
	
	@RequestMapping("getall")
	@ResponseBody
	public List<ContactFormVO> getAll(){
		
		return contactFormList;
	}
	
	
	@RequestMapping(value = "charts",method = RequestMethod.GET)
	public String charts() {
		return "charts";
	}
	
	@RequestMapping("getcahrts")
	@ResponseBody
	public TreeMap<String, List<String>> getcahrts() {
		TreeMap<String, List<String>> data = new TreeMap<>();
		List<String> dataList =  new ArrayList<>();
		List<String> labels =  new ArrayList<>();
		List<String> backgroundColorList = new ArrayList<>();
		long male = contactFormList.stream().filter(e->e.getGender()==1).count();
		long woman = contactFormList.stream().filter(e->e.getGender()==2).count();
		labels.add("男");
		labels.add("女");
		dataList.add(String.valueOf(male));
		dataList.add(String.valueOf(woman));
		for(int i = 0; i<labels.size(); i++) {
			backgroundColorList.add(randomRGB());
		}
		data.put("data", dataList);
		data.put("backgroundColor", backgroundColorList);
		data.put("labels",labels);
		System.out.println(data);
		return data;
	}
	
	public static String randomRGB() {
		StringBuffer stringBuffer = new StringBuffer("rgb(");
		Random random = new Random();
		for(int i = 0; i<=2;i++) {
			stringBuffer.append(String.valueOf(random.nextInt(256)));
			if(i<2) {
				stringBuffer.append(", ");
			}
		}
		stringBuffer.append(")");
		return stringBuffer.toString();
	}
}
