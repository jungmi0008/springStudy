package org.zerock.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SampleDTOList {
	private List<SampleDTO> list;
	
//	생성자 블록 오버라이드
	public SampleDTOList() {
		list = new ArrayList<SampleDTO>();
	}
	
}
