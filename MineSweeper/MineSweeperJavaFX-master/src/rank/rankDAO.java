package rank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class rankDAO {
    public static void main(String[] args) {
        // rank ���̺��� �̸�/���̵�/Ŭ����Ÿ��/���� �� �ֽ��ϴ�.
        insert("����", "�����", "1500��", "2010-08-21");
    }

    public static void insert(String name, String difficulty, String timestamp,
                              String timestamp1){
        Connection conn = null;
        PreparedStatement pstmt = null;
        //�ǽ��ǿ�   String jdbc_url = "jdbc:mysql://127.0.0.1:3306/test;
        String jdbc_url = "jdbc:mysql://127.0.0.1/test?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";

        try{
            // 1. ����̹� �ε�
            Class.forName("com.mysql.jdbc.Driver");

            // 2. �����ϱ�
            String url = "jdbc:mysql://localhost/root";
            conn = DriverManager.getConnection(jdbc_url, "test", "1234");


            // 3. SQL ���� �غ�
            // �߰��Ϸ��� �������� ���� ���޵� ���ڸ� ���� �������� �Ҵ�Ǵ� ���̴�.
            // �� � ���� ���޵��� �𸣹Ƿ� Select �� ���� �޸�
            // stmt = conn.createStatement(); �� �ۼ����� �ʰ�
            // pstmt = conn.prepareStatement(sql); �� �ۼ��Ͽ� �����͸� �߰��� ������ �˸��ϴ�.
            // ���� sql ���� ������ + �����ڷ� �� �ٷ� �ۼ��� �� ������ �������� �ʹ� �������� �ǹǷ�
            // �� ����� ���մϴ�.
            String sql = "INSERT INTO pet VALUES (?,?,?,?)";
            pstmt = conn.prepareStatement(sql);


            // 4. ������ binding
            pstmt.setString(1, name);
            pstmt.setString(2, difficulty);
            pstmt.setString(3, timestamp);
            pstmt.setString(4, timestamp1);


            // 5. ���� ���� �� ��� ó��
            // SELECT�� �޸� INSERT�� ��ȯ�Ǵ� �����͵��� �����Ƿ�
            // ResultSet ��ü�� �ʿ� ����, �ٷ� pstmt.executeUpdate()�޼��带 ȣ���ϸ� �˴ϴ�.
            // INSERT, UPDATE, DELETE ������ �̿� ���� �޼��带 ȣ���ϸ�
            // SELECT������ stmt.executeQuery(sql); �޼��带 ����߾����ϴ�.
            // @return     int - �� ���� row�� ������ ���ƴ����� ��ȯ
            int count = pstmt.executeUpdate();
            if( count == 0 ){
                System.out.println("������ �Է� ����");
            }
            else{
                System.out.println("������ �Է� ����");
            }
        }
        catch( ClassNotFoundException e){
            System.out.println("����̹� �ε� ����");
        }
        catch( SQLException e){
            System.out.println("���� " + e);
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