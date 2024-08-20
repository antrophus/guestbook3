package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;


@Controller
public class guestbookController {

	// 필드
	@Autowired
	private GuestbookDao guestbookDao;
	// 생성자

	// 메소드 -gs

	// 메소드 일반----------------------------------------------------

	// 등록폼 & 리스트
	@RequestMapping(value="/addlist", method={RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("GuestbookController.addList()");
		
		List<GuestbookVo> guestList = guestbookDao.getGuestlist();
		System.out.println(guestList);
		
		model.addAttribute("guestList", guestList);
		
		return "addList";
	}
	
	/* 등록 */
	/* Vo로 받을 때 */
	@RequestMapping(value="/insert", method= {RequestMethod.GET, RequestMethod.POST})
	public String insert(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("GuestbookController.insert()");
	 	
	 	//phonebookDao의 메소드를 이용해서 저장한다.
	 	int count = guestbookDao.insertGuestBook(guestbookVo);
		System.out.println(count);
	 	
		//리스트로 리다이렉트
		return "redirect:/addlist"; //
	}
	
	/* 삭제폼 이동 */
	@RequestMapping(value="/deleteform", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteform(@RequestParam(value="no") int no, Model model) {
		System.out.println("GuestbookController.deleteform()");
		
		GuestbookVo guestbookVo = guestbookDao.getScriptNo(no);
		model.addAttribute("guestbookVo", guestbookVo);
		
		//deleteForm.jsp로 포워드
		
		return "deleteForm";
	}
	/* 삭제 */
	@RequestMapping(value="/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("GuestbookController.delete()");
		
		// GuestbookDao의 delete 메소드를 사용하여 방명록 항목을 삭제
		int count = guestbookDao.delete(guestbookVo);
		System.out.println(count);
		
		// 삭제 후 리스트로 리다이렉트
		return "redirect:/addlist";
	}
}