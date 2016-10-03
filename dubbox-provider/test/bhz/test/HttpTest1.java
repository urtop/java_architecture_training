package mark.test;

import java.util.HashMap;
import java.util.Map;

import mark.entity.User;
import mark.utils.FastJsonConvert;
import mark.utils.HttpProxy;

public class HttpTest1 {

	public static void main(String[] args) throws Exception{
    	User user = new User();
    	user.setId("1001");
    	user.setName("李四");
		String responseStr1 = HttpProxy.postJson("http://localhost:8888/provider/userService/postUser",
				FastJsonConvert.convertObjectToJSON(user));
		System.out.println(responseStr1);
		
		
	}
}
