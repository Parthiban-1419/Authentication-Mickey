package com.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.adventnet.ds.query.Column;
import com.adventnet.ds.query.Criteria;
import com.adventnet.ds.query.Join;
import com.adventnet.ds.query.QueryConstants;
import com.adventnet.ds.query.SelectQuery;
import com.adventnet.ds.query.SelectQueryImpl;
import com.adventnet.ds.query.Table;
import com.adventnet.mfw.bean.BeanUtil;
import com.adventnet.persistence.DataAccess;
import com.adventnet.persistence.DataAccessException;
import com.adventnet.persistence.DataObject;
import com.adventnet.persistence.Persistence;
import com.adventnet.persistence.Row;

import com.student.User;

@WebServlet("/get-marks")
public class GetMarks extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		
		try {	
			User user = User.getObj();
    		
			json.put("user", user.getUser());
			json.put("marks", getData("MarkDetails"));
			json.put("students", getData("StudentDetail"));
			user.setUser("");
			
			out.print(json);
			
		} catch (Exception e) {
			out.print(e);
		}
	}
	
	JSONArray getData(String table) throws DataAccessException{
		JSONArray jArray = new JSONArray();
		Row r = null;
		DataObject d = null;
		Iterator i = null;
		
		d = DataAccess.get(table, (Criteria)null);
		i = d.getRows(table);
		
		while(i.hasNext()) {
			r = (Row) i.next();
			jArray.put(r.getAsJSON());
		}
		
		return jArray;
	}
}
