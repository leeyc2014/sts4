package com.rubypaper.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.dto.BoardDTO;

public interface BoardRepository extends JpaRepository<Board, Long> {
	// QueryMethodTest
	List<Board> findByTitle(String searchKeyword);
	List<Board> findByContentContaining(String searchKeyword);
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	List<Board> findByTitleContainingOrderBySeqDesc(String searchKeyword);
	Page<Board> findByTitleContaining(String searchKeyword, Pageable paging);
	List<Board> findByTitleContainingOrderBySeqAsc(String searchKeyword, Pageable paging);
	
	// QueryMethodTest1
	List<Board> findByTitleContaining(String searchKeyword);
	List<Board> findByTitleContainingAndCntGreaterThan(String title, Long cnt);
	List<Board> findByCntGreaterThanEqualAndCntLessThanEqualOrderBySeqAsc(Long cnt1, Long cnt2);
	List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String title, String content);
	
	// QueryAnnotationTest
	@Query("SELECT b FROM Board b WHERE b.title like %?1% ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest1(String searchKeyword);	
	@Query("SELECT b FROM Board b WHERE b.title like %:searchKeyword% ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest2(@Param("searchKeyword") String searchKeyword);
	@Query("SELECT b.seq, b.title, b.writer, b.createDate FROM Board b WHERE b.title like %:searchKeyword% ORDER BY b.seq DESC")
	List<Object[]> queryAnnotationTest3(String searchKeyword);
	@Query("SELECT new com.rubypaper.domain.dto.BoardDTO(b.seq, b.title, b.writer) FROM Board b WHERE b.title like %:searchKeyword% ORDER BY b.seq DESC")
	List<BoardDTO> queryAnnotationTest31(String searchKeyword);
	@Query(value="SELECT seq, title, writer, create_date FROM board WHERE title like '%'||:searchKeyword||'%' ORDER BY seq DESC", nativeQuery=true)
	List<Object[]> queryAnnotationTest4(String searchKeyword);
	@Query("select b from Board b order by b.seq DESC")
	List<Board> queryAnnotationTest5(Pageable paging);
}
