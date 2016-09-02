package mirrg.helium.swing.nitrogen.wrapper;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.JDialog;

public class DialogNitrogen extends JDialog
{

	private static final long serialVersionUID = -3051793174468124790L;
	public final WindowWrapperNitrogen windowWrapper;

	public DialogNitrogen()
	{
		super();
		windowWrapper = new WindowWrapperNitrogen(this);
	}

	public DialogNitrogen(Dialog owner, boolean modal)
	{
		super(owner, modal);
		windowWrapper = new WindowWrapperNitrogen(this);
	}

	public DialogNitrogen(Dialog owner, String title, boolean modal, GraphicsConfiguration gc)
	{
		super(owner, title, modal, gc);
		windowWrapper = new WindowWrapperNitrogen(this);
	}

	public DialogNitrogen(Dialog owner, String title, boolean modal)
	{
		super(owner, title, modal);
		windowWrapper = new WindowWrapperNitrogen(this);
	}

	public DialogNitrogen(Dialog owner, String title)
	{
		super(owner, title);
		windowWrapper = new WindowWrapperNitrogen(this);
	}

	public DialogNitrogen(Dialog owner)
	{
		super(owner);
		windowWrapper = new WindowWrapperNitrogen(this);
	}

	public DialogNitrogen(Frame owner, boolean modal)
	{
		super(owner, modal);
		windowWrapper = new WindowWrapperNitrogen(this);
	}

	public DialogNitrogen(Frame owner, String title, boolean modal, GraphicsConfiguration gc)
	{
		super(owner, title, modal, gc);
		windowWrapper = new WindowWrapperNitrogen(this);
	}

	public DialogNitrogen(Frame owner, String title, boolean modal)
	{
		super(owner, title, modal);
		windowWrapper = new WindowWrapperNitrogen(this);
	}

	public DialogNitrogen(Frame owner, String title)
	{
		super(owner, title);
		windowWrapper = new WindowWrapperNitrogen(this);
	}

	public DialogNitrogen(Frame owner)
	{
		super(owner);
		windowWrapper = new WindowWrapperNitrogen(this);
	}

	public DialogNitrogen(Window owner, ModalityType modalityType)
	{
		super(owner, modalityType);
		windowWrapper = new WindowWrapperNitrogen(this);
	}

	public DialogNitrogen(Window owner, String title, ModalityType modalityType, GraphicsConfiguration gc)
	{
		super(owner, title, modalityType, gc);
		windowWrapper = new WindowWrapperNitrogen(this);
	}

	public DialogNitrogen(Window owner, String title, ModalityType modalityType)
	{
		super(owner, title, modalityType);
		windowWrapper = new WindowWrapperNitrogen(this);
	}

	public DialogNitrogen(Window owner, String title)
	{
		super(owner, title);
		windowWrapper = new WindowWrapperNitrogen(this);
	}

	public DialogNitrogen(Window owner)
	{
		super(owner);
		windowWrapper = new WindowWrapperNitrogen(this);
	}

}
