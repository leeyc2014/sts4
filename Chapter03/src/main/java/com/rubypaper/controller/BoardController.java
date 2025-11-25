package com.rubypaper.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.BoardVO;

@RestController		// 사용자가 호출하는 URL을 처리하는 핸들러 클래스(JSON 데이터를 응답)
public class BoardController {
	public BoardController() {
		System.out.println("===> BoardController 생성");
	}
	
	@GetMapping("/hello")
	public String hello(String name) {
		return "Hello : " + name;
	}
	
	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		BoardVO board = new BoardVO();
		board.setSeq(1);
		board.setTitle("테스트 제목...");
		board.setWriter("테스터");
		board.setContent("테스트 내용입니다..........");
		board.setCreateDate(new Date());
		board.setCnt(0);
		return board;
	}
	
	@GetMapping("/getBoard1")
	public BoardVO getBoard1() {
		BoardVO board = BoardVO.builder()
						.seq(1)
						.title("테스트 제목...")
						.writer("테스터")
						.content("테스트 내용입니다..........")
						.createDate(new Date())
						.cnt(0)
						.build();
		return board;
	}
	
	@GetMapping("/getBoardList")
	public List<BoardVO> getBoardList() {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		for(int i = 1; i <= 10; i++) {
			BoardVO board = new BoardVO();
			board.setSeq(i);
			board.setTitle("제목" + i);
			board.setWriter("테스터");
			board.setContent(i + "번 내용입니다.");
			board.setCreateDate(new Date());
			board.setCnt(0);
			boardList.add(board);
		}
		return boardList;
	}
	
	@GetMapping("/getBoard2")
	public BoardVO board(Integer seq) {
		BoardVO board = new BoardVO();
		board.setSeq(seq);
		board.setWriter("테스터");
		return board;
	}
	
	@GetMapping("/getBoard3")
	public BoardVO board() {
		BoardVO board = new BoardVO();
		board.setWriter("테스터");
		return board;
	}
}
