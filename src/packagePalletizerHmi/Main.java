package packagePalletizerHmi;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.MqttException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import packageMqttFx.MqttFx;
import packageMqttFx.MqttFxCallback;



public class Main extends Application{

	static MqttFx mqttClient ;
	TextArea console;
	byte[] mqttPayload = new byte[34];
	
	public static void main(String[] args) {
		launch();
		
		try {
			mqttClient.disconnect();
			System.out.println("[Main.main] Mqtt client disconnected.");
			mqttClient.close();
			System.out.println("[Main.main] Mqtt client closed.");
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("[Main.main] Application exited.");	

	}
	
	public static ArrayList<Node> getAllNodes(Parent root) {
	    ArrayList<Node> nodes = new ArrayList<Node>();
	    addAllDescendents(root, nodes);
	    return nodes;
	}

	private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
	    for (Node node : parent.getChildrenUnmodifiable()) {
	        nodes.add(node);
	        if (node instanceof Parent)
	            addAllDescendents((Parent)node, nodes);
	    }
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		System.out.println("[Main.start] App launched");
		
		Parent root = FXMLLoader.load(new File("C:\\Users\\fboid\\Documents\\JavaFX-Test\\Palletizer3.fxml").toURI().toURL());
		
		
		ArrayList<Node> nodes = getAllNodes(root);
		
					
		for(Node node : nodes)
		{
			if(node.getId() == null)
				continue;
			
			switch(node.getId())
			{
			
			case "beltPlusId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish("beltPlusId"));
				break;
				
			case "beltMinusId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish("beltMinusId"));
				break;
				
			case "turnId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish("turnId"));
				break;
				
			case "chainPlusId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish("chainPlusId"));
				break;
				
			case "clampId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish("clampId"));
				break;
				
			case "pushId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish("pushId"));
				break;
				
			case "openPlateId":
								
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish("openPlateId"));
				break;
				
			case "elevatorPlusId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish("elevatorPlusId"));
				break;
				
			case "elevatorMinusId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish("elevatorMinusId"));
				break;
				
			case "moveToLimitId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish("moveToLimitId"));
				break;
				
			case "beltConveyorId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish("beltConveyorId"));
				break;
				
			case "rollerConveyor1Id":
							
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish("rollerConveyor1Id"));
				break;
							
			case "rollerConveyor2Id":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish("rollerConveyor2Id"));
				break;
				
			case "consoleId":
				
				console = (TextArea) node;
				break;
				
			case "manualId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish("manualId"));
				break;
				
			case "automaticId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish("automaticId"));
				break;
				
			case "startId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish("startId"));
				break;
				
			case "stopId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish("stopId"));
				break;
				
			case "homeId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish("homeId"));
				break;
				
			case "resetId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish("resetId"));
				break;
	
				
			}
		}
		
		mqttClient = new MqttFx("tcp://localhost:1883", new MqttFxCallback(console) ) ;
		mqttClient.subscribe("PalletizerHmiMessages"); 
		Scene scene = new Scene(root);
		stage.setScene(scene);		
		stage.show();
		stage.setMaximized(true);
		stage.setMinWidth(stage.getWidth());
		stage.setMinHeight(stage.getHeight());
		
	}

	/*
	 * 	TYPE ST_MqttPayload :
		STRUCT
			id : UINT;
			data : ARRAY[1..32] OF Byte;
		END_STRUCT
		END_TYPE

	 * 
	 * 
	 * */
	private Object mqttPublish(String nodeId) {
		
		
		System.out.println("[Main.mqttPublish] node " + nodeId + " clicked.");
		switch(nodeId)
		{
		
		case "beltPlusId":
			
			
			mqttPayload[4] = 1;
			mqttPayload[5] = 1;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "beltMinusId":
			
			mqttPayload[4] = 1;
			mqttPayload[5] = 2;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "turnId":
			
			mqttPayload[4] = 5;
			mqttPayload[5] = 4;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "chainPlusId":
			
			mqttPayload[4] = 2;
			mqttPayload[5] = 1;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "clampId":
			
			mqttPayload[4] = 6;
			mqttPayload[5] = 4;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "pushId":
			
			mqttPayload[4] = 7;
			mqttPayload[5] = 4;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "openPlateId":
							
			mqttPayload[4] = 8;
			mqttPayload[5] = 4;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "elevatorPlusId":
			
			mqttPayload[4] = 9;
			mqttPayload[5] = 6;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case "elevatorMinusId":
			
			mqttPayload[4] = 9;
			mqttPayload[5] = 7;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case "moveToLimitId":
			
			mqttPayload[4] = 9;
			mqttPayload[5] = 8;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case "beltConveyorId":
			
			mqttPayload[4] = 3;
			mqttPayload[5] = 1;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case "rollerConveyor1Id":
						
			mqttPayload[4] = 4;
			mqttPayload[5] = 1;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
						
		case "rollerConveyor2Id":
			
			
			break;
			
			
		case "manualId":
			
			mqttPayload[3] = 2;
			
			break;
			
		case "automaticId":
			
			mqttPayload[3] = 1;
			//node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish());
			break;
			
		case "startId":
			
			//node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish());
			break;
			
		case "stopId":
			
			//node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish());
			break;
			
		case "homeId":
			
			//node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish());
			break;
			
		case "resetId":
			
			//node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish());
			break;

			
		}
		return null;
	}

}
