package com.iu.s1.util;


public class Pager {
	
	//1. DB에 일정한 갯수 만큼  조회
		//2. JSP페이지에 일정한 갯수 만큼 번호를 curPage 번호 출력
		
		private Long perPage; //DB에 조회할 갯수
		private Long perBlock; //JSP에 출력할 번호의 갯수
		
		public Pager() {
			this.perPage=10L;
			this.perBlock=5L;
			this.curPage=1L;
		}
		
		private Long curPage; //현재 페이지 번호
		
		private Long startRow;
		private Long lastRow;
		
		//rownum 계산하는 메서드
		public void makeRow() {
			// curPage		startRow		lastRow
			// 1			1				10
			// 2			11				20
			// 3			21				30

// 			For Oracle
//			this.startRow = (this.curPage-1)*perPage+1;
//			this.lastRow = this.curPage*this.perPage;
			
//			For MySql, Maria DB
			this.startRow = (this.curPage-1)*perPage;
		}

		//---------------------- 페이징 계산 
		
		private Long totalPage;
		private Long startNum;
		private Long lastNum;
		private boolean pre;
		private boolean next;
		
		//페이징 계산하는 메서드
		public void makeNum(Long totalCount) {
			//1. 전체 글의 갯수로 전체 페이지 갯수 구하기
			this.totalPage = totalCount/this.perPage;
			if(totalCount%this.perPage != 0) {
				totalPage++;
			}
			
			//2. 전체 페이지 전체 block 수 구하기
			Long totalBlock = this.totalPage / this.perBlock;
			if(this.totalPage%this.perBlock != 0) {
				totalBlock++;
			}
			
			//3. curPage를 이용해서 curBlock 구하기
			// curPage		curBlock
			// 1			1
			// 2			1
			// 5			1
			// 6			2
			// 10			2
			
			Long curBlock = this.curPage / this.perBlock;
			if(this.curPage%this.perBlock != 0) {
				curBlock++;
			}
			
			//4. curBlock으로 startNum, lastNum 구하기
			//  curBlock	startNum	lastNum
			//  1			1			5
			//  2			6			10
			//  3			11			15
			this.startNum = (curBlock-1)*this.perBlock+1;
			this.lastNum= curBlock*this.perBlock;
			
			//5. curBlock이 totalBlock 과 같다면
			if(curBlock == totalBlock) {
				this.lastNum= this.totalPage;
			}
			
			//6. 이전, 다음 유무
			if(curBlock > 1) {
				this.pre=true;
			}
			
			if(curBlock != totalBlock) {
				this.next=true;
			}
			
			
			
			
		}

		public Long getPerPage() {
			return perPage;
		}

		public void setPerPage(Long perPage) {
			this.perPage = perPage;
		}

		public Long getPerBlock() {
			return perBlock;
		}

		public void setPerBlock(Long perBlock) {
			this.perBlock = perBlock;
		}

		public Long getCurPage() {
			return curPage;
		}

		public void setCurPage(Long curPage) {
			this.curPage = curPage;
		}



		public Long getStartRow() {
			return startRow;
		}

		public void setStartRow(Long startRow) {
			this.startRow = startRow;
		}

		public Long getLastRow() {
			return lastRow;
		}

		public void setLastRow(Long lastRow) {
			this.lastRow = lastRow;
		}

		public Long getTotalPage() {
			return totalPage;
		}

		public void setTotalPage(Long totalPage) {
			this.totalPage = totalPage;
		}

		public Long getStartNum() {
			return startNum;
		}

		public void setStartNum(Long startNum) {
			this.startNum = startNum;
		}

		public Long getLastNum() {
			return lastNum;
		}

		public void setLastNum(Long lastNum) {
			this.lastNum = lastNum;
		}

		public boolean isPre() {
			return pre;
		}

		public void setPre(boolean pre) {
			this.pre = pre;
		}

		public boolean isNext() {
			return next;
		}

		public void setNext(boolean next) {
			this.next = next;
		}
		
		// ----- 검색 -----
		private String kind; //검색할 컬럼명
		private String search;//검색어
		
		
		public String getKind() {
			return kind;
		}
		public void setKind(String kind) {
			this.kind = kind;
		}
		public String getSearch() {
			if(this.search == null) {
				this.search="";
			}
			return search;
		}
		public void setSearch(String search) {
			if(search== null) {
				search="";
			}
			this.search = search;
		}

}
