package com.rubypaper;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.rubypaper.domain.Board;

public class JPAClient {

	private static void Insert(EntityManagerFactory emf) {
		// EntityManager 생성
		EntityManager em = emf.createEntityManager();

		// Transaction 생성
		EntityTransaction tx = em.getTransaction();

		try {
			// Transaction 시작
			tx.begin();

			// DB에 저장할 객체 생성
			Board board = new Board();
			board.setTitle("JPA 제목");
			board.setWriter("관리자");
			board.setContent("JPA 글 등록 잘 되네요");
			board.setCreateDate(new Date());
			board.setCnt(0L);

			// 글 등록
			em.persist(board);

			// Transaction commit
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Transaction rollback
			tx.rollback();
		} finally {
			// 사용한 리소스 객체 닫기
			em.close();
		}
	}

	private static void Search(EntityManagerFactory emf) {
		// EntityManager 생성
		EntityManager em = emf.createEntityManager();

		try {
			// 글 상세 조회
			Board searchBoard = em.find(Board.class, 1L);
			System.out.println("---> " + searchBoard.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	private static void Search2(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();

		try {
			List<Board> list = em.createQuery("select b from Board b", Board.class).getResultList();
			list.stream().forEach(System.out::println);

			@SuppressWarnings("unchecked")
			List<Board> list1 = em.createNativeQuery("select * from Board", Board.class).getResultList();
			list1.stream().forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	private static void Update(EntityManagerFactory emf) {
		// EntityManager 생성
		EntityManager em = emf.createEntityManager();
		// Transaction 생성
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin(); // Transaction 시작

			// 수정할 게시글 조회
			Board board = em.find(Board.class, 1L);
			board.setTitle("검색한 게시글의 제목 수정");

			tx.commit(); // Transaction commit
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback(); // Transaction rollback
		} finally {
			em.close();
		}
	}

	private static void Delete(EntityManagerFactory emf) {
		// EntityManager 생성
		EntityManager em = emf.createEntityManager();
		// Transaction 생성
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin(); // Transaction 시작

			// 수정할 게시글 조회
			Board board = em.find(Board.class, 1L);
			em.remove(board);

			tx.commit(); // Transaction commit
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback(); // Transaction rollback
		} finally {
			em.close();
		}
	}

	private static void ListSearch(EntityManagerFactory emf) {
		// EntityManager 생성
		EntityManager em = emf.createEntityManager();
		// Transaction 생성
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin(); // Transaction 시작

			Board board = new Board();
			board.setTitle("JPA 제목");
			board.setWriter("관리자");
			board.setContent("JPA 글 등록 잘 되네요");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			em.persist(board);

			tx.commit(); // Transaction commit

			// 글 목록 조회
			String jpql = "select b from Board b order by b.seq desc";
			TypedQuery<Board> query = em.createQuery(jpql, Board.class);
			List<Board> boardList = query.getResultList();
			for (Board board1 : boardList) {
				System.out.println("---> " + board1.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback(); // Transaction rollback
		} finally {
			em.close();
		}
	}

	private static void ListSearch2(EntityManagerFactory emf) {
		// EntityManager 생성
		EntityManager em = emf.createEntityManager();
		// Transaction 생성
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin(); // Transaction 시작

			Board board = new Board();
			board.setTitle("JPA 제목");
			board.setWriter("관리자");
			board.setContent("JPA 글 등록 잘 되네요");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			em.persist(board);

			tx.commit(); // Transaction commit

			// 글 목록 조회
			String jpql = "select b.seq, b.title from Board b order by b.seq desc";
			Query query = em.createQuery(jpql);
			@SuppressWarnings("unchecked")
			List<Object[]> resList = query.getResultList();
			for(Object[] b : resList) {
				System.out.print(b[0]);
				if(1 < b.length) {
					for(int i = 1; i < b.length; i++) {
						System.out.print(", " + b[i]);
					}
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback(); // Transaction rollback
		} finally {
			em.close();
		}
	}

	public static void main(String[] args) {
		// EntityManagerFactory 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		// Insert(emf);
		// Search(emf);
		// Search2(emf);
		// Update(emf);
		// Delete(emf);
		//ListSearch(emf);
		ListSearch2(emf);
		emf.close();
	}
}
