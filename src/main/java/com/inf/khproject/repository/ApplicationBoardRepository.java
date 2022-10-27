package com.inf.khproject.repository;

import com.inf.khproject.entity.ApplicationBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ApplicationBoardRepository extends JpaRepository<ApplicationBoard, Long> {
    @Query("select m, ai from ApplicationBoard m " +
            "left outer join ApplicationBoardImage ai on ai.applicationBoard = m group by m ")
    Page<Object[]> getListPage(Pageable pageable);

    @Query("select m, ai from ApplicationBoard m " +
            "left outer join ApplicationBoardImage ai on ai.applicationBoard = m group by m "+
            "having m.writer.id = :id ")
    Page<Object[]> getMypageListPage(Pageable pageable,@Param("id") long id);

    @Query("select m, mi " +
            " from ApplicationBoard m left outer join ApplicationBoardImage mi on mi.applicationBoard = m " +
            " where m.boardNo = :boardNo group by mi")
    List<Object[]> getApplicationboardWithAll(Long boardNo);


    @Query("update ApplicationBoard m set m.view_count = m.view_count + 1 where m.boardNo = :boardNo")
    @Modifying
    @Transactional
    void updateview_count(long boardNo);

    @Query("select a from ApplicationBoard a where a.boardNo =:boardNo")
    Object getApplicationboardByBoardNo(@Param("boardNo") Long boardNo);


}
