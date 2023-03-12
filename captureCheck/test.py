import os
import random

import cv2
import tensorflow as tf


import fastapi
import uvicorn




import numpy as np


def conver2gray(img):
    if len(img.shape)>2:
        gray = np.mean(img,-1)
        return gray
    else:
        return img
def predict_get_batch(image,num=4,batch_size=1):
    batch_x = np.zeros([batch_size, 60, 160, 1])

    # generrator=ImageCaptcha(height=height,width=width)

    texts=''
    for i in range(batch_size):

        image = tf.reshape(conver2gray(image).flatten()/255, (60, 160, 1))
        batch_x[i, :] = image



    return batch_x


def vec2text(vec):
    number = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']

    lowercase = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']
    capital = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
               'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
    # temp = []
    set_sumarrys = number + lowercase + capital
    text = []
    for i, c in enumerate(vec):
        text.append(set_sumarrys[c])
    return "".join(text)
if __name__ == '__main__':
    imgPaths = os.listdir('./model/assets/sample')
    # tenstr='A12b'
    # print(tenstr.lower())

    num=0

    model = tf.keras.models.load_model('./model/captcha28.h5')


    for i in range(100):
        imgname=imgPaths[random.randint(0,len(imgPaths))]
        checkCode = imgname.split('.')[0]
        path='./model/assets/sample/'+imgname
        img = cv2.imread(path)

        # 直接指定目标图片大小
        # img = cv2.resize(img, (192, 108))

        # 元祖参数，为宽，高
        img = cv2.resize(img, (160, 60))
        data_x=predict_get_batch(img)
        prediction_value = model.predict(data_x)

        prediction_values = np.argmax(prediction_value, axis=2)[0]
        prediction_values = vec2text(prediction_values)
        print(np.argmax(prediction_value, axis=2)[0])
        print('预测的验证码：{}，正确{}'.format(prediction_values,checkCode))
        if checkCode.upper() == prediction_values.upper():
            num += 1
    print('预测准确率：{}'.format(num / 100))