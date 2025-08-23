package com.example;

import java.util.Date;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * <p>Helper class to monitor/troubleshoot sql-date/time operations.
 * 
 * <p>Updates:
 * <ul>
 * <li>2025-08-23: {@link TestSqlOutputToJDate}, {@link TestJTypeToSqlParamToJType}, added explicit getters 
 * due to json-format inconsistency observed across different applications, possibly due to non-evident 
 * <i>jackson</i> date-time format configuration.
 * </ul>
 * 
 * @author Alberto Romero
 * @since 2025-07-23
 * 
 */
@Component
public class MysqlTimeHelper {

	private static Logger log = LoggerFactory.getLogger(MysqlTimeHelper.class);

	@Value("${spring.datasource.url}")
	private String springDatasourceUrl;

	@Autowired
	MysqlTimeMapper mysqlTimeMapper;


	public MysqlTimeHelperPojo main() {
		SpringbootDatasourceUrlReqParams o1 = testSpringbootDatasourceUrlReqParams();
		TestSessionTimeZone o2 = testSessionTimeZone();
		TestSqlOutputToJString o3 = testSqlOutputToJString();
		TestSqlOutputToJDate o4 = testSqlOutputToJDate();
		TestJTypeToSqlParamToJType o5 = testJTypeToSqlParamToJType();
		MysqlTimeHelperPojo o = new MysqlTimeHelperPojo();
		o.setSpringbootDatasourceUrlReqParams(o1);
		o.setTestSessionTimeZone(o2);
		o.setTestSqlOutputToJString(o3);
		o.setTestSqlOutputToJDate(o4);
		o.setTestJTypeToSqlParamToJType(o5);
		return o;
	}


	/**
	 * <p>ServerTimezone, from applicaton-yml, spring-datasource-url property.
	 * 
	 * @author Alberto Romero
	 * @since 2025-07-22
	 */
	public SpringbootDatasourceUrlReqParams testSpringbootDatasourceUrlReqParams() {
		log.info("Start testSpringbootDatasourceUrlReqParams");
		String serverTimezone = null;
		String[] parts = springDatasourceUrl.split("[&]");
		for (String p : parts) {
			if (p.matches(".*serverTimezone.*")) {
				serverTimezone = p.replace("serverTimezone=", "");
				break;
			}
		}
		SpringbootDatasourceUrlReqParams o = new SpringbootDatasourceUrlReqParams();
		o.setServerTimezone(serverTimezone);
		log.info("serverTimezone: {}", serverTimezone);
		return o;
	}


	/**
	 * <p>Sql session-timezone tests.
	 * 
	 * @author Alberto Romero
	 * @since 2025-07-22
	 */
	public TestSessionTimeZone testSessionTimeZone() {
		log.info("Start testSessionTimeZone");
		/* String timeZone = null;
		timeZone = "+00:00";
		int updRegs = mySqlTimeMapper.setSessionTimeZone(timeZone);
		log.info("setSessionTimeZone, updRegs: {}", updRegs); */
		Optional<String> globalSystemTimeZoneOpt = mysqlTimeMapper.selectGlobalSystemTimeZone();
		Optional<String> globalTimeZoneOpt = mysqlTimeMapper.selectGlobalTimeZone();
		Optional<String> sessionTimeZoneOpt = mysqlTimeMapper.selectSessionTimeZone();
		TestSessionTimeZone o = new TestSessionTimeZone();
		o.setGlobalSystemTimeZone(globalSystemTimeZoneOpt.get());
		o.setGlobalTimeZone(globalTimeZoneOpt.get());
		o.setSessionTimeZone(sessionTimeZoneOpt.get());
		log.info("globalSystemTimeZone: {}, globalTimeZone: {}, sessionTimeZone: {}", globalSystemTimeZoneOpt.get(), globalTimeZoneOpt.get(), sessionTimeZoneOpt.get());
		return o;
	}



	/**
	 * <p>Date-time values generated from sql-functions, as they are, no interpretation.
	 * 
	 * @author Alberto Romero
	 * @since 2025-07-22
	 */
	public TestSqlOutputToJString testSqlOutputToJString() {
		log.info("Start testSqlOutputToJString");
		Optional<String> utcTimestampOpt = mysqlTimeMapper.selectUtcTimestampAsString();
		Optional<String> nowOpt = mysqlTimeMapper.selectNowAsString();
		Optional<String> timestampNowOpt = mysqlTimeMapper.selectTimestampNowAsString();
		TestSqlOutputToJString o = new TestSqlOutputToJString();
		o.setUtcTimestamp(utcTimestampOpt.get());
		o.setNow(nowOpt.get());
		o.setTimestampNow(timestampNowOpt.get());
		log.info("utcTimestamp: {}, now: {}, timestampNow: {}", utcTimestampOpt.get(), nowOpt.get(), timestampNowOpt.get());
		return o;
	}



