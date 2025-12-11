package localhost.sandbox.jse8.JavaLang.ScratchPad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import localhost.sandbox.jse8.Object.Pojo.Person;

public class JavaLangScratchPadMain {


	private enum Letter {
		A,
		B,
		C
	}


	public static void main() {

		System.out.println("Hello from JavaLangScratchPadMain!");


		// test 2025-12-01
		String file1 = "order_20251120220216_3467597.csv";
		String from = "20251101000000";


		String[] file1Parts = file1.split("_");
		System.out.println("file1Parts[1]: " + file1Parts[1]);

		Long fromL = Long.parseLong(from);
		Long file1L = Long.parseLong(file1Parts[1]);
		boolean file1GreaterThanFrom = file1L > fromL;
		System.out.println("file1GreaterThanFrom: " + file1GreaterThanFrom);








		// test 2025-10-05
		argTripleDotTest();
		argTripleDotTest("a");
		argTripleDotTest("a", "b");
		argTripleDotTest("a", "b", "c");
		argTripleDotTest("a", "b", "c", "d");




		// test 2025-08-29
		/* String url = "http:/login/success";
		String url2 = url.replace("http:", "");
		System.out.println("url: " + url);
		System.out.println("url2: " + url2); */




		// test 2025-08-18
		/*String envName;
		String additionalInfo = "env=" + "PRE";
		String envNameForReq;

		// PRE
		envName = "PRE";
		envNameForReq = envName.split("-")[0];
		additionalInfo = "env=" + envNameForReq;
		System.out.println("additionalInfo: " + additionalInfo);

		// WM1-PRO-GOODBUY
		envName = "WM1-PRO-GOODBUY";
		envNameForReq = envName.split("-")[0];
		additionalInfo = "env=" + envNameForReq;
		System.out.println("additionalInfo: " + additionalInfo);*/







		// other

		/* Letter letter = null;

		if (letter == null) {
			letter = Letter.A;
		}

		switch (letter) {
		case A:
			System.out.println("letter is: " + letter);
		case B:
			System.out.println("letter is: " + letter);
		case C:
		default:
			System.out.println("letter is: " + letter);
		} */






		/*
		Set<CustomerRole> crSet = new HashSet<>();
		crSet.add(new CustomerRole("Alberto", "alberto@email.com"));
		crSet.add(new CustomerRole("Alberto", "alberto@email.com"));
		crSet.add(new CustomerRole("Alberto", "alberto@email.com"));

		System.out.println("crSet: " + crSet);
		 */





		/*
		doSomething("right this!");
		doSomething(null);
		System.out.println("method: ");
		 */






		/*
		String s = null;
		boolean b = true;
		try {
			s.equals("ok?");
		} catch (Throwable e) {
			System.out.println("error -- " + e);
			System.out.println("error -- " + e.getClass() + " -- " + e.getMessage() + " -- " + e.getCause());
			System.out.println("error -- " + e.getClass().getSimpleName() + " -- " + e.getMessage() + " -- " + e.getCause());	
		}
		 */






		/*
		// init
		Map<Integer,String> map = new HashMap<>();
		System.out.println("map: " + map);

		// 1st value
		map.put(1, "Mary");
		System.out.println("map: " + map);

		// 2nd value
		map.put(1, "Ponky");
		System.out.println("map: " + map);

		// 3rd value
		map.put(1, "Sarah");
		System.out.println("map: " + map);
		 */







		/*
		List<String> stringList = new ArrayList<>();
		System.out.println("stringList.getClass(): " + stringList.getClass());
		stringList.add("hello");

		if ( stringList.get(0) instanceof String  ) {
			System.out.println("stringList.get(0) instanceof String : true");
			System.out.println("stringList.get(0).getClass() : " + stringList.get(0).getClass());
		}
		 */





		/*
		long t1 = System.currentTimeMillis() ;
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long t2 = System.currentTimeMillis() ;

		float diffSecs = (float) ( (t2-t1) / 1000.0 ) ;

		System.out.println("t1:" + t1 + " ; t2: " + t2 + " ; diffSecs: " + diffSecs);
		 */





		/*
		for (int i = 0 ; i < 100*1000 ; i++ ) {

			if ( (i % 1000) == 0) {
				System.out.println("i: " + i);	
			}

		}
		 */








		/*
		String cumm = "";
		cumm += String.format("string: %s ; int: %s ; long: %s ; boolean: %s ; double: %s ----", "hi!", 1, 10L, false, 3.1416);
		System.out.println(cumm);
		 */


		/*
		String ts1 = String.format("Download error, file empty -- fileName: %s ; storeId: %s ; job: %s", "a", "b", 1);
		System.out.println(ts1);
		 */



		/*
		String listStr = "alberto,mary,nena,josefina".toLowerCase();
		String[] listArr = listStr.split(",");
		Set<String> listSet = new HashSet<String>();
		listSet.addAll(Arrays.asList(listArr));
		System.out.println("listSet: " + listSet);
		System.out.println("alberto: " + listSet.contains("alberto"));
		System.out.println("diego: " + listSet.contains("diego"));
		 */












		/* 
		String s = "hello";
		String s2 = "hello";

		if (s!=null && s.equalsIgnoreCase(s2)) {
		    System.out.println("one!");
		} else if (s!=null && s.equalsIgnoreCase(s2)) {
		    System.out.println("two!");
		}
		 */



		/*
		String s = "Alberto";

		System.out.println("s.getClass(): " + s.getClass() );


		PersonDetail personDetail = new PersonDetail();

		personDetail.name = "Alberto";

		if (personDetail == null || personDetail.name == null) {
			System.out.println("personDetail or personDetail.name is null");
		}

		if (personDetail != null && personDetail.name != null) {
			System.out.println("personDetail and personDetail.name are different to null");
		}
		 */




		// test 2024-12-04
		/* String input = "0004234955132";
		String output = Long.valueOf(input).toString();
		System.out.println("input: " + input + " ; output: " + output); */

	}

