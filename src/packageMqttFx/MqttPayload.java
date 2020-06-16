package packageMqttFx;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MqttPayload {
	//id : UINT;
	//data : ARRAY[1..32] OF Byte;
	private short id;
	private byte[] data = new byte[32];
	
	public MqttPayload(short id, byte[] data)
	{
		this.id = id;
		this.data = data;
	}
	
	//pass a 34byte array
	public MqttPayload(byte[] data)
	{
		ByteBuffer bb = ByteBuffer.allocate(2);
		
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.put(data[0]);
		bb.put(data[1]);
		
		this.id = bb.getShort(0);
		System.out.println("[MqttPayload] Payload id: " + this.id);
		bb = ByteBuffer.allocate(32);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		
		for(int i = 0 ; i<32 ; i++)
		{
			this.data[i] = data[i+2]; 
		}
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
