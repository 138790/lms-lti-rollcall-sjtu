package com.lmsltirollcallsjtu.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ListenerMessageController {

//    @Autowired
//    private ScanSignServcie scanSignServcie;
//    @Autowired
//    private SimpMessageSendingOperations simpMessageSendingOperations;
//
//
//
//    @RabbitListener(queues = "sign_in_queue")
//    public void listenerProcess(Message message, Channel channel, @Headers Map<String, Object> headers) throws Exception {
//
//        try {
//            //mq为每一个 consumer设置一个缓冲区，大小就是prefetch。每次收到一条消息，MQ会把消息推送到缓存区中，然后再推送给客户端。当收到一个ack消息时(consumer 发出baseack指令)，
//            //mq会从缓冲区中空出一个位置，然后加入新的消息。但是这时候如果缓冲区是满的，MQ将进入堵塞状态。更具体点描述，假设prefetch值设为10，共有两个consumer。
//            //也就是说每个consumer每次会从queue中预抓取 10 条消息到本地缓存着等待消费。同时该channel的unacked数变为20。而Rabbit投递的顺序是，先为consumer1投递满10个message，
//            //再往consumer2投递10个message。如果这时有新message需要投递，先判断channel的unacked数是否等于20，如果是则不会将消息投递到consumer中，message继续呆在queue中。
//            //之后其中consumer对一条消息进行ack，unacked此时等于19，Rabbit就判断哪个consumer的unacked少于10，就投递到哪个consumer中。
//            //通过basic.qos方法设置prefetch_count=1，这样RabbitMQ就会使得每个Consumer在同一个时间点最多处理一个Message，换句话说,在接收到该Consumer的ack前,它不会将新的Message分发给它。
//            //但是这样客户端执行速度将会很慢，所以一般最好将客户端缓存消息数量prefetch_count设为大于等于10。
//            channel.basicQos(10);
//
//            //获取消息数据，并打印信息
//            String messageId = message.getMessageProperties().getMessageId();
//            String signInParamStr = new String(message.getBody(), "UTF-8");
//            SignInParam signInParam = JSONObject.parseObject(signInParamStr, SignInParam.class);
//            System.out.println("========spring-boot-queue========== receive msg : " + signInParamStr + "，消息ID：" + messageId);
//
//            //幂等性问题（即重复消费问题）解决方案：查询消息ID是否存在，存在则中断执行，不存在才继续执行。因为重试是间隔性执行同一个消息，不存在并发问题。
//            //注意抛异常时事务回滚，而redis不会回滚，虽然不会出现幂等性问题，但会出现数据库与redis数据不一致性问题，使得redis误认为存在(即已经执行过了)，便直接return中断执行，
//            //以致消息留在队列中无法处理，所以一般建议直接查数据库，统一使用数据库事务。
//            String prefixOfKey = "mq_idempotence";
//            String signHistoryId = RedisUtil.getString(prefixOfKey + messageId);
//            if (!StringUtils.isEmpty(signHistoryId)) {
//                return;
//            }
//
//            //学生签到
//            UserStateInfo userStateInfo = scanSignServcie.scanUpdateState(signInParam.getSignHistoryId(), signInParam.getUserCode(), signInParam.getSignInDate());
//            //推送签到用户的姓名到客户端
//            simpMessageSendingOperations.convertAndSend("/topic/users/" + userStateInfo.getSignHistoryId(), userStateInfo.getUserName()+","+userStateInfo.getCurrentAttendancesCount());
//
//
//            //用于解决幂等性问题（即重复消费问题）,redis写入之后一般立马查询一下确认是否存入成功，因为redis没有记录成功的话会导致重试时查询不到而通过，依然会导致重复消费
//            RedisUtil.setString(prefixOfKey + messageId, signInParam.getSignHistoryId(), 1L, TimeUnit.HOURS);
//            String messageIdOfRedis = RedisUtil.getString(prefixOfKey + messageId);
//            if (StringUtils.isEmpty(messageIdOfRedis)) {
//                throw BusinessException.getInstance(BusinessExceptionEnum.REDIS_ERROR);
//            }
//
//            //确认消息成功消费（手动签收），注意：一旦签收便删除消息，该签收不会回滚且无法再重试，一定要放到最后一行执行
//            //为了保证永远不会丢失消息，RabbitMQ支持消息应答机制。当消费者接收到消息并完成任务后会往RabbitMQ服务器发送一条确认的命令，然后RabbitMQ才会将消息删除。
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        } catch (Exception e) {
//            long retryCount = getRetryCount(message);
//            //如果重试次数小于5次，则抛出异常再进行重试，否则转发给死信队列
//            if (retryCount < 5L) {
//                throw BusinessException.getInstance(BusinessExceptionEnum.SYSTEM_ERROR);
//            } else {
//                //拒绝消费消息（丢弃消息），并告诉mq将消息再次放入队列，如果spring-boot-queue队列绑定有死信队列，则消息将被转发给死信队列
//                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
//            }
//        }
//    }
//
//    /**
//     * 获取重试次数
//     */
//    public long getRetryCount(Message message) {
//
//        MessageProperties properties = message.getMessageProperties();
//        long retryCount = 0L;
//        Map<String, Object> header = properties.getHeaders();
//        if (header != null && header.containsKey("x-death")) {
//            List<Map<String, Object>> deaths = (List<Map<String, Object>>) header.get("x-death");
//            if (deaths.size() > 0) {
//                Map<String, Object> death = deaths.get(0);
//                retryCount = (Long) death.get("count");
//            }
//        }
//        return retryCount;
//    }
//
//
//    /**
//     * 监听死信队列（消费者）
//     */
//    @RabbitListener(queues = "sign_in_dead_letter_queue")
//    public void listenerDeadProcess(Message message, Channel channel, @Headers Map<String, Object> headers) throws Exception {
//
//        channel.basicQos(10);
//
//        try {
//            //后台打印消息
//            String messageId = message.getMessageProperties().getMessageId();
//            String signInParamStr = new String(message.getBody(), "UTF-8");
//            SignInParam signInParam = JSONObject.parseObject(signInParamStr, SignInParam.class);
//
//            System.out.println("========dead-letter-queue========== receive msg : " + signInParamStr + "，消息ID：" + messageId);
//
//            //幂等性问题（即重复消费问题）解决方案：查询消息ID是否存在，存在则中断执行，不存在才继续执行。因为重试是间隔性执行同一个消息，不存在并发问题。
//            //即使收到重复消息导致并发，数据库主键是唯一的，所以只会有一条数据执行成功。
//
//            //做失败日志记录或者通过渠道调用业务服务
//            System.out.println("=========接到死信消息，通过渠道1记录失败日志，开始做日志记录==========");
////            logService1.sendOrderFailed(orderParamsMap);
//
//            //确认消息成功消费（手动签收）
//            //为了保证永远不会丢失消息，RabbitMQ支持消息应答机制。当消费者接收到消息并完成任务后会往RabbitMQ服务器发送一条确认的命令，然后RabbitMQ才会将消息删除。
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        } catch (Exception e) {
//            long retryCount = getRetryCount(message);
//            //如果重试次数小于5次，则抛出异常再进行重试，否则转发给死信队列
//            if (retryCount < 5L) {
//                throw BusinessException.getInstance(BusinessExceptionEnum.SYSTEM_ERROR);
//            } else {
//                //做失败日志记录或者通过渠道调用业务服务
//                log.error("=========渠道1异常，通过渠道2记录失败日志，开始做日志记录==========");
////                logService2.sendOrderFailed(orderParamsMap);
//                //拒绝消费消息（丢弃消息），并告诉mq将消息再次放入队列。
//                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
//            }
//        }
//    }
}
