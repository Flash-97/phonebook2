package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.PersonVo;

@WebServlet("/phonebook")
public class PhoneController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/pbc --> doGet");
		
		String action = request.getParameter("action");
		response.setContentType("text/html;charset=utf-8");    
		request.setCharacterEncoding("UTF-8");
		
		if("list".equals(action)) {
			
			//리스트
			System.out.println("list");
			
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> pList = phoneDao.getPersonList();
			
			//포워드
			request.setAttribute("personList", pList);
			
			WebUtil.forword(request, response, "/WEB-INF/list.jsp");
		}
		else if("wform".equals(action)) {
			//등록
			System.out.println("wform");
			
			WebUtil.forword(request, response, "/WEB-INF/WriteForm.jsp");
		}
		else if("insert".equals(action)) {
			System.out.println("번호저장");
			
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			PersonVo personVo = new PersonVo(name, hp, company);
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personInsert(personVo);

			WebUtil.redirect(request, response, "/phonebook2/phonebook?action=list");
		}
		else if("delete".equals(action)) {
			System.out.println("삭제");
			PhoneDao phoneDao = new PhoneDao();
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			phoneDao.personDelete(id);
			WebUtil.redirect(request, response, "/phonebook2/phonebook?action=list");
		}
		else if("uform".equals(action)) {
			PhoneDao phoneDao = new PhoneDao();
			int id = Integer.parseInt(request.getParameter("id"));
			 
			PersonVo vo = phoneDao.getPerson(id);
			
			request.setAttribute("personVo", vo);
			WebUtil.forword(request, response, "/WEB-INF/updateForm.jsp");
			
		}
		else if("update".equals(action)) {
			System.out.println("update");
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			int id = Integer.parseInt(request.getParameter("id"));
			
			PersonVo vo = new PersonVo(id, name, hp, company);
			PhoneDao phoneDao = new PhoneDao();
			
			phoneDao.personUpdate(vo);
			
			WebUtil.redirect(request, response, "/phonebook2/phonebook?action=list");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}