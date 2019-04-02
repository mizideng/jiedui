import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

public class SP {
	public JPanel P = new JPanel();
	public JPanel tP = new JPanel();
	public JPanel screen = new JPanel()
	{	
		private static final long serialVersionUID = 1L;

		public void paint(Graphics g)
		{
			super.paint(g);
			if (tFlag)
			{
				g.drawString("(" + String.valueOf(rT) + "ms)", 350, 20);
			}
			if (sFlag)
			{
				if (fFlag)
				{
					for (int i = 0; i < t.length; i++)
					{
						g.drawString(t[i] + ":" + String.valueOf(F.get(i)), 20, 20 * i + 50);
					}
					for (int i = 0; i < t.length; i++)
					{
						g.drawRect(10 * i + 200, 320 - 270 * F.get(i) / sum, 10, 270 * F.get(i) / sum);
					}
				}
				else
				{
					for (int i = 0; i < t.length; i++)
					{
						g.drawString(String.valueOf(i + 1) + " " + t[i] + ":" + String.valueOf(F.get(i)), 200, 20 * i + 50);
					}
				}
			}
			else
			{
				g.drawString(info, 200, 200);
			}
		}
	};
	private Choice c = new Choice();
	private JTextField input = new JTextField();
	private JButton submit = new JButton("�ύ");
	private JButton wExp = new JButton("��������ͳ�ƽ��");
	private JButton pExp = new JButton("��������ͳ�ƽ��");

	public int rN;
	public long cN;
	private String info;
	private boolean sFlag = false;
	private boolean fFlag = false;
	private boolean tFlag = false;
	private int sum = 0;
	private String[] t;
	private ArrayList<Integer> F;
	private long rT = 0;
	
	public void sI()
	{
		info = "ͳ�Ƴɹ�" + "(" + String.valueOf(rN) + "�У� " + String.valueOf(cN) + "�ַ�)";
		sFlag = false;
		fFlag = false;
		tFlag = false;
		screen.repaint();
	}
	
	public void inl()
	{
		c.add("ָ��");
		c.add("��Ƶ");
		c.addItemListener((ItemEvent IE) -> {
			if (c.getSelectedItem().equals("ָ��"))
			{
				info = "�����뵥��";
				sFlag = false;
				fFlag = false;
				tFlag = false;
				screen.repaint();
			}
			else
			{
				info = "���������";
				sFlag = false;
				fFlag = false;
				tFlag = false;
				screen.repaint();
			}
		});
		submit.addActionListener((ActionEvent AE) -> {
			if (c.getSelectedItem().equals("ָ��"))
			{
				long S = System.currentTimeMillis();
				String temp = input.getText();
				t = temp.split(" ");
				F = new ArrayList<Integer>();
				sum = 0;
				for (String s : t)
				{
					F.add(DS.wm.get(s));
					sum += DS.wm.get(s);
				}
				long E = System.currentTimeMillis();
				rT = E - S;
				sFlag = true;
				fFlag = true;
				tFlag = true;
				screen.repaint();
			}
			else
			{
				int b = Integer.parseInt(input.getText());
				if (b <= DS.wm.size())
				{
					long S = System.currentTimeMillis();
					Set<String> temp = new HashSet<String>();
					t = new String[b];
					F = new ArrayList<Integer>();
					for (int i = 0; i < b; i++)
					{
						int max = -1;
						String mk = null;
						for (String k : DS.wm.keySet())
						{
							if (DS.wm.get(k) > max && !temp.contains(k))
							{
								mk = k;
								max = DS.wm.get(k);
							}
						}
						t[i] = mk;
						F.add(DS.wm.get(mk));
						temp.add(mk);
					}
					long E = System.currentTimeMillis();
					rT = E - S;
					sFlag = true;
					fFlag = false;
					tFlag = true;
					screen.repaint();
				}
				else
				{
					info = "�������ʸ���";
					sFlag = false;
					fFlag = false;
					tFlag = false;
					screen.repaint();
				}
			}
		});
		wExp.addActionListener((ActionEvent AE) -> {
			try
			{
				long S = System.currentTimeMillis();
				BufferedWriter bw = new BufferedWriter(new FileWriter("WR.txt"));
				String[] w = new String[DS.wm.size()];
				int c = 0;
				for (String s : DS.wm.keySet())
				{
					w[c] = s;
					c++;
				}
				Arrays.sort(w);
				for (int i = 0; i < w.length; i++)
				{
					bw.write(w[i] + ":" + String.valueOf(DS.wm.get(w[i])) + "\r\n");
				}
				long E = System.currentTimeMillis();
				rT = E - S;
				bw.flush();
				bw.close();
				info = "�������";
				sFlag = false;
				fFlag = false;
				tFlag = true;
				screen.repaint();
			}
			catch (IOException e)
			{
				
			}
		});
		pExp.addActionListener((ActionEvent AE) -> {
			try
			{
				long S = System.currentTimeMillis();
				BufferedWriter bw = new BufferedWriter(new FileWriter("PR.txt"));
				int L;
				if (DS.pm.size() > 10)
				{
					L = 10;
				}
				else
				{
					L = DS.pm.size();
				}
				Set<String> temp = new HashSet<String>();
				for (int i = 0; i < L; i++)
				{
					int max = 0;
					String mm = "";
					for (String str : DS.pm.keySet())
					{
						if (DS.pm.get(str) > max && !temp.contains(str))
						{
							max = DS.pm.get(str);
							mm = str;
						}
					}
					bw.write(mm + ":" + String.valueOf(DS.pm.get(mm)) + "\r\n");
					temp.add(mm);
				}
				long E = System.currentTimeMillis();
				rT = E - S;
				bw.flush();
				bw.close();
				info = "�������";
				sFlag = false;
				fFlag = false;
				tFlag = true;
				screen.repaint();
			}
			catch (IOException e)
			{
				
			}
		});
		tP.setLayout(new GridLayout(6, 1));
		P.setLayout(new GridLayout(2, 1));
		P.add(screen);
		tP.add(c);
		tP.add(input);
		tP.add(submit);
		tP.add(wExp);
		tP.add(pExp);
		P.add(tP);
	}

}
