package org.kobic.ntis;

import org.json.simple.JSONArray;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class RequestMap {

	@RequestMapping("/") // ��Ʈ ������
	String home() {
		return "THIS IS NTIS SEARCHING API HOME <br><br>Usage: current url/search/{key words}";
	}

	@RequestMapping("/search/{keyWord}")
	/*
	 * url/search/ �Է½� XML Data ���������� ����
	 */
	public JSONArray search(@PathVariable("keyWord") String keyWord) {

		NTISCommon common = new NTISCommon();

		return common.getNtisXmlDataFile(keyWord);
	}

	//@RequestMapping("/search/{keyWord}/displaycount/{displayCount}/startposition/{startPosition}")
	@RequestMapping("/search/{keyWord}/{displayCount}/{startPosition}")	
	public JSONArray modifiedSearch(
			@PathVariable("keyWord") String keyWord,
			@PathVariable("displayCount") String displayCount,
			@PathVariable("startPosition") String startPosition) {
			
			NTISCommon common = new NTISCommon();
			
			return common.getNtisXmlDataFile(keyWord,displayCount,startPosition);
			
	}

}
