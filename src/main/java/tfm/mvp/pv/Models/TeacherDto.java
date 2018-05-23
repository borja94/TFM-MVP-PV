/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfm.mvp.pv.Models;

import java.sql.Connection;
import java.sql.DriverManager;
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
public class TeacherDto extends Dto {

	public TeacherDto() {
		super();
	}

	public ResultSet Insert(Teacher teacher) {
		Connection conexion = null;
		ResultSet result = null;
		try {

			conexion = basicDataSource.getConnection();

			String sql = "INSERT INTO TEACHER (NAME,SURNAME) VALUES(?,?)";
			PreparedStatement sentencia1 = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia1.setString(1, teacher.getName());
			sentencia1.setString(2, teacher.getSurname());

			sentencia1.executeUpdate();
			result = sentencia1.getGeneratedKeys();
			int id = -1;
			if (result.next()) {
				id = result.getInt(1);
			}

			for (Subject subject : teacher.getSubjectCollection()) {
				InsertSubjectTeacher(id, subject.getId());
			}

			sentencia1.close();
		} catch (SQLException ex) {
			Logger.getLogger(TeacherDto.class.getName()).log(Level.SEVERE, null, ex);

		} finally {
			if (conexion != null)
				try {
					conexion.close();
				} catch (SQLException ex) {
					Logger.getLogger(TeacherDto.class.getName()).log(Level.SEVERE, null, ex);
				}
		}

		return result;
	}

	public void Update(Teacher teacher) {
		Connection conexion = null;

		try {

			conexion = basicDataSource.getConnection();

			String sql = "UPDATE TEACHER  SET NAME = ?, SURNAME = ? WHERE ID= ?";
			PreparedStatement sentencia1 = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, Statement.RETURN_GENERATED_KEYS);
			sentencia1.setString(1, teacher.getName());
			sentencia1.setString(2, teacher.getSurname());
			sentencia1.setInt(3, teacher.getId());

			RemoveAllTeacherSubjects(teacher.getId());
			for (Subject subject : teacher.getSubjectCollection()) {
				InsertSubjectTeacher(teacher.getId(), subject.getId());
			}

			sentencia1.executeUpdate();

			sentencia1.close();
		} catch (SQLException exx) {
		} finally {
			if (conexion != null)
				try {
					conexion.close();
				} catch (SQLException ex) {
					Logger.getLogger(TeacherDto.class.getName()).log(Level.SEVERE, null, ex);
				}
		}
	}

	public List<Teacher> GetAll() {

		List<Teacher> result = new ArrayList<Teacher>();
		Connection conexion = null;

		try {
			conexion = basicDataSource.getConnection();

			Statement sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = sentencia.executeQuery("SELECT * FROM TEACHER ");

			rs.afterLast();
			while (rs.previous()) {

				Teacher teacher = new Teacher(rs.getInt("ID"), rs.getString(2), rs.getString(3));
				String sql = "SELECT * FROM SUBJECT S " + "INNER JOIN TEACHER_SUBJECT TS " + "ON S.ID = TS.ID_SUBJECT "
						+ "WHERE TS.ID_TEACHER = ?";

				PreparedStatement sentenciaSubject = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				sentenciaSubject.setInt(1, rs.getInt("ID"));

				ResultSet rsSuebjects = sentenciaSubject.executeQuery();
				while (rsSuebjects.next()) {
					teacher.getSubjectCollection().add(new Subject(rsSuebjects.getInt("ID"),
							rsSuebjects.getString("TITLE"), rsSuebjects.getInt("COURSE")));
				}
				result.add(teacher);

			}
		} catch (SQLException ex) {
			Logger.getLogger(TeacherDto.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (conexion != null)
				try {
					conexion.close();
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					Logger.getLogger(TeacherDto.class.getName()).log(Level.SEVERE, null, ex);
				}
		}

		return result;
	}

	public Teacher Get(int id) {
		Connection conexion = null;
		Teacher teacher = null;
		try {
			conexion = basicDataSource.getConnection();

			String sql = "SELECT T.* , S.ID AS SUBJECT_ID , S.TITLE , S.COURSE FROM TEACHER T "
					+ "LEFT JOIN TEACHER_SUBJECT TS " + "ON T.ID = TS.ID_TEACHER " + "LEFT JOIN SUBJECT S "
					+ "ON TS.ID_SUBJECT = S.ID " + " WHERE T.ID = ?";
			PreparedStatement sentencia1 = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			sentencia1.setInt(1, id);

			ResultSet rs = sentencia1.executeQuery();
			teacher = new Teacher();
			List<Subject> subjectCollection = new ArrayList<>();
			if (rs.next()) {
				teacher = new Teacher(rs.getInt("ID"), rs.getString("NAME"), rs.getString("SURNAME"));

				do {
					if (rs.getString("TITLE") != null) {
						subjectCollection
								.add(new Subject(rs.getInt("SUBJECT_ID"), rs.getString("TITLE"), rs.getInt("COURSE")));
					}
				} while (rs.next());
			}
			teacher.setSubjectCollection(subjectCollection);

			sentencia1.close();
		} catch (SQLException ex) {
			Logger.getLogger(TeacherDto.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (conexion != null)
				try {
					conexion.close();
				} catch (SQLException ex) {
					Logger.getLogger(TeacherDto.class.getName()).log(Level.SEVERE, null, ex);
				}
		}

		return teacher;
	}

	public void Remove(int id) {
		Connection conexion = null;
		try {
			conexion = basicDataSource.getConnection();

			String sql = "DELETE FROM  TEACHER WHERE ID = ?";
			PreparedStatement sentencia1 = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, Statement.RETURN_GENERATED_KEYS);
			sentencia1.setInt(1, id);

			sentencia1.executeUpdate();
			sentencia1.close();

		} catch (SQLException ex) {
			Logger.getLogger(TeacherDto.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (conexion != null)
				try {
					conexion.close();
				} catch (SQLException ex) {
					Logger.getLogger(TeacherDto.class.getName()).log(Level.SEVERE, null, ex);
				}
		}

	}

	public void RemoveAllTeacherSubjects(int idTeacher) {
		Connection conexion = null;

		try {
			conexion = basicDataSource.getConnection();
			String sql = "DELETE FROM  TEACHER_SUBJECT WHERE ID_TEACHER = ?";
			PreparedStatement sentencia1 = conexion.prepareStatement(sql);
			sentencia1.setInt(1, idTeacher);

			sentencia1.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(TeacherDto.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (conexion != null)
				try {
					conexion.close();
				} catch (SQLException ex) {

					Logger.getLogger(TeacherDto.class.getName()).log(Level.SEVERE, null, ex);
				}
		}

	}

	public void InsertSubjectTeacher(int idTeacher, int idSubject) {

		Connection conexion = null;

		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			conexion = DriverManager.getConnection("jdbc:derby://localhost:1527/TFM", "APP", null);
			String sql = "INSERT INTO TEACHER_SUBJECT (ID_SUBJECT,ID_TEACHER) VALUES(?,?)";
			PreparedStatement sentencia1 = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia1.setInt(1, idSubject);
			sentencia1.setInt(2, idTeacher);

			sentencia1.executeUpdate();
		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(TeacherDto.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (conexion != null)
				try {
					conexion.close();
				} catch (SQLException ex) {
					Logger.getLogger(TeacherDto.class.getName()).log(Level.SEVERE, null, ex);
				}
		}

	}
}
