package packageMqttFx;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javafx.scene.control.TextArea;

public class MqttFx {

	private String topic;          
	private String broker;     
	private String clientId;   
	private int qos  = 2;    
	private static MemoryPersistence persistence;
	private static MqttClient mqttClient;
	private static MqttConnectOptions connOpts;
	private TextArea console;
	//private MqttCallback mqttCallback;
	
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	public MqttFx(String broker, MqttCallback mqttCallback, TextArea console) throws MqttSecurityException, MqttException 
	{	
		if(mqttClient == null)
		{
			this.console = console;
			this.broker = broker;
			this.clientId = randomAlphaNumeric(8);
			
			persistence = new MemoryPersistence();  
			
			mqttClient = new MqttClient(broker, clientId, persistence);
			mqttClient.setCallback(mqttCallback);
					
			connOpts = new MqttConnectOptions();
	        connOpts.setCleanSession(true);
	        
	        if(console != null)
	        {
	        	console.appendText("Connecting to broker: "+broker + ".\n");
	        }
	        
	        System.out.println("[MqttFx] Connecting to broker: "+broker);
	        
			mqttClient.connect(connOpts);
			
	        System.out.println("[MqttFx] Client connected");
	        
	        if(console != null)
	        {
	        	console.appendText("Client connected.\n");
	        }
		}
		else if(!mqttClient.isConnected())
		{
			mqttClient.reconnect();
			System.out.println("[MqttFx] Client reconnected");
			
			if(console != null)
	        {
	        	console.appendText("Client reconnected.\n");
	        }
		}
	}

	public boolean isConnected() {
		if(mqttClient != null)
			return mqttClient.isConnected();
		else
			return false;
	}
	
	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
	
	public synchronized boolean subscribe(String topic) throws MqttException
	{
		if(mqttClient != null && mqttClient.isConnected())
		{
			System.out.println("[MqttFx.subscribe] Subscribing to topic: " + topic);
			mqttClient.subscribe(topic);
			if(console != null)
	        {
	        	console.appendText("Subscribed to topic: " + topic + ".\n");
	        }
			return true;
		}
		else
		{
			System.out.println("[MqttFx.subscribe] Not subscribed to topic, mqtt client either null or not connected");	
			return false;
		}
		
	}
	
	public synchronized boolean unsubscribe(String topic) throws MqttException
	{
		if(mqttClient != null && mqttClient.isConnected())
		{
			System.out.println("[MqttFx.unsubscribe] Unubscribing topic: " + topic);
			mqttClient.unsubscribe(topic);
			return true;
		}
		else
		{
			System.out.println("[MqttFx.subscribe] Not subscribed to topic, mqtt client either null or not connected");	
			return false;
		}
		
	}
	
	public synchronized void publish(String topic,String message) throws MqttPersistenceException, MqttException
	{
		if(mqttClient != null && mqttClient.isConnected())
		{

			System.out.println("[MqttFx.publish] Publishing message: "+message);
	        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
	        mqttMessage.setQos(qos);
	        mqttClient.publish(topic, mqttMessage);
	        System.out.println("[MqttFx.publish] Message published");
						
		} 
		else
		{
			System.out.println("[MqttFx.publish] Message not published, mqtt client either null or not connected");	
		}
	}
	
	public synchronized void publish(String message) throws MqttPersistenceException, MqttException
	{
		if(mqttClient != null && mqttClient.isConnected())
		{

			System.out.println("[MqttFx.publish] Publishing message: "+message);
	        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
	        mqttMessage.setQos(qos);
	        mqttClient.publish(topic, mqttMessage);
	        System.out.println("[MqttFx.publish] Message published");
						
		} 
		else
		{
			System.out.println("[MqttFx.publish] Message not published, mqtt client either null or not connected");	
		}
	}
	
	public synchronized void publish(String topic, byte[] payload) throws MqttPersistenceException, MqttException
	{
		if(mqttClient != null && mqttClient.isConnected())
		{			
					mqttClient.publish(topic,payload,2,false);
			        System.out.println("[MqttFx.publish] Message published");	
			        if(console != null)
			        {
			        	console.appendText("Message published.\n");
			        }
		}  
		else
		{
			 System.out.println("[MqttFx.publish] Message not published, mqtt client either null or not connected");	
		}
	}
	
	public synchronized void disconnect() throws MqttException
	{
		if(mqttClient != null && mqttClient.isConnected())
		{	
			mqttClient.disconnect();				
			System.out.println("[MqttFx.close] Client disconnected");
			if(console != null)
	        {
	        	console.appendText("Client disconnected.\n");
	        }
		}
	}
	
	public synchronized void close() throws MqttException
	{
		disconnect();
		if(mqttClient != null)
		{
			mqttClient.close();			
			mqttClient = null;
			System.out.println("[MqttFx.close] Client closed");
			if(console != null)
	        {
	        	console.appendText("Client closed.\n");
	        }
	        	
		}
		
	}
}
