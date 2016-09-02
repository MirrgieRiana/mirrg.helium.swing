package mirrg.helium.swing.nitrogen.wrapper.artifacts.logging;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

import mirrg.helium.standard.hydrogen.struct.Tuple;

public class LoggerMirrg
{

	/**
	 * 例外をerrorとして処理する。ログウィンドウが表示される。
	 *
	 * @see #processException(Exception, String, boolean, EnumTypeLog)
	 */
	public void processException(Exception exception)
	{
		processException(exception, null, true, EnumTypeLog.ERROR);
	}

	/**
	 * 例外を想定外の問題として処理する。ログウィンドウは表示されない。
	 *
	 * @see #processException(Exception, String, boolean, EnumTypeLog)
	 */
	public void processExceptionWarning(Exception exception)
	{
		processException(exception, null, false, EnumTypeLog.WARNING);
	}

	/**
	 * 例外を想定外の問題として処理する。ログウィンドウが表示される。
	 *
	 * @see #processException(Exception, String, boolean, EnumTypeLog)
	 */
	public void processExceptionUnexpected(Exception exception)
	{
		processException(exception, null, true, EnumTypeLog.UNEXPECTED);
	}

	/**
	 * @param isFatal
	 *            実行中のタスクが停止するような致命的な問題であるか。
	 *            trueの場合errorとして、falseの場合warningとして扱う。 また、trueの場合はログウィンドウを表示する。
	 * @see #processException(Exception, String, boolean, EnumTypeLog)
	 */
	public void processException(Exception exception, String string,
		boolean isFatal)
	{
		processException(exception, string, isFatal,
			isFatal ? EnumTypeLog.ERROR : EnumTypeLog.WARNING);
	}

	/**
	 * @param exception
	 *            nullable。
	 * @param string
	 *            nullable。 この例外が発生しているときのシステムの状態。
	 */
	public void processException(Exception exception, String string,
		boolean showFrameLog, EnumTypeLog typeLog)
	{
		FrameLog frameLog = null;
		if (showFrameLog) {
			frameLog = new FrameLog(0);
			frameLog.setVisible(true);
		}
		if (string != null) log(typeLog, string);
		if (exception != null) exception.printStackTrace(log(typeLog));
		if (frameLog != null) frameLog.disableAcceptMessage();
	}

	// //////////////////////////////////////////////////////

	public boolean bridgeStdout;

	public LoggerMirrg(boolean bridgeStdout)
	{
		this.bridgeStdout = bridgeStdout;
	}

	public LoggerMirrg()
	{
		this(false);
	}

	public void setBridgeStdout(boolean bridgeStdout)
	{
		this.bridgeStdout = bridgeStdout;
	}

	public boolean isBridgeStdout()
	{
		return bridgeStdout;
	}

	public void info(String string)
	{
		log(EnumTypeLog.INFO, string);
	}

	public void fine(String string)
	{
		log(EnumTypeLog.FINE, string);
	}

	public void warning(String string)
	{
		log(EnumTypeLog.WARNING, string);
	}

	public void error(String string)
	{
		log(EnumTypeLog.ERROR, string);
	}

	public void unexpected(String string)
	{
		log(EnumTypeLog.UNEXPECTED, string);
	}

	public void log(EnumTypeLog typeLog, String string)
	{
		if (bridgeStdout) System.out.println("[" + typeLog.name() + "] " + string);

		Tuple<EnumTypeLog, String> message = new Tuple<>(typeLog, string);

		messages.add(message);

		Iterator<Predicate<Tuple<EnumTypeLog, String>>> iterator = listeners
			.iterator();
		while (iterator.hasNext()) {
			Predicate<Tuple<EnumTypeLog, String>> next = iterator.next();

			if (next.test(message)) {
				iterator.remove();
			}
		}
	}

	// /////////////////////////////////////////////

	private PrintStream outInfo;

	public PrintStream info()
	{
		if (outInfo == null) {
			outInfo = createPrintStream(this::info);
		}
		return outInfo;
	}

	private PrintStream outFine;

	public PrintStream fine()
	{
		if (outFine == null) {
			outFine = createPrintStream(this::fine);
		}
		return outFine;
	}

	private PrintStream outWarning;

	public PrintStream warning()
	{
		if (outWarning == null) {
			outWarning = createPrintStream(this::warning);
		}
		return outWarning;
	}

	private PrintStream outError;

	public PrintStream error()
	{
		if (outError == null) {
			outError = createPrintStream(this::error);
		}
		return outError;
	}

	private PrintStream outUnexpected;

	public PrintStream unexpected()
	{
		if (outUnexpected == null) {
			outUnexpected = createPrintStream(this::unexpected);
		}
		return outUnexpected;
	}

	public PrintStream log(EnumTypeLog typeLog)
	{
		switch (typeLog) {
			case INFO:
				return info();
			case FINE:
				return fine();
			case WARNING:
				return warning();
			case ERROR:
				return error();
			case UNEXPECTED:
				return unexpected();
			default:
				return null;
		}
	}

	private PrintStream createPrintStream(Consumer<String> consumer)
	{
		try {
			return new PrintStream(new OutputStreamLogger(consumer), true,
				"UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	// /////////////////////////////////////////////

	private ArrayList<Tuple<EnumTypeLog, String>> messages = new ArrayList<>();

	public int getMessageCount()
	{
		return messages.size();
	}

	public Tuple<EnumTypeLog, String> getMessage(int index)
	{
		return messages.get(index);
	}

	private ArrayList<Predicate<Tuple<EnumTypeLog, String>>> listeners = new ArrayList<>();

	/**
	 * @param listener
	 *            イベントハンドラを削除する場合にtrueを返す。
	 */
	public void registerListener(Predicate<Tuple<EnumTypeLog, String>> listener)
	{
		listeners.add(listener);
	}

	private static class OutputStreamLogger extends OutputStream
	{

		private static final Charset charset = Charset.forName("UTF-8");

		private Consumer<String> consumer;

		public OutputStreamLogger(Consumer<String> consumer)
		{
			this.consumer = consumer;
		}

		private ArrayList<Byte> buffer = new ArrayList<>();
		private boolean afterR = false;

		@Override
		public void write(int b) throws IOException
		{
			if (b == '\r') {
				flush2();
			} else {
				if (b == '\n') {
					if (afterR) {

					} else {
						flush2();
					}
				} else {
					buffer.add(Byte.valueOf((byte) b));
				}
			}

			afterR = b == '\r';
		}

		private void flush2()
		{
			byte[] bytes = new byte[buffer.size()];
			for (int i = 0; i < buffer.size(); i++) {
				bytes[i] = buffer.get(i);
			}

			consumer.accept(new String(bytes, charset));
			buffer.clear();
		}

	}

}
