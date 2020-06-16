package packageMqttFx;

import java.util.Arrays;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import javafx.scene.control.TextArea;

public class MqttFxCallback implements MqttCallback {

	TextArea ta;
	public MqttFxCallback(TextArea ta)
	{
		this.ta = ta;
		
	}
	
	@Override
	public void connectionLost(Throwable cause) {
		System.out.println("[MqttFxCallback.connectionLost] Connection lost");
		ta.appendText("Connection lost\n");
	}

	@Override
	public synchronized void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("[MqttFxCallback.messageArrived] Message arrived");
		System.out.println("[MqttFxCallback.messageArrived] Message length: " + message.getPayload().length);
		
		ta.appendText("[MqttFxCallback.messageArrived] Message arrived\n");
		ta.appendText("----------------------------------------------------------------------\n");
		/*
		MqttPayload payload = new MqttPayload(message.getPayload());
		ta.appendText("Payload Id: " + payload.getId() + "\n");
		
		String dataString = "";
		int iData;
		byte[] data = payload.getData();
		for( int i = 0 ; i < 32 ; i++ )
		{
			iData = Byte.toUnsignedInt(data[i]) ;
			dataString += iData;
		}
		
		ta.appendText("Payload data: " + dataString + "\n");
		*/
		
		ta.appendText(new String(message.getPayload(), "UTF-8") + "\n");
		ta.appendText("----------------------------------------------------------------------\n");
		
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("[MqttFxCallback.deliveryComplete] Delivery complete");
		
	}

}
