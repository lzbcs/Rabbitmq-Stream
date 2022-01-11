package xyz.lunfee.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author lunfee
 * @create 2021/12/27-16:12
 */



@Configuration
public class RabbitMqConfig {

    @Bean(name="lasertopic")
    public Queue queueMessages(){
        return new Queue("laser.consumer");
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("laser");
    }

    @Bean
    public Binding bindingExchangeMessage(@Qualifier("lasertopic") Queue queueMessage,TopicExchange exchange){
        return BindingBuilder.bind(queueMessage).to(exchange).with("laser.*");
    }


}