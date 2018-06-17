package tfm.mvp.pv;

import javax.swing.UnsupportedLookAndFeelException;

import java.awt.EventQueue;
import java.util.logging.*;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import tfm.mvp.pv.views.MenuFrame;

public class App {

	public static void main(String[] args) {

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
			Logger.getLogger(MenuFrame.class.getName()).log(Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(()->new MenuFrame().setVisible(true));
	}
}
