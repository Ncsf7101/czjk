//package com.rqiang.controller;
//
//import com.alibaba.dubbo.config.annotation.Reference;
//import com.rqiang.constant.MessageConstant;
//import com.rqiang.entity.PageResult;
//import com.rqiang.entity.QueryPageBean;
//import com.rqiang.entity.Result;
//import com.rqiang.pojo.CheckGroup;
//import com.rqiang.service.CheckGroupService;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/checkgroup")
//public class SetMealController {
//
//    @Reference
//    private CheckGroupService checkGroupService;
//
//    @RequestMapping("/add")
//    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds){
//        try{
//            checkGroupService.add(checkGroup, checkitemIds);
//        }catch (Exception e){
//            e.printStackTrace();
//            //调用失败
//            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
//        }
//        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
//    }
//
//    @RequestMapping("/findPage")
//    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
//        PageResult pageResult = checkGroupService.pageQuery(queryPageBean); //@ResponseBody注解自动转成json
//        return pageResult;
//    }
//
//    @RequestMapping("/findById")
//    public Result findById(Integer id){
//        try{
//            CheckGroup item = checkGroupService.findById(id);
//            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, item);
//        }catch (Exception e){
//            e.printStackTrace();
//            //调用失败
//            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
//        }
//    }
//
//    @RequestMapping("/findCheckItemIdsByCheckGroupId")
//    public Result findCheckItemIdsByCheckGroupId(Integer id){
//        try{
//            List<Integer> checkitemIds = checkGroupService.findCheckItemIdsByCheckGroupId(id);
//            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkitemIds);
//        }catch (Exception e){
//            e.printStackTrace();
//            //调用失败
//            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
//        }
//    }
//
//    @RequestMapping("/edit")
//    public Result edit(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds){
//        try{
//            checkGroupService.edit(checkGroup, checkitemIds);
//        }catch (Exception e){
//            e.printStackTrace();
//            //调用失败
//            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
//        }
//        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
//    }
//
//    @RequestMapping("/findAll")
//    public Result findAll(){
//        try{
//            List<CheckGroup> items = checkGroupService.findAll();
//            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, items);
//        }catch (Exception e){
//            e.printStackTrace();
//            //调用失败
//            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
//        }
//    }
//}
