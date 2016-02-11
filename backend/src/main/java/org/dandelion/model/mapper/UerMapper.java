package org.dandelion.model.mapper;

import org.dandelion.model.entity.UserEntity;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface UerMapper extends Mapper<UserEntity>, MySqlMapper<UserEntity> {
}
