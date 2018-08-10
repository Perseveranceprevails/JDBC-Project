package com.lucky.dao;


import com.lucky.util.PageUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 所有接口的父接口
 * 所有接口的公共方法
 * @param <T>
 */
public interface IBaseDao<T> {

    //添加方法
    int add(T t);

    //根据条件删除
    int deleteByCondition(Serializable s);

    //修改方法
    int update(T t);

    //根据条件查找
    T findByCondition(Serializable s);

    //查找所有
    List<T> findAll();

    //查找总记录数
    int findRownum();

    //查找分页的数据
    List<T> findAllByPage(PageUtil pageUtil,Object... params);

}
