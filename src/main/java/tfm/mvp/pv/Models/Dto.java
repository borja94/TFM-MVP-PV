package tfm.mvp.pv.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.dbcp2.BasicDataSource;

public abstract class Dto {

	protected BasicDataSource basicDataSource;

	public Dto() {
		basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
		basicDataSource.setUsername("APP");
		basicDataSource.setPassword(null);
		basicDataSource.setUrl("jdbc:derby://localhost:1527/TFM");
	}

	protected void CloseConnection(Connection connection) {

		try {
			connection.close();
		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		}
	}

	protected void ClosePreparedStatement(PreparedStatement preparedStatement) {

		try {
			preparedStatement.close();
		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		}
	}

	protected void CloseResultSet(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		}
	}
	protected void CloseStatement(Statement statement) {

		try {
			statement.close();
		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		}
	}

}
