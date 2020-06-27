package com.skovdev.alfabattle.model.rest;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)
public class UserPaymentAnalytic {

    public Map<Integer, PaymentCategoryInfo> analyticInfo = new HashMap<>();
    public Float totalSum = 0f;
    public String userId;

    public void addPayment(Integer category, Float amount){
        PaymentCategoryInfo paymentCategoryInfo = analyticInfo.computeIfAbsent(category, cat -> new PaymentCategoryInfo().setMin(amount));
        if(amount<paymentCategoryInfo.min)
            paymentCategoryInfo.min=amount;
        if(amount>paymentCategoryInfo.max)
            paymentCategoryInfo.max=amount;
        paymentCategoryInfo.sum+=amount;
        paymentCategoryInfo.paymentsCntr++;

        totalSum+=amount;
    }

}
