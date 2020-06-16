package packagePalletizerHmi;

import java.io.File;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

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
	
	//Actuator type
	final int 	NO_ACTUATOR = 0,
				PALLETIZER_BELT = 1,
				PALLETIZER_CHAIN = 2,
				BELT_CONVEYOR = 3,
				ROLLER_CONVEYOR_1 = 4,
				ROLLER_CONVEYOR_2 = 5,
				TURN_CYLINDER = 6,
				CLAMPER_CYLINDER = 7,
				PUSHER_CYLINDER = 8,
				PLATE_CYLINDER = 9,
				ELEVATOR = 10;
	
	//Actuator cmd type
	final int 	NO_CMD = 0,
				FORWARD = 1,
				BACKWARD = 2,
				VELOCITY = 3,
				PUSH = 4,
				RETRACT = 5,
				UP = 6,
				DOWN = 7,
				UP_TO_LIMIT = 8,
				DOWN_TO_LIMIT = 9,
				START = 10,
				STOP = 11,
				HOME = 12,
				RESET = 13;

	//Mode type
	final int 	NO_MODE_TYPE = 0,
				AUTOMATIC_MODE_TYPE = 1,
				MANUAL_MODE_TYPE = 2,
				SYSTEM_MODE_TYPE = 3;
	
	//Payload byte section
	final int 	MODE = 2,
				ACTUATOR = 3,
				CMD = 4;
	
	
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
			
			System.out.println("[Main.start] node " + node.getId() + " found.");
			switch(node.getId())
			{
			
			case "beltPlusId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "beltMinusId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "beltStopId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "turnerPlusId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "turnerMinusId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;	
				
			case "chainPlusId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "chainMinusId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "chainStopId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;	
				
			case "clamperPlusId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "clamperMinusId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;	
				
			case "pusherPlusId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "pusherMinusId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;		
				
			case "openPlateId":
								
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "closePlateId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "elevatorPlusId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "elevatorMinusId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "moveToMaxLimitId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "moveToMinLimitId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "beltConvPlusId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "beltConvMinusId":
					
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "beltConvStopId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "rollerConveyor1PlusId":
							
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "rollerConveyor1MinusId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;	
				
			case "rollerConveyor1StopId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;	
				
			case "rollerConveyor2PlusId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "rollerConveyor2MinusId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;	
				
			case "rollerConveyor2StopId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;	
				
			case "consoleId":
				
				console = (TextArea) node;
				break;
				
			case "manualId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "automaticId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "startId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "stopId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "homeId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			case "resetId":
				
				node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish(node.getId()));
				break;
				
			}
		}
		
		mqttClient = new MqttFx("tcp://localhost:1883", new MqttFxCallback(console), console ) ;
		mqttClient.subscribe("PalletizerHmiMessages"); 
		Scene scene = new Scene(root);
		stage.setScene(scene);		
		stage.show();
		stage.setMaximized(true);
		stage.setMinWidth(stage.getWidth());
		stage.setMinHeight(stage.getHeight());
		
	}


	private Object mqttPublish(String nodeId) {
		
		
		System.out.println("[Main.mqttPublish] node " + nodeId + " clicked.");
		switch(nodeId)
		{
		
		case "beltPlusId":
			
			
			mqttPayload[ACTUATOR] = PALLETIZER_BELT;
			mqttPayload[CMD] = FORWARD;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "beltStopId":
	
			mqttPayload[ACTUATOR] = PALLETIZER_BELT;
			mqttPayload[CMD] = STOP;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "beltMinusId":
			
			mqttPayload[ACTUATOR] = PALLETIZER_BELT;
			mqttPayload[CMD] = BACKWARD;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "turnerPlusId":
			
			mqttPayload[ACTUATOR] = TURN_CYLINDER;
			mqttPayload[CMD] = PUSH;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "turnerMinusId":
			
			mqttPayload[ACTUATOR] = TURN_CYLINDER;
			mqttPayload[CMD] = RETRACT;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "chainPlusId":
			
			mqttPayload[ACTUATOR] = PALLETIZER_CHAIN;
			mqttPayload[CMD] = FORWARD;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "chainStopId":
			
			mqttPayload[ACTUATOR] = PALLETIZER_CHAIN;
			mqttPayload[CMD] = STOP;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "chainMinusId":
			
			mqttPayload[ACTUATOR] = PALLETIZER_CHAIN;
			mqttPayload[CMD] = BACKWARD;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;	
			
		case "clamperPlusId":
			
			mqttPayload[ACTUATOR] = CLAMPER_CYLINDER;
			mqttPayload[CMD] = PUSH;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "clamperMinusId":
			
			mqttPayload[ACTUATOR] = CLAMPER_CYLINDER;
			mqttPayload[CMD] = RETRACT;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "pusherPlusId":
			
			mqttPayload[ACTUATOR] = PUSHER_CYLINDER;
			mqttPayload[CMD] = PUSH;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "pusherMinusId":
			
			mqttPayload[ACTUATOR] = PUSHER_CYLINDER;
			mqttPayload[CMD] = RETRACT;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "openPlateId":
							
			mqttPayload[ACTUATOR] = PLATE_CYLINDER;
			mqttPayload[CMD] = PUSH;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "closePlateId":
			
			mqttPayload[ACTUATOR] = PLATE_CYLINDER;
			mqttPayload[CMD] = RETRACT;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "elevatorPlusId":
			
			mqttPayload[ACTUATOR] = ELEVATOR;
			mqttPayload[CMD] = UP;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case "elevatorMinusId":
			
			mqttPayload[ACTUATOR] = ELEVATOR;
			mqttPayload[CMD] = DOWN;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case "moveToMaxLimitId":
			
			mqttPayload[ACTUATOR] = ELEVATOR;
			mqttPayload[CMD] = UP_TO_LIMIT;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case "moveToMinLimitId":
			
			mqttPayload[ACTUATOR] = ELEVATOR;
			mqttPayload[CMD] = DOWN_TO_LIMIT;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case "beltConvPlusId":
			
			mqttPayload[ACTUATOR] = BELT_CONVEYOR;
			mqttPayload[CMD] = FORWARD;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case "beltConvMinusId":
			
			mqttPayload[ACTUATOR] = BELT_CONVEYOR;
			mqttPayload[CMD] = BACKWARD;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case "beltConvStopId":
			
			mqttPayload[ACTUATOR] = BELT_CONVEYOR;
			mqttPayload[CMD] = STOP;
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case "rollerConveyor1PlusId":
						
			mqttPayload[ACTUATOR] = ROLLER_CONVEYOR_1;
			mqttPayload[CMD] = FORWARD;
			
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "rollerConveyor1StopId":
			
			mqttPayload[ACTUATOR] = ROLLER_CONVEYOR_1;
			mqttPayload[CMD] = STOP;
			
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "rollerConveyor1MinusId":
			
			mqttPayload[ACTUATOR] = ROLLER_CONVEYOR_1;
			mqttPayload[CMD] = BACKWARD;
			
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "rollerConveyor2PlusId":
			
			mqttPayload[ACTUATOR] = ROLLER_CONVEYOR_2;
			mqttPayload[CMD] = FORWARD;
			
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "rollerConveyor2StopId":
			
			mqttPayload[ACTUATOR] = ROLLER_CONVEYOR_2;
			mqttPayload[CMD] = STOP;
			
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "rollerConveyor2MinusId":
			
			mqttPayload[ACTUATOR] = ROLLER_CONVEYOR_2;
			mqttPayload[CMD] = BACKWARD;
			
			
			try {
				mqttClient.publish("PalHmi", mqttPayload);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "manualId":
			
			mqttPayload[MODE] = 2;
			
			break;
			
		case "automaticId":
			
			mqttPayload[MODE] = 1;
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
