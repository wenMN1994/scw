package com.dragon.scw.webui.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2020-01-19
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
@Component
public class AlipayConfig implements InitializingBean {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id;
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key;
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	public static String alipay_public_key;

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url;

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url;

	// 签名方式
	public static String sign_type;
	
	// 字符编码格式
	public static String charset;
	
	// 支付宝网关
	@Value("${alipay.gatewayUrl}")
	public static String gatewayUrl;
	
	@Value("${alipay.app_id}")
	public String appId;
	
	@Value("${alipay.merchant_private_key}")
    public String merchantPrivateKey;
	
	@Value("${alipay.alipay_public_key}")
	public String alipayPublicKey;

	@Value("${alipay.notify_url}")
	public String notifyUrl;

	@Value("${alipay.return_url}")
	public String returnUrl;

	@Value("${alipay.sign_type}")
	public String signType;
	
	@Value("${alipay.charset}")
	public String charset2;
	
	@Value("${alipay.gatewayUrl}")
	public String gatewayUrl2;

	@Override
	public void afterPropertiesSet() throws Exception {
		// 为所有的静态属性赋值为Spring获取到的值
		app_id = appId;
		merchant_private_key = merchantPrivateKey;
		alipay_public_key = alipayPublicKey;
		notify_url = notifyUrl;
		return_url = returnUrl;
		sign_type = signType;
		charset = charset2;
		gatewayUrl = gatewayUrl2;

	}

}

