package dataStructureHomework;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;


public class Register extends MainUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6933767527344751505L;

	protected static String registertitle;
	protected static JPanel registerc = new JPanel();

	private JButton jb1 = null;	//功能：进入管理员注册
	private JButton jb2 = null;	//功能：进入用户注册
	private static File userf = new File("userdata.txt");		//链接至用户数据文件
	private static File managerf = new File("managerdata.txt");	//链接至管理员数据文件
	public Register(){
		StringBuffer regstr  = new StringBuffer(title);
		regstr.append("——注册");
		registertitle = regstr.toString();
		jf.setTitle(registertitle);
		
		//设置按钮功能
		jb1 = new JButton("管理员");
		jb1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				new subRegist("管理员",managerf);
			}
			
		});
		jb1.setBounds(100, 150, 100, 50);
		jb2 = new JButton("普通用户");
		jb2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				new subRegist("用户",userf);
			}
		});
		jb2.setBounds(400, 150, 100, 50);
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				jf.setTitle(title);
				jf.setContentPane(c);
			}
			
		});
		backButton.setBounds(0, 0, 60, 40);
		//将按钮添加进布局容器
		registerc.setLayout(null);
		registerc.add(jb1);
		registerc.add(jb2);
		registerc.add(backButton);
		jf.setContentPane(registerc);
		registerc.setOpaque(false);
		
		jf.setVisible(true);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	/*
	public static void main(String[] arg0){
		new Register();
	}
	*/
}

@SuppressWarnings("serial")
class subRegist extends Register{
	private static File subf;		//链接至用户数据文件
	private static String subregtitle;
	private JPanel userjp = new JPanel();
	private JPanel[] subjp = new JPanel[7];	//姓名行
	private JLabel[] subjlb = new JLabel[6];
	private JPasswordField[] subjpf = new JPasswordField[2];
	private JTextField[] subjtf = new JTextField[4];	
	private JButton subjb1 = new JButton("确定");		//确定 按钮
	private JButton subjb2 = new JButton("重置");		//重置 按钮
	private JButton subjb3 = new JButton("取消");		//取消 按钮
	
	private void setjpftext(){			//清空两个密码框内容，注册失败以及按下重置按钮时调用
		subjpf[1].setText("");
		subjpf[0].setText("");
	}
	