	private static void argTripleDotTest(String... args) {
		System.out.println("Hello from argTripleDotTest()! -- arg.length: " + args.length);
	}


	private static void attributeProcess() {
		// test 2025-09-05, Attribute, 2

		// source attributes
		NameValueListType[] srcList = new NameValueListType[] {
				new NameValueListType("left_size", new String[] {"10cm"}),
				new NameValueListType("brand", new String[] {"Lenovo"}),
				new NameValueListType("voltage", new String[] {"120V"}),
		};
		System.out.println("srcNvlt: " + srcList);

		// required attribues names
		LinkedHashSet<String> rNameSet = new LinkedHashSet<>();
		rNameSet.addAll(Arrays.asList("BRAND", "WEIGHT"));

		// target list, populate
		List<NameValueListType> tgtList = new ArrayList<>();
		for (NameValueListType srcNvlt : srcList) {
			tgtList.add(new NameValueListType(srcNvlt));
		}

		// target list, homologate char-case in names of source and required attributes
		Iterator<NameValueListType> tgtIterator = tgtList.iterator();
		List<NameValueListType> tmpAddLaterList = new ArrayList<>();
		while (tgtIterator.hasNext()) {
			@SuppressWarnings("unchecked")
			NameValueListType tgtAttr = tgtIterator.next();
			for (String rName : rNameSet) {
				if (tgtAttr.getName().equalsIgnoreCase(rName) && !tgtAttr.getName().equals(rName)) {
					tmpAddLaterList.add(new NameValueListType(rName, tgtAttr.getValues()));
					tgtIterator.remove();
					break;
				}
			}
		}
		tgtList.addAll(tmpAddLaterList);
		System.out.println("tgtList, rName homologation: " + tgtList);

		// target list, add missing required attributes with value "N.A."
		for (String rName : rNameSet) {
			boolean rNameAlreadyIncluded = false;
			for (NameValueListType tgtNvlt : tgtList) {
				if (tgtNvlt.getName().equals(rName)) {
					rNameAlreadyIncluded = true;
					break;
				}
			}
			if (!rNameAlreadyIncluded) {
				tgtList.add(new NameValueListType(rName, new String[] { "N.A." } ));
			}
		}
		System.out.println("tgtList, rName missing addition: " + tgtList);



		// test 2025-09-04, Attribute, 1
		// source attributes
		/* LinkedHashMap<String,String> srcMap = new LinkedHashMap<>();
		srcMap.put("left_size", "10cm");
		srcMap.put("brand", "lenovo");
		srcMap.put("voltage", "120V");
		System.out.println("srcMap: " + srcMap);

		// required attributes names
		LinkedHashSet<String> mSet = new LinkedHashSet<>();
		mSet.add("BRAND");
		mSet.add("WEIGHT");
		System.out.println("mSet: " + mSet);


		// target map, populate
		LinkedHashMap<String,String> tgtMap = new LinkedHashMap<>();
		Set<String> srcKeySet = srcMap.keySet();
		for (String srcKey : srcKeySet) {
			tgtMap.put(srcKey, srcMap.get(srcKey));
		}

		// target map, homologate char-case in names of source and required attributes
		Iterator<Map.Entry<String,String>> tgtIterator = tgtMap.entrySet().iterator();
		LinkedHashMap<String,String> tmpPutLaterMap = new LinkedHashMap<>();
		while (tgtIterator.hasNext()) {
			@SuppressWarnings("unchecked")
			Map.Entry<String,String> tgtMapEntry = (Map.Entry) tgtIterator.next();
			for (String mKey : mSet) {
				if (tgtMapEntry.getKey().equalsIgnoreCase(mKey) && !tgtMapEntry.getKey().equals(mKey)) {
					tmpPutLaterMap.put(mKey, tgtMapEntry.getValue());
					tgtIterator.remove();
					break;
				}
			}
		}
		tgtMap.putAll(tmpPutLaterMap);
		System.out.println("tgtMap: " + tgtMap); */
	}



	public static void doSomething(String some) {
		System.out.println("doSomething method -- params -- some: " + some);
	}


	public static class CustomerRole {

		private String email;
		private String role;

		public CustomerRole() {
			super();
		}

		public CustomerRole(String email, String role) {
			super();
			this.email = email;
			this.role = role;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

	}


	public static class NameValueListType {

		private String name;

		private List<String> values;


		// constructors

		public NameValueListType() {
			super();
		}

		public NameValueListType(NameValueListType srcAttr) {
			super();
			this.name = srcAttr.name;
			this.values = new ArrayList<>();
			for (String v : srcAttr.getValues()) {
				this.values.add(v);
			}
		}

		public NameValueListType(String name, String[] values) {
			super();
			this.name = name;
			this.values = new ArrayList<>();
			for (String v : values) {
				this.values.add(v);
			}
		}

		public NameValueListType(String name, List<String> values) {
			super();
			this.name = name;
			this.values = new ArrayList<>();
			for (String v : values) {
				this.values.add(v);
			}
		}


		// getters, setters

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<String> getValues() {
			return values;
		}

		public void setValues(List<String> values) {
			this.values = values;
		}




	}

}	
