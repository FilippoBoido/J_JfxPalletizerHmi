package packageMqttFx;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MqttPlcMessage {
	
	private short id;
	private byte[] data;
	
	public MqttPlcMessage(short id, byte[] data)
	{
		this.id = id;
		this.data = data;
	}
	
	//pass a 34byte array
	public MqttPlcMessage(byte[] data)
	{

		System.out.println("[MqttPayload] Payload data to string: " + this.data.toString());
	}
	
	public short getId()
	{
		return id;
	}
	
	public byte[] getData()
	{
		return data;
	}
}
