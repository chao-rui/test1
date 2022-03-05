package test;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class hello {
    private JPanel root;
    private JButton ok;
    private JTextField add1;
    private JTextField add2;
    private JComboBox symbol;
    private JPanel tip;
    private JTextArea resultarea;
    private JButton clear;
    private JTextField answeredit;
    private JLabel answertext;
    private JLabel righttext;
    private JLabel wrongtext;
    private JLabel wrongnums;
    private JLabel rightnums;

    public hello() {
        ok.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String num1=add1.getText();
                String num2=add2.getText();
                String answer=answeredit.getText();
                String rightsum=rightnums.getText();
                String wrongsum=wrongnums.getText();
                int num1_int=Integer.parseInt(num1);
                int num2_int=Integer.parseInt(num2);
                int answer_int=Integer.parseInt(answer);
                int right_int=Integer.parseInt(rightsum);
                int wrong_int=Integer.parseInt(wrongsum);
                String Symbol=symbol.getSelectedItem().toString();
                int result=0,flag=1;
                switch (Symbol) {
                    case ("+"):
                        result = num1_int + num2_int;
                        break;
                    case ("-"):
                        result=num1_int-num2_int;
                        break;
                    case("*"):
                        result=num1_int*num2_int;
                        break;
                    case("/"):{
                        if(num2_int==0) {
                            JOptionPane.showMessageDialog(root,"除数不能为0");
                            flag=0;
                            break;
                        }
                        result=num1_int/num2_int;
                        break;}
                    default:break;
                }
                if(flag==1){
                    if(answer_int==result) {
                        JOptionPane.showMessageDialog(root, "回答正确");
                        right_int++;
                        rightsum=Integer.toString(right_int);
                        rightnums.setText(rightsum);
                    }
                    else {
                        JOptionPane.showMessageDialog(root, "答案错误，正确答案为" + result);
                        wrong_int++;
                        wrongsum = Integer.toString(wrong_int);
                        wrongnums.setText(wrongsum);
                    }
//                    JOptionPane.showMessageDialog(root,"正确答案："+num1+Symbol+num2+"="+result);
                   resultarea.append(num1+Symbol+num2+"="+result+"\n");
                }
            }
        });

        clear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                resultarea.setText("");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("hello");
        frame.setContentPane(new hello().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);
        frame.setVisible(true);
    }
}
