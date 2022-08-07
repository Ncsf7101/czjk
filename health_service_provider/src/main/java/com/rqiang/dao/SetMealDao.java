package com.rqiang.dao;

import com.github.pagehelper.Page;
import com.rqiang.pojo.CheckGroup;
import com.rqiang.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetMealDao {

    void add(Setmeal setmeal);

    void setSetmealAndCheckGroup(Map<String, Integer> map);

    Page<Setmeal> selectByCondition(String queryString);
}
