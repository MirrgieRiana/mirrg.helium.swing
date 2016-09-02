package mirrg.helium.swing.nitrogen.wrapper.artifacts;

import java.awt.Window;
import java.util.Hashtable;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import mirrg.helium.swing.nitrogen.wrapper.artifacts.logging.HLog;

public class MenuLookAndFeel extends JMenu
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6466801304390680234L;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private Hashtable<String, JRadioButtonMenuItem> hashClassNameToRadio = new Hashtable<>();

	public MenuLookAndFeel()
	{
		super("LookAndFeel(L)");

		setMnemonic('L');

		for (LookAndFeelInfo lookAndFeelInfo : UIManager
			.getInstalledLookAndFeels()) {
			JRadioButtonMenuItem radio = createRadio(lookAndFeelInfo.getName(),
				lookAndFeelInfo.getClassName());
			add(radio);
			hashClassNameToRadio.put(lookAndFeelInfo.getClassName(), radio);
		}

		addActionListener(e -> {
			buttonGroup.setSelected(
				hashClassNameToRadio.get(
					UIManager.getLookAndFeel().getClass()).getModel(),
				true);
		});
	}

	protected JRadioButtonMenuItem createRadio(String name, String className)
	{
		JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem();
		radioButtonMenuItem.setText(name);
		radioButtonMenuItem.setActionCommand(className);
		radioButtonMenuItem.setHideActionText(true);
		radioButtonMenuItem.addActionListener(e -> {
			ButtonModel m = buttonGroup.getSelection();
			try {
				setLookAndFeel(m.getActionCommand());
			} catch (Exception e1) {
				HLog.processException(e1);
			}
		});
		buttonGroup.add(radioButtonMenuItem);
		return radioButtonMenuItem;
	}

	public void setLookAndFeel(String lookAndFeel)
		throws ClassNotFoundException, InstantiationException,
		IllegalAccessException, UnsupportedLookAndFeelException
	{
		UIManager.setLookAndFeel(lookAndFeel);
		updateLookAndFeel();
	}

	private void updateLookAndFeel()
	{
		for (Window window : Window.getWindows()) {
			SwingUtilities.updateComponentTreeUI(window);
		}
	}

}
