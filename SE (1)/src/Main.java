import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class Main {
	public static void main(String args[])
	{
		JFrame JF = new JFrame();
		JPanel JP = new JPanel();
		SP tSP = new SP();
		JLabel TSL = new JLabel("请选择文本", SwingConstants.CENTER);
		JTextField TS = new JTextField();
		JButton submit = new JButton("开始");
		JButton exit = new JButton("返回");
		tSP.inl();
		JF.setSize(480, 720);
		JP.setLayout(new GridLayout(3, 1));
		JP.add(TSL);
		JP.add(TS);
		JP.add(submit);
		JF.add(JP);
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("SW.txt"));
			String temp;
			while ((temp = br.readLine()) != null)
			{
				DS.SW.add(temp);
			}
			br.close();
		}
		catch (Exception e)
		{
			
		}
		submit.addActionListener((ActionEvent AE) -> {
			try
			{
				BufferedReader br = new BufferedReader(new FileReader(TS.getText()));
				String temp;
				tSP.rN = 0;
				tSP.cN = 0;
				while ((temp = br.readLine()) != null)
				{
					String t[] = temp.split(" ");
					String p = "";
					for (String s : t)
					{
						while (true)
						{
							if (s.length() == 0)
							{
								break;
							}
							else
							{
								if (s.charAt(0) < 'A' || (s.charAt(0) > 'Z' && s.charAt(0) < 'a') || s.charAt(0) > 'z')
								{
									s = s.substring(1);
								}
								else
								{
									break;
								}
							}
						}
						if (s.length() > 0 && !DS.SW.contains(s.toLowerCase()))
						{
							tSP.cN += s.length();
							if (DS.wm.containsKey(s))
							{
								DS.wm.put(s, DS.wm.get(s) + 1);
							}
							else
							{
								DS.wm.put(s, 1);
							}
							if (p.length() > 0 && !DS.SW.contains(p.toLowerCase()))
							{
								String ts = p + " " + s;
								if (DS.pm.containsKey(ts))
								{
									DS.pm.put(ts, DS.pm.get(ts) + 1);
								}
								else
								{
									DS.pm.put(ts, 1);
								}
							}
						}
						p = s;
					}
					tSP.rN++;
				}
				br.close();
				JP.setVisible(false);
				JF.remove(JP);
				JF.add(tSP.P);
				tSP.P.setVisible(true);
				tSP.tP.add(exit);
				tSP.sI();
				TSL.setText("请选择文本");
			}
			catch (Exception E)
			{
				TSL.setText("查找有误");
			}
		});
		exit.addActionListener((ActionEvent AE) -> {
			tSP.P.setVisible(false);
			JF.remove(tSP.P);
			tSP.tP.remove(exit);
			JP.setVisible(true);
			JF.add(JP);
		});
		
		JF.setVisible(true);
	}

}
