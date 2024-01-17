package com.codewiz.judge.config

import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.DefaultClassMapper
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.amqp.support.converter.SimpleMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration



//Ye this does not work for some reason
//@Configuration
//class RabbitMQConfig {
//
////    @Bean
////    fun converter(): MessageConverter {
////        val defaultClassMapper = DefaultClassMapper()
////        defaultClassMapper.setTrustedPackages("com.codewiz.problemSubmission.commons.dto.SubmissionQueueDto") // trusted packages
////        val jackson2JsonMessageConverter = Jackson2JsonMessageConverter()
////        jackson2JsonMessageConverter.setClassMapper(defaultClassMapper)
////        return jackson2JsonMessageConverter
////    }
//
//        @Bean
//    fun converter(): SimpleMessageConverter {
//        val converter: SimpleMessageConverter = SimpleMessageConverter()
//        converter.setAllowedListPatterns(listOf("*", "java.util.*"))
//
//        return converter
//    }
//
//    @Bean
//    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
//        val rabbitTemplate = RabbitTemplate(connectionFactory)
//        rabbitTemplate.messageConverter = converter()
//
//        return rabbitTemplate
//    }
//}
