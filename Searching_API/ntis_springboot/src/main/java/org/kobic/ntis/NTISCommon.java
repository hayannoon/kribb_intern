package org.kobic.ntis;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.kobic.ntis.Field;
import org.kobic.ntis.NTISDataParser;
import org.kobic.ntis.ResultModel;

public class NTISCommon {

	public static String url_head = "http://fast.ntis.go.kr/apis/service/search/project?userKey=821614f489a22930b4971025776fb4b2e2b0cc3d&query="; // url
																																					// 앞부분
	public static String url_tail = "&displayCount=100&startPosition=1&sortby=-new"; // url 뒷부분

	
	public static String url_tail_version2 = "&sortby=-new";
	
	private static boolean hasAnd = false; // 검색어에 '+' 연산자가 들어갔는지 여부를 저장할 변수
	private static boolean hasOr = false; // 검색어에 '|' 연산자가 들어갔는지 여부를 저장할 변수
	private String finalUrl;

	public JSONArray getNtisXmlDataFile(String input) {

		hasAnd = false;
		hasOr = false;
		int howMany = 0; // 검색어를 몇개의 단위로 자를것인지 저장할 변수
		String[] temp = new String[10]; // url을 자를건데 그 조각들을 임시로 저장할 배열

		checkSearchingOperator(input); // And 혹은 or 연산자 확인

		if (hasAnd) {
			/*
			 * 검색 연산자를 단위로 검색어를 잘라서 배열에 넣는다.
			 */
			StringTokenizer tokens = new StringTokenizer(input, "+");
			for (int i = 0; tokens.hasMoreElements(); i++) {
				temp[i] = new String(tokens.nextToken());
				howMany++;
			}
		} else if (hasOr) {
			StringTokenizer tokens = new StringTokenizer(input, "|");
			for (int i = 0; tokens.hasMoreElements(); i++) {
				temp[i] = new String(tokens.nextToken());
				howMany++;
			}
		} else {
			temp[0] = new String(input);
			howMany++;
		}

		for (int i = 0; i < howMany; i++) {
			/*
			 * 검색어에 필드가 없는경우 디폴트로 bi를 넣어준다.
			 */
			if (!(temp[i].contains(":"))) {
				temp[i] = "bi:" + temp[i];
			}
		}

		for (int i = 0; i < howMany; i++) {
			/*
			 * API 문서대로 필드:(키워드) 형태를 만들어주기위한 과정 + 필드값이 적절한 값인지 isMember 함수를 사용해서 검사하고, 적절하지
			 * 않은 필드는 디폴트값인 bi로 치환한다.
			 */
			StringTokenizer tokens = new StringTokenizer(temp[i], ":");
			String tmp1 = tokens.nextToken();
			if ((!Field.isMember(tmp1))) {
				temp[i] = "bi:(" + tokens.nextToken().trim() + ")";
			} else {
				temp[i] = tmp1 + ":(" + tokens.nextToken().trim() + ")";
			}
		}

		String query = "";
		/*
		 * query 부분을 완성시킨다. ( +,| 는 각각 and or 로 치환하고, 모든 검색어를 연결한다.)
		 */
		if (hasAnd) {
			for (int i = 0; i < howMany; i++) {
				if (i != 0) {
					query = query.concat(" and ");
				}
				query = query.concat(temp[i]);
			}
		} else if (hasOr) {
			for (int i = 0; i < howMany; i++) {
				if (i != 0) {
					query = query.concat(" or ");
				}
				query = query.concat(temp[i]);
			}
		} else {
			query = query.concat(temp[0]);
		}

		try { // 완성된 query를 UTF-8로 인코딩 한다.
			query = URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.finalUrl = this.makeURL(query); // url 완성시킨다.
		
		
		

		NTISDataParser parser = new NTISDataParser();
		List<ResultModel> list = parser.getNtisData(finalUrl);
		// 완성된 URL로 쿼리를 날려서 파싱도니 데이터를 list에 저장한다.

		return this.makeJSON(list); // 완성된 JSON 반환

	}
	
	
	//오버로딩
	public JSONArray getNtisXmlDataFile(String input , String displayCount, String startPosition) {

		hasAnd = false;
		hasOr = false;
		int howMany = 0; // 검색어를 몇개의 단위로 자를것인지 저장할 변수
		String[] temp = new String[10]; // url을 자를건데 그 조각들을 임시로 저장할 배열
		
		checkSearchingOperator(input); // And 혹은 or 연산자 확인

		if (hasAnd) {
			/*
			 * 검색 연산자를 단위로 검색어를 잘라서 배열에 넣는다.
			 */
			StringTokenizer tokens = new StringTokenizer(input, "+");
			for (int i = 0; tokens.hasMoreElements(); i++) {
				temp[i] = new String(tokens.nextToken());
				howMany++;
			}
		} else if (hasOr) {
			StringTokenizer tokens = new StringTokenizer(input, "|");
			for (int i = 0; tokens.hasMoreElements(); i++) {
				temp[i] = new String(tokens.nextToken());
				howMany++;
			}
		} else {
			temp[0] = new String(input);
			howMany++;
		}

		for (int i = 0; i < howMany; i++) {
			/*
			 * 검색어에 필드가 없는경우 디폴트로 bi를 넣어준다.
			 */
			if (!(temp[i].contains(":"))) {
				temp[i] = "bi:" + temp[i];
			}
		}

		for (int i = 0; i < howMany; i++) {
			/*
			 * API 문서대로 필드:(키워드) 형태를 만들어주기위한 과정 + 필드값이 적절한 값인지 isMember 함수를 사용해서 검사하고, 적절하지
			 * 않은 필드는 디폴트값인 bi로 치환한다.
			 */
			StringTokenizer tokens = new StringTokenizer(temp[i], ":");
			String tmp1 = tokens.nextToken();
			if ((!Field.isMember(tmp1))) {
				temp[i] = "bi:(" + tokens.nextToken().trim() + ")";
			} else {
				temp[i] = tmp1 + ":(" + tokens.nextToken().trim() + ")";
			}
		}

		String query = "";
		/*
		 * query 부분을 완성시킨다. ( +,| 는 각각 and or 로 치환하고, 모든 검색어를 연결한다.)
		 */
		if (hasAnd) {
			for (int i = 0; i < howMany; i++) {
				if (i != 0) {
					query = query.concat(" and ");
				}
				query = query.concat(temp[i]);
			}
		} else if (hasOr) {
			for (int i = 0; i < howMany; i++) {
				if (i != 0) {
					query = query.concat(" or ");
				}
				query = query.concat(temp[i]);
			}
		} else {
			query = query.concat(temp[0]);
		}

		try { // 완성된 query를 UTF-8로 인코딩 한다.
			query = URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

		this.finalUrl = this.makeURL(query,displayCount,startPosition); // url 완성시킨다.
		
		
		

		NTISDataParser parser = new NTISDataParser();
		List<ResultModel> list = parser.getNtisData(finalUrl);
		// 완성된 URL로 쿼리를 날려서 파싱도니 데이터를 list에 저장한다.

		return this.makeJSON(list); // 완성된 JSON 반환

	}
	
	
	

	private void checkSearchingOperator(String input) {
		// input값에 +,| 포함 여부 확인
		if (input.contains("+"))
			hasAnd = true;
		if (input.contains("|"))
			hasOr = true;
	}

	private String makeURL(String query) {

		String outputUrl = new String(url_head);
		outputUrl = outputUrl.concat(query);
		outputUrl = outputUrl.concat(url_tail);
		outputUrl = outputUrl.replaceAll("  ", " ");
		outputUrl = outputUrl.replaceAll("   ", " ");
		// URL 조각들을 모두 이어서 완성시킨다.
		return outputUrl;
	}

	
	private String makeURL(String query,String DC, String SP) {

		String outputUrl = new String(url_head);
		outputUrl = outputUrl.concat(query);
		outputUrl = outputUrl.concat("&displayCount=" + DC + "&startPosition=" + SP);
		outputUrl = outputUrl.concat(url_tail_version2);
		outputUrl = outputUrl.replaceAll("  ", " ");
		outputUrl = outputUrl.replaceAll("   ", " ");
		// URL 조각들을 모두 이어서 완성시킨다.
		return outputUrl;
	}
	
	

	@SuppressWarnings("unchecked")
	private JSONArray makeJSON(List<ResultModel> modelList) {
		JSONArray returnValue = new JSONArray();
		JSONObject element = new JSONObject();

		for (ResultModel m : modelList) {
			// 완성된 리스트를 JSON 형태로 만들어준다.

			element.put("Project Number", m.getProjectNumber());
			// element.put("Project Title", m.getProjectTitle());

			JSONObject forTitle = new JSONObject();
			forTitle.put("Korean", m.getProjectTitle_kor());
			forTitle.put("English", m.getProjectTitle_eng());
			element.put("Project Title", forTitle);

			JSONObject forManager = new JSONObject();
			forManager.put("Name", m.getManager());
			forManager.put("ID", m.getManagerID());
			forManager.put("ResearcherNo", m.getManagerResearcherNo());
			element.put("Manager", forManager);

			JSONObject forResearchers = new JSONObject();
			forResearchers.put("Name", m.getResearchers_name());
			forResearchers.put("ID", m.getResearchersHumanID());
			forResearchers.put("ManCount", m.getResearchers_manCount());
			forResearchers.put("WomanCount", m.getResearchers_womanCount());
			forResearchers.put("PrStRegistrationNumber", m.getResearchers_prStRegistrationNumber());
			element.put("Researchers", forResearchers);

			JSONObject forKeyWord = new JSONObject();
			forKeyWord.put("Korean", m.getKeyWord_kor());
			forKeyWord.put("English", m.getKeyWord_eng());
			element.put("KeyWord", forKeyWord);

			JSONObject forOrderAgency = new JSONObject();
			forOrderAgency.put("Name", m.getOrderAgency());
			forOrderAgency.put("Code", m.getOrderAgency_code());
			element.put("OrderAgency", forOrderAgency);

			element.put("LeadAgency", m.getLeadAgency());

			JSONObject forResearchAgency = new JSONObject();
			forResearchAgency.put("Name", m.getResearchAgency_name());
			forResearchAgency.put("Code", m.getResearchAgency_code());
			element.put("ResearchAgency", forResearchAgency);

			JSONObject forBudgetProject = new JSONObject();
			forBudgetProject.put("Name", m.getBudgetProject_name());
			forBudgetProject.put("Code", m.getBudgetProject_code());
			element.put("BudgetProject", forBudgetProject);

			element.put("BigProjectTitle", m.getBigProjectTitle());

			JSONObject forManageAgency = new JSONObject();
			forManageAgency.put("Name", m.getManageAgency());
			forManageAgency.put("Code", m.getManageAgency_code());
			element.put("ManageAgency", forManageAgency);

			JSONObject forMinistry = new JSONObject();
			forMinistry.put("Name", m.getMinistry());
			forMinistry.put("Code", m.getMinistry_code());
			element.put("Ministry", forMinistry);

			element.put("Year", m.getProjectYear());
			element.put("SecretProject", m.getSecretProject());

			JSONObject forProjectPeriod = new JSONObject();
			forProjectPeriod.put("StartDate", m.getStartDate());
			forProjectPeriod.put("EndDate", m.getEndDate());
			forProjectPeriod.put("TotalStart", m.getTotalStart());
			forProjectPeriod.put("TotalEnd", m.getTotalEnd());
			element.put("Period", forProjectPeriod);

			element.put("Organization PNumber", m.getOrganizationPNumber());

			JSONObject forMinistryScienceClass = new JSONObject();
			forMinistryScienceClass.put("Large", m.getMinistryScienceClass_large());
			forMinistryScienceClass.put("Medium", m.getMinistryScienceClass_medium());
			forMinistryScienceClass.put("Small", m.getMinistryScienceClass_small());

			returnValue.add(element);

			element = new JSONObject(); // 다만든 JSON 객체 반환
		}
		return returnValue;
	}

}
