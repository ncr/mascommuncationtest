/**
 * 
 */
package com.mastest;

import jade.core.Agent;


public class MessageSender extends Agent {

	private static final long serialVersionUID = 2275401822957013778L;

	@Override
	protected void setup() {
		addBehaviour(new SendMessage());
	}
}
