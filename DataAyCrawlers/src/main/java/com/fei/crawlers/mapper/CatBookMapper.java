package com.fei.crawlers.mapper;

import com.fei.crawlers.pojo.CatBook;
import com.fei.crawlers.pojo.CatBookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CatBookMapper {
    long countByExample(CatBookExample example);

    int deleteByExample(CatBookExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CatBook record);

    int insertSelective(CatBook record);

    List<CatBook> selectByExample(CatBookExample example);

    CatBook selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CatBook record, @Param("example") CatBookExample example);

    int updateByExample(@Param("record") CatBook record, @Param("example") CatBookExample example);

    int updateByPrimaryKeySelective(CatBook record);

    int updateByPrimaryKey(CatBook record);

	CatBook selectOneByExample(CatBookExample example);

	//self_code_start
	void insertCatBooks(List<CatBook> catBooks);
	//self_code_end

}