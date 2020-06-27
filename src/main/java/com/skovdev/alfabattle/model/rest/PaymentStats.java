package com.skovdev.alfabattle.model.rest;

import lombok.Data;

@Data
public class PaymentStats {
    public Integer maxAmountCategoryId;
    public Integer minAmountCategoryId;
    public Integer oftenCategoryId;
    public Integer rareCategoryId;
}
