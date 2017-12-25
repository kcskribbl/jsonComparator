

import java.util.ArrayList;
import java.util.List;

import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

/**
 * 
 * @author gothai
 *
 */
public class CompareJson {

	/*
	 * expectedResponseBody
	 * 
	 * {"menu": { "id": "file", "value": "File", "date" : "2017-01-01", "popup":
	 * { "menuitem": [ {"value": "New", "onclick": "CreateNewDoc()"}, {"value":
	 * "Open", "onclick": "OpenDoc()"}, {"value": "Close", "onclick":
	 * "CloseDoc()"} ] } }}
	 */

	//
	public static String expectedResponseBody = "{\"menu\": {\r\n  \"id\": \"file\",\r\n  \"value\": \"File\", \r\n  \"date\" : \"2017-01-01\",\r\n  \"popup\": {\r\n    \"menuitem\": [\r\n      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\r\n      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\r\n      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\r\n    ]\r\n  }\r\n}}";
	public static String responseBody = "{\"menu\": {\r\n  \"id\": \"file\",\r\n  \"value\": \"File1\", \r\n  \"date\" : \"2017-01-03\",\r\n  \"popup\": {\r\n    \"menuitem\": [\r\n      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\r\n      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\r\n      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\r\n    ]\r\n  }\r\n}}";

	public static String responseBody_Error = "{\"menu\": {\r\n  \"id\": \"file\",\r\n  \"value\": \"File1\", \r\n  \"date\" : \"2017-01-03\",\r\n  \"popup\": {\r\n    \"menuitem\": [\r\n      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\r\n      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\r\n      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\r\n    ]\r\n  }\r\n}}";

	public static void main(String[] args) {
		happyCompare();
		happyCompare_1();
		errorCompare_1();
		
		List<String> jsonIdElementList = new ArrayList<String>();
		jsonIdElementList.add("menu.date");
		jsonIdElementList.add("menu.value");
		happyCompareAsList(jsonIdElementList);
	}

	
	private static void happyCompareAsList(List<String> jsonIdElementList) {
		System.out.println("..Beginning happyCompareAsList...");
		List<Customization> customizationList = new ArrayList();
		for(String jsonIdElement : jsonIdElementList) {
			customizationList.add(createCustomCustomization(jsonIdElement));
		}
		
		JSONAssert.assertEquals(expectedResponseBody, responseBody, new CustomComparator(JSONCompareMode.LENIENT,
				customizationList.toArray((new Customization[customizationList.size()]) )));
		System.out.println("..Successfully compared happyCompareAsList...");
	}
	
	
	private static void happyCompare() {
		System.out.println("..Beginning happyCompare...");
		JSONAssert.assertEquals(expectedResponseBody, responseBody, new CustomComparator(JSONCompareMode.LENIENT,
				new Customization("menu.date", (o1, o2) -> true), new Customization("menu.date", (o1, o2) -> true)));
		System.out.println("..Successfully compared happyCompare...");
	}

	private static void happyCompare_1() {
		System.out.println("..Beginning happyCompare_1...");
		String jsonElementPath_menu_date = "menu.date";
		String jsonElementPath_menu_value = "menu.value";
		JSONAssert.assertEquals(expectedResponseBody, responseBody_Error,new CustomComparator(JSONCompareMode.LENIENT,
				createCustomCustomization(jsonElementPath_menu_date), createCustomCustomization(jsonElementPath_menu_value)));
		System.out.println("..Successfully happyCompare_1 compared...");
	}
	
	private static void errorCompare_1() {
		System.out.println("..Beginning errorCompare_1...");
		String jsonElementPath_menu_date = "menu.date";
		String jsonElementPath_menu_value = "menu.id";
		JSONAssert.assertEquals(expectedResponseBody, responseBody_Error,new CustomComparator(JSONCompareMode.LENIENT,
				createCustomCustomization(jsonElementPath_menu_date), createCustomCustomization(jsonElementPath_menu_value)));
		System.out.println("..Successfully errorCompare_1 compared...");
	}
	
	private static Customization createCustomCustomization(String jsonElementPath) {
		return new Customization(jsonElementPath, (o1, o2) -> true);
	}
	
}
