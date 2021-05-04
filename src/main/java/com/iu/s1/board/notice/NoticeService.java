package com.iu.s1.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.s1.board.BoardMapper;
import com.iu.s1.board.BoardService;
import com.iu.s1.board.BoardVO;
import com.iu.s1.util.Pager;

@Service
public class NoticeService implements BoardService {

	@Autowired
	BoardMapper noticeMapper;
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		// TODO Auto-generated method stub
		pager.makeRow();
		
		System.out.println(pager.getSearch()==null);
		
		pager.makeNum(noticeMapper.getTotalCount(pager));
		
		return noticeMapper.getList(pager);
	}
	
	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.getDetail(boardVO);
	}
	
	@Override
	public int setWrite(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.setWrite(boardVO);
	}

	
	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.setUpdate(boardVO);
	}
	
	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.setDelete(boardVO);
	}
}
