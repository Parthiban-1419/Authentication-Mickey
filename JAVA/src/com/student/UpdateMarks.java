package com.student;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.adventnet.ds.query.Column;
import com.adventnet.ds.query.Criteria;
import com.adventnet.ds.query.QueryConstants;
import com.adventnet.persistence.DataAccess;
import com.adventnet.persistence.DataObject;
import com.adventnet.persistence.Row;
import com.adventnet.persistence.WritableDataObject;


@WebServlet("/update-marks")
public class UpdateMarks extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		
		Row r = null;
		Criteria criteria = null;
		DataObject d = null;
		
		try {
			
			JSONArray jArray = (JSONArray) parser.parse(request.getParameter("jArray"));
			
			
			for(Object j : jArray) {
				
				json = (JSONObject)j;
				criteria = new Criteria(new Column("MarkDetails", "REG_NUMBER"), json.get("regNumber"), QueryConstants.EQUAL);
				d = DataAccess.get("MarkDetails", criteria);
				
				if(d.getRows("MarkDetails").hasNext()) {
					r = d.getRow("MarkDetails");
					r.set("REG_NUMBER", json.get("regNumber"));
					r.set("TAMIL", json.get("tamil"));
					r.set("ENGLISH", json.get("english"));
					r.set("MATHS", json.get("maths"));
					r.set("SCIENCE", json.get("science"));
					r.set("SOCIAL", json.get("social"));
					r.set("TOTAL", json.get("total"));
					r.set("PERCENTAGE", json.get("percentage"));
					
					d.updateRow(r);
					DataAccess.update(d);
				}
				else {
					r = new Row ("MarkDetails");
					r.set("REG_NUMBER", json.get("regNumber"));
					r.set("TAMIL", json.get("tamil"));
					r.set("ENGLISH", json.get("english"));
					r.set("MATHS", json.get("maths"));
					r.set("SCIENCE", json.get("science"));
					r.set("SOCIAL", json.get("social"));
					r.set("TOTAL", json.get("total"));
					r.set("PERCENTAGE", json.get("percentage"));
					
					d = new WritableDataObject();
					d.addRow(r);

					DataAccess.add(d);
				}
			}
			out.print(true);
		} catch (Exception e) {
			out.print(e);
		}
		
	}

}
