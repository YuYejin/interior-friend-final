package com.inf.khproject.service;

import com.inf.khproject.dto.NoticeDTO;
import com.inf.khproject.dto.PageRequestDTO;
import com.inf.khproject.dto.PageResultDTO;
import com.inf.khproject.entity.Member;
import com.inf.khproject.entity.Notice;
import com.inf.khproject.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public Long register(NoticeDTO dto) {

        log.info(dto);

        Notice notice = dtoToEntity(dto);
        noticeRepository.save(notice);

        return notice.getNoticeNo();

    }

    @Override
    public PageResultDTO<NoticeDTO, Notice> getList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("noticeNo").descending());

        Page<Notice> result = noticeRepository.findAll(pageable);

        Function<Notice, NoticeDTO> fn = (entity -> entityToDTO(entity));

        return new PageResultDTO<>(result, fn);

    }
}
