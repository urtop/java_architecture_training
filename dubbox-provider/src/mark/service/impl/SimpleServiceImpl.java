package mark.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import mark.entity.Simple;
import mark.service.SimpleService;

@Service("simpleService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass= mark.service.SimpleService.class, protocol={"dubbo"}, retries=0)
public class SimpleServiceImpl implements SimpleService{

	@Override
	public String sayHello(String name) {
		return "hello" + name;
	}

	@Override
	public Simple getSimple() {
        Map<String,Integer> map = new HashMap<String, Integer>(2);  
        map.put("zhang0", 1);  
        map.put("zhang1", 2);  
        return new Simple("zhang3", 21, map);
	}

}
