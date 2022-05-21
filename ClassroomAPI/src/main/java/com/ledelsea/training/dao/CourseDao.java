package com.ledelsea.training.dao;

import com.ledelsea.training.data.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDao {

    public boolean create(Course course) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ConnectionUtility.getInstance().getConnection();

            // TODO Maybe do other way with ?,? and set values later
            pstmt = con.prepareStatement("INSERT INTO course (`ID`, `TITLE`) VALUES (`" + course.getId() + "`, `" + course.getTitle() + "`)");

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionUtility.getInstance().close(pstmt);
            ConnectionUtility.getInstance().close(con);
        }

        return true;
    }

    //findall
    public List<Course> findAll() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<Course> courses = new ArrayList<>();

        try {
            con = ConnectionUtility.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `course`");
            
            while (rs.next()) {

                Course course = new Course();
                long id =  Long.parseLong(rs.getString("ID"));
                course.setId(id);
                course.setTitle(rs.getString("TITLE"));

                courses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionUtility.getInstance().close(rs);
            ConnectionUtility.getInstance().close(stmt);
            ConnectionUtility.getInstance().close(con);
        }

        return courses;
    }
    //delete
    //update
}
