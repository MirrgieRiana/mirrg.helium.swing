package mirrg.helium.swing.nitrogen.wrapper;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class FrameNitrogen extends JFrame
{

	private static final long serialVersionUID = 2621862565624785025L;
	public final WindowWrapperNitrogen windowWrapper;

	public FrameNitrogen() throws HeadlessException
	{
		super();
		windowWrapper = new WindowWrapperNitrogen(this);
	}

	public FrameNitrogen(GraphicsConfiguration gc)
	{
		super(gc);
		windowWrapper = new WindowWrapperNitrogen(this);
	}

	public FrameNitrogen(String title, GraphicsConfiguration gc)
	{
		super(title, gc);
		windowWrapper = new WindowWrapperNitrogen(this);
	}

	public FrameNitrogen(String title) throws HeadlessException
	{
		super(title);
		windowWrapper = new WindowWrapperNitrogen(this);
	}

}
