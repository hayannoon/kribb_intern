package org.kobic.ntis;

import org.json.simple.JSONArray;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class RequestMap {

	@RequestMapping("/")
	String home() {
		return "THIS IS NRIS SEARCHING API HOME <br><br>Usage: current url/search/{key words}";
	}

	
	@RequestMapping("/search/{url}")
	public JSONArray test(@PathVariable("url") String url) {

		NTISCommon common = new NTISCommon();

		return common.getNtisXmlDataFile(url);
	}
	
	
	@RequestMapping("/Swagger/{name}")
	public String swagger(@PathVariable("name") String name) {
		
		return "Swagger Test: " + name;
	}
}
