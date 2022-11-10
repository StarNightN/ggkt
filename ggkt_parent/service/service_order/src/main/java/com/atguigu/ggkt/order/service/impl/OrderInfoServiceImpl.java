package com.atguigu.ggkt.order.service.impl;

import com.atguigu.ggkt.model.order.OrderDetail;
import com.atguigu.ggkt.model.order.OrderInfo;
import com.atguigu.ggkt.order.mapper.OrderInfoMapper;
import com.atguigu.ggkt.order.service.OrderDetailService;
import com.atguigu.ggkt.order.service.OrderInfoService;
import com.atguigu.ggkt.vo.order.OrderInfoQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 订单表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-06
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Autowired
    private OrderDetailService orderDetailService;

    //订单列表
    @Override
    public Map<String, Object> selectOrderInfoPage(Page<OrderInfo> pageParam, OrderInfoQueryVo orderInfoQueryVo) {
        //1.获取查询条件
        Long userId = orderInfoQueryVo.getUserId();
        String outTradeNo = orderInfoQueryVo.getOutTradeNo();
        String phone = orderInfoQueryVo.getPhone();
        String createTimeEnd = orderInfoQueryVo.getCreateTimeEnd();
        String createTimeBegin = orderInfoQueryVo.getCreateTimeBegin();
        Integer orderStatus = orderInfoQueryVo.getOrderStatus();
        //2.判断条件是否为空
        QueryWrapper<OrderInfo> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(userId)){
            wrapper.eq("user_id",userId);
        }
        if(!StringUtils.isEmpty(outTradeNo)){
            wrapper.eq("out_trade_no",outTradeNo);
        }
        if(!StringUtils.isEmpty(phone)){
            wrapper.eq("phone",phone);
        }
        if(!StringUtils.isEmpty(createTimeBegin)){
            wrapper.ge("create_time",createTimeBegin);
        }
        if(!StringUtils.isEmpty(createTimeEnd)){
            wrapper.le("create_time",createTimeEnd);
        }
        if(!StringUtils.isEmpty(orderStatus)){
            wrapper.eq("order_status",orderStatus);
        }
        //3.调用实现条件分页查询
        Page<OrderInfo> pages = baseMapper.selectPage(pageParam, wrapper);
        long total = pages.getTotal();
        long pageCount = pages.getPages();
        List<OrderInfo> records = pages.getRecords();
        //4.订单里面包含详情内容，封装详情数据，更具订单id查询详情
        records.stream().forEach(item ->{
            this.getOrderDetail(item);
        });
        //5.封装数据进行返回
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("records",records);
        return map;
    }

    //查询订单详情数据
    private OrderInfo getOrderDetail(OrderInfo orderInfo) {
        //订单id
        Long id = orderInfo.getId();
        //通过订单id查询订单详情
        OrderDetail orderDetail = orderDetailService.getById(id);
        if(orderDetail != null){
            String courseName = orderDetail.getCourseName();
            orderInfo.getParam().put("courseName",courseName);
        }
        return orderInfo;
    }

}

