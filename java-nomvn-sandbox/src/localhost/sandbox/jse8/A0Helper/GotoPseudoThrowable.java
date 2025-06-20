package localhost.sandbox.jse8.A0Helper;

/**
 * <p>For situations like the following one within a method, where <i>log.info(...)</i> is desired
 * at the end, to print 'result' value(s):</p>
 * <pre>
 * boolean result = false;
 * 
 * if (condition 1) {
 *     result = false;
 *     log.info("result: {}", result);
 *     return result;
 * }
 * 
 * if (condition 2) {
 *     result = false;
 *     log.info("result: {}", result);
 *     return result;
 * }
 * 
 * result = true;
 * log.info("result: {}", result);
 * return result;
 * 
 * </pre>
 * 
 * <p>Content of <i>log.info(...)</i> may be cumbersome to check / maintain in all lines.
 * 
 * <p>So this class is intended to act as a <i>pseudo-throwable</i>, or sort of <i>goto</i>,
 * as follows:
 * <pre>
 * boolean result = false;
 * 
 * try {
 * 
 *     if (condition1) {
 *         result = false;
 *         throw new PseudoThrowable();
 *     }
 * 
 *     if (condition2) {
 *         result = false;
 *         throw new PseudoThrowable();
 *     }
 *
 * } catch (Exception ex) {
 *       // manage Exception:
 *       //   - re-throw Exception, or
 *       //   - return some valuem or
 *       //   - just assign value to variable 'result'.
 * 
 * } catch (GotoPseudoThrowable tw) {
 *       // manage GotoPseudoThrowable:
 *       //   - do nothing significant;
 *       //   - just assign value to variable 'result';
 *       //   - main interest is to reach end of method.
 * 
 * } catch (Throwable tw) {
 *       // manage Throwable:
 *       //   - re-throw Throwable, or
 *       //   - return some value, or
 *       //   - just assign value to variable 'result'.
 * }
 * 
 * log.info("result: {}", result);
 * return result;
 * </pre>
 * 
 * <p>Using this later approach, it could be enough to have line <i>log.info(...)</i> just once,
 * for normal execution.
 * 
 * <p>This approach still does not solve requiring to have <i>log.info(...)</i> into <i>catch</i>
 * blocks where <i>result</i> is returned or {@link Exception} / {@link Throwable} is re-thrown.
 * 
 * <p><i>Catch</i> block of {@link GotoPseudoThrowable} can be placed after {@link Exception}, and must 
 * be placed before {@link Throwable}.
 * 
 * @author Alberto Romero
 * @since 2025-05-29
 * 
 */
public class GotoPseudoThrowable extends Throwable {

	private static final long serialVersionUID = 1L;

	/**
	 * <p>See {@link GotoPseudoThrowable}.
	 * 
	 * @author Alberto Romero
	 * @since 2025-05-29
	 * 
	 */
	public static class GPT extends GotoPseudoThrowable {

		private static final long serialVersionUID = 1L;

	}

}
