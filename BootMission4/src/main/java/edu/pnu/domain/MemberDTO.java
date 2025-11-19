package edu.pnu.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {
	private Integer id;
	private String pass;
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date regidate;
}