	/**
	 * <p>Date-time values generated from sql-functions, result converted to {@link Date} Java-type.
	 * 
	 * @author Alberto Romero
	 * @since 2025-07-22
	 */
	public TestSqlOutputToJDate testSqlOutputToJDate() {
		log.info("Start testSqlOutputToJDate");
		Optional<Date> utcTimestampOpt = mysqlTimeMapper.selectUtcTimestampAsDate();
		Optional<Date> nowOpt = mysqlTimeMapper.selectNowAsDate();
		Optional<Date> timestampNowOpt = mysqlTimeMapper.selectTimestampNowAsDate();
		TestSqlOutputToJDate o = new TestSqlOutputToJDate();
		o.setUtcTimestamp(utcTimestampOpt.get());
		o.setNow(nowOpt.get());
		o.setTimestampNow(timestampNowOpt.get());
		log.info("utcTimestamp: {}, now: {}, timestampNow: {}", utcTimestampOpt.get(), nowOpt.get(), timestampNowOpt.get());
		return o;
	}



	/**
	 * <p>Values generated from Java, converted to sql-parameter, and back to Java-type.
	 * 
	 * @author Alberto Romero
	 * @since 2025-07-22
	 */
	public TestJTypeToSqlParamToJType testJTypeToSqlParamToJType() {
		log.info("Start testJTypeToSqlParamToJType");
		Date date = new Date();
		String dateStr = date.toString();
		Optional<String> stringStringOpt = mysqlTimeMapper.selectParamStringAsString(dateStr);
		Optional<String> dateStringOpt = mysqlTimeMapper.selectParamDateAsString(date);
		Optional<Date> dateDateOpt = mysqlTimeMapper.selectParamDateAsDate(date);
		TestJTypeToSqlParamToJType o = new TestJTypeToSqlParamToJType();
		o.setJStringJString(stringStringOpt.get());
		o.setJDateJString(dateStringOpt.get());
		o.setJDateJDate(dateDateOpt.get());
		log.info("jStringJString: {}, jDateJString: {}, jDateJDate: {}", stringStringOpt.get(), dateStringOpt.get(), dateDateOpt.get());
		return o;
	}



	/*
	 * MAPPER
	 * 
	 */

	/**
	 * <p>Mapper intended to read mysql time related behavior.
	 * 
	 * @author Alberto Romero
	 * @since 2025-07-22
	 */
	@Mapper
	private interface MysqlTimeMapper {

		/*
		 * Sql-session-timeZone
		 */

		@Select("SELECT @@GLOBAL.system_time_zone")
		Optional<String> selectGlobalSystemTimeZone();

		@Select("SELECT @@GLOBAL.time_zone")
		Optional<String> selectGlobalTimeZone();

		@Select("SELECT @@SESSION.time_zone")
		Optional<String> selectSessionTimeZone();

		/**
		 * <p>It is OK for this method to return 0 rows updated.
		 * 
		 * <p>Example values for <i>timeZone</i>:
		 * <ul>
		 * <li>"+00:00"
		 * <li>"UTC" <i>-- Available if pre-loaded in mysql timezone table</i>
		 * </ul>
		 * 
		 */
		@Update("SET @@SESSION.time_zone = #{timeZone}")
		Integer setSessionTimeZone(String timeZone);



		/*
		 * Sql-function-output, as Java-String
		 */

		@Select("SELECT UTC_TIMESTAMP()")
		Optional<String> selectUtcTimestampAsString();

		@Select("SELECT NOW()")
		Optional<String> selectNowAsString();

		@Select("SELECT TIMESTAMP( NOW() )")
		Optional<String> selectTimestampNowAsString();



		/*
		 * Sql-function-output, as Java-Date
		 */

		@Select("SELECT UTC_TIMESTAMP()")
		Optional<Date> selectUtcTimestampAsDate();

		@Select("SELECT NOW()")
		Optional<Date> selectNowAsDate();

		@Select("SELECT TIMESTAMP( NOW() )")
		Optional<Date> selectTimestampNowAsDate();



		/*
		 * Java-type to sql-parameter, and back to Java-type
		 */

		@Select("SELECT #{paramString}")
		Optional<String> selectParamStringAsString(String paramString);

		@Select("SELECT #{paramDate}")
		Optional<String> selectParamDateAsString(Date paramDate);

		@Select("SELECT #{paramDate}")
		Optional<Date> selectParamDateAsDate(Date paramDate);

	}




	/*
	 * POJOS
	 *  
	 */

	@Getter
	@Setter
	@NoArgsConstructor
	public static class MysqlTimeHelperPojo {
		private SpringbootDatasourceUrlReqParams springbootDatasourceUrlReqParams;
		private TestSessionTimeZone testSessionTimeZone;
		private TestSqlOutputToJString testSqlOutputToJString;
		private TestSqlOutputToJDate testSqlOutputToJDate;
		private TestJTypeToSqlParamToJType testJTypeToSqlParamToJType;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class SpringbootDatasourceUrlReqParams {
		private String serverTimezone;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class TestSessionTimeZone {
		private String globalSystemTimeZone;
		private String globalTimeZone;
		private String sessionTimeZone;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class TestSqlOutputToJString {
		private String utcTimestamp;
		private String now;
		private String timestampNow;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class TestSqlOutputToJDate {
		private Date utcTimestamp;
		private Date now;
		private Date timestampNow;

		public String getUtcTimestamp() {
			return this.utcTimestamp.toString();
		}

		public String getNow() {
			return this.now.toString();
		}

		public String getTimestampNow() {
			return this.timestampNow.toString();
		}
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class TestJTypeToSqlParamToJType {
		private String jStringJString;
		private String jDateJString;
		private Date jDateJDate;

		public String getJDateJDate() {
			return this.jDateJDate.toString();
		}
	}

}
