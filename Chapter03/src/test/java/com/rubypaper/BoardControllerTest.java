package com.rubypaper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.rubypaper.domain.BoardVO;

//@WebMvcTest
//@SpringBootTest(webEnvironment=WebEnvironment.MOCK)
//@AutoConfigureMockMvc
//@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
public class BoardControllerTest {
	
	@Autowired
	//private MockMvc mockMvc;
	private TestRestTemplate restTemplate;
	
//	@Test
//	@Order(1)
//	public void testHello() throws Exception {
//		mockMvc.perform(get("/hello").param("name", "둘리"))
//		.andExpect(status().isOk())
//		.andExpect(content().string("Hello : 둘리"))
//		.andDo(print());
//	}
	
	@Test
	public void testHello() throws Exception {
		BoardVO board = restTemplate.getForObject("/getBoard3", BoardVO.class);
		assertEquals("테스터", board.getWriter());
	}
	
//	@Test
//	public void testHelloJson() throws Exception {
//		ObjectMapper objectMapper = new ObjectMapper();
//		MvcResult mvcResult = mockMvc.perform(get("/getBoard2").param("seq", "100"))
//				.andExpect(status().isOk())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
//				.andExpect(jsonPath("$.writer").value("테스터"))
//				.andDo(print())
//				.andReturn();
//		String jsonString = mvcResult.getResponse().getContentAsString();
//		BoardVO board = objectMapper.readValue(jsonString, BoardVO.class);
//		assertEquals(board.getSeq(), 100);
//	}
}
