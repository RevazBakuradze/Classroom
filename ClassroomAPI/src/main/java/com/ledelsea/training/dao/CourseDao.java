package com.ledelsea.training.dao;

import com.ledelsea.training.data.Course;

import java.sql.*;

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
}
