import cv2
import numpy as np
import tensorflow as tf
import os

class getCheckCode(object):
    def __init__(self):
        pass

    @classmethod
    def vec2text(self,vec):
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
    @classmethod
    def conver2gray(self,img):
        if len(img.shape) > 2:
            gray = np.mean(img, -1)
            return gray
        else:
            return img

    @classmethod
    def predict_get_batch(self,image):
        batch_x = np.zeros([1, 60, 160, 1])

        # generrator=ImageCaptcha(height=height,width=width)

        texts = ''
        for i in range(1):
            image = tf.reshape(getCheckCode.conver2gray(image).flatten() / 255, (60, 160, 1))
            batch_x[i, :] = image

        return batch_x

    @classmethod
    def getCode(self,content):
        # os.environ['CUDA_VISIBLE_DEVICES'] = '-1'
        img = cv2.imdecode(np.frombuffer(content, np.uint8), cv2.IMREAD_COLOR)
        img = cv2.resize(img, (160, 60))
        data_x =getCheckCode. predict_get_batch(img)
        model = tf.keras.models.load_model('./model/captcha28.h5')
        prediction_value = model.predict(data_x)

        prediction_values = np.argmax(prediction_value, axis=2)[0]
        prediction_values =getCheckCode. vec2text(prediction_values)
        # print(np.argmax(prediction_value, axis=2)[0])
        print('预测的验证码：{}'.format(prediction_values))
        return prediction_values
