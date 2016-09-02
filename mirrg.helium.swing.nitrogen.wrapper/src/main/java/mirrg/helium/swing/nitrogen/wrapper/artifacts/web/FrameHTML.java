package mirrg.helium.swing.nitrogen.wrapper.artifacts.web;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.LinkedList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import mirrg.helium.standard.hydrogen.struct.Tuple;
import mirrg.helium.swing.nitrogen.util.GroupBuilder;
import mirrg.helium.swing.nitrogen.util.HSwing;
import mirrg.helium.swing.nitrogen.wrapper.FrameNitrogen;
import mirrg.helium.swing.nitrogen.wrapper.artifacts.logging.EnumTypeLog;
import mirrg.helium.swing.nitrogen.wrapper.artifacts.logging.HLog;

public class FrameHTML extends FrameNitrogen
{

	private static final long serialVersionUID = 6630306464004851697L;
	/**
	 * 下に行くほど新しい。
	 */
	private LinkedList<Tuple<URL, Integer>> historyLeft = new LinkedList<>();
	/**
	 * 下に行くほど新しい。
	 */
	private LinkedList<Tuple<URL, Integer>> historyRight = new LinkedList<>();

	private JButton button1;
	private JButton button2;
	private JTextField textField1;
	private WebViewer webViewer;

	public FrameHTML()
	{
		this(new WebViewerTextPane());
	}

	public FrameHTML(WebViewer webViewer)
	{
		this.webViewer = webViewer;
		setTitle("HTMLビューワ");

		{

			button1 = new JButton("戻る");
			button1.setToolTipText("右クリック：プルダウンメニューの表示");
			button1.addActionListener(e -> {
				if (!historyLeft.isEmpty())
					travelLeft(1);
			});
			HSwing.hookPopup(button1, e -> {

				if (!historyLeft.isEmpty()) {
					JPopupMenu popupMenu = new JPopupMenu();

					for (int i = historyLeft.size() - 1; i >= 0; i--) {
						Tuple<URL, Integer> entry = historyLeft.get(i);
						JMenuItem menuItem = new JMenuItem(entry.getY()
							.toString() + ": " + entry.getX().toString());
						popupMenu.add(menuItem);

						int i2 = i + 1;
						menuItem.addActionListener(e2 -> {
							travelLeft(i2);
						});
					}

					popupMenu.show((Component) e.getSource(), e.getX(),
						e.getY());

					return true;
				}

				return false;
			});

			button2 = new JButton("進む");
			button2.setToolTipText("右クリック：プルダウンメニューの表示");
			button2.addActionListener(e -> {
				if (!historyRight.isEmpty())
					travelRight(1);
			});
			HSwing.hookPopup(button2, e -> {

				if (!historyRight.isEmpty()) {
					JPopupMenu popupMenu = new JPopupMenu();

					for (int i = 0; i < historyRight.size(); i++) {
						Tuple<URL, Integer> entry = historyRight.get(i);
						JMenuItem menuItem = new JMenuItem(entry.getY()
							.toString() + ": " + entry.getX().toString());
						popupMenu.add(menuItem);

						int i2 = i + 1;
						menuItem.addActionListener(e2 -> {
							travelRight(i2);
						});
					}

					popupMenu.show((Component) e.getSource(), e.getX(),
						e.getY());

					return true;
				}

				return false;
			});

			textField1 = new JTextField();
			textField1.addActionListener(e -> {
				navigate(textField1.getText());
			});

			JButton button3 = new JButton("移動");
			button3.addActionListener(e -> {
				navigate(textField1.getText());
			});

			JButton button4 = new JButton("更新");
			button4.addActionListener(e -> {
				refresh();
			});

			webViewer.init(new IListenerWebViewer() {

				@Override
				public void activateHyperlink(URL url)
				{
					navigate(url);
				}

				@Override
				public void keyPressed(int keyCode)
				{
					if (keyCode == KeyEvent.VK_F5) {
						refresh();
						return;
					}
					if (keyCode == KeyEvent.VK_BACK_SPACE) {
						if (!historyLeft.isEmpty())
							travelLeft(1);
						return;
					}
				}

			});

			{
				GroupLayout layout = new GroupLayout(getContentPane());
				setLayout(layout);

				layout.setAutoCreateGaps(true);
				layout.setAutoCreateContainerGaps(false);

				GroupBuilder.group(
					GroupBuilder.group(button1, button2,
						new JLabel("アドレス:"), textField1, button3,
						button4).align(Alignment.CENTER), webViewer.getComponent())
					.apply(layout);
			}
		}

		historyChanged();

		HLog.log(EnumTypeLog.FINE, "HTMLビューワを起動しました");

		windowWrapper.prepareFrame();
	}

	private void refresh()
	{
		webViewer.refresh();
	}

	private void travelLeft(int times)
	{
		Tuple<URL, Integer> now = webViewer.getNow();

		// 履歴操作
		for (int i = 0; i < times; i++) {
			historyRight.addFirst(now);
			now = historyLeft.removeLast();
		}

		historyChanged();

		setPage(now);
	}

	private void travelRight(int times)
	{
		Tuple<URL, Integer> now = webViewer.getNow();

		// 履歴操作
		for (int i = 0; i < times; i++) {
			historyLeft.addLast(now);
			now = historyRight.removeFirst();
		}

		historyChanged();

		setPage(now);
	}

	private void pushHistory()
	{
		Tuple<URL, Integer> now = webViewer.getNow();
		if (now != null) {
			historyLeft.addLast(now);
			historyChanged();
		}
	}

	private void historyChanged()
	{
		button1.setEnabled(!historyLeft.isEmpty());
		button2.setEnabled(!historyRight.isEmpty());
	}

	private void setPage(Tuple<URL, Integer> url)
	{
		webViewer.setPageFromURL(url);
		textField1.setText(url.getX().toString());
	}

	public void navigate(String url)
	{
		pushHistory();
		webViewer.setPage(url);
		textField1.setText(url);
	}

	public void navigate(URL url)
	{
		pushHistory();
		webViewer.setPage(url);
		textField1.setText(url.toString());
	}

}
