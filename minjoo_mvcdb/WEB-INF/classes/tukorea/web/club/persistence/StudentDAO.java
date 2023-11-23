package tukorea.web.club.persistence;

import java.sql.*;
import java.util.*;
import tukorea.web.club.domain.StudentVO;

public class StudentDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url =
	"jdbc:mysql://localhost/jspdb?allowPublicKeyRetrieval"
	+ "=true&useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";

	void connect() {
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "jspbook", "passwd");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void disconnect() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean add(StudentVO vo) {
		connect();
		String sql = "insert into student values (?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPasswd());
			pstmt.setString(3, vo.getUsername());
			pstmt.setString(4, vo.getSnum());
			pstmt.setString(5, vo.getDepart());
			pstmt.setString(6, vo.getMobile());
			pstmt.setString(7, vo.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}

	public boolean delete(String studentId) {
        connect();

        String sql = "DELETE FROM student WHERE id = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, studentId);
            int rowCount = pstmt.executeUpdate();

            if (rowCount > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return false;
    }
	
	public boolean update(StudentVO vo) {
	    connect();
	    String sql = "UPDATE student SET passwd=?, username=?, snum=?, depart=?, mobile=?, email=? WHERE id=?";
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, vo.getPasswd());
	        pstmt.setString(2, vo.getUsername());
	        pstmt.setString(3, vo.getSnum());
	        pstmt.setString(4, vo.getDepart());
	        pstmt.setString(5, vo.getMobile());
	        pstmt.setString(6, vo.getEmail());
	        pstmt.setString(7, vo.getId());
	        int rowCount = pstmt.executeUpdate();

	        if (rowCount > 0) {
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        disconnect();
	    }
	    return false;
	}
	
	public StudentVO read(String studentId) {
	    connect();
	    StudentVO student = null;
	    String sql = "SELECT * FROM student WHERE id = ?";
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, studentId);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            student = new StudentVO();
	            student.setId(rs.getString("id"));
	            student.setPasswd(rs.getString("passwd"));
	            student.setUsername(rs.getString("username"));
	            student.setSnum(rs.getString("snum"));
	            student.setDepart(rs.getString("depart"));
	            student.setMobile(rs.getString("mobile"));
	            student.setEmail(rs.getString("email"));
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        disconnect();
	    }
	    return student;
	}
	
	public ArrayList<StudentVO> getStudentList() {
		connect();
		ArrayList<StudentVO> studentlist = new ArrayList<StudentVO>();
		String sql = "select * from student";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				StudentVO vo = new StudentVO();
				vo.setId(rs.getString("id"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setUsername(rs.getString("username"));
				vo.setSnum(rs.getString("snum"));
				vo.setDepart(rs.getString("depart"));
				vo.setMobile(rs.getString("mobile"));
				vo.setEmail(rs.getString("email"));
				studentlist.add(vo);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return studentlist;
	}
}
