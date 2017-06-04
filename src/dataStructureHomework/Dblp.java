package dataStructureHomework;
import java.awt.*;
import javax.swing.*;

public class Dblp extends MainProgram{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame MainJf = null;	//科学文献主界面
	private JPanel UserPane = null;	//用户面板（查询文献面板）
	private JTabbedPane ManagerPane = null;//管理员模式界面
	
	public Dblp(){
		MainJf = new JFrame(programtitle);	//设置标题，programtitle在MainProgram.java中定义
		//设置窗口位置
		Toolkit tk = this.getToolkit();
		Dimension dm = tk.getScreenSize();  
		int width = 600;  
		int height = 700;  
		MainJf.setSize(width, height);// 设置程序的大小  
		MainJf.setLocation((int) (dm.getWidth() - width) / 2,  
				                (int) (dm.getHeight() - height) / 2);// 显示在屏幕中央 
		
		
		if(UserType.equals("用户")){		//用户模式	
			UserPane = new JPanel(null);
			
			MainJf.setContentPane(UserPane);
		}
		else{			//管理员模式
			ManagerPane = new JTabbedPane(JTabbedPane.LEFT);
			ManagerPane.add("查询文献", UserPane);
			JPanel DeletePane = new JPanel(null);
			ManagerPane.add("删除文献", DeletePane);
			JPanel AddPane = new JPanel(null);
			ManagerPane.add("新增文献", AddPane);
			JPanel AlterPane = new JPanel(null);
			ManagerPane.add("修改文献", AlterPane);
			
			
			MainJf.setContentPane(ManagerPane);
		}
		MainJf.setVisible(true);
		MainJf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
