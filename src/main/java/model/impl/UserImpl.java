package model.impl;

import constants.SQLConstants;
import model.beans.User;
import model.db.ConnectionManager;
import model.interfaces.IUserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImpl implements IUserDAO {
    public User getUser(String login, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        User user = null;

        try {
            conn = ConnectionManager.createConnection();
            pst = conn.prepareStatement(SQLConstants.SELECT_USER);
            pst.setString(1, login);
            pst.setString(2, password);
            rs = pst.executeQuery();

            while (rs.next()){
                int id = rs.getInt(SQLConstants.ID_LABEL);
                String login_user = rs.getString(SQLConstants.LOGIN_LABEL);
                String firstName = rs.getString(SQLConstants.FNAME_LABEL);
                String secondName = rs.getString(SQLConstants.SNAME_LABEL);
                String email = rs.getString(SQLConstants.EMAIL_LABEL);

                user = new User(id, login_user, firstName, secondName, email);
            }
        } finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(pst);
            ConnectionManager.closeConnection();
        }

        return user;
    }

    public boolean isAddUser(User user, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        boolean result = false;

        try {
            conn = ConnectionManager.createConnection();
            pst = conn.prepareStatement(SQLConstants.INSERT_USER);
            pst.setString(1, user.getLogin());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getFirstName());
            pst.setString(4, user.getSecondName());
            pst.setString(5, password);

            synchronized (UserImpl.class){
                if(!isFoundLogin(user.getLogin())){
                    pst.executeUpdate();
                    result = true;
                }
            }
        } finally {
            ConnectionManager.closeStatement(pst);
            ConnectionManager.closeConnection();
        }

        return result;
    }

    private boolean isFoundLogin(String login) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            cn = ConnectionManager.createConnection();
            pst = cn.prepareStatement(SQLConstants.FOUND_LOGIN);
            pst.setString(1, login);
            rs = pst.executeQuery();
            result = rs.next();
        } finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(pst);
        }
        return result;
    }
}
