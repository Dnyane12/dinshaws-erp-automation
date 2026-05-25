package utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportManager {
	private static final String REPORT_BASE_PATH = System.getProperty("user.dir") + "/reports";
	private static final int DAYS_TO_KEEP = 7;
	private static String reportFolderPath;

	static {
		deleteOldReports();
		String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		reportFolderPath = REPORT_BASE_PATH + "/" + timestamp;
		createFolders();
	}

	private static void createFolders() {
		new File(reportFolderPath).mkdirs();
		new File(reportFolderPath + "/screenshots").mkdirs();
	}

	public static String getReportFolderPath() {
		return reportFolderPath;
	}

	public static String getScreenshotFolderPath() {
		return reportFolderPath + "/screenshots";
	}

	private static void deleteOldReports() {
		File reportsDir = new File(REPORT_BASE_PATH);
		if (!reportsDir.exists()) {
			return;
		}
		File[] reportFolders = reportsDir.listFiles();
		if (reportFolders == null) {
			return;
		}
		
		long purgeTime = System.currentTimeMillis() - (DAYS_TO_KEEP * 24L * 60 * 60 * 1000);
		for (File folder : reportFolders) {
			if (folder.isDirectory() && folder.lastModified() < purgeTime) {
				try {
					FileUtils.deleteDirectory(folder);
					System.out.println("Deleted old report folder: " + folder.getName());
				} catch (IOException e) {
					System.out.println("Unable to delete folder: " + folder.getName());
					e.printStackTrace();
				}
			}
		}
	}
}