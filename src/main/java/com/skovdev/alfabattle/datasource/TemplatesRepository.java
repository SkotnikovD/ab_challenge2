package com.skovdev.alfabattle.datasource;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.skovdev.alfabattle.model.rest.UserTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class TemplatesRepository {

    private Multimap<String, UserTemplate> map = ArrayListMultimap.create();

    public Collection<UserTemplate> getUserTemplate(String userId) {
        return this.map.get(userId);
    }

    public void add(String userId, UserTemplate userTemplate) {
        Optional<UserTemplate> first = map.get(userId).stream().filter(item -> item.equals(userTemplate)).findFirst();
        if (first.isPresent()) {
            first.get().cntr++;
        } else {
            map.put(userId, userTemplate);
        }
    }


}
