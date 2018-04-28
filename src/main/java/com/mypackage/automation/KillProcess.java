package com.mypackage.automation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KillProcess {

	public static void killProcess() throws IOException {
		String line = null;
		try {
		Process process = Runtime.getRuntime().exec("ps -ax");
		BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		r.readLine();
		 while((line=r.readLine())!=null) {

			 String[] p = r.readLine().split(" ");
			 if(p.length>12 && p[12]!=null)
				if(p[12].equals("/Users/krithickasivarajan/Desktop/HackerRankGithubAutomation/drivers/chromedriver"))
					Runtime.getRuntime().exec("kill "+p[1]);
		 }
		}
		catch(Exception e)
		{
			System.out.println(line);
			e.printStackTrace();
		}

	}

	public static void main(String args[]) throws IOException {
		killProcess();
	}

}
