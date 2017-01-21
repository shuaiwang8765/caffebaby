package Consumer;

import java.io.IOException;

import javax.jms.Destination;
import javax.jms.TextMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class Receiver {

	public static void main(String[] args) throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String("classpath:applicationContext.xml"));
		JmsTemplate jmsTemplate = (JmsTemplate)context.getBean("jmsTemplate");
		Destination destination = (Destination)context.getBean("destination");
		while(true){
			try {
				TextMessage txtMessage = (TextMessage) jmsTemplate.receive(destination);
				if(null != txtMessage){
					System.out.println("[DB Proxy] 收到消息内容为：" + txtMessage.getText());
				}else{
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
