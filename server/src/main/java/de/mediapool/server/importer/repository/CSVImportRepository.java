package de.mediapool.server.importer.repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.opencsv.CSVReader;

@Repository
public class CSVImportRepository {

	private static final Logger logger = LoggerFactory.getLogger(CSVImportRepository.class);

	public void importCSV (String csvFileName) {
		logger.debug("Invoking: importCSV(csvFileName)");

		CSVReader reader = null;
		
		try {
			reader = new CSVReader(new FileReader(csvFileName));
			String [] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				// nextLine[] is an array of values from the line
				System.out.println(nextLine[0] + nextLine[1] + "etc...");
			}
		} catch (IOException e) {
			
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) { }
			}
		}
	}
}
