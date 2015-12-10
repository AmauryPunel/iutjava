package edu.iut.gui.widget.agenda;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import edu.iut.app.ApplicationSession;
import edu.iut.app.ExamEvent;
import edu.iut.gui.frames.SchedulerFrame;

public class AjoutEvenementPanel {
	static JSpinner  yearsSpinner;  
	static JComboBox monthComboBox ;
	static JComboBox daysOfWeekComboBox ;
	static JSpinner hourSpinner;
	static JSpinner minuteSpinner;
	
	public static ExamEvent creerEvenement(Date date){
        return new ExamEvent(date,null,null,null,null);
	}

	public static void showEventCreate(){
		Calendar calendar = Calendar.getInstance();
        SpinnerNumberModel dateModel = new SpinnerNumberModel(calendar.get(Calendar.YEAR),
        												calendar.get(Calendar.YEAR)-5,
        												calendar.get(Calendar.YEAR)+5,
        												1);
        yearsSpinner = new JSpinner(dateModel);
        yearsSpinner.setEditor(new JSpinner.NumberEditor(yearsSpinner, "#"));
        monthComboBox = new JComboBox(ApplicationSession.instance().getMonths());
        daysOfWeekComboBox = new JComboBox(ApplicationSession.instance().getDays());
        SpinnerNumberModel hourModel = new SpinnerNumberModel(12,0,23,1);
        SpinnerNumberModel minuteModel = new SpinnerNumberModel(0,0,45,15);
        hourSpinner = new JSpinner(hourModel);
        minuteSpinner = new JSpinner(minuteModel);
        JFrame frame = new JFrame("Ajout d'un évenement");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try 
        {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
           e.printStackTrace();
        }
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(true);
        panel.add(yearsSpinner);
        panel.add(monthComboBox);
        panel.add(daysOfWeekComboBox);
        panel.add(hourSpinner);
        panel.add(minuteSpinner);
        JButton button = new JButton("Valider");
        button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedYear=(int)yearsSpinner.getValue();
				int selectedMonth=monthComboBox.getSelectedIndex()+1;
				int selectedDay=daysOfWeekComboBox.getSelectedIndex()+1;
				int selectedHour=(int)hourSpinner.getValue();
				int selectedMinute=(int)minuteSpinner.getValue();
				SimpleDateFormat ft= new SimpleDateFormat("d M yyyy HH:m");
				String date=""+selectedDay+" "+selectedMonth+" "+selectedYear+" "+selectedHour+":"+selectedMinute;
				try{
					ExamEvent babouche = new ExamEvent(ft.parse(date),null,null,null,null);
					SchedulerFrame.listEvent.add(babouche);
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				((JFrame)((JButton)e.getSource()).getParent().getParent().getParent().getParent().getParent()).dispose();
			}
		});
        panel.add(button);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
	}
}

