package mirrg.helium.swing.nitrogen.wrapper;

import static org.junit.Assert.*;

import javax.swing.SwingUtilities;

import org.junit.Test;

public class TestFrameNeon
{

	@Test
	public void test1()
	{
		StringBuffer sb = new StringBuffer();

		FrameNitrogen frame = new FrameNitrogen();

		frame.windowWrapper.getEventManager().register(EventNitrogen.Initialized.class, e -> sb.append("I"));
		frame.windowWrapper.getEventManager().register(EventNitrogen.Shown.class, e -> sb.append("S"));
		frame.windowWrapper.getEventManager().register(EventNitrogen.Hidden.class, e -> sb.append("H"));
		frame.windowWrapper.getEventManager().register(EventNitrogen.Disposed.class, e -> sb.append("D"));

		SwingUtilities.invokeLater(() -> {
			assertEquals("", sb.toString());
		});
		SwingUtilities.invokeLater(() -> {
			frame.setVisible(true);
		});
		SwingUtilities.invokeLater(() -> {
			assertEquals("IS", sb.toString());
		});
		SwingUtilities.invokeLater(() -> {
			frame.setVisible(false);
		});
		SwingUtilities.invokeLater(() -> {
			assertEquals("ISH", sb.toString());
		});
		SwingUtilities.invokeLater(() -> {
			frame.setVisible(true);
		});
		SwingUtilities.invokeLater(() -> {
			assertEquals("ISHS", sb.toString());
		});
		SwingUtilities.invokeLater(() -> {
			frame.setVisible(false);
		});
		SwingUtilities.invokeLater(() -> {
			assertEquals("ISHSH", sb.toString());
		});
		SwingUtilities.invokeLater(() -> {
			frame.setVisible(true);
		});
		SwingUtilities.invokeLater(() -> {
			assertEquals("ISHSHS", sb.toString());
		});
		SwingUtilities.invokeLater(() -> {
			frame.dispose();
		});
		SwingUtilities.invokeLater(() -> {
			assertEquals("ISHSHSHD", sb.toString());
		});

	}

	@Test
	public void test2()
	{
		StringBuffer sb = new StringBuffer();

		FrameNitrogen frame = new FrameNitrogen();

		frame.windowWrapper.getEventManager().register(EventNitrogen.Initialized.class, e -> sb.append("I"));
		frame.windowWrapper.getEventManager().register(EventNitrogen.Shown.class, e -> sb.append("S"));
		frame.windowWrapper.getEventManager().register(EventNitrogen.Hidden.class, e -> sb.append("H"));
		frame.windowWrapper.getEventManager().register(EventNitrogen.Disposed.class, e -> sb.append("D"));

		SwingUtilities.invokeLater(() -> {
			assertEquals("", sb.toString());
		});
		SwingUtilities.invokeLater(() -> {
			frame.setVisible(true);
		});
		SwingUtilities.invokeLater(() -> {
			assertEquals("IS", sb.toString());
		});
		SwingUtilities.invokeLater(() -> {
			frame.setVisible(false);
		});
		SwingUtilities.invokeLater(() -> {
			assertEquals("ISH", sb.toString());
		});
		SwingUtilities.invokeLater(() -> {
			frame.setVisible(true);
		});
		SwingUtilities.invokeLater(() -> {
			assertEquals("ISHS", sb.toString());
		});
		SwingUtilities.invokeLater(() -> {
			frame.setVisible(false);
		});
		SwingUtilities.invokeLater(() -> {
			assertEquals("ISHSH", sb.toString());
		});
		SwingUtilities.invokeLater(() -> {
			frame.dispose();
		});
		SwingUtilities.invokeLater(() -> {
			assertEquals("ISHSHD", sb.toString());
		});

	}

}
