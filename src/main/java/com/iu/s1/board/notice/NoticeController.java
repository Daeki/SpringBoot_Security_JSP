package com.iu.s1.board.notice;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iu.s1.board.BoardService;
import com.iu.s1.board.BoardVO;
import com.iu.s1.util.Pager;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {
	
	@Autowired
	private BoardService noticeService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "notice";
	}
	
	@GetMapping("delete")
	public String setDelete(BoardVO boardVO)throws Exception{
		int result = noticeService.setDelete(boardVO);
		return "redirect:list";
	}
	
	@PostMapping("update")
	public String setUpdate(BoardVO boardVO)throws Exception{
		int result = noticeService.setUpdate(boardVO);
		return "redirect:list";
	}
	
	@GetMapping("update")
	public String setUpdate(BoardVO boardVO, Model model)throws Exception{
		model.addAttribute("detail", noticeService.getDetail(boardVO));
		model.addAttribute("act", "update");
		return "board/boardForm";
	}
	
	@PostMapping("write")
	public String setWrite(BoardVO boardVO)throws Exception{
		int result = noticeService.setWrite(boardVO);
		return "redirect:list";
	}
	
	@GetMapping("write")
	public String setWrite(Model model, HttpSession session)throws Exception{
		model.addAttribute("detail", new BoardVO());
		model.addAttribute("act", "write");
		
		session.setAttribute("test", "admin");
		return "board/boardForm";
	}
	
	@GetMapping("detail")
	public String getDetail(BoardVO boardVO, Model model)throws Exception{
		model.addAttribute("detail", noticeService.getDetail(boardVO));
		return "board/boardDetail";
	}
	
	@GetMapping("list")
	public String getList(Pager pager, Model model)throws Exception{
		model.addAttribute("list", noticeService.getList(pager));
		model.addAttribute(pager);
		return "board/boardList";
	}

}
