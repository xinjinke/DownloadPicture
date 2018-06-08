package cn;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 *  TODO 待优化httpclientUtil
 * 2017-06-13
 * @author zxh
 *
 */
public class HttpClientUtil {
	
	private static final Logger logger= LoggerFactory.getLogger(HttpClientUtil.class);


	public static String doGet(String url,String param){

		logger.info("请求地址："+url);

		GetMethod method = new GetMethod(url+param);

		HttpClient httpClient = new HttpClient(new HttpClientParams(),new SimpleHttpConnectionManager(true));
		String resultDate = null;
		try {
			httpClient.executeMethod(method);
			resultDate= method.getResponseBodyAsString();
		} catch (IOException e) {
			logger.info("接口异常,"+e.getMessage());
			return null;
		}finally{
			method.releaseConnection();
		}
//		logger.info("返回结果："+resultDate);
		if(resultDate==null||resultDate.equals("")){
			logger.info(url+"接口异常,返回值为空");
			return null;
		}
		return resultDate;
	}



//	public static void main(String[] args) {
//		Map<String,Object> map=new HashMap<>();
//		map.put("accountName", "jinhs-2");
//		map.put("password", "123456");
////		String token=OAuth2ServiceImpl.getCurrentNormalToken().getAccess_token();
////		doPost("http://192.168.1.11:8094/api/Account",map,token);
//
//	}
	
}
