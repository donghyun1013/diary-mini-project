package com.kh.mini_project.dao;

import com.kh.mini_project.vo.DiaryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

import static com.kh.mini_project.common.DiaryQuery.*;

@Repository
@RequiredArgsConstructor
public class DiaryDao {
    private final JdbcTemplate jdbcTemplate;

    public Integer insertAndReturnPk(DiaryVo vo) {
        // 생성된 pk 값을 얻기 위한 KeyHolder
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, new String[]{"DIARY_NUM"});
            ps.setInt(1, vo.getMemberNum());
            ps.setString(2, vo.getTitle());
            ps.setString(3, vo.getContent());
            ps.setObject(4, vo.getWrittenDate());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        return key != null ? key.intValue() : null;
    }
}
