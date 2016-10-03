package mark.dubbo.dependency.provider.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mark.dubbo.dependency.provider.DependencyService;
import mark.dubbo.sample.provider.SampleService;
@Service("dependencyServiceImpl")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass= mark.dubbo.dependency.provider.DependencyService.class, protocol={"dubbo"}, retries=0)
public class DependencyServiceImpl implements DependencyService {

	//注入SampleService
	@Autowired
	private SampleService sampleService;
		
	public String dependency() throws Exception {
		//这里 我们可能需要调用SampleService，也可能不需要...
		System.out.println(sampleService.sayHello("jack"));
		return "dependency exec";
	}

}
