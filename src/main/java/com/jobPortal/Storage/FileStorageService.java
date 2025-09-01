package com.jobPortal.Storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileStorageService {

	private final Path root;

	public FileStorageService(@Value("${app.upload.dir:uploads/resumes}") String uploadDir) throws IOException {
		this.root = Paths.get(uploadDir).toAbsolutePath().normalize();
		if (!Files.exists(root))
			Files.createDirectories(root);
	}

	public String store(MultipartFile file) throws IOException {
		if (file == null || file.isEmpty())
			return null;

		String original = file.getOriginalFilename();
		String ext = null;
		if (original != null && original.contains("."))
			ext = original.substring(original.lastIndexOf('.'));

		String filename = "resume_" + UUID.randomUUID() + (ext != null ? ext : ".pdf");
		Path dest = root.resolve(filename).normalize().toAbsolutePath();
		Files.copy(file.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);
		return dest.toString();
	}

	public void deleteIfExists(String pathStr) {
		if (pathStr == null || pathStr.isBlank())
			return;
		try {
			Files.deleteIfExists(Paths.get(pathStr));
		} catch (Exception ignored) {
		}
	}
}
