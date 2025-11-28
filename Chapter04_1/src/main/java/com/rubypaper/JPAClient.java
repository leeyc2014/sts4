package com.rubypaper;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.domain.Board;

public class JPAClient {
	
	private static void Insert(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			Board board = new Board();
			board.setTitle("JPA 제목");
			board.setWriter("관리자");
			board.setContent("JPA 글 등록 잘 되네요");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			
			em.persist(board);
			tx.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		finally {
			em.close();
		}
	}
	
	private static void Search(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		try {
			Board searchBoard = em.find(Board.class, 1L);
			System.out.println("---> " + searchBoard.toString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
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
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
	}
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04_1");
		//Insert(emf);
		//Search(emf);
		Search2(emf);
		emf.close();
	}
}
