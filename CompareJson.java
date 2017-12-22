package com.capitalone.dsd.identity;

import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

public class CompareJson {
	
	
	/*
	 * expectedResponseBody
	 * 
	 * {"menu": {
  "id": "file",
  "value": "File", 
  "date" : "2017-01-01",
  "popup": {
    "menuitem": [
      {"value": "New", "onclick": "CreateNewDoc()"},
      {"value": "Open", "onclick": "OpenDoc()"},
      {"value": "Close", "onclick": "CloseDoc()"}
    ]
  }
}}
	 */
	
	
	
	public static String expectedResponseBody = "{\"menu\": {\r\n  \"id\": \"file\",\r\n  \"value\": \"File\", \r\n  \"date\" : \"2017-01-01\",\r\n  \"popup\": {\r\n    \"menuitem\": [\r\n      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\r\n      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\r\n      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\r\n    ]\r\n  }\r\n}}";
	public static String responseBody = "{\"menu\": {\r\n  \"id\": \"file\",\r\n  \"value\": \"File\", \r\n  \"date\" : \"2017-01-03\",\r\n  \"popup\": {\r\n    \"menuitem\": [\r\n      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\r\n      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\r\n      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\r\n    ]\r\n  }\r\n}}";
	
	public static String responseBody_Error = "{\"menu\": {\r\n  \"id\": \"file\",\r\n  \"value\": \"File1\", \r\n  \"date\" : \"2017-01-03\",\r\n  \"popup\": {\r\n    \"menuitem\": [\r\n      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\r\n      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\r\n      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\r\n    ]\r\n  }\r\n}}";
	
	public static void main(String[] args) {
		happyCompare();
		errorCompare();
	}
	
	private static void happyCompare() {
		System.out.println("..Beginning happyCompare...");
		JSONAssert.assertEquals(expectedResponseBody, responseBody,
	            new CustomComparator(JSONCompareMode.LENIENT,
	                new Customization("menu.date", (o1, o2) -> true)));
		System.out.println("..Successfully compared happyCompare...");
	}
	
	private static void errorCompare() {
		System.out.println("..Beginning errorCompare...");
		JSONAssert.assertEquals(expectedResponseBody, responseBody_Error,
	            new CustomComparator(JSONCompareMode.LENIENT,
	                new Customization("menu.date", (o1, o2) -> true)));
		System.out.println("..Successfully errorCompare compared...");
	}

}
