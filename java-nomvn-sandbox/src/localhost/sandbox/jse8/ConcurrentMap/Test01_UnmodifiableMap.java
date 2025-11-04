package localhost.sandbox.jse8.ConcurrentMap;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>Pending.
 * 
 * @author Alberto Romero
 * @since 2025-11-04
 * 
 */
public class Test01_UnmodifiableMap {

	public static void test00_unmodifiableMap() {
		System.out.println("Hello from Test01_UnmodifiableMap.test00_unmodifiableMap()!");
		final ConcurrentMap<String,String> cMap = new ConcurrentHashMap<>();
		Map<String,String> uMap = Collections.unmodifiableMap(cMap);

		cMap.put("1", "one");
		cMap.put("2", "two");
		cMap.put("3", "three");

		System.out.println("cMap: " + cMap);
		System.out.println("uMap: " + uMap);
	}

}
