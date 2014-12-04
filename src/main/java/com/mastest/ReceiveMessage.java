/**
 * 
 */
package com.mastest;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;


public class ReceiveMessage extends CyclicBehaviour{


	private static final long serialVersionUID = -4474791529162391141L;

	@Override
	public void action() {

		ACLMessage incomingMessage = getAgent().blockingReceive();
		if(incomingMessage != null)
			{
			App.receivedMessages++;
				}
		
	}

}
