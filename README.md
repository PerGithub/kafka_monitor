# kafka_monitor
1. 监控多个topic(可配置)多个消费者组(可配置)消费状况
2. 每隔一段时间(可配置)发送邮件反馈消费状况

目的 : 能够第一时间发现消费延迟,或Kafka集群异常状态
程序入口 : KafkaOffsetTools
