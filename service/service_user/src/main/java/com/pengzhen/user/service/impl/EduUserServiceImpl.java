package com.pengzhen.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengzhen.user.entity.EduUser;
import com.pengzhen.user.mapper.EduUserMapper;
import com.pengzhen.user.service.EduUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author pengzhen
 * @since 2021-03-11
 */
@Service
public class EduUserServiceImpl extends ServiceImpl<EduUserMapper, EduUser> implements EduUserService {

    @Override
    public int getUserStatService(String day) {
        return this.baseMapper.getUserStatService(day);
    }
}
