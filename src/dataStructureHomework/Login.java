package dataStructureHomework;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class Login extends MainUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String logintitle;
	protected JPanel loginc = new JPanel();
	protected static String Name = null;	//注册的名字
	protected static String UserType = null;//用户类型：“管理员”或“用户”
	
	private JTextField jtf;	//用户名框
	private JPasswordField jpf;	//密码框
	private ButtonGroup group;	//单选按钮组
	private JRadioButton jrb1;	//管理员选项
	private JRadioButton jrb2;	//用户选项
	private JButton jb1;//“确定”按钮
	private JButton jb2;//“取消”按钮
	private static File userf = new File("userdata.txt");		//链接至用户数据文件
	private static File managerf = new File("managerdata.txt");	//链接至管理员数据文件
	private File f;
	public Login()	{
		StringBuffer logstr = new StringBuffer(title);
		logstr.append("——登录");
		logintitle = logstr.toString();
		jf.setTitle(logintitle);
		//设置面板布局
		loginc.setLayout(null);
	    //面板第一行，输入用户名
		JLabel jl1 = new JLabel("用户名:");
		jl1.setBounds(150, 100, 50, 30);
		jtf = new JTextField(10);
		jtf.setBounds(200, 100, 200, 30);
	    //面板第二行，输入密码
		JLabel jl2 = new JLabel("密    码:");
		jl2.setBounds(150, 150, 50, 30);
		jpf = new JPasswordField(10);
		jpf.setBounds(200, 150, 200, 30);
	    //面板第三行，选择登录选项
	    jrb1 = new JRadioButton("管理员");
	    jrb1.setBackground(Color.gray);
	    jrb1.setBounds(220, 200, 80, 30);
	    jrb2 = new JRadioButton("用户");	  
	    jrb2.setBackground(Color.gray);
	    jrb2.setBounds(300, 200, 80, 30);
	    group = new ButtonGroup();
	    group.add(jrb1);
	    group.add(jrb2);
	    
	    //面板第四行,按钮行
		jb1 = new JButton("确定");
		jb1.addActionListener(new LoginJb1());
		jb1.setBounds(200, 250, 70, 40);
		jb2 = new JButton("取消");
		jb2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				jf.setTitle(title);
				jf.setContentPane(c);
			}
			
		});
		jb2.setBounds(300, 250, 70, 40);
	    loginc.add(jl1);
	    loginc.add(jtf);
	    loginc.add(jl2);
	    loginc.add(jpf);
	    loginc.add(jrb1);
	    loginc.add(jrb2);
	    loginc.add(jb1);
	    loginc.add(jb2);
	    loginc.setBackground(Color.gray);
		jf.setContentPane(loginc);
	    jf.getRootPane().setDefaultButton(jb1);
	    jf.setVisible(true);
	    jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	class LoginJb1 implements ActionListener{
		@SuppressWarnings("resource")
		public void actionPerformed(ActionEvent e){
			if(jrb1.isSelected() == jrb2.isSelected()){
				JOptionPane.showMessageDialog(jf, "请选择登录方式！","提示", JOptionPane.WARNING_MESSAGE);
				return ;
			}
			else if(jrb1.isSelected()){
				f = managerf;
				UserType = "管理员";
			}
			else{
				f = userf;
				UserType = "用户";
			}
			
			String str = null;
			String name = jtf.getText();
			String pw = new String(jpf.getPassword());
			boolean exited = false;
			try {
				FileReader in = new FileReader(f);
				BufferedReader br = new BufferedReader(in);
				while((str = br.readLine()) != null){
					if(str.equals("<name>")){
						str = br.readLine();
						if(str.equals(name)){
							exited = true;
							break;
						}
					}
				}
				if(exited){
					if((str = br.readLine()).equals(pw)){
						JOptionPane.showMessageDialog(jf, "登陆成功！", "提示", JOptionPane.WARNING_MESSAGE);
						Name = name;
						new MainProgram();
					}
					
					else {
						JOptionPane.showMessageDialog(jf, "密码错误！", "警告", JOptionPane.ERROR_MESSAGE);
						Reset();
						return ;
					}
				}
				else {
					JOptionPane.showMessageDialog(jf, "用户不存在!","警告", JOptionPane.ERROR_MESSAGE);
					Reset();
					return ;
				}
			br.close();
			in.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	void Reset(){
		jpf.setText("");
		jtf.setText("");
	}
	/*
	public static void main(String[] args){
		new Login();
	}
	*/
}
