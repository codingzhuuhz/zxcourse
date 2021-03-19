package com.pengzhen.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pengzhen.user.entity.EduUser;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author pengzhen
 * @since 2021-03-11
 */
public interface EduUserMapper extends BaseMapper<EduUser> {

    int getUserStatService(String day);
}
