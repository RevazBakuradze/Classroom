package com.ledelsea.training.dao;

import com.ledelsea.training.data.Course;
import com.ledelsea.training.data.Student;

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


    // TODO Check if entry exists
    public boolean delete(long id) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionUtility.getInstance().getConnection();
            stmt = con.createStatement();
//			stmt.executeUpdate("DELETE from student [WHERE ID = studentId]");
            // TODO Should I put `` around Query names?
            rs = stmt.executeQuery("DELETE from student [WHERE ID = `" + id + "`]");


            while (rs.next()) {
                // TODO Switch to log4j from sout
                System.out.println("Deleting a Course by id:" + id);
                System.out.println("Title = " + rs.getString("TITLE"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionUtility.getInstance().close(rs);
            ConnectionUtility.getInstance().close(stmt);
            ConnectionUtility.getInstance().close(con);
        }

        return true;
    }

    // TODO check if course exists
    public boolean update(long id, Course course) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            String sqlStatement = "UPDATE course" +
                    "SET " +
                    "TITLE = `" + course.getTitle() + "`, " +
                    "[WHERE ID = `" + course.getId() + "`]";

            con = ConnectionUtility.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sqlStatement);

            while (rs.next()) {
                // TODO Switch to log4j from sout
                System.out.println("Updated Course with id: " + id);
                System.out.println("Title = " + rs.getString("TITLE"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionUtility.getInstance().close(con);
            ConnectionUtility.getInstance().close(stmt);
            ConnectionUtility.getInstance().close(rs);
        }

        return true;
    }
}
