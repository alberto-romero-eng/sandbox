package localhost.cache.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class SpringBootCacheHelper {

	private static Logger log = LoggerFactory.getLogger(SpringBootCacheHelper.class);

	@Autowired
	private ApplicationContext ac;


	/**
	 * <p>Obtain list of Bean names including "cache" sequence.
	 * 
	 * @author Alberto Romero
	 * @since 2025-05-31
	 * 
	 */
	public List<String> getCacheRelatedBeanNames() {
		String[] beanNames = ac.getBeanDefinitionNames();
		List<String> cacheBeansList = new ArrayList<>();
		for (String bName : beanNames) {
			if (bName.matches("(?i)^.*cache.*$")) {
				cacheBeansList.add(bName);
			}
		}
		return cacheBeansList;
	}

	/**
	 * <p>{@link CacheProperties} reflects only parameters read from <i>application.yml</i>.
	 * 
	 * <p>Actual project's <i>cache</i> implementation depends also on related dependencies in pom.xml.
	 * 
	 * <p>As of 2025-05-31, only tests using {@link CacheType#NONE} and {@link CacheType#SIMPLE} have
	 * been done, without any <i>cache</i>-related dependencies in pom.xml.
	 * 
	 * <p>If property <i>spring.cache.type</i> is not present in application.yml, and no <i>cache</i>-related 
	 * dependencies exist in pom.xml, {@link CacheProperties#getType()} returns <i>null</i> and default 
	 * underlying implementation is {@link CacheType#SIMPLE}.
	 * 
	 * @author Alberto Romero
	 * @since 2025-05-31
	 * 
	 */
	public CacheProperties getCachePropertiesBean() {
		String[] beanNames = ac.getBeanDefinitionNames();
		List<String> cachePropList = new ArrayList<>();
		for (String bName : beanNames) {
			if (bName.matches("(?i)^.*cache.*properties.*$")) {
				cachePropList.add(bName);
			}
		}
		if (cachePropList.size() > 1) {
			log.warn("ideally, cachePropList.size should be 1 -- cachePropList.size: {}", cachePropList.size());
		}
		Object cachePropObj = ac.getBean(cachePropList.get(0));
		if (cachePropObj.getClass() != CacheProperties.class) {
			log.error("cachePropertiesObject class unknown; it's not possible to determine CacheType -- returning null");
			return null;
		}
		CacheProperties cacheProp = (CacheProperties) cachePropObj;
		CacheType cacheType = cacheProp.getType();
		log.info("Finish -- results -- cacheType: " + cacheType);
		return cacheProp;
	}


}
