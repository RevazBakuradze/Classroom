package com.ledelsea.training.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.ledelsea.training.data.Student;

public class StudentDao {

	public boolean create(Student student) {
		String sql = "INSERT INTO STUDENT(`FIRST_NM`,`LAST_NM`,`ADDRESS`,`CITY`,`STATE`,`CCT_CREATETIME_DTE`,`CCT_UPDATETIME_DTE`) VALUES(?,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionUtility.getInstance().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getFirstName());
			pstmt.setString(2, student.getLastName());
			pstmt.setString(3, student.getAddress());
			pstmt.setString(4, student.getCity());
			pstmt.setString(5, student.getState());
			pstmt.setDate(6, new Date(Calendar.getInstance().getTime().getTime()));
			pstmt.setDate(7, new Date(Calendar.getInstance().getTime().getTime()));
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtility.getInstance().close(pstmt);
			ConnectionUtility.getInstance().close(con);
		}
		
		return true;
	}
	
	public List<Student> findAll() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Student> studentList = new ArrayList<Student>();
		try {
			con = ConnectionUtility.getInstance().getConnection();
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("select ID, FIRST_NM,LAST_NM,ADDRESS,CITY,STATE from STUDENT");

			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getLong("ID"));
				student.setFirstName(rs.getString("FIRST_NM"));
				student.setLastName(rs.getString("LAST_NM"));
				student.setAddress(rs.getString("ADDRESS"));
				student.setCity(rs.getString("CITY"));
				student.setState(rs.getString("STATE"));
				studentList.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtility.getInstance().close(rs);
			ConnectionUtility.getInstance().close(stmt);
			ConnectionUtility.getInstance().close(con);
		}
		return studentList;
	}

}