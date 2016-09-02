package mirrg.helium.swing.nitrogen.wrapper.artifacts.logging;

import java.io.PrintStream;
import java.util.function.Predicate;

import mirrg.helium.standard.hydrogen.struct.Tuple;

public class HLog
{

	/**
	 * このロガーは標準出力にも垂れ流す。
	 */
	public static final LoggerMirrg logger = new LoggerMirrg(true);

	public static void processException(Exception exception)
	{
		logger.processException(exception);
	}

	public static void processExceptionWarning(Exception exception)
	{
		logger.processExceptionWarning(exception);
	}

	public static void processExceptionUnexpected(Exception exception)
	{
		logger.processExceptionUnexpected(exception);
	}

	public static void processException(Exception exception, String string,
		boolean isFatal)
	{
		logger.processException(exception, string, isFatal);
	}

	public static void processException(Exception exception, String string,
		boolean showFrameLog, EnumTypeLog typeLog)
	{
		logger.processException(exception, string, showFrameLog, typeLog);
	}

	public static void info(String string)
	{
		logger.info(string);
	}

	public static void fine(String string)
	{
		logger.fine(string);
	}

	public static void warning(String string)
	{
		logger.warning(string);
	}

	public static void error(String string)
	{
		logger.error(string);
	}

	public static void unexpected(String string)
	{
		logger.unexpected(string);
	}

	public static void log(EnumTypeLog typeLog, String string)
	{
		logger.log(typeLog, string);
	}

	public static PrintStream info()
	{
		return logger.info();
	}

	public static PrintStream fine()
	{
		return logger.fine();
	}

	public static PrintStream warning()
	{
		return logger.warning();
	}

	public static PrintStream error()
	{
		return logger.error();
	}

	public static PrintStream unexpected()
	{
		return logger.unexpected();
	}

	public static PrintStream log(EnumTypeLog typeLog)
	{
		return logger.log(typeLog);
	}

	public static int getMessageCount()
	{
		return logger.getMessageCount();
	}

	public static Tuple<EnumTypeLog, String> getMessage(int index)
	{
		return logger.getMessage(index);
	}

	public static void registerListener(
		Predicate<Tuple<EnumTypeLog, String>> listener)
	{
		logger.registerListener(listener);
	}

}
