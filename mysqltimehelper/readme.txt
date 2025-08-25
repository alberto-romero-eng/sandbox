MysqlTimeHelper -- updated 2025-08-25 01:20

Notes:



(1) Jdbc url (spring.datasource.url):
  jdbc:mysql://localhost:3306/my_database?allowMultiQueries=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8&useSSL=false

Attention to url parameter:
  serverTimezone=UTC
'UTC' is a Java Timezone designation.

So:
- Date-time from mysql is retrieved without timezone indication; thus this timezone to build Java-Date type.
- Java-Date is sent to mysql without timezone indication; thus this timezone also for respective definition.



(2) Connection init sql (spring.datasource.hikari.connection-init-sql):
  'SET @@SESSION.time_zone = "+00:00"'

Attention to value:
  "+00:00"
Named timezones like 'UTC' cannot be used if not registered in mysql configuration / system tables.

Using "+00:00" ensures that sql functions 'NOW()' and 'UTC_TIMESTAMP()' produce equal output.
Remember:
- 'NOW()' depends on session timezone.
- 'UTC_TIMESTAMP()' does NOT depend on session timezone.



(3) Recommended strategy is using 'UTC' for (1) and "+00:00" for (2).



(4) Typical MysqlTimeHelper test json output:
{
  "springbootDatasourceUrlReqParams": {
    "serverTimezone":   "UTC"
  },
  "testSessionTimeZone": {
    "globalSystemTimeZone": "Venezuela Standard Time",
    "globalTimeZone":       "SYSTEM",
    "sessionTimeZone":      "+00:00"
  },
  "testSqlOutputToJString": {
    "utcTimestamp":   "2025-08-23 22:02:14",
    "now":            "2025-08-23 22:02:14",
    "timestampNow":   "2025-08-23 22:02:14"
  },
  "testSqlOutputToJDate": {
    "utcTimestamp":   "Sat Aug 23 18:02:14 VET 2025",
    "now":            "Sat Aug 23 18:02:14 VET 2025",
    "timestampNow":   "Sat Aug 23 18:02:14 VET 2025"
  },
  "testJTypeToSqlParamToJType": {
    "jstringJString": "Sat Aug 23 18:02:14 VET 2025",
    "jdateJString":   "2025-08-23 22:02:14.067",
    "jdateJDate":     "Sat Aug 23 18:02:14 VET 2025"
  }
}

