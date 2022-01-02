import json
import pika
import time

import rospy
from sensor_msgs.msg import LaserScan
from entity.LaserMessage import LaserMessage

#初始化验证密码
credentials = pika.PlainCredentials("lunfee", "lunfee")
#建立与虚拟主机的连接
connection = pika.BlockingConnection(pika.ConnectionParameters(host='101.43.56.164',
                                                               port=5672,
                                                               virtual_host='/lunfee',
                                                               credentials=credentials))
## 创建通道
channel = connection.channel()
##声明X
channel.exchange_declare(exchange='laser', exchange_type='topic', durable='false')

routing_key = 'laser.topic'


def callback(laser):
    # rospy.loginfo("heard %s", laser)
    global i
    i += 1
    if i == 20:
        secs = laser.header.stamp.secs
        nsecs = laser.header.stamp.nsecs
        range = laser.ranges
        intensity = laser.intensities
        laser = LaserMessage(secs, nsecs, range, intensity)
        data = json.dumps(laser.__dict__)
        message = data
        channel.basic_publish(exchange='laser', routing_key=routing_key, body=message)
        i = 0
        print(" [x] Sent %r" % message)



    # laserMessage = LaserMessage(laser.header.stamp.secs, laser.header.stamp.nsecs)
    # senddata = json.dumps(laserMessage.__dict__)
    # print(senddata)
def listener():
    rospy.init_node('laserSub', anonymous=True)

    rospy.Subscriber("/scan", LaserScan, callback)
    # spin() simply keeps python from exiting until this node is stopped
    rospy.spin()
if __name__ == '__main__':
    global i
    i = 0
    listener()
