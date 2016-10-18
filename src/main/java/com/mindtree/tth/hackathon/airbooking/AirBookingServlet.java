package com.mindtree.tth.hackathon.airbooking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AirBookingServlet extends HttpServlet {
	
	
	
	@Override
    protected void service(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws ServletException,
        IOException {
		
		String response = null;
        System.out.println("Method: "+httpRequest.getMethod());        
        
        try {
			if (httpRequest.getContentLength() > 0) {
				String availResXml = readAvailResponseXml();
				httpResponse.getOutputStream().write(availResXml.getBytes());
			}
			else {
				httpResponse.sendError(HttpServletResponse.SC_NO_CONTENT);
			}
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
        System.out.println("In service method..");
        System.out.println("-------------------------------------------");
    }
	
	
	/**
	 * The method reads the availability response xml.
	 * @param httpReq
	 * @param httpRes
	 */
	private String readAvailResponseXml() {
		InputStream is = null;
		BufferedReader buffRdr = null;
		StringBuilder availResBldr = new StringBuilder();
		try {			
			is = getClass().getClassLoader().getResourceAsStream("response/avail-res.xml");
			buffRdr = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line = null;
			while ((line = buffRdr.readLine()) != null) {
				availResBldr.append(line);
			}						
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		finally {
			if (buffRdr != null) {
				try {
					buffRdr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				}
				catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		return availResBldr.toString();
	}
	private String getString(InputStream is) {
		
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				System.out.println("Line is not null");
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("Data: "+sb);
		return sb.toString();

	}
}
