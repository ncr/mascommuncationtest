/**
 * 
 */
package com.mastest;

import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;


public class SendMessage extends CyclicBehaviour{

	private static final long serialVersionUID = 1411808806472250134L;


	@Override
	public void action() {

		final DFAgentDescription dfd = new DFAgentDescription();
		final ServiceDescription sd = new ServiceDescription();
		sd.setType(MessageReceiver.MESSAGE_RECEIVER);
		dfd.addServices(sd);
		ACLMessage newMsg = new ACLMessage(ACLMessage.INFORM);
		
		try {
			final DFAgentDescription[] result = DFService.search(getAgent(),
					dfd);
			for (int i = 0; i < result.length; i++) {
				newMsg.addReceiver(result[i].getName());
			}

		} catch (FIPAException e1) {
			//e1.printStackTrace();
		}
		getAgent().send(newMsg);
		App.sentMessages++;
		
	}

}
