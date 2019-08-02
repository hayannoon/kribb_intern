package org.kobic.ntis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class NTISDataParser {

	public List<ResultModel> getNtisData(String url) {

		List<ResultModel> resultList = null;

		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = builderFactory.newDocumentBuilder();

			Document xmlDocument = builder.parse(url);

			XPath xPath = XPathFactory.newInstance().newXPath();

			resultList = new ArrayList<ResultModel>();

			String expression = "/RESULT/RESULTSET/HIT";

			NodeList nodeList = null;

			nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);

			for (int i = 0; i < nodeList.getLength(); i++) {
				ResultModel rm = new ResultModel();
				rm = set(xmlDocument, xPath, nodeList.item(i).getAttributes().getNamedItem("NO").getTextContent(), rm);
				// System.out.println(rm.getManageAgency() + "");
				resultList.add(rm);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		int i = 0;

		return resultList;
	}

	public ResultModel set(Document xmlDocument, XPath xPath, String no, ResultModel rm) {

		String expression = "/RESULT/RESULTSET/HIT[@NO='" + no + "']%s";

		rm.setStartDate(getContext(xmlDocument, xPath, expression, "/ProjectPeriod/Start"));
		rm.setEndDate(getContext(xmlDocument, xPath, expression, "/ProjectPeriod/End"));
		rm.setProjectNumber(getContext(xmlDocument, xPath, expression, "/ProjectNumber"));
		rm.setProjectTitle_kor(getContext(xmlDocument, xPath, expression, "/ProjectTitle/Korean"));
		rm.setProjectTitle_eng(getContext(xmlDocument, xPath, expression, "/ProjectTitle/English"));
		rm.setManager(getContext(xmlDocument, xPath, expression, "/Manager/Name"));
		rm.setManagerID(getContext(xmlDocument, xPath, expression, "/Manager/HumanID"));
		rm.setManagerResearcherNo(getContext(xmlDocument, xPath, expression, "/Manager/ResearcherNO"));
		rm.setResearchers_name(getContext(xmlDocument, xPath, expression, "/Researchers/Name"));
		rm.setResearchers_humanID(getContext(xmlDocument, xPath, expression, "/Researchers/HumanID"));
		rm.setResearchers_manCount(getContext(xmlDocument, xPath, expression, "/Researchers/ManCount"));
		rm.setResearchers_womanCount(getContext(xmlDocument, xPath, expression, "/Researchers/WomanCount"));
		rm.setResearchers_prStRegistrationNumber(
				getContext(xmlDocument, xPath, expression, "/Researchers/PrStRegistrationNumber"));
		rm.setKeyWord_kor(getContext(xmlDocument, xPath, expression, "/Keyword/Korean"));
		rm.setKeyWord_eng(getContext(xmlDocument, xPath, expression, "/Keyword/English"));

		rm.setOrderAgency(getContext(xmlDocument, xPath, expression, "/OrderAgency/Name"));
		rm.setOrderAgency_code(getContext(xmlDocument, xPath, expression, "/OrderAgency/Code"));
		rm.setLeadAgency(getContext(xmlDocument, xPath, expression, "/LeadAgency"));

		rm.setResearchAgency_name(getContext(xmlDocument, xPath, expression, "/ResearchAgency/Name"));
		rm.setResearchAgency_code(getContext(xmlDocument, xPath, expression, "/ResearchAgency/Code"));
		rm.setBudgetProject_name(getContext(xmlDocument, xPath, expression, "/BudgetProject/Name"));
		rm.setBudgetProject_code(getContext(xmlDocument, xPath, expression, "/BudgetProject/Code"));

		rm.setBusinessName(getContext(xmlDocument, xPath, expression, "/BusinessName"));
		rm.setBigProjectTitle(getContext(xmlDocument, xPath, expression, "/BigprojectTitle"));

		rm.setManageAgency(getContext(xmlDocument, xPath, expression, "/ManageAgency/Name"));
		rm.setManageAgency_code(getContext(xmlDocument, xPath, expression, "/ManageAgency/Code"));
		rm.setMinistry(getContext(xmlDocument, xPath, expression, "/Ministry/Name"));
		rm.setMinistry_code(getContext(xmlDocument, xPath, expression, "/Ministry/Code"));

		rm.setProjectYear(getContext(xmlDocument, xPath, expression, "/ProjectYear"));
		rm.setSecretProject(getContext(xmlDocument, xPath, expression, "/SecretProject"));

		rm.setStartDate(getContext(xmlDocument, xPath, expression, "/ProjectPeriod/Start"));
		rm.setEndDate(getContext(xmlDocument, xPath, expression, "/ProjectPeriod/End"));
		rm.setTotalStart(getContext(xmlDocument, xPath, expression, "/ProjectPeriod/TotalStart"));
		rm.setTotalEnd(getContext(xmlDocument, xPath, expression, "/ProjectPeriod/TotalEnd"));

		rm.setOrganizationPNumber(getContext(xmlDocument, xPath, expression, "/OrganizationPNumber"));

		rm.setMinistryScienceClass_large(getContext(xmlDocument, xPath, expression, "/MinistryScienceClass/Large"));
		rm.setMinistryScienceClass_medium(getContext(xmlDocument, xPath, expression, "/MinistryScienceClass/Medium"));
		rm.setMinistryScienceClass_small(getContext(xmlDocument, xPath, expression, "/MinistryScienceClass/Small"));

		return rm;
	}

	public String getContext(Document xmlDocument, XPath xPath, String expression, String item) {

		String context = "";

		try {
			context = ((Node) xPath.evaluate(String.format(expression, item), xmlDocument, XPathConstants.NODE))
					.getTextContent();
		} catch (DOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return context;
	}
}