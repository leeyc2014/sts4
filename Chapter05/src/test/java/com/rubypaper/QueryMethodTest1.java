package com.rubypaper;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@SpringBootTest
public class QueryMethodTest1 {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@BeforeAll
	static void dataPrepare(@Autowired BoardRepository boardRepo) {
		for(int i = 1; i <= 100; i++) {
			Board board = new Board();
			Random random = new Random();
			board.setTitle("테스트 제목 " + i);
			board.setWriter("테스터");
			board.setContent("테스트 내용 " + i);
			board.setCreateDate(new Date());
			board.setCnt(random.nextLong(0L, 100L));
			//board.setCnt((long)Math.random() * 100);
			boardRepo.save(board);
		}
	}
	
	//@Test
	public void findByTitleContaining() {
		List<Board> boardList = boardRepo.findByTitleContaining("1");
		System.out.println("검색 결과");
		for(Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}
	
	//@Test
	public void findByTitleContainingAndCntGreaterThan() {
		List<Board> boardList = boardRepo.findByTitleContainingAndCntGreaterThan("1", 50L);
		System.out.println("검색 결과");
		for(Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}
	
	//@Test
	public void findByCntLessThanEqualAndGreaterThanEqualOrderBySeqAsc() {
		List<Board> boardList = boardRepo.findByCntGreaterThanEqualAndCntLessThanEqualOrderBySeqAsc(10L, 50L);
		System.out.println("검색 결과");
		for(Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}
	
	@Test
	public void findByTitleContainingOrContentContainingOrderBySeqDesc() {
		List<Board> boardList = boardRepo.findByTitleContainingOrContentContainingOrderBySeqDesc("10", "2");
		System.out.println("검색 결과");
		for(Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}
}