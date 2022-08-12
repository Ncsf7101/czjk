package com.rqiang.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.rqiang.dao.CheckItemDao;
import com.rqiang.entity.PageResult;
import com.rqiang.entity.QueryPageBean;
import com.rqiang.pojo.CheckItem;
import com.rqiang.service.CheckItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检查项服务
 */
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    //分页查询检查项
    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();//查询条件
        //完成分页查询，基于mybatis框架提供的分页助手插件
        //线程绑定，拼接sql语句
        PageHelper.startPage(currentPage, pageSize);
        //正常语句为：select * from t_checkitem limit 0, 10
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
//        PageResult pageResult = ne
        long total = page.getTotal();
        List<CheckItem> rows = page.getResult();
        return new PageResult(total, rows);
    }

    @Override
    public void delete(Integer id) {
        //查看检查项是否关联检查组
        long count = checkItemDao.findCountByCheckItemId(id);
        if(count > 0){
            //关联到检查组 不允许删除
            new RuntimeException();
        }else{
            checkItemDao.deleteById(id);
        }
    }

    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.editByItem(checkItem);
    }

    @Override
    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }

    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}
