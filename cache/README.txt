See reminder comment in pom.xml

2025-05-11:
Default VM Args in launcher configuration:
-Xms8m
-Xmx192m
-Dspring.profiles.active=default

Non-recognized system properties are simply ignored, so this is feasible:
-D__ZZZ__com.zaxxer.hikari.housekeeping.periodMs=6000

