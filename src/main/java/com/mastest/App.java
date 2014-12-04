package com.mastest;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.PlatformController;
import jade.wrapper.StaleProxyException;

/**
 * Hello world!
 *
 */
public class App 
{
	private static ContainerController cc;
	private static final int NUMBER_OF_MESSAGE_RECEIVERS = 1000;
	private static final int NUMBER_OF_MESSAGE_SENDERS = 500;    	
	public static int receivedMessages = 0;
	public static int sentMessages = 0;
	
	
    public static void main( String[] args )
    {

		// Get a hold on JADE runtime
		final Runtime rt = Runtime.instance();
		// Create a default profile
		final Profile p = new ProfileImpl();
		cc = rt.createMainContainer(p);
		
		//create and start MessageReceiverAgents
		
		int counter = 0;
		for(int i = 0 ; i < NUMBER_OF_MESSAGE_RECEIVERS; i++)
		{
		counter++;
		final AgentController messageReceiverAgent;
		try {
			messageReceiverAgent = cc
					.createNewAgent(
							"messageReceiverAgent:" + counter,
							"com.mastest.MessageReceiver",
							args);
		
				messageReceiverAgent.start();
			} catch (StaleProxyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0 ; i < NUMBER_OF_MESSAGE_SENDERS; i++)
		{
			
		counter++;
		final AgentController messageReceiverAgent;
		try {
			
			messageReceiverAgent = cc
					.createNewAgent(
							"messageSenderAgent:" + counter,
							"com.mastest.MessageSender",
							args);
		
				messageReceiverAgent.start();
			} catch (StaleProxyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		System.out.println("Kill the agent platform. Send Messges " + sentMessages + " ; received Messages: " + receivedMessages);
		final PlatformController pc;
		try {
			pc = cc.getPlatformController();
			pc.kill();
		} catch (ControllerException e1) {
			e1.printStackTrace();
		}
	}
}