	public subRegist(String strtitle,File f){
		if(strtitle.equals("管理员"))
			subjlb[0] = new JLabel("管理员名字 : ");
		StringBuffer subregstr = new StringBuffer(registertitle);
		subregstr.append(strtitle);
		subregtitle = subregstr.toString();
		jf.setTitle(subregtitle);
		subf = f;
		jf.setContentPane(userjp);
		
	    
	    //设置按钮功能
		subjb1.addActionListener(new jb1ActionListener());
		subjb2.addActionListener(new jb2ActionListener());
		subjb3.addActionListener(new ActionListener(){	//取消按钮，即返回上一界面
			public void actionPerformed(ActionEvent e){
				jf.setTitle(registertitle);
				jf.setContentPane(registerc);
			}
		});
		//设置布局
		userjp.setLayout(new GridLayout(7,1));	//总面板4行1列（每行一个子面板）
		for(int i = 0; i < 7; i++){
			subjp[i] = new JPanel();
		}
		subjlb[0] = new JLabel("用      户     名:");		//对应subjtf[0],初始化标签
		subjlb[1] = new JLabel("密码(6-16位):");		//对应密码框subjpf[0]
		subjlb[2] = new JLabel("确  认  密  码 :");		//对应密码框subjpf[1]
		subjlb[3] = new JLabel("手  机  号  码 :");		//对应subjtf[1]
		subjlb[4] = new JLabel("QQ   号    码 : ");	//对应subjtf[2]
		subjlb[5] = new JLabel("邮  箱  地  址 :");		//对应subjtf[3]
		subjpf[0] = new JPasswordField(15);		//初始化密码框
		subjpf[1] = new JPasswordField(15);
		for(int i = 0; i < 4; i++){				//初始化密码框外的文本框
			subjtf[i] = new JTextField(15);
		}
		for(int i = 0; i < 6; i++){				//将标签添加进面板中
			subjp[i].add(subjlb[i]);
		}
		subjp[0].add(subjtf[0]);				//添加各文本框
		subjp[1].add(subjpf[0]);
		subjp[2].add(subjpf[1]);
		for(int i = 3; i < 6; i++){
			subjp[i].add(subjtf[i-2]);
		}
		subjp[6].add(subjb1);		//按钮行
		subjp[6].add(subjb2);
		subjp[6].add(subjb3);
		for(int i = 0; i < 7; i++){	//将子面板加入至总面板
			subjp[i].setOpaque(false);
			userjp.add(subjp[i]);
		}
	    userjp.setOpaque(true);
		jf.getLayeredPane().setLayout(null);
		jf.setVisible(true);
		jf.getRootPane().setDefaultButton(subjb1);		//设置回车对应的按钮为“确定按钮”
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	class jb1ActionListener extends JDialog implements ActionListener{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String name = subjtf[0].getText();
			String pw1 = new String(subjpf[0].getPassword());
			String pw2 = new String(subjpf[1].getPassword());

			//判断用户名是否为空
			if(name.length()==0){
				JOptionPane.showMessageDialog(jf, "用户名不能为空！", "错误！", JOptionPane.ERROR_MESSAGE);
				return ;
			}
			
			//判断两次密码是否一致
			if(!pw1.equals(pw2)){	
				JOptionPane.showMessageDialog(jf, "两次密码不对，请重新输入！", "错误！", JOptionPane.ERROR_MESSAGE);
				setjpftext();
				return ;
			}
			
			//判断密码是否是6-16位
			if(pw1.length()<6 || pw1.length()>16){
				JOptionPane.showMessageDialog(jf, "密码长度不符合规定，请重新输入！", "错误", JOptionPane.ERROR_MESSAGE);
				setjpftext();
				return ;
			}
			
			//判断用户名是否已存在
			try {
				FileReader in = new FileReader(subf);
				@SuppressWarnings("resource")
				BufferedReader bufr = new BufferedReader(in);
				String s = null;
				while((s = bufr.readLine()) != null){
					if(s.equals("<name>")){
						s = bufr.readLine();
						if(s.equals(name)){		
							JOptionPane.showMessageDialog(jf, "该用户名已存在，请重新输入！", "错误！", JOptionPane.ERROR_MESSAGE);
							subjtf[0].setText("");
							setjpftext();
							return ;
						}
					}
				}
				in.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			//判断手机号码位数是否正确
			if(subjtf[1].getText().length()!=11){
				JOptionPane.showMessageDialog(jf, "请输入正确的手机号码！", "错误！", JOptionPane.ERROR_MESSAGE);
				return ;
			}
			//判断QQ号码位数是否正确
			if(subjtf[2].getText().length()<5 || subjtf[2].getText().length()>10){
				JOptionPane.showMessageDialog(jf, "请输入正确的QQ号码！", "错误！", JOptionPane.ERROR_MESSAGE);
				return ;
			}
			//判断邮箱地址格式是否正确
			String email = subjtf[3].getText();
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
			//注册成功
			try {
				FileWriter out = new FileWriter(subf, true);
				BufferedWriter bufw = new BufferedWriter(out);
				bufw.write("<name>");
				bufw.newLine();
				bufw.write(name);	
				bufw.newLine();
				bufw.write(pw1);
				bufw.newLine();
				bufw.write(subjtf[1].getText());
				bufw.newLine();
				bufw.write(subjtf[2].getText());
				bufw.newLine();
				bufw.write(subjtf[3].getText());
				bufw.newLine();
				bufw.close();
				out.close();
				
				JOptionPane.showMessageDialog(jf, "注册成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
				jf.setTitle(title);
				jf.setContentPane(c);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	class jb2ActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			subjtf[0].setText("");
			setjpftext();
			subjtf[1].setText("");
			subjtf[2].setText("");
			subjtf[3].setText("");
			return ;
		}
	}	
}