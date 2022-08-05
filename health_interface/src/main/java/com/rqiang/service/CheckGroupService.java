package com.rqiang.service;

import com.rqiang.entity.PageResult;
import com.rqiang.entity.QueryPageBean;
import com.rqiang.pojo.CheckGroup;
import com.rqiang.pojo.CheckItem;

import java.util.List;

//服务接口
public interface CheckGroupService {
    void add(CheckGroup checkGroup, Integer[] checkitemIds);
    void setCheckGroupAndCheckItem(Integer id, Integer[] checkitemIds);

    PageResult pageQuery(QueryPageBean queryPageBean);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
}
