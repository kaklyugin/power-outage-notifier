package org.rostovenergoparser.tgclient.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.tg.message.exchange.name}")
    private String tgMessageExchangeName;

    @Value("${rabbitmq.tg.message.update.queue.name}")
    private String tgMessageUpdateQueueName;

    @Value("${rabbitmq.tg.message.update.routing.key}")
    private String tgMessageUpdateRoutingKey;

    @Bean
    public DirectExchange tgMessageExchange() {
        return new DirectExchange(tgMessageExchangeName);
    }

    @Bean
    public Queue tgMessageQueue() {
        return new Queue(tgMessageUpdateQueueName);
    }

    @Bean
    public Binding tgMessageBinding() {
        return BindingBuilder.bind(tgMessageQueue())
                .to(tgMessageExchange())
                .with(tgMessageUpdateRoutingKey);
    }

}
