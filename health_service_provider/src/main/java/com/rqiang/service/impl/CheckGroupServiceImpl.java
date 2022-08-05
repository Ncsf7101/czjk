package com.rqiang.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rqiang.dao.CheckGroupDao;
import com.rqiang.entity.PageResult;
import com.rqiang.entity.QueryPageBean;
import com.rqiang.pojo.CheckGroup;
import com.rqiang.pojo.CheckItem;
import com.rqiang.service.CheckGroupService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 检查项服务
 */
@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;



    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.add(checkGroup);
        setCheckGroupAndCheckItem(checkGroup.getId(), checkitemIds);

    }

    @Override
    public void setCheckGroupAndCheckItem(Integer id, Integer[] checkitemIds) {
        Map<String, Integer> map = new HashMap<>();
        for(Integer checkitemId:checkitemIds){
            map.put("checkgroup_id", id);
            map.put("checkitem_id", checkitemId);
            checkGroupDao.setCheckGroupAndCheckItem(map);
        }
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();//查询条件
        //完成分页查询，基于mybatis框架提供的分页助手插件
        //线程绑定，拼接sql语句
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckGroup> page = checkGroupDao.selectByCondition(queryString);
//        PageResult pageResult = ne
        long total = page.getTotal();
        List<CheckGroup> rows = page.getResult();
        return new PageResult(total, rows);
    }

    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }
}
