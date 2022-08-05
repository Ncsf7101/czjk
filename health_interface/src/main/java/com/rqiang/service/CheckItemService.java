package com.rqiang.service;

import com.rqiang.entity.PageResult;
import com.rqiang.entity.QueryPageBean;
import com.rqiang.pojo.CheckItem;

import java.util.List;

//服务接口
public interface CheckItemService {
     public void add(CheckItem checkItem);

     public PageResult pageQuery(QueryPageBean queryPageBean);

    void delete(Integer id);

    void edit(CheckItem checkItem);

    CheckItem findById(Integer id);

    List<CheckItem> findAll();
}
