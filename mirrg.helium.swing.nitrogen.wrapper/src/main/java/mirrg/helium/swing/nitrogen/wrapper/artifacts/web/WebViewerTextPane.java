package mirrg.helium.swing.nitrogen.wrapper.artifacts.web;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent.EventType;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;

import mirrg.helium.standard.hydrogen.struct.Struct1;
import mirrg.helium.standard.hydrogen.struct.Tuple;
import mirrg.helium.swing.nitrogen.wrapper.artifacts.logging.HLog;

public class WebViewerTextPane extends WebViewer
{

	private JEditorPane editorPane1;
	private JScrollPane scrollPane1;

	@Override
	public void setPage(URL url)
	{
		try {
			editorPane1.setPage(url);
		} catch (IOException e) {
			HLog.processExceptionWarning(e);
			return;
		}
	}

	@Override
	public void setPage(String url)
	{
		try {
			editorPane1.setPage(url);
		} catch (IOException e) {
			HLog.processExceptionWarning(e);
			return;
		}
	}

	@Override
	protected void setPositionImpl(int position)
	{
		// 表示位置調整
		Struct1<PropertyChangeListener> listener = new Struct1<>();
		listener.x = e -> {
			editorPane1.removePropertyChangeListener("page", listener.x);

			scrollPane1.getVerticalScrollBar().setValue(position);

		};
		editorPane1.addPropertyChangeListener("page", listener.x);

		// 同じページに遷移した場合にイベントが発生しないので念のために変更しておく
		// TODO イベントが残留するが次のイベントで死ぬのでとりあえず良しとする
		scrollPane1.getVerticalScrollBar().setValue(position);
	}

	@Override
	public void init(IListenerWebViewer listenerWebViewer)
	{
		editorPane1 = new JEditorPane();
		{
			editorPane1.setEditable(false);
			editorPane1.addHyperlinkListener(e -> {
				if (e.getEventType() == EventType.ACTIVATED) {
					listenerWebViewer.activateHyperlink(e.getURL());
				}
			});
			editorPane1.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e)
				{

				}

				@Override
				public void keyReleased(KeyEvent e)
				{

				}

				@Override
				public void keyPressed(KeyEvent e)
				{
					listenerWebViewer.keyPressed(e.getKeyCode());
				}

			});
		}
		scrollPane1 = new JScrollPane(editorPane1);
		{
			scrollPane1.setPreferredSize(new Dimension(800, 800));
		}
	}

	@Override
	public URL getPage()
	{
		return editorPane1.getPage();
	}

	@Override
	public int getPosition()
	{
		return scrollPane1.getVerticalScrollBar().getValue();
	}

	@Override
	public Component getComponent()
	{
		return scrollPane1;
	}

	@Override
	public void refresh()
	{
		Tuple<URL, Integer> now = getNow();

		// 同じ場所を表示させても更新されないので、
		// 現在の場所情報を削除して新たな場所だと思い込ませる処理
		((HTMLDocument) editorPane1.getDocument()).getDocumentProperties()
			.remove(Document.StreamDescriptionProperty);

		if (now != null) setPageFromURL(now);
	}

}
