package dataStructureHomework;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class PartImformation extends MainProgram{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private File UserFile = new File("userdata.txt");
	private File TempFile = new File("TempFile.txt");
	private String ImformationTitle;	//标题
	private JPanel ImformationC;	//此类的容器面板
	private JPanel ShowInput;//输入信息面板
	private JPanel ShowImformation;//显示结果面板
	private JLabel Jl;
	private JTextField Jtf = new JTextField(15);	//输入框
	@SuppressWarnings("rawtypes")
	private JComboBox JCB; // 下拉框，选择输入信息的种类
	private JButton ImfBackButton = new JButton("返回");
	private JButton CheckEnter = new JButton("确定");	//查找确认
	private JButton ChangeButton = new JButton("修改");	//变为可修改状态
	private JButton ChangeEnter = new JButton("确认");	//修改确认
	private String[] CheckedUser = new String[5];
	


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PartImformation(){
		// 设置标题
		StringBuffer imfstr = new StringBuffer(programtitle);
		imfstr.append("——用户信息");
		ImformationTitle = imfstr.toString();
		jf.setTitle(ImformationTitle);
		// 设置主面板
		ImformationC = new JPanel(null);
		ImfBackButton.setBounds(0, 0, 60, 40);
		ImfBackButton.setText("返回");
		ImfBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.setTitle(programtitle);
				jf.setContentPane(programc);
			}
		});
		
		ShowImformation = new JPanel(null);	//输出信息子面板
		ShowImformation.setBackground(Color.GRAY);
		
		if(UserType.equals("管理员")){
			ShowInput = new JPanel(null);	//输入信息子面板
			ShowInput.setBounds(0, 40, 600, 110);
			ShowInput.setBackground(Color.gray);
			
			//输入信息面板
			Jl = new JLabel("根据所选查找用户信息：");
			Jl.setBounds(50, 10, 150, 30);
			String str[] = { "用户名", "手机号码", "邮箱地址" };
			JCB = new JComboBox(str);
			JCB.setSelectedItem(str[0]);
			JCB.setBounds(200, 10, 80, 30);
			Jtf.setBounds(300, 10, 200, 30);
			CheckEnter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { // 将用户信息存储到cur字符串中
					String cur = null;
					boolean isexited = false;
					BufferedReader br = null;
					try {
						br = new BufferedReader(new FileReader(UserFile));
						if (JCB.getSelectedItem().equals("用户名")) {
							try {
								cur = br.readLine();
								while (cur != null) {
									if (cur.equals("<name>")) {
										cur = br.readLine();
										if (cur.equals(Jtf.getText())) {
											isexited = true;
											CheckedUser[0] = cur;
											for (int i = 1; i < 5; i++) {
												CheckedUser[i] = br.readLine();
											}
											break;
										}
									}
									else cur = br.readLine();
								}
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						} else if (JCB.getSelectedItem().equals("手机号码")) {
							try {
								cur = br.readLine();
								while (cur != null) {
									if (cur.equals("<name>")) {
										cur = br.readLine();
										for (int i = 0; i < 2; i++) { // 手机号码为索引2号位置
											CheckedUser[i] = cur;
											cur = br.readLine();
										}
		
										if (cur.equals(Jtf.getText())) {
											isexited = true;
											CheckedUser[2] = cur;
											for (int i = 3; i < 5; i++) {
												cur = br.readLine();
												CheckedUser[i] = cur;
											}
											break;
										}
									}
									else cur = br.readLine();
								}
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						} else {
							try {
								cur = br.readLine();
								while (cur != null) {
									if (cur.equals("<name>")) {
										cur = br.readLine();
										for (int i = 0; i < 4; i++) {		//邮箱号在索引4号位置
											CheckedUser[i] = cur;
											cur = br.readLine();
										}
										if (cur.equals(Jtf.getText())) {
											isexited = true;
											CheckedUser[4] = cur;
											break;
										}
										else cur = br.readLine();
									}
								}
							} catch (IOException e1) {
								e1.printStackTrace();
							}
		
						}
						try {
							br.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						if (isexited)
							showImformation(); // 显示用户信息
						else{
							ImformationC.remove(ShowImformation);
							jf.setContentPane(ImformationC);
							JOptionPane.showMessageDialog(jf, "用户不存在", "错误！", JOptionPane.ERROR_MESSAGE);
						}
					} catch (FileNotFoundException e2) {
						e2.printStackTrace();
					}
					finally{
						try {
							br.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			});
			CheckEnter.setBounds(250, 60, 60, 40);
	
			ShowInput.add(Jl); // 提示标签
			ShowInput.add(JCB); // 下拉框
			ShowInput.add(Jtf); // 文本框
			ShowInput.add(CheckEnter); // 确定按钮
			ShowInput.setBackground(Color.gray);//设置背景色
			
			ShowImformation.setBounds(0, 150, 600, 250);
			ImformationC.add(ShowInput);
		}
		else{		//用户模式
			BufferedReader in;
			try {
				in = new BufferedReader(new FileReader(UserFile));
			String cur = null;
			while((cur = in.readLine()) != null){	//读取用户信息到CheckedUser中
				if(cur.equals("<name>")){
					if((cur = in.readLine()).equals(Name)){
						for(int i = 0; i < 5; i++){
							CheckedUser[i] = cur;
							cur = in.readLine();
						}
						break;
					}
				}
			}
			}  catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}		
			ShowImformation.setBounds(0, 50, 600, 250);
			showImformation();
		}
		ImformationC.add(ImfBackButton);
		ImformationC.setBackground(Color.gray);
		jf.setContentPane(ImformationC);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	//输出信息面板
	void showImformation() {
		String[] messageName = new String[] { "用户名：", "密码：", "手机号码：", "QQ号码：", "邮箱地址：" };
		JLabel[] showMessage = new JLabel[5];
		JTextField[] messageText = new JTextField[5];
		//从CheckedUser[]中读取用户信息
		for (int i = 0; i < 5; i++) {
			showMessage[i] = new JLabel(messageName[i]);
			showMessage[i].setHorizontalAlignment(SwingConstants.RIGHT); // 标签文本右对齐
			showMessage[i].setBounds(150, i * 30, 100, 25);
			messageText[i] = new JTextField(CheckedUser[i]);
			messageText[i].setBounds(250, i * 30, 200, 25);
			messageText[i].setEditable(false);
			ChangeEnter.setVisible(false);
		}
		//将信息添加进面板中
		for (int i = 0; i < 5; i++) {
			ShowImformation.add(showMessage[i]);
			ShowImformation.add(messageText[i]);
		}
		//将信息设置为可修改
		ChangeButton.setBounds(220, 150, 60, 40);
		ChangeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				for(int i = 1; i < 5; i++){
					messageText[i].setEditable(true);
				}
				ChangeEnter.setVisible(true);
				ShowImformation.add(ChangeEnter);
				jf.setContentPane(ImformationC);
			}
		});
		//确认信息修改
		ChangeEnter.setBounds(300, 150, 60, 40);
		ChangeEnter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//判断密码是否符合规定
				if(messageText[1].getText().length()<6 || messageText[1].getText().length()>16){
					JOptionPane.showMessageDialog(jf, "密码长度应在6-16位之间，请重新输入！", "错误", JOptionPane.ERROR_MESSAGE);
					return ;
				}
				//判断手机号码位数是否正确
				if(messageText[2].getText().length()!=11){
					JOptionPane.showMessageDialog(jf, "请输入正确的手机号码！", "错误！", JOptionPane.ERROR_MESSAGE);
					return ;
				}
				//判断QQ号码位数是否正确
				if(messageText[3].getText().length()<5 || messageText[3].getText().length()>10){
					JOptionPane.showMessageDialog(jf, "请输入正确的QQ号码！", "错误！", JOptionPane.ERROR_MESSAGE);
					return ;
				}
				//判断邮箱地址格式是否正确
				String email = messageText[4].getText();
				boolean j = false;
				if(email.length()>7)
					if(email.endsWith(".com"))
						for(int i = 0; i<email.length()-4; i++)
							if(email.charAt(i) == '@')
								j = true;
				if(!j){
					JOptionPane.showMessageDialog(jf, "请输入正确的邮箱地址", "错误！", JOptionPane.ERROR_MESSAGE);
					return ;
				}
				else{	//修改信息无误
					BufferedReader in = null;
					BufferedWriter out = null;
					try {
						in = new BufferedReader(new FileReader(UserFile));
						out = new BufferedWriter(new FileWriter(TempFile));	//将原文件暂存进TempFile里
						String Line = null;
						while((Line = in.readLine()) != null){
							if(Line.equals("<name>")){
								out.write(Line);
								out.newLine();
								Line = in.readLine();	//名字行
								if(Line.equals(CheckedUser[0])){
									for(int i = 0; i < 5; i++){
										CheckedUser[i] = messageText[i].getText();
										out.write(CheckedUser[i]);
										out.newLine();
										Line = in.readLine();//与原文件读取的内容保持一致
									}
								}else{
									for(int i = 0; i < 5; i++){
										out.write(Line);
										out.newLine();
										Line = in.readLine();
									}
								}
							}
						}
						out.flush();
						in.close();
						out.close();
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					} 
					try {
						in = new BufferedReader(new FileReader(TempFile));
						out = new BufferedWriter(new FileWriter(UserFile));	//将TempFile写会原文件
						String Line = null;
						while((Line = in.readLine()) != null){
							out.write(Line);
							out.newLine();
						}
						out.flush();
						in.close();
						out.close();
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					for(int i = 0; i < 5; i++)
						messageText[i].setEditable(false);
					ChangeEnter.setVisible(false);
					jf.setContentPane(ImformationC);
					JOptionPane.showMessageDialog(jf, "修改成功", "提示！", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		ShowImformation.add(ChangeButton);
		ImformationC.add(ShowImformation);
		jf.setContentPane(ImformationC);
	}
	
}

