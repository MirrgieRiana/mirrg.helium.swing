package mirrg.helium.swing.nitrogen.wrapper.util;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Field;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

public class HSwing
{

	public static void setWindowsLookAndFeel()
	{
		try {
			UIManager
				.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	public static boolean tryAddWebLookAndFeel()
	{
		try {
			Class<?> clazz = Class.forName("com.alee.laf.WebLookAndFeel");
			if (clazz != null) {

				Field[] fields = clazz.getFields();

				Pattern pattern = Pattern.compile("global[\\w\\d_]*Font");
				Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);

				for (Field field : fields) {
					if (pattern.matcher(field.getName()).matches()) {
						if (field.getType().isInstance(font)) {
							try {
								field.set(null, font);
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
						}
					}
				}

				UIManager.installLookAndFeel(new LookAndFeelInfo(clazz
					.getSimpleName(), clazz.getName()));

			}

			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	public static void hookRightClick(Component component,
		Predicate<MouseEvent> listener)
	{
		component.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e)
			{

			}

			@Override
			public void mousePressed(MouseEvent e)
			{

			}

			@Override
			public void mouseExited(MouseEvent e)
			{

			}

			@Override
			public void mouseEntered(MouseEvent e)
			{

			}

			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getButton() == MouseEvent.BUTTON3) {
					if (listener.test(e)) e.consume();
				}
			}

		});
	}

	public static void hookPopup(Component component,
		Predicate<MouseEvent> listener)
	{
		component.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e)
			{
				if (e.isPopupTrigger()) {
					if (listener.test(e)) e.consume();
				}
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				if (e.isPopupTrigger()) {
					if (listener.test(e)) e.consume();
				}
			}

			@Override
			public void mouseExited(MouseEvent e)
			{

			}

			@Override
			public void mouseEntered(MouseEvent e)
			{

			}

			@Override
			public void mouseClicked(MouseEvent e)
			{

			}

		});
	}

	public static void hookChange(JTextComponent textComponent,
		Consumer<DocumentEvent> listener)
	{
		textComponent.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e)
			{
				listener.accept(e);
			}

			@Override
			public void removeUpdate(DocumentEvent e)
			{
				listener.accept(e);
			}

			@Override
			public void changedUpdate(DocumentEvent e)
			{
				listener.accept(e);
			}

		});
	}

	public static void hookDoubleClick(Component component,
		Consumer<MouseEvent> listener)
	{
		component.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 2) {
					listener.accept(e);
				}
			}

			@Override
			public void mousePressed(MouseEvent e)
			{

			}

			@Override
			public void mouseReleased(MouseEvent e)
			{

			}

			@Override
			public void mouseEntered(MouseEvent e)
			{

			}

			@Override
			public void mouseExited(MouseEvent e)
			{

			}

		});
	}

}
