package com.graphhopper.osmdownloader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OSMDownloader {
    private static final Logger logger = LoggerFactory.getLogger(OSMDownloader.class);
    private static final String GEOFABRIK_BASE_URL = "https://download.geofabrik.de/";
    private static final String GEOFABRIK_INDEX_URL = GEOFABRIK_BASE_URL + "index-v1.json";
    private static final String DEFAULT_DOWNLOAD_DIR = "osm-data";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final String downloadDir;
    private final boolean keepOldFiles;

    public OSMDownloader(String downloadDir, boolean keepOldFiles) {
        this.downloadDir = downloadDir != null ? downloadDir : DEFAULT_DOWNLOAD_DIR;
        this.keepOldFiles = keepOldFiles;
    }

    public void downloadRegion(String region) throws IOException {
        logger.info("Starting download for region: {}", region);
        
        // Create download directory if it doesn't exist
        Path downloadPath = Paths.get(downloadDir);
        Files.createDirectories(downloadPath);

        // Get the download URL for the region
        String downloadUrl = getDownloadUrl(region);
        if (downloadUrl == null) {
            throw new IllegalArgumentException("Region not found: " + region);
        }

        // Generate filename with timestamp
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = String.format("%s_%s.osm.pbf", region, timestamp);
        Path filePath = downloadPath.resolve(filename);

        // Download the file
        downloadFile(downloadUrl, filePath);
        logger.info("Successfully downloaded {} to {}", region, filePath);

        // Clean up old files if not keeping them
        if (!keepOldFiles) {
            cleanupOldFiles(region);
        }
    }

    private String getDownloadUrl(String region) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(GEOFABRIK_INDEX_URL);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                JsonNode root = objectMapper.readTree(response.getEntity().getContent());
                JsonNode files = root.get("files");
                
                // Look for the region in the files
                for (JsonNode file : files) {
                    if (file.get("name").asText().equals(region + ".osm.pbf")) {
                        return GEOFABRIK_BASE_URL + file.get("url").asText();
                    }
                }
            }
        }
        return null;
    }

    private void downloadFile(String url, Path filePath) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request);
                 InputStream inputStream = response.getEntity().getContent();
                 FileOutputStream outputStream = new FileOutputStream(filePath.toFile())) {
                
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        }
    }

    private void cleanupOldFiles(String region) throws IOException {
        Path downloadPath = Paths.get(downloadDir);
        File[] files = downloadPath.toFile().listFiles((dir, name) -> 
            name.startsWith(region + "_") && name.endsWith(".osm.pbf"));
        
        if (files != null) {
            // Keep only the most recent file
            File mostRecent = null;
            long mostRecentTime = 0;
            
            for (File file : files) {
                long lastModified = file.lastModified();
                if (lastModified > mostRecentTime) {
                    mostRecentTime = lastModified;
                    mostRecent = file;
                }
            }
            
            // Delete all files except the most recent
            for (File file : files) {
                if (file != mostRecent) {
                    if (file.delete()) {
                        logger.info("Deleted old file: {}", file.getName());
                    } else {
                        logger.warn("Failed to delete old file: {}", file.getName());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java -jar osm-downloader.jar <region> [download-dir] [--keep-old]");
            System.err.println("Example: java -jar osm-downloader.jar berlin /path/to/download --keep-old");
            System.exit(1);
        }

        String region = args[0];
        String downloadDir = args.length > 1 ? args[1] : null;
        boolean keepOldFiles = args.length > 2 && "--keep-old".equals(args[2]);

        try {
            OSMDownloader downloader = new OSMDownloader(downloadDir, keepOldFiles);
            downloader.downloadRegion(region);
        } catch (Exception e) {
            logger.error("Error downloading region: {}", e.getMessage(), e);
            System.exit(1);
        }
    }
} 