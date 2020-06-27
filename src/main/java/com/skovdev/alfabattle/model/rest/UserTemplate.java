package com.skovdev.alfabattle.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserTemplate {
    public Float amount;
    public Integer categoryId;
    public String recipientId;
    @JsonIgnore
    public Integer cntr = 1;

    public UserTemplate(Float amount, Integer categoryId, String recipientId) {
        this.amount = amount;
        this.categoryId = categoryId;
        this.recipientId = recipientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTemplate that = (UserTemplate) o;

        if (!amount.equals(that.amount)) return false;
        if (!categoryId.equals(that.categoryId)) return false;
        return recipientId.equals(that.recipientId);
    }

    @Override
    public int hashCode() {
        int result = amount.hashCode();
        result = 31 * result + categoryId.hashCode();
        result = 31 * result + recipientId.hashCode();
        return result;
    }
}
