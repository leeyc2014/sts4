package com.rubypaper.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@RestController
@RequestMapping("/api")
public class TestController {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@GetMapping("/board")
	private List<Board> getBoards() {				
		return boardRepo.findAll();			// 검색 결과 목록 리턴
	}
	
	@GetMapping("/board/{seq}")
	private Board getBoard(@PathVariable Long seq) {
		Board getBoard = boardRepo.findById(seq).get();
		return getBoard;			// 검색 결과 객체 리턴
	}
	
	@PostMapping("/board")
	private Board postBoard(@RequestBody Board board) {		
		board.setCreateDate(new Date());
		board.setCnt(0L);
		boardRepo.save(board);
		return board;			// 입력 객체 리턴
	}
	
	@PutMapping("/board/{seq}")
	private Board putBoard(@PathVariable Long seq, @RequestBody Board board) {
		Board putBoard = boardRepo.findById(seq).get();
		putBoard.setTitle(board.getTitle());
		putBoard.setWriter(board.getWriter());
		putBoard.setContent(board.getContent());
		putBoard.setCreateDate(putBoard.getCreateDate());
		putBoard.setCnt(putBoard.getCnt());
		boardRepo.save(putBoard);
		return putBoard;			// 수정 객체 리턴
	}
	
	@PatchMapping("/board/{seq}")
	private Board patchBoard(@PathVariable Long seq, @RequestBody Board board) {
		Board patchBoard = boardRepo.findById(seq).get();
		if(board.getTitle() != null) {
			patchBoard.setTitle(board.getTitle());
		}
		if(board.getWriter() != null) {
			patchBoard.setWriter(board.getWriter());
		}
		if(board.getContent() != null) {
			patchBoard.setContent(board.getContent());
		}
		boardRepo.save(patchBoard);
		return patchBoard;			// 수정 객체 리턴
	}
	
	//@DeleteMapping("/board/{seq}")
	private void deleteBoard(@PathVariable Long seq) {
		boardRepo.deleteById(seq);			// 삭제 객체 리턴
	}
	
	@DeleteMapping("/board/{seq}")
	private Board deleteBoard1(@PathVariable Long seq) {
		Board deleteBoard = boardRepo.findById(seq).get();
		boardRepo.deleteById(seq);
		return deleteBoard;
	}
}
