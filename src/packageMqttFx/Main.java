package packageMqttFx;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.MqttException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	
	MqttFx mqttClient ;
	//Node textField,textArea;
	TextField textField;
	TextArea textArea;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch();
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {

			System.out.println("[Main.start] App launched");
			
			Parent root = FXMLLoader.load(new File("C:\\Users\\fboid\\Documents\\JavaFX-Test\\Test.fxml").toURI().toURL());
			
			
			ArrayList<Node> nodes = getAllNodes(root);
			
						
			for(Node node : nodes)
			{
				if(node.getId() == null)
					continue;
				
				switch(node.getId())
				{
				
				case "minimizeId":
					
					node.addEventHandler(ActionEvent.ACTION, event -> minimize(stage));
					break;
					
				case "maximizeId":
					
					node.addEventHandler(ActionEvent.ACTION, event -> maximize(stage));
					break;
					
				case "closeId":
					
					node.addEventHandler(ActionEvent.ACTION, event -> close(stage));
					break;
					
				case "connectId":
					
					node.addEventHandler(ActionEvent.ACTION, event -> mqttConnect());
					break;
					
				case "clearId":
					
					node.addEventHandler(ActionEvent.ACTION, event -> clear());
					break;
					
				case "disconnectId":
					
					node.addEventHandler(ActionEvent.ACTION, event -> mqttDisconnect());
					break;
					
				case "textFieldId":
									
					textField = (TextField) node;
					break;
					
				case "textAreaId":
					
					textArea = (TextArea) node;
					break;
					
				case "subscribeId":
					
					node.addEventHandler(ActionEvent.ACTION, event -> mqttSubscribe());
					break;
					
				case "unsubscribeId":
					
					node.addEventHandler(ActionEvent.ACTION, event -> mqttUnsubscribe());
					break;	
				case "publishId":
					
					node.addEventHandler(ActionEvent.ACTION, event -> mqttPublish());
					break;
					
				}
			}
			
			Scene scene = new Scene(root);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(scene);		
			stage.show();
			stage.setMaximized(true);
			stage.setMinWidth(stage.getWidth());
			stage.setMinHeight(stage.getHeight());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private Object mqttPublish() {
		
		return null;
	}

	private Object mqttUnsubscribe() {
		if(		mqttClientOperational() 
			&& 	textField!= null 
			&& 	!textField.getText().isEmpty()
			&& 	textArea!= null )
		{
			textArea.appendText("Unsubscribing following topic: " + textField.getText() + "\n");
			try {
				mqttClient.unsubscribe(textField.getText());
			} catch (MqttException e) {
				if(textArea != null)
				{
					textArea.appendText("Exception message: " + e.getMessage()+ "\n");
				}
				e.printStackTrace();
			}
		}
		return null;
	}

	private boolean mqttClientOperational()
	{
		return (mqttClient != null) && (mqttClient.isConnected());
	}
	
	private Object mqttSubscribe() {
		
		if(		mqttClientOperational() 
			&& 	textField!= null 
			&& 	!textField.getText().isEmpty())
		{
			try {
				if (	mqttClient.subscribe(textField.getText()) 
					&& 	textArea != null
				)
				{
					textArea.appendText("Mqtt client subscribed to following topic: " + textField.getText() + "\n");
				}
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				if(textArea != null)
				{
					textArea.appendText("Exception message: " + e.getMessage()+ "\n");
				}
				e.printStackTrace();
			}
		}
		return null;
	}

	private Object mqttDisconnect() {
		
		if(mqttClient != null && mqttClient.isConnected())
		{				
			try {
				mqttClient.disconnect();
				if(textArea != null)
				{
					textArea.appendText("Disconnected from broker.\n");
				}
			} catch (MqttException e) {
				if(textArea != null)
				{
					textArea.appendText("Exception message: " + e.getMessage()+ "\n");
				}
				e.printStackTrace();
			}
			
		}
		
		return null;
	}
	
	private Object mqttClose() {
		
		if(mqttClient != null )
		{				
			try {
				mqttClient.close();
			} catch (MqttException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		return null;
	}

	private Object clear() {
		
		if(textArea != null)
		{
			textArea.clear();
		}
		
		return null;
	}

	private Object mqttConnect() {
		
		String broker = null,localBroker = "tcp://localhost:1883";
		
		if(mqttClient != null && !mqttClient.isConnected())
		{
			;
		}
		else if(textField == null )
		{
			broker = localBroker;
		}
		else if(textField.getText().isEmpty())
		{
			broker = localBroker;
			if(textArea != null)
			{
				textArea.appendText("Connecting to localhost broker.\n");
			}
		}
		else
		{
			broker = textField.getText();
			if(textArea != null)
			{
				textArea.appendText("Connecting to broker at following address: " + textField.getText() + "\n");
			}
		}
					
		try {
			
			mqttClient = new MqttFx(broker, new MqttFxCallback(textArea),textArea ) ;
			if(textArea != null)
			{
				textArea.appendText("Connected to broker.\n");
			}
					
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			if(mqttClient != null)
			{				
				try {
					mqttClient.close();
				} catch (MqttException e1) {
					if(textArea != null)
					{
						textArea.appendText("Exception message: " + e.getMessage()+ "\n");
					}
								
					e1.printStackTrace();
				}
				
			}
			e.printStackTrace();
		}
			
		
		return null;
	}

	private Object close(Stage stage) {
		mqttClose();
		Platform.exit();
		System.out.println("[Main.close] App closed");
		return null;
	}

	private Object maximize(Stage stage) {
		
		if(stage.isMaximized())
		{
			stage.setMaximized(false);
			
			//stage.setHeight(stage.getMinHeight());
			//stage.setWidth(stage.getMinWidth());
		}
		else
			stage.setMaximized(true);
		
		System.out.println("[Main.maximize] App resized");
		return null;
	}

	private Object minimize(Stage stage) {
		stage.setIconified(true);
		System.out.println("[Main.minimize] App minimized");
		return null;
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


}
