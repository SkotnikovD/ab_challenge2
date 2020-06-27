package com.skovdev.alfabattle.datasource;

import com.skovdev.alfabattle.model.rest.UserPaymentAnalytic;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UsersRepository {

    private Map<String, UserPaymentAnalytic> storage = new HashMap<>();

    public Map<String, UserPaymentAnalytic> getAllAnalytic() {
        return storage;
    }

    public UserPaymentAnalytic getUserAnalytic(String userId) {
        return this.storage.get(userId);
    }
}
