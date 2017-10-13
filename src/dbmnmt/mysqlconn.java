package dbmnmt;
import java.sql.*;
public class mysqlconn {
 public static void main(String[] args){
  //驱动程序名
  String Driver = "com.mysql.jdbc.Driver";
  // 数据表url,student为表名
  String url = "jdbc:mysql://localhost:3306/author";
  // MySQL用户名
  String user = "root";
  // MySQL密码
  String password = "syw961018";

  // 开始连接数据库
  try {
  // 加载驱动程序
  Class.forName(Driver);
  // 连续数据库
  Connection conn = DriverManager.getConnection(url, user, password);
  if(!conn.isClosed())
   System.out.println("connecting to the database successfully!");
  // statement用来执行SQL语句
  Statement statement = conn.createStatement();
  // insert语句,info这里代指表明
//  String sql = "insert into info(id,name,address) value(5,'def','hebei')";
//  statement.execute(sql);
  // update语句
//  String sql = "update info set name='Sinon' where id=3";
//  statement.execute(sql);
  // select语句
  String sql = "select id,name,age,country from info";
  ResultSet rs = statement.executeQuery(sql);  
  // 输出student的所有信息
  while(rs.next()) {
   System.out.println(rs.getString("id") + "\t" + rs.getString("name") + "\t" + rs.getString("age") + "\t" + rs.getString("country")); 
   } 

  rs.close(); 
  conn.close();  
  } catch(ClassNotFoundException e) {  
   System.out.println("sorry, can't find the driver!");  
   e.printStackTrace();  
  } catch(SQLException e) {
   e.printStackTrace();
  } catch(Exception e){
   e.printStackTrace();
  }
 }  
}