package com.lmsltirollcallsjtu.common.enums;

import lombok.Getter;

public enum MessageQueueEnum {

    SIGN_IN_QUEUE("sign_in","sign_in","sign_in_exchange","sign_in_queue"),
    SIGN_IN_DEAD_LETTER_QUEUE("sign_in_dead","sign_in_dead","sign_in_dead_letter_exchange","sign_in_dead_letter_queue");

    @Getter
    private String routingKey;
    @Getter
    private String bindingKey;
    @Getter
    private String exchangeName;
    @Getter
    private String queueName;

    MessageQueueEnum(String routingKey, String bindingKey, String exchangeName, String queueName){
        this.routingKey = routingKey;
        this.bindingKey = bindingKey;
        this.exchangeName = exchangeName;
        this.queueName = queueName;
    }
}
