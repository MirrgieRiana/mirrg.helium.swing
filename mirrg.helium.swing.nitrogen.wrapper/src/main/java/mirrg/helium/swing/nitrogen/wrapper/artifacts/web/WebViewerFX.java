package mirrg.helium.swing.nitrogen.wrapper.artifacts.web;

import java.awt.Component;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

public class WebViewerFX extends WebViewer
{

	private JFXPanel panel;
	private WebView webView;
	private String url;

	@Override
	public void init(IListenerWebViewer listenerWebViewer /* TODO */)
	{
		panel = new JFXPanel();
		panel.setPreferredSize(new Dimension(800, 600));

		Platform.runLater(() -> {
			AnchorPane root = new AnchorPane();
			{
				webView = new WebView();
				{
					AnchorPane.setLeftAnchor(webView, 0.0);
					AnchorPane.setRightAnchor(webView, 0.0);
					AnchorPane.setTopAnchor(webView, 0.0);
					AnchorPane.setBottomAnchor(webView, 0.0);
				}
				root.getChildren().add(webView);
			}
			Scene scene = new Scene(root);
			panel.setScene(scene);
		});
	}

	@Override
	public void setPage(URL url)
	{
		setPage(url.toString());
	}

	@Override
	public void setPage(String url)
	{
		this.url = url;
		Platform.runLater(() -> {
			webView.getEngine().load(url);
		});
	}

	@Override
	protected void setPositionImpl(int position)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public URL getPage()
	{
		String url = this.url;// webView.getEngine().getLocation();
		try {
			return url == null ? null : new URL(url);
		} catch (MalformedURLException e) {
			return null;
		}
	}

	@Override
	public int getPosition()
	{
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public Component getComponent()
	{
		return panel;
	}

	@Override
	public void refresh()
	{
		webView.getEngine().reload();
	}

}
