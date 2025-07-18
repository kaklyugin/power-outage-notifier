package org.rostovenergoparser.rabbit.config;

import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class RabbitMQConfig {

    @Value("${rabbitmq.updates.exchange.name}")
    private String updatesExchangeName;

    @Value("${rabbitmq.updates.queue.name}")
    private String updatesQueueName;

    @Value("${rabbitmq.updates.routing.key}")
    private String updatesRoutingKey;

    @Bean
    public DirectExchange updatesMessageExchange() {
        return new DirectExchange(updatesExchangeName);
    }

    @Bean
    public Queue updatesMessageQueue() {
        return new Queue(updatesQueueName);
    }

    @Bean
    public Binding updatesMessageBinding() {
        return BindingBuilder.bind(updatesMessageQueue())
                .to(updatesMessageExchange())
                .with(updatesRoutingKey);
    }

}
