package cn.itcast.order.service;


import cn.itcast.feign.clients.UserClient;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.User;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserClient userClient;
//    @Autowired
//    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.利用Feign发送请求
        User user = userClient.findById(order.getUserId());

        // 2.利用RestTemplate发送http请求，查询用户
//        String url = "http://userservice/user/"+ order.getUserId();
//        // getForObject适合于get请求，还可以使用以post开头的发post请求
//        // url表示请求的地址，User.class表示请求后返回的类型
//        User user = restTemplate.getForObject(url, User.class);
        order.setUser(user);
        // 4.返回
        return order;
    }
}
