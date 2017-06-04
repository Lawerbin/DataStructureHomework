package dataStructureHomework;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class MainProgram extends Login {

	protected static String programtitle;
	protected JPanel programc;
	private JButton jb1; // 信息模块
	private JButton jb2; // 文献模块

	public MainProgram() { // Name是注册的名字，UserType是“管理员”或者“用户”
		// 设置标题
		StringBuffer prostr = new StringBuffer(title);
		prostr.append("，");
		prostr.append(Name);
		prostr.append(UserType);
		programtitle = prostr.toString();
		jf.setTitle(programtitle);
		// 主界面
		programc = new JPanel(null);
		jb1 = new JButton();
		if(UserType.equals("管理员"))
			jb1.setText("用户信息");
		else jb1.setText("个人信息");
		jb1.setBounds(100, 150, 100, 50);
		jb1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new PartImformation();
			}
		});
		jb2 = new JButton("科学文献");
		jb2.setBounds(400, 150, 100, 50);
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Dblp();
			}
		});
		backButton.setText("登出");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jf.setTitle(title);
				jf.setContentPane(c);
			}
		});
		backButton.setBounds(0, 0, 60, 40);
		programc.add(jb1);
		programc.add(jb2);
		programc.add(backButton);
		programc.setBackground(Color.gray);
		jf.setContentPane(programc);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
