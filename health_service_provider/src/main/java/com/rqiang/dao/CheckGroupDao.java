package com.rqiang.dao;

import com.github.pagehelper.Page;
import com.rqiang.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {

    void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(Map map);

    Page<CheckGroup> selectByCondition(String queryString);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void deleteCheckGroupAndCheckItem(Integer id);

    void edit(CheckGroup checkGroup);

    List<CheckGroup> findAll();
}
