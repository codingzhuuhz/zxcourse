package com.pengzhen.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pengzhen.user.entity.EduUser;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author pengzhen
 * @since 2021-03-11
 */
public interface EduUserService extends IService<EduUser> {

    int getUserStatService(String day);
}
