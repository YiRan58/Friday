# 请根据实际情况修改 HOST_IP 等自定义配置

# 安装docker
curl -sSL https://get.daocloud.io/docker | sh

# 安装mysql
docker pull mysql:5.7.5
docker run mysql -itd --name mysql -p 3306:3306 --restart always -e MYSQL_ROOT_PASSWORD=4324 mysql
# 安装redis
docker pull redis
docker run -itd -name redis -p 6379:6379 --restart always redis

# 安装zookeeper kafka kafka-manager
docker pull wurstmeister/zookeeper
docker pull wurstmeister/kafka
docker pull sheepkiller/kafka-manager


docker run -d --name zookeeper -p 2181:2181  --restart always wurstmeister/zookeeper

docker run -d --name kafka -p 9082:9092 \
--link zookeeper:zookeeper \
--env KAFKA_BROKER_ID=100 \
--env HOST_IP=192.168.1.59 \
--env KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 \
--env KAFKA_ADVERTISED_HOST_NAME=192.168.1.59 \
--env KAFKA_ADVERTISED_PORT=9082 \
-v /etc/localtime:/etc/localtime --restart=always wurstmeister/kafka


docker run -d --name kafka-manager -p 9000:9000 \
--link zookeeper:zookeeper --link kafka:kafka --env ZK_HOSTS=zookeeper:2181 \
--restart=always sheepkiller/kafka-manager