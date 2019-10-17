package rank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class rankDAO {
    public static void main(String[] args) {
        // rank 테이블에는 이름/난이도/클리어타임 이 있습니다.
        insert("범찬", "어려움", "1500초");
    }

    public static void insert(String name, String difficulty, String time_A){
        Connection conn = null;
        PreparedStatement pstmt = null;
        //실습실용   String jdbc_url = "jdbc:mysql://127.0.0.1:3306/test;
        String jdbc_url = "jdbc:mysql://127.0.0.1/test?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";

        try{
            // 1. 드라이버 로딩
            Class.forName("com.mysql.jdbc.Driver");

            // 2. 연결하기
            conn = DriverManager.getConnection(jdbc_url, "root", "1234");


            // 3. SQL 쿼리 준비
            // 추가하려는 데이터의 값은 전달된 인자를 통해 동적으로 할당되는 값이다.
            // 즉 어떤 값이 전달될지 모르므로 Select 할 때와 달리
            // stmt = conn.createStatement(); 를 작성하지 않고
            // pstmt = conn.prepareStatement(sql); 로 작성하여 데이터를 추가할 것임을 알립니다.
            String sql = "INSERT INTO pet VALUES (?,?,?)";
            pstmt = conn.prepareStatement(sql);


            // 4. 데이터 binding
            pstmt.setString(1, name);
            pstmt.setString(2, difficulty);
            pstmt.setString(3, time_A);


            // 5. 쿼리 실행 및 결과 처리
            // SELECT와 달리 INSERT는 반환되는 데이터들이 없으므로
            // ResultSet 객체가 필요 없고, 바로 pstmt.executeUpdate()메서드를 호출하면 됩니다.
            // INSERT, UPDATE, DELETE 쿼리는 이와 같이 메서드를 호출하며
            // SELECT에서는 stmt.executeQuery(sql); 메서드를 사용했었습니다.
            // @return     int - 몇 개의 row가 영향을 미쳤는지를 반환
            int count = pstmt.executeUpdate();
            if( count == 0 ){
                System.out.println("데이터 입력 실패");
            }
            else{
                System.out.println("데이터 입력 성공");
            }
            
            ///// 게임에 저장된 정보를 DB 파일에 저장하기
            public void rankRec() {
                rankDao rdao = new RankDao();
                
                rdao.fileWrite(arrlist);
            }


        }
        catch( ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch( SQLException e){
            System.out.println("에러 " + e);
        }
        finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();
                }
                if( pstmt != null && !pstmt.isClosed()){
                    pstmt.close();
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
        }
    }
}