package com.rqiang.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rqiang.constant.RedisConstant;
import com.rqiang.dao.SetMealDao;
import com.rqiang.entity.PageResult;
import com.rqiang.entity.QueryPageBean;
import com.rqiang.pojo.CheckGroup;
import com.rqiang.pojo.Setmeal;
import com.rqiang.service.SetMealService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 检查项服务
 */
@Service(interfaceClass = SetMealService.class)
@Transactional
public class SetMealServiceImpl implements SetMealService {

    @Autowired
    private SetMealDao setMealDao;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setMealDao.add(setmeal);
        if(checkgroupIds != null && checkgroupIds.length > 0){
            //绑定套餐和检查组的多对多关系
            setSetmealAndCheckGroup(setmeal.getId(),checkgroupIds);
        }
        setPicRedis(setmeal.getImg());
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();//查询条件
        //完成分页查询，基于mybatis框架提供的分页助手插件
        //线程绑定，拼接sql语句
        PageHelper.startPage(currentPage, pageSize);
        Page<Setmeal> page = setMealDao.selectByCondition(queryString);
//        PageResult pageResult = ne
        long total = page.getTotal();
        List<Setmeal> rows = page.getResult();
        return new PageResult(total, rows);
    }

    @Override
    public List<Setmeal> findAll() {
        return setMealDao.findAll();
    }

    @Override
    public Setmeal findById(Integer id) {
        return setMealDao.findById(id);
    }

//    @Override
//    public List<Integer> getSetmealAndCheckGroup(Integer id) {
//        return setMealDao.getSetmealAndCheckGroup(id);
//    }

    //绑定套餐和检查组的多对多关系
    private void setSetmealAndCheckGroup(Integer id, Integer[] checkgroupIds) {
        for (Integer checkgroupId : checkgroupIds) {
            Map<String,Integer> map = new HashMap<>();
            map.put("setmeal_id",id);
            map.put("checkgroup_id",checkgroupId);
            setMealDao.setSetmealAndCheckGroup(map);
        }
    }

    public void setPicRedis(String pic){
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,pic);
    }

}
