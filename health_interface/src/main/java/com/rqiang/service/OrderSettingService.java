package com.rqiang.service;

import com.rqiang.entity.Result;
import com.rqiang.pojo.OrderSetting;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {

    void add(List<OrderSetting> orderSettingList);

    List<Map> getOrderSettingByMonth(String date);

    void editNumberByDate(OrderSetting orderSetting);
}
