package com.iu.s1.board;

import java.util.List;

import com.iu.s1.util.Pager;



public interface BoardMapper {
	
	public List<BoardVO> getList(Pager pager) throws Exception;

	public BoardVO getDetail(BoardVO boardVO)throws Exception;
	
	public int setWrite(BoardVO boardVO) throws Exception;
	
	public long getTotalCount(Pager pager)throws Exception;
	
	//글 수정
	public int setUpdate(BoardVO boardVO)throws Exception;
	
	//글 삭제
	public int setDelete(BoardVO boardVO)throws Exception;
}
