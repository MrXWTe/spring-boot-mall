import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class ActivemqTest {

    /**
     * 点到点形式发送消息
     * @throws Exception
     */
    @Test
    public void testQueueProducer() throws Exception{
        // 1、建立activemq建立连接，创建一个连接工厂对象，需要指定服务的IP及端口
        ConnectionFactory connectionFactory = 
                new ActiveMQConnectionFactory("tcp://192.168.56.102:61616");

        // 2、使用工厂对象创建一个connection对象
        Connection connection = connectionFactory.createConnection();

        // 3、开启连接，调用Connection对象的start方法
        connection.start();

        // 4、创建一个session对象
        // 第一个参数：是否开启事务，一般不开启事务，如果开启事务，第二个参数无意义。一般不开启事务false
        // 第二个参数：应答模式。一般自动应答或者是手动应答。一般是自动应答
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 5、使用Session对象创建一个Destination对象。两种形式queue、topic，现在应该使用queue
        Queue queue = session.createQueue("test-queue");

        // 6、使用Session对象创建一个Producer对象
        MessageProducer producer = session.createProducer(queue);

        // 7、创建一个Message对象，可以使用TextMessage
//        TextMessage textMessage = new ActiveMQTextMessage();
//        textMessage.setText("Hello ActiveMQ");
        TextMessage textMessage = session.createTextMessage("Hello ActiveMQ");

        // 8、发送消息
        producer.send(textMessage);

        // 9、关闭资源
        producer.close();
        session.close();
        connection.close();
    }


    @Test
    public void testQueue() throws Exception{
        // 创建一个连接工厂连接MQ服务器
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://192.168.56.102:61616");

        // 创建连接对象
        Connection connection = connectionFactory.createConnection();

        // 开启连接
        connection.start();

        // 使用connection对象创建session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 创建一个destination对象。queue对象
        Queue queue = session.createQueue("test-queue");

        // 使用session创建消费者对象
        MessageConsumer consumer = session.createConsumer(queue);

        // 接受消息
        consumer.setMessageListener(new MessageListener(){
            @Override
            public void onMessage(Message message) {
                // 打印结果
                TextMessage textMessage = (TextMessage) message;
                String text = null;
                try{
                    text = textMessage.getText();
                    System.out.println(text);
                }catch (JMSException e){
                    e.printStackTrace();
                }

            }
        });
        // 等待成功接受消息
        System.in.read();

        // 关闭资源
        consumer.close();
        session.close();
        connection.close();
    }


    /**
     * 广播式发布消息
     * @throws Exception
     */
    @Test
    public void testTopicProducer() throws Exception{
        // 1、建立activemq建立连接，创建一个连接工厂对象，需要指定服务的IP及端口
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://192.168.56.102:61616");

        // 2、使用工厂对象创建一个connection对象
        Connection connection = connectionFactory.createConnection();

        // 3、开启连接，调用Connection对象的start方法
        connection.start();

        // 4、创建一个session对象
        // 第一个参数：是否开启事务，一般不开启事务，如果开启事务，第二个参数无意义。一般不开启事务false
        // 第二个参数：应答模式。一般自动应答或者是手动应答。一般是自动应答
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 5、使用Session对象创建一个Destination对象。两种形式queue、topic，现在应该使用topic
        Topic topic = session.createTopic("test-topic");

        // 6、使用Session对象创建一个Producer对象
        MessageProducer producer = session.createProducer(topic);

        // 7、创建一个Message对象，可以使用TextMessage
//        TextMessage textMessage = new ActiveMQTextMessage();
//        textMessage.setText("Hello ActiveMQ");
        TextMessage textMessage = session.createTextMessage("topic ActiveMQ");

        // 8、发送消息
        producer.send(textMessage);

        // 9、关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testTopicConsumer() throws Exception{
        // 创建一个连接工厂连接MQ服务器
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://192.168.56.102:61616");

        // 创建连接对象
        Connection connection = connectionFactory.createConnection();

        // 开启连接
        connection.start();

        // 使用connection对象创建session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 创建一个destination对象。topic对象
        Topic topic = session.createTopic("test-topic");

        // 使用session创建消费者对象
        MessageConsumer consumer = session.createConsumer(topic);

        // 接受消息
        consumer.setMessageListener(new MessageListener(){
            @Override
            public void onMessage(Message message) {
                // 打印结果
                TextMessage textMessage = (TextMessage) message;
                String text = null;
                try{
                    text = textMessage.getText();
                    System.out.println(text);
                }catch (JMSException e){
                    e.printStackTrace();
                }

            }
        });

        System.out.println("消费者1启动。。。");

        // 等待成功接受消息
        System.in.read();

        // 关闭资源
        consumer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testTopicConsumer2() throws Exception{
        // 创建一个连接工厂连接MQ服务器
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://192.168.56.102:61616");

        // 创建连接对象
        Connection connection = connectionFactory.createConnection();

        // 开启连接
        connection.start();

        // 使用connection对象创建session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 创建一个destination对象。topic对象
        Topic topic = session.createTopic("test-topic");

        // 使用session创建消费者对象
        MessageConsumer consumer = session.createConsumer(topic);

        // 接受消息
        consumer.setMessageListener(new MessageListener(){
            @Override
            public void onMessage(Message message) {
                // 打印结果
                TextMessage textMessage = (TextMessage) message;
                String text = null;
                try{
                    text = textMessage.getText();
                    System.out.println(text);
                }catch (JMSException e){
                    e.printStackTrace();
                }

            }
        });

        System.out.println("消费者2启动。。。");

        // 等待成功接受消息
        System.in.read();

        // 关闭资源
        consumer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testTopicConsumer3() throws Exception{
        // 创建一个连接工厂连接MQ服务器
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://192.168.56.102:61616");

        // 创建连接对象
        Connection connection = connectionFactory.createConnection();

        // 开启连接
        connection.start();

        // 使用connection对象创建session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 创建一个destination对象。topic对象
        Topic topic = session.createTopic("test-topic");

        // 使用session创建消费者对象
        MessageConsumer consumer = session.createConsumer(topic);

        // 接受消息
        consumer.setMessageListener(new MessageListener(){
            @Override
            public void onMessage(Message message) {
                // 打印结果
                TextMessage textMessage = (TextMessage) message;
                String text = null;
                try{
                    text = textMessage.getText();
                    System.out.println(text);
                }catch (JMSException e){
                    e.printStackTrace();
                }

            }
        });

        System.out.println("消费者3启动。。。");

        // 等待成功接受消息
        System.in.read();

        // 关闭资源
        consumer.close();
        session.close();
        connection.close();
    }
}
