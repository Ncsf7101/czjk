package com.rqiang.service;

import com.rqiang.entity.PageResult;
import com.rqiang.entity.QueryPageBean;
import com.rqiang.pojo.CheckGroup;
import com.rqiang.pojo.Setmeal;

import java.util.List;

public interface SetMealService {
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    PageResult pageQuery(QueryPageBean queryPageBean);

    List<Setmeal> findAll();

    Setmeal findById(Integer id);

    //List<Integer> getSetmealAndCheckGroup(Integer id);
}
