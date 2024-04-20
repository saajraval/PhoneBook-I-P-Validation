package com.cse5382.assignment.util;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

public class Validator {
	public static boolean isValidForPhoneNumber(String phoneNumber) {

		ArrayList<String> list = new ArrayList<>();
		list.add("[0-9][0-9]\\s[0-9][0-9]\\s[0-9][0-9]\\s[0-9][0-9]");// danish Regex
		list.add("[0-9]{4}\\s[0-9]{4}");// danish type 2 Regex
		list.add("^(\\+45)?\\s?(\\d{4}[.-]?){2}\\d{4}$");// danish type 3 Regex	
		
		list.add("[0-9]{3}-[0-9]{4}");//North America Regex
		list.add("([0-9]{3})[0-9]{3}-[0-9]{4}");// North America Regex 
		list.add("[0-9]{3}-[0-9]{3}-[0-9]{4}"); // North America Regex
		list.add("1-[0-9]{3}-[0-9]{3}-[0-9]{4}");//North America Regex
		list.add("1([0-9]{3})[0-9]{3}-[0-9]{4}");// North America Regex
		list.add("[0-9]{3}\\s[0-9]{3}\\s[0-9]{4}");// North America Regex
		list.add("[0-9]{3}.[0-9]{3}.[0-9]{4}");// North America Regex
		list.add("\\b\\d{3}\\.\\d{3}\\.\\d{4}\\b");// North America Regex
		list.add("1\\s[0-9]{3}\\s[0-9]{3}\\s[0-9]{4}");// North America Regex
		list.add("\\b1\\.\\d{3}\\.\\d{3}\\.\\d{4}\\b");// North America Regex
	
		list.add("\\+\\d{1}\\(\\d{3}\\)\\d{3}-\\d{4}");
		list.add("\\b\\d{5}[ .]\\d{5}\\b");
		list.add("\\+32 \\(\\d{2}\\) \\d{3}-\\d{4}");
		list.add("\\d{1}\\(\\d{3}\\)\\d{3}-\\d{4}");
		list.add("[0-9]{5}.[0-9]{5}");
		list.add("011 1 [0-9]{3}\\s[0-9]{3}\\s[0-9]{4}");
		
		
		for (String x : list) {
			if (phoneNumber.matches(x) == true) {
				return true;
			}//end of if
		}//end of for
		return false;

		// return phoneNumber.matches(danishRegex) || phoneNumber.matches(danishRegex1)
//				|| phoneNumber.matches(northAmerica);// valid->true otherwise false

	}//end of isValidPhoneNumber 

	public static boolean isValidForName(String name) {
		
		ArrayList<String> list = new ArrayList<>();
		list.add("[A-Za-z]+(?: [A-Za-z]+)*");
		list.add("[A-Za-z]+(?:, [A-Za-z]+(?: [A-Za-z]+)?)?");
		
		for(String y:list) {
			if(name.matches(y) == true) {
				return true;
			}//end of if 
		}//end of for
		
		return false;
		
	}//end of isValidForName
	
	public static void main(String[] args) {

		System.out.println(Validator.isValidForPhoneNumber("23"));
		System.out.println(Validator.isValidForPhoneNumber("1234 5678"));
		System.out.println(Validator.isValidForPhoneNumber("12 34 56 78"));
		System.out.println(Validator.isValidForPhoneNumber("123-4567"));
		System.out.println(Validator.isValidForPhoneNumber("123-45678"));
		
	}// end of main 
}//end of Validator class

