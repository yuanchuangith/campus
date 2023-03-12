#导入pymsql模块
import pymysql

#创建连接MYSQL的类
class connectMysql(object):

    #初始化变量
    def __init__(self,database):
        self.username = 'root'
        self.host = '127.0.0.1'
        self.passwd = '123456'
        self.database = database

    def conn_mysql(self):
        conn = pymysql.connect(user=self.username, host=self.host, password=self.passwd, db=self.database)
        return conn

    def selectSql(self,sql):
        self.conn = self.conn_mysql()
        cur = self.conn.cursor(pymysql.cursors.DictCursor)
        cur.execute(sql)

        results = cur.fetchall()
        cur.close()
        return results
    def selectListSql(self,sql):
        self.conn = self.conn_mysql()
        cur = self.conn.cursor(pymysql.cursors.Cursor)
        cur.execute(sql)

        results = cur.fetchall()
        cur.close()
        return results
    def executeSql(self,sql):
        self.conn = self.conn_mysql()
        cur = self.conn.cursor()
        cur.execute(sql)


        results = cur.fetchall()
        self.conn.commit()
        cur.close()
        return results