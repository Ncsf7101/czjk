package com.rqiang.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.rqiang.dao.OrderSettingDao;
import com.rqiang.pojo.OrderSetting;
import com.rqiang.service.OrderSettingService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public void add(List<OrderSetting> orderSettingList) {
        if(orderSettingList != null && orderSettingList.size() >0){
            for(OrderSetting orderSetting:orderSettingList){
                long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if(count > 0){
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                }else{
                    orderSettingDao.add(orderSetting);
                }

            }
        }
    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        String[] str = date.split("-");
//        if(Integer.parseInt(str[1]) < 10){
//            str[1] = "0" + str[1];
//        }
//        String dateBegin = str[0] + "-" + str[1] + "-01";//2019-3-1
//        String dateEnd = str[0] + "-" + str[1] + "-31";//2019-3-31
        String dateBegin = date + "-1";//2019-3-1
        String dateEnd = date + "-31";//2019-3-31
        Map map = new HashMap();
        System.out.println(dateBegin);
        System.out.println(dateEnd);
        map.put("dateBegin",dateBegin);
        map.put("dateEnd",dateEnd);
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
        List<Map> result = new ArrayList<>();
        if(list != null && list.size() >0){
            for(OrderSetting orderSetting:list){
                Map<String, Object> m = new HashMap<>();
                m.put("date", orderSetting.getOrderDate().getDate()); //获取日期几号
                m.put("number", orderSetting.getNumber());
                m.put("reservations", orderSetting.getReservations());
                result.add(m);
            }
        }
        return result;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if(count > 0){
            //当前日期已经进行了预约设置，需要进行修改操作
            orderSettingDao.editNumberByOrderDate(orderSetting);
        }else{
            //当前日期没有进行预约设置，进行添加操作
            orderSettingDao.add(orderSetting);
        }
    }


}
