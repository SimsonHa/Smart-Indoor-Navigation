package com.Snoopy.SmartIndoorNavigation.MQTT;

import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Service;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.ESL;

@Service
public class PublishArtikel {
	
	//MQTT Broker
    String topic        = "nav/updateData";
    
    int qos             = 2;
    String broker       = "tcp://mqtt.iot-embedded.de:1883";
    String clientId     = "JavaSample";
    String username     = "indoor-nav";
    String password		= "(IndoorNav)";
    MemoryPersistence persistence = new MemoryPersistence();
	
    MqttClient sampleClient;
    
    public PublishArtikel() {
		
	}
	

    public void publish(List<ESL> esl) {
    	int eslSize = esl.size();
    	
    	
    	try {
            sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setUserName(username);
            connOpts.setPassword(password.toCharArray());
            System.out.println("Connecting to broker: "+broker);
            
            
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }

	        
	        for(int i = 0; i<eslSize; i++) {
	        	String content      = null;
	        	try {
	        		content = "{\"macAdress\":\""+esl.get(i).getPi().getMacAdres()+ "\", \"name\":\""+esl.get(i).getArtikel().getName()+"\", \"preis\":\""+esl.get(i).getArtikel().getPreis()+"\", \"kategorie\":\""+esl.get(i).getArtikel().getKategorie().getName()+"\"}" ;
	        	}
	        	catch(NullPointerException e) {
	        		System.out.println("ESL ist nicht mit einem Pi verknüpft!");
	        	}
	        	
	        	if(content!= null) {
		        try {

		            System.out.println("Publishing message: "+content);
		            MqttMessage message = new MqttMessage(content.getBytes());
		            message.setQos(qos);
		            sampleClient.publish(topic, message);
		            System.out.println("Message published");
		            if(eslSize-i<=1) {
			            sampleClient.disconnect();
			            System.out.println("Disconnected");
		            }

		            
		        } catch(MqttException me) {
		            System.out.println("reason "+me.getReasonCode());
		            System.out.println("msg "+me.getMessage());
		            System.out.println("loc "+me.getLocalizedMessage());
		            System.out.println("cause "+me.getCause());
		            System.out.println("excep "+me);
		            me.printStackTrace();
		        }
	        	}
	        }
	    }
	}

