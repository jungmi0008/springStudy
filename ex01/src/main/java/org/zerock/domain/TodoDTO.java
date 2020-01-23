package org.zerock.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoDTO {
	private String title;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	//request로 넘어갈때 문자열로 넘어가기 때문에 dataBinding을 해준다.
	private Date dueDate;
}
