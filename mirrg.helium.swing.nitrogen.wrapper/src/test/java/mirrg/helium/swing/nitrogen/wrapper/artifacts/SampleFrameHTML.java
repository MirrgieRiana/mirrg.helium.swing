package mirrg.helium.swing.nitrogen.wrapper.artifacts;

import mirrg.helium.swing.nitrogen.wrapper.artifacts.web.FrameHTML;
import mirrg.helium.swing.nitrogen.wrapper.artifacts.web.WebViewerFX;
import mirrg.helium.swing.nitrogen.wrapper.artifacts.web.WebViewerTextPane;

public class SampleFrameHTML
{

	public static void main(String[] args)
	{
		{
			FrameHTML frame = new FrameHTML(new WebViewerTextPane());

			frame.navigate(SampleFrameHTML.class.getResource("test.html"));
			frame.setSize(600, 800);

			frame.setVisible(true);
		}
		{
			FrameHTML frame = new FrameHTML(new WebViewerFX());

			frame.navigate(SampleFrameHTML.class.getResource("test.html"));
			frame.setSize(600, 800);

			frame.setVisible(true);
		}
	}

}
