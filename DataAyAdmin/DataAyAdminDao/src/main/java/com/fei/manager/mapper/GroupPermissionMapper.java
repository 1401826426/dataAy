package com.fei.manager.mapper;

import com.fei.manager.pojo.GroupPermission;
import com.fei.manager.pojo.GroupPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupPermissionMapper {
    long countByExample(GroupPermissionExample example);

    int deleteByExample(GroupPermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GroupPermission record);

    int insertSelective(GroupPermission record);

    List<GroupPermission> selectByExample(GroupPermissionExample example);

    GroupPermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GroupPermission record, @Param("example") GroupPermissionExample example);

    int updateByExample(@Param("record") GroupPermission record, @Param("example") GroupPermissionExample example);

    int updateByPrimaryKeySelective(GroupPermission record);

    int updateByPrimaryKey(GroupPermission record);
}