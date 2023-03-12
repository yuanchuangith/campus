package com.example.server.config;
//import com.alipay.easysdk.kernel.Config;
//
//import com.alipay.easysdk.factory.Factory;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Data
@Component

public  class zfbPayConfig {
//2021000116671801
    public static String appID = "2021000116671801";
    // 私钥 pkcs8格式的
    public static String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC64pXyIHqdXZq7uW20XXkw/Ks1Fzxvj7+PKh2Xd7JYm5kUeUCklVuks92HGjDoosUg9P+zFSv+XELEMDEZJj0in761U7TOV1ywirXmkcZJm6UMdACRlmd1pEgrYciy/vDZ/+Mte6oAVIZRBuW4w7tC5pc6VKT2ehU2h6KOudxfFBS18ADKtSh9XEMUihUPLZg7W+gWTNhsNQ0IhJwpO0D6A5xhgsPiH5Qoj9mBgeLMf6E6FomvRDtzAwQydakqrHDzEaNGE6CO3f76yMtqySIhk6GziDG8QSsVTcX6E/d7QyjFMV51GlMu9daNp+BKkLF86n3zFveOUy6V1MIbs4P1AgMBAAECggEBAIfFLaJmLi49XribEjRC5FTOe6NqaiGykTx1IGJ2Nz9ga2oQXQI08AtMENiiEn4wIn66/zkfXILCjE7zo/fda/THkUkystxlY5VucJA9v4yiYtoVPISIfElfPH8n3yMLS/dUi7rzaKIsxhf/ps0Ahf8ofx2FeQMH1nlzOInwRUVclvVlnXU5y+o4Vvn4Zmoap9u/x6plIuZU3gBT84BpNekVt8jbi1YfpOqDOrtD6sIfRhpYfqq4Do8Kvumxl8JqjAA+6TqdJTQlu3olNCNz9s1ZqcuUC+tgCuLU2oHNcz1LT85ijEBudLrR7G+pIRD2kEMCuWzO8Y8IeWvibYnu6wECgYEA6JPORI/PHHA3dgcQDCuY9yqZxWjRI6BgZ3VWI7ljQrfumSh7XAsUJdkmJ1sjQwuVW1RIAQZ55FoJ5ZkCTOOhVbDBS/yptX3m7LhCvId9Q0J/PEcygWx5A6nqmgX7V99leqUOakIB7Zs7r1EjsUZebQGbuIggXWPo7E3KacsrEuUCgYEAzbTDwUpdxXx2rCJdD/6Q++CbD1/AgDFIAm+/qpb2Ij+yYD++Jq+QHSbqAEWl+Vdu15QhSbvJwK5gYvJm0F+MlpZ0rh/kYZBbnpLI6WW3IuQ9KCn/JYHkz5cXZQpxP7yQjKw4v32wbMDrxjsAnsyqs35H+PkaLxIfXlMpEjMAS9ECgYEAx5WPfOasJQtnjLGi487GBdVZAD9iud/22tGmiiGdPLP63RQQSHx5XcFrLHrZV7+o/CbnY3nbQErEE3OqtbDh0ldMiZjZShYxBRNasc7Tf9Sgu2mdvdMIRkyaNnLLrizXONhL9oOOhAiiv2Ax8P8iKQkoSMMIgxwp1h8rhH4FCMECgYAULEUCQKO/iTwuF1SfC+z19oneURqUt8CDFKhsN0+9/hclHzsrmg38/eL20d27/RoOpCU7kOHxNmiXa4QvrR8haXHZ3aDM/JYvj9+KHAKAtndU6AFwkTN6NCwEKaj/giN+2Cjchpv1nlnA3+poqFOZNKokPXGW6iUPYAJoGX5O8QKBgBfSsB4o9EzSnZxSnVyY3ZmNnY621bdivpWSrg85Kdq/uJnSA2/UQwz0+ZG2PjCq4OTuVtnYR+acW2MDogiGOkjlAtRl4ASZnbD2QspSDE+iuEhyXxYTrZb8FTq0/e1ScZwIorqfsxDD12Y8StUdAb1XQbb+SM36Kgjl3GZkVOjA";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://7q4gh6.natappfree.cc/aply/notify";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://ycxhll.site:8080/#/reCharge";
    // 请求网关地址https://openapi.alipaydev.com/gateway.do
//https://openapi.alipaydev.com/gateway.do
    public static String url = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String charset = "utf-8";
    // 返回格式
    public static String format = "json";
    // 支付宝公钥
    public static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsAqCWxrc1B0YEnmIQa87q/IQYoxtjOzgV8z6ppN8+Yo2lWSvpTiw/zM1ggy/Qmnw5ljprJPKporhqnjmsbI60xQxv3SyvI/lQTWy9Xe1pcIqz0Sn4cmPwX8ZbGNHuY7ja/S58dhNcR84KBOxxMO5F4Tdh8sBSasi5FRXUylXzeq/Vb8kiD3074AZFEcCm2LrT6fd1VC0oiyyZIK/OhrwJNGEqWvTBPBkFK3V0IvLvIMAmxRgRwtxqpUigOqL08lYga6XeJh5uvVgoZURF5NDAxXV4b/4frX1hGN4ffuI3jls+jndbDZVpljdErrXdNZ52IAjaRldb7IpMHXP0A5SkQIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String signType = "RSA2";



}
