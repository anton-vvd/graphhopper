package com.graphhopper.osmdownloader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OSMDownloaderTest {

    @TempDir
    Path tempDir;

    private OSMDownloader downloader;

    @BeforeEach
    void setUp() {
        downloader = new OSMDownloader(tempDir.toString(), false);
    }

    @Test
    void testDownloadIsraelAndPalestine() throws IOException {
        // Download the region
        downloader.downloadRegion("israel-and-palestine");

        // Verify that files were created
        List<Path> files = Files.list(tempDir).toList();
        assertFalse(files.isEmpty(), "No files were downloaded");
        
        // Verify that at least one .osm.pbf file exists
        boolean hasPbfFile = files.stream()
                .anyMatch(path -> path.toString().endsWith(".osm.pbf"));
        assertTrue(hasPbfFile, "No .osm.pbf file was downloaded");

        // Verify file size (should be more than 1MB)
        Path pbfFile = files.stream()
                .filter(path -> path.toString().endsWith(".osm.pbf"))
                .findFirst()
                .orElseThrow();
        assertTrue(Files.size(pbfFile) > 1024 * 1024, "Downloaded file is too small");
    }

    @Test
    void testInvalidRegion() {
        assertThrows(IllegalArgumentException.class, () -> {
            downloader.downloadRegion("non-existent-region");
        });
    }

    @Test
    void testKeepOldFiles() throws IOException {
        // Download twice with keepOldFiles=true
        OSMDownloader downloaderWithKeep = new OSMDownloader(tempDir.toString(), true);
        downloaderWithKeep.downloadRegion("israel-and-palestine");
        downloaderWithKeep.downloadRegion("israel-and-palestine");

        // Verify that multiple files exist
        List<Path> files = Files.list(tempDir).toList();
        assertTrue(files.size() > 1, "Multiple files should exist when keepOldFiles is true");
    }

    @Test
    void testCleanupOldFiles() throws IOException {
        // Download twice with keepOldFiles=false
        downloader.downloadRegion("israel-and-palestine");
        downloader.downloadRegion("israel-and-palestine");

        // Verify that only one file exists
        List<Path> files = Files.list(tempDir).toList();
        assertEquals(1, files.size(), "Only one file should exist when keepOldFiles is false");
    }
} 