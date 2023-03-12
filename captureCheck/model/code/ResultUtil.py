
class ResultUtil(object):
    def __init__(self,code,message,data):
        self.code=code
        self.message=message
        if data:
            self.data=data
        else:
            data=None
        pass
    def success(code,message,data):
        return {'code':code,'message':message,'data':data}
    def fail(code,message):
        return {'code': code, 'message': message}
