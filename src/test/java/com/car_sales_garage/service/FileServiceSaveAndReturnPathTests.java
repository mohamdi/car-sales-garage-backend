package com.car_sales_garage.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FileServiceSaveAndReturnPathTests {

    @InjectMocks
    private FileService service;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(service, "uploadPath", "uploads");
    }

    @Test
    void saveAndReturnPath_ShouldReturnsGeneratedFileName_WhenFileIsValid() {
        String originalFilename = "test.txt";
        MultipartFile file = new MockMultipartFile(originalFilename, originalFilename, "text/plain", "content".getBytes());
        String result = service.saveAndReturnPath(file);
        assertNotNull(result);
        assertTrue(result.matches("\\d{4}-\\d{2}-\\d{2}T.*_" + originalFilename));
    }

    @Test
    void saveAndReturnPath_ShouldThrowException_WhenFileIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.saveAndReturnPath(null);
        });
    }

    @Test
    void saveAndReturnPath_ShouldThrowException_WhenFileIsEmpty() {
        MultipartFile emptyFile = new MockMultipartFile("empty.txt", "empty.txt", "text/plain", new byte[0]);
        assertThrows(IllegalArgumentException.class, () -> {
            service.saveAndReturnPath(emptyFile);
        });
    }
}
