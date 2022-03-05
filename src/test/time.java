package test;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class time {
    private JPanel root;
    private JLabel year;
    private JLabel symbol1;
    private JLabel month;
    private JLabel symbol2;
    private JLabel day;
    private JLabel symbol3;
    private JLabel hour;
    private JLabel symbol4;
    private JLabel minute;
    private JLabel symbol5;
    private JLabel sevond;
    private JLabel symbol6;

    public time() {
        Thread T = new Thread(){
            @Override
            public void run() {
                int sec=Integer.parseInt(sevond.getText());
                int min=Integer.parseInt(minute.getText());
                int h=Integer.parseInt(hour.getText());
                while(Integer.parseInt(year.getText())!=999)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    sec++;
                    if (sec >= 60) {min++;sec=0;}
                    if (min >= 60) {h++;min=0;}
                    sevond.setText(Integer.toString(sec));
                    minute.setText(Integer.toString(min));
                    hour.setText(Integer.toString(h));
                }
            }
        };
        root.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                LocalDate now=LocalDate.now();
                LocalDate loveday=LocalDate.of(2020,7,7);
                Period p=Period.between(loveday,now);
                String Y=Integer.toString(p.getYears());
                String M=Integer.toString(p.getMonths());
                String D=Integer.toString(p.getDays());
                year.setText(Y);
                month.setText(M);
                day.setText(D);
                Calendar Now=Calendar.getInstance();
                String H;
                if(Now.get(Calendar.MINUTE)<40) {
                    H = Integer.toString((Now.get(Calendar.HOUR_OF_DAY) + 6) % 24);
                }
                else{
                    H = Integer.toString(((Now.get(Calendar.HOUR_OF_DAY) + 6) % 24)+1);
                }
                String Min=Integer.toString((Now.get(Calendar.MINUTE)+20)%60);
                String S=Integer.toString(Now.get(Calendar.SECOND));
                hour.setText(H);
                minute.setText(Min);
                sevond.setText(S);
                T.start();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("time");
        frame.setContentPane(new time().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(400,80);
    }
}

