package com.lmsltirollcallsjtu.common.utils;

public class SignInConfirmCallback /*implements RabbitTemplate.ConfirmCallback*/ {
//
//    /**
//     * 生产消息确认机制：生产者往队列服务端发送消息的时候，采用应答机制
//     */
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//
//        //获取消息ID
//        String correlationId = correlationData.getId();
//        //获取消息内容
//        Message returnedMessage = correlationData.getReturnedMessage();
//        String content = returnedMessage.getBody().toString();
//
//        if(ack) {
//            System.out.println("发送消息确认成功！");
//        }else {
//            long retryCount = getRetryCount(returnedMessage);
//            //重试小于5次则重新发送，超过重试次数则放弃重试并将消息记录到日志表中。
//            if(retryCount<5L){
//                System.out.println("第"+retryCount+"次重试发送消息，原因："+cause);
//                //重新发送消息
//                SendMessageUtil.sendMsg(correlationId,content);
//            }else{
//                System.out.println("发送消息确认失败：" + cause);
//                System.out.println("====开始记录日志===");
//                //如果重试4次还是失败的，就将失败的消息记录到日志表中，定时任务扫描日志表或者人工干预（根据日志表信息来做补偿操作）
//
//            }
//        }
//    }
//
//    /**
//     * 获取重试次数
//     */
//    public static long getRetryCount(Message message){
//
//        MessageProperties properties = message.getMessageProperties();
//        long retryCount = 0L;
//        Map<String,Object> header = properties.getHeaders();
//        if(header != null && header.containsKey("x-death")){
//            List<Map<String,Object>> deaths = (List<Map<String,Object>>)header.get("x-death");
//            if(deaths.size()>0){
//                Map<String,Object> death = deaths.get(0);
//                retryCount = (Long)death.get("count");
//            }
//        }
//        return retryCount;
//    }

}
