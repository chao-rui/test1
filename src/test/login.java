package test;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class login {

    private JPanel root;
    private JLabel username;
    private JTextField useredit;
    private JLabel keyword;
    private JPasswordField keywordedit;
    private JButton login;
    private JButton register;

    public login() {
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String name=useredit.getText();
                String key=keywordedit.getText();
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                }catch (ClassNotFoundException cne){
                    cne.printStackTrace();
                }
                String url="jdbc:mysql://localhost:3306/user?user=root&password=root123";
                try {
                    Connection conn= DriverManager.getConnection(url);
                    Statement stmt=conn.createStatement();
                    String sql="SELECT keyword FROM userlist WHERE username="+name;
                    ResultSet ret=stmt.executeQuery(sql);
                    String rightkey="";
                    while(ret.next()) {
                        rightkey=ret.getString(1);
                        System.out.println("rightkey="+rightkey);
                        System.out.println("key="+key);
                    }
  //                  String rightkey=ret.getString(1);
                    if(rightkey.equals(key)) JOptionPane.showMessageDialog(root,"用户名:"+name+"\n登录成功");
                    else JOptionPane.showMessageDialog(root,"密码错误，请重试");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String name=useredit.getText();
                String key=keywordedit.getText();
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                }catch (ClassNotFoundException cne){
                    cne.printStackTrace();
                }
                String url="jdbc:mysql://localhost:3306/user?user=root&password=root123";
                Connection conn= null;
                try {
                    conn = DriverManager.getConnection(url);
                    Statement stmt=conn.createStatement();
                    String sql="INSERT INTO userlist(username, keyword) VALUES ("+name+","+key+");";
                    int connt=stmt.executeUpdate(sql);
                    if(connt!=0)
                    JOptionPane.showMessageDialog(root,"注册成功");
                    else
                        JOptionPane.showMessageDialog(root,"注册失败，请重试");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("login");
        frame.setContentPane(new login().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

