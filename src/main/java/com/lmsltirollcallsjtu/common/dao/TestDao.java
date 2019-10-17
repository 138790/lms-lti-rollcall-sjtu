package com.lmsltirollcallsjtu.common.dao;

/**
 * @author wangzhijun
 * @createdDate 2019.10.16
 * @description 根据ID主键查询院系信息
 */
public interface TestDao {

    /**
     * @author wangzhijun
     * @createdDate 2019.10.16
     * @updatedDate 2019.10.16
     * @description 根据ID主键查询院系信息
     * @param id
     * @return AccountsPo
     */
    void findAccount(Long id);
}
