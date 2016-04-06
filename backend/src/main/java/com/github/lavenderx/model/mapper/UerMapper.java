package com.github.lavenderx.model.mapper;

import com.github.lavenderx.model.entity.UserEntity;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface UerMapper extends Mapper<UserEntity>, MySqlMapper<UserEntity> {
}
