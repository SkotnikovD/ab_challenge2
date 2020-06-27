package com.skovdev.alfabattle.controller;

import com.skovdev.alfabattle.controller.error.NoUserFoundException;
import com.skovdev.alfabattle.datasource.TemplatesRepository;
import com.skovdev.alfabattle.datasource.UsersRepository;
import com.skovdev.alfabattle.model.rest.PaymentCategoryInfo;
import com.skovdev.alfabattle.model.rest.PaymentStats;
import com.skovdev.alfabattle.model.rest.UserPaymentAnalytic;
import com.skovdev.alfabattle.model.rest.UserTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class RestController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TemplatesRepository templatesRepository;

    @RequestMapping(value = "/admin/health", method = RequestMethod.GET,
            produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getHealth() {
        return "{\"status\":\"UP\"}";
    }

    @GetMapping("/analytic")
    public Collection<UserPaymentAnalytic> getAllAnalytic() {
        return usersRepository.getAllAnalytic().values();
    }

    @GetMapping("/analytic/{userId}")
    public UserPaymentAnalytic getUserAnalytic(@PathVariable String userId) {
        UserPaymentAnalytic res = usersRepository.getAllAnalytic().get(userId);
        if (res == null) {
            throw new NoUserFoundException();
        }
        return res;
    }

    @GetMapping("/analytic/{userId}/stats")
    public PaymentStats getUserStats(@PathVariable String userId) {
        UserPaymentAnalytic res = usersRepository.getAllAnalytic().get(userId);
        if (res == null) {
            throw new NoUserFoundException();
        }
        //Вынести логику в слой сервисов
        PaymentStats paymentStats = new PaymentStats();


//        самая частая категория трат
//
//        самая редкая категория трат
//
//        категория с наибольшей суммой
//
//        категория с наименьшей суммой

        Float max = 0f;
        Float min = Float.MAX_VALUE;
        Integer often = 0;
        Integer rare = Integer.MAX_VALUE;

        for (Map.Entry<Integer, PaymentCategoryInfo> pi : res.getAnalyticInfo().entrySet()) {
            if (pi.getValue().sum > max) {
                max = pi.getValue().sum;
                paymentStats.maxAmountCategoryId=pi.getKey();
            }
            if (pi.getValue().sum < min) {
                min = pi.getValue().sum;
                paymentStats.minAmountCategoryId=pi.getKey();
            }
            if (pi.getValue().paymentsCntr > often) {
                often = pi.getValue().paymentsCntr;
                paymentStats.oftenCategoryId=pi.getKey();
            }
            if (pi.getValue().paymentsCntr < rare) {
                rare = pi.getValue().paymentsCntr;
                paymentStats.rareCategoryId=pi.getKey();
            }
        }
        return paymentStats;
    }

    @GetMapping("/analytic/{userId}/templates")
    public List<UserTemplate> getUserTemplate(@PathVariable String userId) {
        UserPaymentAnalytic check = usersRepository.getAllAnalytic().get(userId);
        if (check == null) {
            throw new NoUserFoundException();
        }
        //Вынести логику в слой сервисов
        List<UserTemplate> res = templatesRepository.getUserTemplate(userId).stream().filter(temp -> temp.cntr >= 3).collect(Collectors.toList());


//        for (Map.Entry<Integer, PaymentCategoryInfo> pi: res.getAnalyticInfo().entrySet()) {
//             if(pi.getValue().) paymentStats.maxAmountCategoryId
//        }
        return res;
    }
}
