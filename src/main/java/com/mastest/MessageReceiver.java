/**
 * 
 */
package com.mastest;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;


public class MessageReceiver extends Agent {

	public static String MESSAGE_RECEIVER = "MESSAGE_RECEIVER";
	private static final long serialVersionUID = -490639298997472094L;

	/* (non-Javadoc)
	 * @see jade.core.Agent#setup()
	 */
	@Override
	protected void setup() {
		
		final DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());
		final ServiceDescription sd = new ServiceDescription();
		sd.setType(MESSAGE_RECEIVER);
		sd.setName(this.getAID().toString());
		dfd.addServices(sd);

		try {
			DFService.register(this, dfd);
		} catch (FIPAException fe) {
			//fe.printStackTrace();
		}
		
		addBehaviour(new ReceiveMessage());
	}
}
