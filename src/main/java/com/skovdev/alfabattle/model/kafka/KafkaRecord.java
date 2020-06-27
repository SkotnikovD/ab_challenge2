package com.skovdev.alfabattle.model.kafka;

import lombok.Data;

@Data
public class KafkaRecord {
    String ref;
    Integer categoryId;
    String userId;
    String recipientId;
    String desc;
    Float amount;
}

//{"ref":"U030306190000188", "categoryId":1, "userId":"XAABAA", "recipientId":"XA3SZV", "desc":"Платеж за услуги", "amount":10.0}
