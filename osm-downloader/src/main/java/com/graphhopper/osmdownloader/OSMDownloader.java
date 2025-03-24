package com.graphhopper.osmdownloader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
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
        logger.debug("Starting download for region: {}", region);
        
        // Create download directory if it doesn't exist
        Path downloadPath = Paths.get(downloadDir);
        Files.createDirectories(downloadPath);

        // Get the download URL for the region
        String downloadUrl = getDownloadUrl(region);
        if (downloadUrl == null) {
            throw new IllegalArgumentException("Region not found: " + region);
        }

        System.out.println("Downloading region: " + region);
        
        // Generate filename with timestamp
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = String.format("%s_%s.osm.pbf", region, timestamp);
        Path filePath = downloadPath.resolve(filename);

        // Download the file
        downloadFile(downloadUrl, filePath);
        System.out.println("\nDownload completed: " + filePath);
        logger.debug("Successfully downloaded {} to {}", region, filePath);

        // Clean up old files if not keeping them
        if (!keepOldFiles) {
            cleanupOldFiles(region);
        }
    }

    private String getDownloadUrl(String targetRegion) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(GEOFABRIK_INDEX_URL);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                JsonNode root = objectMapper.readTree(response.getEntity().getContent());
                
                // First check if the region is in the root level files
                JsonNode files = root.get("files");
                if (files != null) {
                    for (JsonNode file : files) {
                        if (file.get("id") != null && file.get("id").asText().equals(targetRegion)) {
                            return file.get("urls").get("pbf").asText();
                        }
                    }
                }
                
                // If not found in root, check all parent regions
                JsonNode regions = root.get("regions");
                if (regions != null) {
                    for (JsonNode region : regions) {
                        String parentRegionId = region.get("id").asText();
                        
                        // Check files in this region
                        files = region.get("files");
                        if (files != null) {
                            for (JsonNode file : files) {
                                if (file.get("id") != null && file.get("id").asText().equals(targetRegion)) {
                                    // If the file has a direct URL in the index, use it
                                    if (file.get("urls") != null && file.get("urls").get("pbf") != null) {
                                        return file.get("urls").get("pbf").asText();
                                    }
                                    // Otherwise construct the URL using the pattern
                                    return GEOFABRIK_BASE_URL + parentRegionId + "/" + targetRegion + "-latest.osm.pbf";
                                }
                            }
                        }
                        
                        // Check subregions
                        JsonNode subregions = region.get("subregions");
                        if (subregions != null) {
                            for (JsonNode subregion : subregions) {
                                files = subregion.get("files");
                                if (files != null) {
                                    for (JsonNode file : files) {
                                        if (file.get("id") != null && file.get("id").asText().equals(targetRegion)) {
                                            // If the file has a direct URL in the index, use it
                                            if (file.get("urls") != null && file.get("urls").get("pbf") != null) {
                                                return file.get("urls").get("pbf").asText();
                                            }
                                            // Otherwise construct the URL using the pattern
                                            String parentId = subregion.get("parent").asText();
                                            return GEOFABRIK_BASE_URL + parentId + "/" + targetRegion + "-latest.osm.pbf";
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                
                // If the region is not found in the index, try the default URL pattern
                if ("israel-and-palestine".equals(targetRegion)) {
                    return GEOFABRIK_BASE_URL + "asia/" + targetRegion + "-latest.osm.pbf";
                }
                
                throw new IllegalArgumentException("Region not found: " + targetRegion);
            }
        }
    }

    private static class ProgressTracker {
        private final long totalBytes;
        private long downloadedBytes;
        private long lastUpdateTime;
        private static final int UPDATE_INTERVAL_MS = 100; // Update progress every 100ms

        public ProgressTracker(long totalBytes) {
            this.totalBytes = totalBytes;
            this.downloadedBytes = 0;
            this.lastUpdateTime = System.currentTimeMillis();
        }

        public void update(int bytesRead) {
            downloadedBytes += bytesRead;
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastUpdateTime > UPDATE_INTERVAL_MS) {
                printProgress();
                lastUpdateTime = currentTime;
            }
        }

        private void printProgress() {
            if (totalBytes <= 0) return;
            
            int progressBarWidth = 50;
            double progress = (double) downloadedBytes / totalBytes;
            int progressChars = (int) (progressBarWidth * progress);
            
            StringBuilder bar = new StringBuilder("[");
            for (int i = 0; i < progressBarWidth; i++) {
                bar.append(i < progressChars ? "=" : " ");
            }
            bar.append("]");
            
            String bytesStr = String.format("%.1f/%.1f MB", 
                downloadedBytes / (1024.0 * 1024.0),
                totalBytes / (1024.0 * 1024.0));
            
            String progressStr = String.format("%3d%%", (int) (progress * 100));
            
            // Clear line and print progress
            System.out.print("\r" + progressStr + " " + bar + " " + bytesStr);
            
            if (downloadedBytes >= totalBytes) {
                System.out.println(); // New line when done
            }
        }
    }

    private void downloadFile(String url, Path filePath) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                long contentLength = response.getEntity().getContentLength();
                ProgressTracker progress = new ProgressTracker(contentLength);
                
                try (InputStream inputStream = response.getEntity().getContent();
                     FileOutputStream outputStream = new FileOutputStream(filePath.toFile())) {
                    
                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        progress.update(bytesRead);
                    }
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
                        logger.debug("Deleted old file: {}", file.getName());
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