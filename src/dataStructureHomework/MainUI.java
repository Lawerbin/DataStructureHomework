package dataStructureHomework;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static String title = "科学文献查询软件";
	protected static JFrame jf = new JFrame(title);
	protected static JPanel c = new JPanel();
	protected static JButton backButton = new JButton("返回");	//返回按钮
	
	private JButton jb1 = new JButton("注册");	//注册
	private JButton jb2 = new JButton("登录");	//登录
	public MainUI(){
		jb1.setBounds(100, 150, 100, 50);
		jb1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Register();
			}
		});
		jb2.setBounds(400, 150, 100, 50);
		jb2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Login();
			}
		});
		
		//设置窗口位置
		Toolkit tk = this.getToolkit();
		Dimension dm = tk.getScreenSize();  
		int width = 600;  
		int height = 400;  
		jf.setSize(width, height);// 设置程序的大小  
		jf.setLocation((int) (dm.getWidth() - width) / 2,  
				                (int) (dm.getHeight() - height) / 2);// 显示在屏幕中央 
		//添加背景图片
		JLabel backPictrue = new JLabel(new ImageIcon("MainBackground.jpg"));
		backPictrue.setBounds(0, 0, jf.getWidth(), jf.getHeight());
		jf.getLayeredPane().add(backPictrue, new Integer(Integer.MIN_VALUE));
		jf.getLayeredPane().setLayout(null);
				
		c.setLayout(null);
		c.add(jb1);
		c.add(jb2);
		c.setOpaque(false);	//设置顶层面板透明
		
		jf.setContentPane(c);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args){
		new MainUI();
	}
}
