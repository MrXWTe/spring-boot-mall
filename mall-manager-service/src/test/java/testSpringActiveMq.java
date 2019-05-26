import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public class testSpringActiveMq {

    @Test
    public void sendMessageQueue() throws Exception{

        // 初始化spring容器
        ApplicationContext ac =
                new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");

        // 从容器中获取JmsTemplate对象
        JmsTemplate jmsTemplate = ac.getBean("jmsTemplate", JmsTemplate.class);

        // 从容器中获得一个destination对象
        ActiveMQQueue queueDestination = ac.getBean("queueDestination", ActiveMQQueue.class);

        //发送消息
        jmsTemplate.send(queueDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("Hello SpringActiveMQ");
            }
        });
    }

    @Test
    public void receiveMessageQueue() throws Exception{

    }
}
