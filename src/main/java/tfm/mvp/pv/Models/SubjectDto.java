package tfm.mvp.pv.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author borja
 */
public class SubjectDto extends Dto{

	public SubjectDto() {
		super();
	}
	public ResultSet Insert(Subject subject) {
		Connection conexion = null;
		ResultSet aux = null;
		try {
			conexion = basicDataSource.getConnection();

			String sql = "INSERT INTO SUBJECT (TITLE,COURSE) VALUES(?,?)";
			PreparedStatement sentencia1 = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, Statement.RETURN_GENERATED_KEYS);
			sentencia1.setString(1, subject.getTitle());
			sentencia1.setInt(2, subject.getCourse());

			sentencia1.executeUpdate();
			aux = sentencia1.getGeneratedKeys();

			sentencia1.close();
		} catch (SQLException ex) {
			Logger.getLogger(SubjectDto.class.getName()).log(Level.SEVERE, null, ex);

		} finally {
			if (conexion != null)
				try {
					conexion.close();
				} catch (SQLException ex) {
					Logger.getLogger(SubjectDto.class.getName()).log(Level.SEVERE, null, ex);

				}
		}

		return aux;
	}

	public List<Subject> GetAll() {

		List<Subject> result = new ArrayList<>();
		Connection conexion = null;
		try {
			conexion = basicDataSource.getConnection();

			Statement sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = sentencia.executeQuery("SELECT * FROM SUBJECT ");

			while (rs.next()) {
				result.add(new Subject(rs.getInt("ID"), rs.getString("TITLE"), rs.getInt("COURSE")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(SubjectDto.class.getName()).log(Level.SEVERE, null, ex);

		} finally {
			if (conexion != null)
				try {
					conexion.close();
				} catch (SQLException ex) {
					Logger.getLogger(SubjectDto.class.getName()).log(Level.SEVERE, null, ex);

				}
		}

		return result;
	}

	public Subject Get(int id) {
		Connection conexion = null;
		Subject subject = null;
		try {
			conexion = basicDataSource.getConnection();
			
			String sql = "SELECT * FROM SUBJECT WHERE ID = ?";
			PreparedStatement sentencia1 = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			sentencia1.setInt(1, id);

			ResultSet rs = sentencia1.executeQuery();
			subject = new Subject();
			if (rs.next()) {
				subject = new Subject(rs.getInt("ID"), rs.getString("TITLE"), rs.getInt("COURSE"));
			}
			sentencia1.close();
		} catch (SQLException ex) {
			Logger.getLogger(SubjectDto.class.getName()).log(Level.SEVERE, null, ex);

		} finally {
			if (conexion != null)
				try {
					conexion.close();
				} catch (SQLException ex) {
					Logger.getLogger(SubjectDto.class.getName()).log(Level.SEVERE, null, ex);

				}
		}
		return subject;
	}

	public void Remove(int id) {
		Connection conexion = null;

		try {
			conexion = basicDataSource.getConnection();

			String sql = "DELETE FROM  SUBJECT WHERE ID = ?";
			PreparedStatement sentencia1 = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, Statement.RETURN_GENERATED_KEYS);
			sentencia1.setInt(1, id);

			sentencia1.executeUpdate();
			sentencia1.close();
		} catch (SQLException ex) {
			Logger.getLogger(SubjectDto.class.getName()).log(Level.SEVERE, null, ex);

		} finally {
			if (conexion != null)
				try {
					conexion.close();
				} catch (SQLException ex) {
					Logger.getLogger(SubjectDto.class.getName()).log(Level.SEVERE, null, ex);

				}
		}
	}

	public void Update(Subject subject) {
		Connection conexion = null;

		try {
			conexion = basicDataSource.getConnection();

			String sql = "UPDATE SUBJECT  SET TITLE = ?, COURSE = ? WHERE ID= ?";
			PreparedStatement sentencia1 = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, Statement.RETURN_GENERATED_KEYS);
			sentencia1.setString(1, subject.getTitle());
			sentencia1.setInt(2, subject.getCourse());
			sentencia1.setInt(3, subject.getId());

			sentencia1.executeUpdate();

			sentencia1.close();
		} catch (SQLException ex) {
			Logger.getLogger(SubjectDto.class.getName()).log(Level.SEVERE, null, ex);

		} finally {
			if (conexion != null)
				try {
					conexion.close();
				} catch (SQLException ex) {
					Logger.getLogger(SubjectDto.class.getName()).log(Level.SEVERE, null, ex);

				}
		}
	}

}
