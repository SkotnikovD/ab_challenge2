package com.skovdev.alfabattle.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PaymentCategoryInfo {
    public Float max = 0f;
    public Float min = 0f;
    public Float sum = 0f;
    @JsonIgnore
    public Integer paymentsCntr = 0;
}
