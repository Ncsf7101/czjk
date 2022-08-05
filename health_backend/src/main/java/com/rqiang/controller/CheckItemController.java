package com.rqiang.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rqiang.constant.MessageConstant;
import com.rqiang.entity.PageResult;
import com.rqiang.entity.QueryPageBean;
import com.rqiang.entity.Result;
import com.rqiang.pojo.CheckItem;
import com.rqiang.service.CheckItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference //查找服务
    private CheckItemService checkItemService;

    //新增标签项
    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        try{
            checkItemService.add(checkItem);
        }catch (Exception e){
            e.printStackTrace();
            //调用失败
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }
    //分页查询 QueryPageBean封装查询条件
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkItemService.pageQuery(queryPageBean); //@ResponseBody注解自动转成json
        return pageResult;
    }

    @RequestMapping("/delete")
    public Result delete(Integer id){
        try{
            checkItemService.delete(id);
        }catch (Exception e){
            e.printStackTrace();
            //调用失败
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem){
        try{
            checkItemService.edit(checkItem);
        }catch (Exception e){
            e.printStackTrace();
            //调用失败
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try{
            CheckItem item = checkItemService.findById(id);
            return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS, item);
        }catch (Exception e){
            e.printStackTrace();
            //调用失败
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("/findAll")
    public Result findAll(){
        try{
            List<CheckItem> items = checkItemService.findAll();
            return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS, items);
        }catch (Exception e){
            e.printStackTrace();
            //调用失败
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }
}
