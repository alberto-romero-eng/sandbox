package localhost.opencsv.sandbox;

// import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.opencsv.bean.HeaderNameBaseMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

public class TestWriteCsv {

	static public void Test00WriteCsvSimple() {

		// csv target file
		String filenameStr = null;
		filenameStr = "out_sample00.csv";
		// filenameStr = "out_sample00__simple.csv";
		// filenameStr = "out_sample00__single_quote.csv";
		// filenameStr = "out_sample00__double_quote.csv";
		// filenameStr = "out_sample00__quote_variations.csv";

		System.out.println("Test00WriteCsvSimple start -- file source: " + filenameStr);

		String folderStr = "./__csv_sample";
		String pathStr = folderStr + "/" + filenameStr;
		Path path = null;

		Writer writer = null;
		// FileWriter fileWriter = null;
		CSVWriter csvWriter = null;
		String[] csvLine = null;

		List<Sample00BeanSimple> sampleBeanList = new ArrayList<Sample00BeanSimple>();
		sampleBeanList.add(new Sample00BeanSimple("Z","0"));
		sampleBeanList.add(new Sample00BeanSimple("Y","9"));
		sampleBeanList.add(new Sample00BeanSimple("X","8"));
		sampleBeanList.add(new Sample00BeanSimple("W,WW","7,77"));
		sampleBeanList.add(new Sample00BeanSimple("V'VV","6'66"));
		sampleBeanList.add(new Sample00BeanSimple("U\"UU","5\"55"));

		try {
			path = Paths.get(pathStr);

			writer = Files.newBufferedWriter(path);

			// fileWriter = new FileWriter(path.toString());

			// CsvWriter works fine with both Reader and FileReader
			csvWriter = (CSVWriter)
					new CSVWriterBuilder(writer)
					// new CSVWriterBuilder(fileWriter)
					.withQuoteChar('\'')
					.withSeparator(',')
					.withLineEnd("\r\n")
					.withEscapeChar('\\')
					.build();

			writer.write("'letter','number'" + "\r\n"); // writer / fileWriter

			for (Sample00BeanSimple sBean : sampleBeanList) {

				csvLine = new String[2];
				csvLine[0] = sBean.getLetter();
				csvLine[1] = sBean.getNumber();

				csvWriter.writeNext(csvLine);

			}

			csvWriter.close();

			System.out.println("see target file: " + filenameStr);

		} catch (Exception e) {

			System.err.println("error: " + e);

		}

	}


	static public void Test01AWriteCsvBindByNameWithMappingStrategy() {

		// csv target file
		String filenameStr = null;
		filenameStr = "out_sample01.csv";

		System.out.println("Test01AWriteCsvBindByNameWithMappingStrategy start -- file target: " + filenameStr);

		List<Sample00BeanBindByName> sampleBeanList = new ArrayList<Sample00BeanBindByName>();
		sampleBeanList.add(new Sample00BeanBindByName("Z","0"));
		sampleBeanList.add(new Sample00BeanBindByName("Y","9"));
		sampleBeanList.add(new Sample00BeanBindByName("X","8"));
		sampleBeanList.add(new Sample00BeanBindByName("W,WW","7,77"));
		sampleBeanList.add(new Sample00BeanBindByName("V'VV","6'66"));
		sampleBeanList.add(new Sample00BeanBindByName("U\"UU","5\"55"));

		String folderStr = "./__csv_sample";
		String pathStr = folderStr + "/" + filenameStr;
		Path path = null;
		Writer writer = null;
		StatefulBeanToCsv<Sample00BeanBindByName> csvWriter = null;

		HeaderNameBaseMappingStrategy<Sample00BeanBindByName> mappingStrategy = null;
		mappingStrategy = new HeaderColumnNameMappingStrategy<>();
		// mappingStrategy = new HeaderColumnNameTranslateMappingStrategy<>(); // when not using annotated Beans

		try {
			path = Paths.get(pathStr);
			writer = Files.newBufferedWriter(path);
			csvWriter = new StatefulBeanToCsvBuilder<Sample00BeanBindByName>(writer)
					.withApplyQuotesToAll(true)
					.withQuotechar('\'')
					.withSeparator(',')
					.withLineEnd("\r\n")
					.withEscapechar('\\')
					.withMappingStrategy(mappingStrategy)
					.build();

			csvWriter.write(Sample00BeanBindByName.getHeaders());

			csvWriter.write(sampleBeanList);
			writer.flush();
			writer.close();

			System.out.println("see target file: " + filenameStr);

		} catch (Exception e) {

			System.err.println("error: " + e);

		}

	}


}
