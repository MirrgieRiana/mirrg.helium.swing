package mirrg.helium.swing.nitrogen.wrapper.artifacts.web;

import java.awt.Component;
import java.net.URL;

import mirrg.helium.standard.hydrogen.struct.Tuple;

public abstract class WebViewer
{

	public abstract void init(IListenerWebViewer listenerWebViewer);

	public abstract void setPage(URL url);

	public abstract void setPage(String url);

	public void setPageFromURL(Tuple<URL, Integer> url)
	{
		setPage(url.getX(), url.getY());
	}

	public void setPageFromString(Tuple<String, Integer> url)
	{
		setPage(url.getX(), url.getY());
	}

	protected abstract void setPositionImpl(int position);

	public void setPage(URL url, int position)
	{
		setPage(url);
		setPositionImpl(position);
	}

	public void setPage(String url, int position)
	{
		setPage(url);
		setPositionImpl(position);
	}

	/**
	 * @return null: 白紙のページ
	 */
	public abstract URL getPage();

	public abstract int getPosition();

	/**
	 * @return null: 白紙のページ
	 */
	public Tuple<URL, Integer> getNow()
	{
		URL url = getPage();
		return url == null ? null : new Tuple<>(url, getPosition());
	}

	public abstract Component getComponent();

	public abstract void refresh();

}
