package localhost.opencsv.sandbox;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import localhost.opencsv.sandbox.SimpleDateFormatHelper.Pattern;

public class TestReadCsvVendorOrder {

	private final static SimpleDateFormatHelper SDFH = new SimpleDateFormatHelper(Pattern.TIMESTAMP_ID);

	public static void readVendorOrderCsv() {

		String timestampId = SDFH.format(new Date());

		// csv feed io definitions
		String srcLocalFilename = "order_20251120220216_3467597.csv";
		String srcLocalFolder = "./tmp/";
		Path path = Paths.get(srcLocalFolder + srcLocalFilename);
		try (Reader reader = Files.newBufferedReader(path)) {
			CSVParser csvParser = null;
			CSVReader csvReader = null;
			String[] csvLine = null;
			csvParser = new CSVParserBuilder()
					.withSeparator(';')
					.withIgnoreQuotations(false)
					.withQuoteChar('"')
					.withEscapeChar('\\')
					.build();
			csvReader =
					new CSVReaderBuilder(reader)
					.withSkipLines(0) // implemented with index i condition (continue)
					.withCSVParser(csvParser)
					.build();
			// data reading loop
			List<VendorOrder> voList = new ArrayList<>();
			int i = 0;
			csvLine = csvReader.readNext();
			while ( csvLine != null ) {
				i++;
				try {
					// do nothing with csv headers line
					if (i == 1) {
						csvLine = csvReader.readNext();
						continue;
					}
					// actual data line
					VendorOrder vo = VendorOrder.from(csvLine);
					voList.add(vo);
					csvLine = csvReader.readNext();
				} catch (Exception e) {
					csvLine = csvReader.readNext();
					continue;
				}
			}
			csvReader.close();
		} catch (Exception ex) {
			// log.error("exeption -- ", ex);
		}
	}

}
