package view;

import controller.Blanks;
import view.controls.IRunnable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ConfigView implements IRunnable {
	@Override
	public void run() {

	}

//	private JDialog _frame;
//
//	public enum OpenMode { VIEW, SELECT }
//
//	private void getGUI(OpenMode mode) {
//		_frame = new JDialog(null, "Выберите шаблон для анализа файла", Dialog.ModalityType.APPLICATION_MODAL);
//		_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//		_frame.setMinimumSize(new Dimension(500, 400));
//		_frame.setPreferredSize(new Dimension(500, 400));
//		_frame.setMaximumSize(new Dimension(500, 400));
//		_frame.setLayout(new GridBagLayout());
//		_frame.addWindowListener(new WindowListener() {
//			@Override
//			public void windowOpened(WindowEvent e) {
//				_blanks.run();
//			}
//
//			@Override
//			public void windowClosing(WindowEvent e) {
//
//			}
//
//			@Override
//			public void windowClosed(WindowEvent e) {
//
//			}
//
//			@Override
//			public void windowIconified(WindowEvent e) {
//
//			}
//
//			@Override
//			public void windowDeiconified(WindowEvent e) {
//
//			}
//
//			@Override
//			public void windowActivated(WindowEvent e) {
//
//			}
//
//			@Override
//			public void windowDeactivated(WindowEvent e) {
//
//			}
//		});
//
//		_list = new JList();
//		_list.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
//	}
}
