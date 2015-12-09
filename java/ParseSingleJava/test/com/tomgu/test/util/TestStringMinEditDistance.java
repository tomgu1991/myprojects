package com.tomgu.test.util;

import com.tomgu.util.string.StringUtil;

public class TestStringMinEditDistance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String [] data = { "kitten", "sitting", "saturday", "sunday", "rosettacode", "raisethysword" };
        for (int i = 0; i < data.length; i += 2)
            System.out.println("distance(" + data[i] + ", " + data[i+1] + ") = " 
            		+ StringUtil.minDistance(data[i], data[i+1]));
	}

}
