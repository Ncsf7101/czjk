package com.rqiang.dao;

import com.github.pagehelper.Page;
import com.rqiang.entity.PageResult;
import com.rqiang.entity.QueryPageBean;
import com.rqiang.pojo.CheckItem;

import java.util.List;

public interface CheckItemDao {

    public void add(CheckItem checkItem);

    Page<CheckItem> selectByCondition(String queryString);

    void deleteById(Integer id);

    long findCountByCheckItemId(Integer id);

    void editByItem(CheckItem checkItem);

    CheckItem findById(Integer id);

    List<CheckItem> findAll();
}
