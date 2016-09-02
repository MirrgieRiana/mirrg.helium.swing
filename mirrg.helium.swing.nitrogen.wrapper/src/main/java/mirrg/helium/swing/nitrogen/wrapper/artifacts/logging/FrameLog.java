package mirrg.helium.swing.nitrogen.wrapper.artifacts.logging;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import mirrg.helium.standard.hydrogen.struct.Tuple;
import mirrg.helium.swing.nitrogen.wrapper.EventNitrogen;
import mirrg.helium.swing.nitrogen.wrapper.FrameNitrogen;
import mirrg.helium.swing.nitrogen.wrapper.artifacts.MenuLookAndFeel;

public class FrameLog extends FrameNitrogen
{

	private static final long serialVersionUID = -1803098633817688275L;

	public static void main(String[] args)
	{
		{
			HLog.error("Error!");
			HLog.warning("Warning!");
			new FrameLog(300).setVisible(true);
			HLog.fine("Fine!");
			HLog.info("Info!");
			new FrameLog(300).setVisible(true);
			HLog.info().println("Print Info!");
			new RuntimeException("Exception!").printStackTrace(HLog.warning());

			Thread thread = new Thread(() -> {

				while (true) {
					HLog.info().println("Info!");
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						break;
					}
				}

			});
			thread.setDaemon(true);
			thread.start();
		}

		{
			LoggerMirrg logger = new LoggerMirrg();

			for (int i = 0; i < 1000; i++) {
				logger.info("abc");
			}

			new FrameLog(logger, 300).setVisible(true);
		}

	}

	private JCheckBox checkBoxAutoScroll;
	private JScrollPane scrollPaneMessages;
	private DefaultStyledDocument document;

	public FrameLog(int loadedMessages)
	{
		this(HLog.logger, loadedMessages);
	}

	public FrameLog(LoggerMirrg loggerMirrg, int loadedMessages)
	{
		super("ログウィンドウ");

		int skipMessages = loggerMirrg.getMessageCount() - loadedMessages;

		{
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);

			{
				JMenu menu = new JMenu("メニュー(M)");
				menuBar.add(menu);

				menu.setMnemonic('M');

				{
					JMenu menu2 = new MenuLookAndFeel();
					menu.add(menu2);
				}

				{
					JMenuItem menuItem = new JMenuItem("ログウィンドウのクリア(C)");
					menu.add(menuItem);

					menuItem.setMnemonic('C');
					menuItem.addActionListener(e -> {
						try {
							document.remove(0, document.getLength());
						} catch (BadLocationException e1) {
							HLog.processExceptionUnexpected(e1);
						}
					});
				}
			}

			{
				checkBoxAutoScroll = new JCheckBox("オートスクロール(A)", true);
				menuBar.add(checkBoxAutoScroll);

				checkBoxAutoScroll.setMnemonic('A');
			}

		}

		{
			document = new DefaultStyledDocument();
			{
				Style styleDefault = StyleContext.getDefaultStyleContext()
					.getStyle(StyleContext.DEFAULT_STYLE);

				Style info = document.addStyle("INFO", styleDefault);
				StyleConstants.setForeground(info, Color.BLACK);

				Style fine = document.addStyle("FINE", styleDefault);
				StyleConstants.setForeground(fine, new Color(0x0088ff));

				Style warning = document.addStyle("WARNING", styleDefault);
				StyleConstants.setForeground(warning, new Color(0xff8800));

				Style error = document.addStyle("ERROR", styleDefault);
				StyleConstants.setForeground(error, Color.RED);

				Style unexpected = document
					.addStyle("UNEXPECTED", styleDefault);
				StyleConstants.setForeground(unexpected, new Color(0x8800ff));

			}

			JTextPane textPane = new JTextPane(document);

			textPane.setEditable(false);

			scrollPaneMessages = new JScrollPane(textPane);
			scrollPaneMessages.setPreferredSize(new Dimension(400, 400));

			add(scrollPaneMessages);
		}

		setLayout(new CardLayout());

		windowWrapper.getEventManager().register(EventNitrogen.Initialized.class, e -> {

			int start = Math.max(0, skipMessages);

			// 省略されたメッセージ
			if (start > 0) {
				addMessage(new Tuple<>(EnumTypeLog.INFO, "省略された" + start
					+ "件のメッセージ"));
			}

			// 既存のメッセージ
			for (int i = start; i < loggerMirrg.getMessageCount(); i++) {
				addMessage(loggerMirrg.getMessage(i));
			}

			// 新しいメッセージ
			loggerMirrg.registerListener(message -> {
				if (disabled || windowWrapper.isDisposed()) return true;
				SwingUtilities.invokeLater(() -> {
					addMessage(message);
				});
				return false;
			});

		});

		windowWrapper.prepareFrame();
	}

	private transient boolean disabled = false;

	public void disableAcceptMessage()
	{
		disabled = true;
		addMessage(new Tuple<>(EnumTypeLog.INFO,
			"Log window has been disabled."));
	}

	private void addMessage(Tuple<EnumTypeLog, String> message)
	{
		try {

			// 追加処理
			document.insertString(document.getLength(), message.getY() + "\n",
				document.getStyle(message.getX().name()));

			// オートスクロール
			if (checkBoxAutoScroll.isSelected()) {
				scrollPaneMessages.validate();
				JScrollBar scrollBar = scrollPaneMessages
					.getVerticalScrollBar();
				try {
					scrollBar.setValue(scrollBar.getMaximum()
						- scrollBar.getVisibleAmount());
				} catch (NullPointerException e) {
					// なぜか内部で例外が発生する。
					HLog.processExceptionUnexpected(e);
				}
			}

		} catch (BadLocationException e) {
			HLog.processExceptionUnexpected(e);
		}
	}

}
