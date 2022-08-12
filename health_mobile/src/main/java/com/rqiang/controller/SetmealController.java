package com.rqiang.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rqiang.constant.MessageConstant;
import com.rqiang.entity.Result;
import com.rqiang.pojo.CheckGroup;
import com.rqiang.pojo.Setmeal;
import com.rqiang.service.CheckGroupService;
import com.rqiang.service.SetMealService;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/setmeal")
@RestController
public class SetmealController {
    @Reference//(check = false)
    private SetMealService setMealService;

    //获取所有套餐信息
    @RequestMapping("/getSetmeal")
    public Result getSetmeal(){
        try{
            List<Setmeal> list = setMealService.findAll();
            return new Result(true, MessageConstant.GET_SETMEAL_LIST_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_SETMEAL_LIST_FAIL);
        }
    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try{
            Setmeal setmeal = setMealService.findById(id);
//            List<Integer> list = setMealService.getSetmealAndCheckGroup(id);
//            List<CheckGroup> listGroup = new ArrayList<>();
//            for (Integer i:list){
//                listGroup.add(checkGroupService.findById(i));
//            }
//            System.out.println(list);
//            setmeal.setCheckGroups(listGroup);
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }

}
