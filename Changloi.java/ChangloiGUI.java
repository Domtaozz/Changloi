

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChangloiGUI extends JFrame{
    private JButton b1,b2,b3,b4 ;
    // b1=Americano  b2=Espresso  b3=Cappuccino  b4=Latte
    private JButton btBuy,btTake,btFill,btRemain,btBack,btEnterFill;
    private Icon icon1,icon2,icon3,icon4;
    private int selected=0; //เพื่อให้ง่ายต่อการระบุเมนูกาแฟที่เลือก
    private int money=0; //เงินในตู้ 
    private int es=0,hw=0,sm=0,mf=0; //วัตถุดิบ

    public ChangloiGUI(){
        super("Changloi Machine");

        //สร้าง Container
        Container container1 = getContentPane();
        container1.setLayout(new BorderLayout());
        
        //เซ็ตสีพื้นหลัง
        Color color=new Color(255, 239 ,213);
        JPanel panel1 = new JPanel();
        panel1.setBackground(color);
        JPanel panel2 = new JPanel();
        panel2.setBackground(color);
        JPanel panel3 = new JPanel();
        panel3.setBackground(color);
        
        //เซ็ต Font
        Font font1 = new Font("Courier",Font.PLAIN|Font.BOLD,20);
        Font font2 = new Font("Courier",Font.PLAIN,15);
        
        JLabel title = new JLabel("Changloi Machine");
        title.setFont(font1);
        
        //ข้อความที่แสดงบน GUI โดยไม่สามารถแก้ไขได้
        JTextArea text = new JTextArea("\nPlease select some drink. (200 ml/cup)");
        text.setFont(font2);
        text.setBackground(color);
        text.setEditable(false);
        
        //รูปกาแฟในแต่ละ JButton
        icon1 = new ImageIcon("C:\\Users\\User\\Desktop\\Americano.png");
        icon2 = new ImageIcon("C:\\Users\\User\\Desktop\\Espresso.png");  
        icon3 = new ImageIcon("C:\\Users\\User\\Desktop\\Cappuccino.png");
        icon4 = new ImageIcon("C:\\Users\\User\\Desktop\\Latte.png");
        
        //สร้างปุ่มของกาแฟแต่ละชนิด
        b1 = new JButton("Americano",icon1);
        b2 = new JButton("Espresso",icon2);
        b3 = new JButton("Cappuccino",icon3);
        b4 = new JButton("Latte",icon4);
        
        //เซ็ตขนาด JButton ของกาแฟ
        b1.setPreferredSize(new Dimension(140,100));
        b2.setPreferredSize(new Dimension(140,100));
        b3.setPreferredSize(new Dimension(140,100));
        b4.setPreferredSize(new Dimension(140,100));
        
        panel1.add(title);

        panel2.add(b1); //Americano
        panel2.add(b2); //Espresso
        panel2.add(b3); //Cappuccino
        panel2.add(b4); //Latte
        panel2.add(text);
        
        //สร้างปุ่มของแต่ละฟังก์ชั่นของ Changloi
        btBuy = new JButton("Buy"); btBuy.setVisible(false);
        btTake = new JButton("Take"); btTake.setVisible(true);
        btFill = new JButton("Fill"); btFill.setVisible(true);
        btRemain = new JButton("Remain"); btRemain.setVisible(true);
        btBack = new JButton("Back"); btBack.setVisible(false);
        btEnterFill = new JButton("EnterFill"); btEnterFill.setVisible(false);

        panel3.add(btBuy); panel3.add(btFill);
        panel3.add(btTake); panel3.add(btRemain);
        panel3.add(btEnterFill); panel3.add(btBack);
        
        //ตรงนี้ใช้สำหรับฟังก์ชัน Fill โดยเฉพาะ
        JLabel f1 = new JLabel("Write how many ml of EspressoShot you want to add:");
        JLabel f2 = new JLabel("Write how many ml of HotWater you want to add:");
        JLabel f3 = new JLabel("Write how many ml of Steamed Milk you want to add:");
        JLabel f4 = new JLabel("Write how many ml of Milk Foam you want to add:");
                
        // สร้าง TextField ไว้รับ Input ของฟังก์ชัน Fill
        JTextField t1 = new JTextField(20);
        JTextField t2 = new JTextField(20);
        JTextField t3 = new JTextField(20);
        JTextField t4 = new JTextField(20);
        
        //ปิดไว้ รอแสดงผลเมื่อ JButton ของ Fill ถูกใช้งาน
        f1.setVisible(false); t1.setVisible(false);
        f2.setVisible(false); t2.setVisible(false);
        f3.setVisible(false); t3.setVisible(false);
        f4.setVisible(false); t4.setVisible(false);

        panel2.add(f1); panel2.add(t1); 
        panel2.add(f2); panel2.add(t2); 
        panel2.add(f3); panel2.add(t3);  
        panel2.add(f4); panel2.add(t4); 
                
        //เซ็ตตำแหน่งของแต่ละ Panel ใน Container
        container1.add(panel1,BorderLayout.NORTH);
        container1.add(panel2,BorderLayout.CENTER);
        container1.add(panel3,BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,300);
        setVisible(true);
        setResizable(false);
        setLocation(500,300);
        
        //กรณีกดปุ่มของ Americano
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                selected = 1;
                //ปิด JButtun ที่ไม่ได้ใช้ หรือ เปิด JButton ที่ใช้ขึ้นมา
                b2.setVisible(false); btBuy.setVisible(true);
                b3.setVisible(false); btFill.setVisible(false);
                b4.setVisible(false); btRemain.setVisible(false);
                btTake.setVisible(false); btBack.setVisible(true);
                //วัตถุดิบไม่พอ
                if(es<60 || hw<140){
                    text.setText("Don't have enough resources."
                    +"\nCan't buy "+b1.getText());
                    btBuy.setVisible(false);
                }
                //วัตถุดิบพอ
                else{
                text.setText("Selecting "+b1.getText()+"\n"
                +"\nEspressoShot 60 ml [30%]"
                +"\nHotWater 140 ml [70%]"
                +"\n\n cash 50 ฿");
                es-=60; hw-=140;
                }
            }
        });
        //กรณีกดปุ่มของ Espresso
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                selected = 2;
                //ปิด JButtun ที่ไม่ได้ใช้ หรือ เปิด JButton ที่ใช้ขึ้นมา
                b1.setVisible(false); btBuy.setVisible(true);
                b3.setVisible(false); btFill.setVisible(false);
                b4.setVisible(false); btRemain.setVisible(false);
                btTake.setVisible(false);btBack.setVisible(true);
                //วัตถุดิบไม่พอ
                if(es<60){
                    text.setText("Don't have enough resources."
                    +"\nCan't buy "+b2.getText());
                    btBuy.setVisible(false);
                }
                //วัตถุดิบพอ
                else{
                text.setText("Selecting "+b1.getText()
                +"\nEspressoShot 60 ml [30%]"
                +"\n\ncash 50 ฿");
                es-=60; 
                }
            }
        });
        //กรณีกดปุ่มของ Cappuccino
        b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                selected = 3;
                //ปิด JButtun ที่ไม่ได้ใช้ หรือ เปิด JButton ที่ใช้ขึ้นมา
                b1.setVisible(false); btBuy.setVisible(true);
                b2.setVisible(false); btFill.setVisible(false);
                b4.setVisible(false); btRemain.setVisible(false);
                btTake.setVisible(false);btBack.setVisible(true);
                //วัตถุดิบไม่พอ
                if(es<60 || sm<70 || mf<70){
                    text.setText("Don't have enough resources."
                    +"\nCan't buy "+b3.getText());
                    btBuy.setVisible(false);
                }
                //วัตถุดิบพอ
                else{
                text.setText("Selecting "+b1.getText()+"\n"
                +"\nEspressoShot 60 ml [30%]"
                +"\nSteamed Milk 70 ml [35%]"
                +"\nMilk Foam 70 ml [35%]"
                +"\n\ncash 50 ฿");
                es-=60; sm-=70; mf-=70;
                }
            }
        });
        //กรณีกดปุ่มของ Latte
        b4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                selected = 4;
                //ปิด JButtun ที่ไม่ได้ใช้ หรือ เปิด JButton ที่ใช้ขึ้นมา
                b1.setVisible(false); btBuy.setVisible(true);
                b2.setVisible(false); btFill.setVisible(false);
                b3.setVisible(false); btRemain.setVisible(false);
                btTake.setVisible(false);btBack.setVisible(true);
                //วัตถุดิบไม่พอ
                if(es<60 || hw<110 || mf<30){
                    text.setText("Don't have enough resources."
                    +"\nCan't buy "+b4.getText());
                    btBuy.setVisible(false);
                }
                //วัตถุดิบพอ
                else{            
                text.setText("Selecting "+b1.getText()+"\n"
                +"\nEspressoShot 60 ml [30%]"
                +"\nSteamed Milk 110 ml [55%]"
                +"\nMilk Foam 30 ml [15%]"
                +"\n\ncash 50 ฿");
                es-=60; sm-=110; mf-=30;
                }
            }
        });   
        //กรณีกดปุ่ม Buy(สั่งซื้อกาแฟและรับเงินเข้าตู้)
        btBuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(selected==1){
                    text.setText("Bought " + b1.getText());
                    btBuy.setVisible(false);
                    money +=50;
                }else if(selected==2){
                    text.setText("Bought " + b2.getText());
                    btBuy.setVisible(false);
                    money +=50;
                }else if(selected==3){
                    text.setText("Bought " + b3.getText());
                    btBuy.setVisible(false);
                    money +=50;
                }else if(selected==4){
                    text.setText("Bought " + b4.getText());
                    btBuy.setVisible(false);
                    money +=50;
                }
            }
            
        });
        //กรณีกดปุ่ม Take(เอาเงินออกจากตู้)
        btTake.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                text.setText("\n\nTotal "+money+" ฿");
                b1.setVisible(false); btFill.setVisible(false);
                b2.setVisible(false); btTake.setVisible(false);
                b3.setVisible(false); btRemain.setVisible(false);
                b4.setVisible(false); btBack.setVisible(true);
                money = 0;
            }
        });
        //กรณีกดปุ่ม Fill(เติมวัตถุดิบในตู้)
        btFill.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                text.setText("");
                // เพิ่ม Label และ TextField เข้าไปใน JFrame
                f1.setVisible(true); t1.setVisible(true);
                f2.setVisible(true); t2.setVisible(true);
                f3.setVisible(true); t3.setVisible(true);
                f4.setVisible(true); t4.setVisible(true); 
                
                b1.setVisible(false); btFill.setVisible(false);
                b2.setVisible(false); btTake.setVisible(false);
                b3.setVisible(false); btRemain.setVisible(false);
                b4.setVisible(false); btBack.setVisible(true);
                btEnterFill.setVisible(true);
                //กรณีกดปุ่ม EnterFill(ยืนยันจำนวนของวัตถุดิบที่เติม)
                btEnterFill.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        es += Integer.parseInt(t1.getText());t1.setText("");
                        hw += Integer.parseInt(t2.getText());t2.setText("");
                        sm += Integer.parseInt(t3.getText());t3.setText("");
                        mf += Integer.parseInt(t4.getText());t4.setText("");
                        text.setText("\nChangloi Machine has been filled.\n"
                        +"\nEspressoShot have "+es+" ml"
                        +"\nHotWater have "+hw+" ml"
                        +"\nSteamed Milk have "+sm+" ml"
                        +"\nMilk Foam have "+mf+" ml");
        
                        f1.setVisible(false); t1.setVisible(false);
                        f2.setVisible(false); t2.setVisible(false);
                        f3.setVisible(false); t3.setVisible(false);
                        f4.setVisible(false); t4.setVisible(false);
                        btEnterFill.setVisible(false);
                    }
                });
            }
        });
        //กรณีกดปุ่ม Remain(เช็ควัตถุดิบที่อยู่ในตู้)
        btRemain.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                text.setText("\nRemaining\n"
                +"\nEspressoShot have "+es+" ml"
                +"\nHotWater have "+hw+" ml"
                +"\nSteamed Milk have "+sm+" ml"
                +"\nMilk Foam have "+mf+" ml");
                b1.setVisible(false); btFill.setVisible(false);
                b2.setVisible(false); btTake.setVisible(false);
                b3.setVisible(false); btRemain.setVisible(false);
                b4.setVisible(false); btBack.setVisible(true);
            }
        });
        //กรณีกดปุ่ม Back(จะย้อนกลับไปหน้ากดกาแฟที่หน้าแรก)
        btBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                selected = 0;
                b1.setVisible(true); b3.setVisible(true);
                b2.setVisible(true); b4.setVisible(true);
                btBuy.setVisible(false); btBack.setVisible(false);
                btFill.setVisible(true); btRemain.setVisible(true);
                btTake.setVisible(true); 
                btEnterFill.setVisible(false);
                f1.setVisible(false); t1.setVisible(false);
                f2.setVisible(false); t2.setVisible(false);
                f3.setVisible(false); t3.setVisible(false);
                f4.setVisible(false); t4.setVisible(false);
                text.setText("\nPlease select some drink (200 ml/cup)");
            }
        });
    }
    public static void main(String[] args){
        new ChangloiGUI();
    }
}