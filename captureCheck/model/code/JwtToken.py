import time

import jwt
class JwtToken(object):
    def __init__(self,expire_time):
        if expire_time:
            self.expire_time = 3600 * 24 * 3
        else:
            self.expire_time = 3600 * 24 * 3  # token时效3天
        self.Jwt_key = 'captcha28'  # 秘钥
        self.algorithm = 'ASE'  # 加密算法
    @classmethod
    def generate_token(self, payload):
        '''
        生成token
        :return:
        '''
        payload['exp'] = int(time.time()) + self.expire_time
        token = jwt.encode(payload, self.Jwt_key, algorithm=self.algorithm)
        return token


    def get_payload_from_token(self, token):
        '''
        解析token中的payload值
        state: 0 token解析失败， 1 token解析成功， 2 token过期， 3 token时间过半
        :return:
        '''
        try:
            pl = jwt.decode(token, self.Jwt_key,options={"verify_signature": False})

            print('token解析成功')  # 该状态继续执行相关业务即可

            return pl['sub']

        except Exception as e:
            print('token解析失败')  # 该状态说明有老6来了

            return None


