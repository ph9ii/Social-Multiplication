package com.social.multiplication.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Handles the communication with the Event Bus.
 * 
 * @author mocha
 *
 */
@Component
public class EventDispatcher {

	private RabbitTemplate rabbitTemplate;

	// The exchange to use to send anything related to the Multiplication.
	private String multiplicationExchange;

	// The routing key to use to send this particular event.
	private String multiplicationSolvedRoutingKey;

	@Autowired
	EventDispatcher(final RabbitTemplate rabbitTemplate,
			@Value("${Multiplication.exchange}") final String multiplicationExchange,
			@Value("${Multiplication.solved.key}") final String multiplicationSolvedRoutingKey) {
		this.rabbitTemplate = rabbitTemplate;
		this.multiplicationExchange = multiplicationExchange;
		this.multiplicationSolvedRoutingKey = multiplicationSolvedRoutingKey;
	}

	public void send(final MultiplicationSolvedEvent multiplicationSolvedEvent) {
		rabbitTemplate.convertAndSend(multiplicationExchange, multiplicationSolvedRoutingKey,
				multiplicationSolvedEvent);
	}
}
