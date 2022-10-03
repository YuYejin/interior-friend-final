package com.inf.khproject.service;

import com.inf.khproject.dto.MemberDTO;
import com.inf.khproject.entity.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
//@Configuration
//@MapperScan("mapper")
public class MemberServiceImpl implements MemberService {

	@Override
	public void regist(MemberDTO dto, String local)throws Exception{
		log.info("DTO----------------");
		log.info(dto);

		Member entity = dtoToEntity(dto);
		log.info(entity);
	}



}
