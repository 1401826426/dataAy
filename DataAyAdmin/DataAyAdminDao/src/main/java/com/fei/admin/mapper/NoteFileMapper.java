package com.fei.admin.mapper;

import com.fei.admin.pojo.NoteFile;
import com.fei.admin.pojo.NoteFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NoteFileMapper {
    long countByExample(NoteFileExample example);

    int deleteByExample(NoteFileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NoteFile record);

    int insertSelective(NoteFile record);

    List<NoteFile> selectByExample(NoteFileExample example);

    NoteFile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NoteFile record, @Param("example") NoteFileExample example);

    int updateByExample(@Param("record") NoteFile record, @Param("example") NoteFileExample example);

    int updateByPrimaryKeySelective(NoteFile record);

    int updateByPrimaryKey(NoteFile record);
}