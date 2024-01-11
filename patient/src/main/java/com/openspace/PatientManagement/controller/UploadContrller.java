package com.openspace.PatientManagement.controller;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.openspace.Model.Config.FileConfig;
import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.DoctorManagement.Document;
import com.openspace.Model.DoctorManagement.Person;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.PatientMgnt.Repositories.PersonRepository;
import com.openspace.Model.PatientMgnt.Repositories.TherapistRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.PatientManagement.dto.Photo;
import com.openspace.PatientManagement.s3.AccessS3Bucket;
import com.openspace.PatientManagement.s3.S3LocatioDto;

@RestController
@RequestMapping(value = "/upload")
public class UploadContrller {

	@Autowired
	private FileConfig fileConfig;
	private Path rootLocation = Paths.get("upload-dir");

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private TherapistRepository therapistRepository;

	@RequestMapping(value = "/image", method = RequestMethod.POST)
	public Photo uploadImage(@RequestBody MultipartFile file) throws IOException {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if (!(extension.equalsIgnoreCase("jpg"))) {
			throw new RuntimeException("Please select file with .jpg");
		}

		String random = UUID.randomUUID().toString() + "." + extension;
		Files.copy(file.getInputStream(), this.rootLocation.resolve(random));
		return new Photo(random, random, null);
	}

 // @RequestMapping(value = "/{filename:.+}", method = RequestMethod.GET)
	public byte[] getBytes(@PathVariable("filename") String filename) throws IOException {
		Path path = load(filename);
		Resource file = new UrlResource(path.toUri());
		if (file.exists() || file.isReadable()) {
		} else {
			throw new RuntimeException("Could not read file: " + filename);

		}
		return Files.readAllBytes(Paths.get(filename));
	}

	public Resource loadFile(String filename) {
		try {
			Path file = Paths.get(fileConfig.getLocationpath()).resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}

	public Resource loadFile(String directory, String filename) {
		try {
			Path file = Paths.get(directory).resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}

	@GetMapping("/viewimage/{username:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable("username") String username) throws MalformedURLException {
		UserAccount dbuserAccount = userAccountRepository.findByUsername(username);
		if (dbuserAccount == null) {
			throw new RuntimeException("User Does not Exist");
		}
		Person dbPerson = personRepository.findByUserAccount_Id(dbuserAccount.getId());
		Resource file = loadFile(dbPerson.getFilePath());
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	@GetMapping("/imageview/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> viewFile(@PathVariable("filename") String filename) throws MalformedURLException {
		Resource file = loadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	@RequestMapping(value = "imageFroTherapistS3/{username:.+}", method = RequestMethod.GET)
	public S3LocatioDto getBytesDataS3Therapist(@PathVariable("username") String username) throws IOException {
		
		Person dbperson  = personRepository.findByEmail(username);
		Doctor dbDoctor = therapistRepository.findOne(dbperson.getId());
		S3LocatioDto s3LocatioDto = new S3LocatioDto();
		String filePathUrl =AccessS3Bucket.URL_TO_ACCESS_GLOBLA_EXTERAL_FILES_LOGOS + dbDoctor.getSignaturePath();
		s3LocatioDto.setLocation(filePathUrl);
		return s3LocatioDto;
	}

	private void setContentHeaders(HttpHeaders httpHeaders, String fileName) {
		httpHeaders.add(HttpHeaders.ACCEPT_RANGES, "bytes");
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
		httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
	}

	@RequestMapping(value = "/fileUploadInLocalDirectory", method = RequestMethod.POST)
	private Photo fileUploadInLocalDirectory(@RequestBody MultipartFile file) throws IOException {
		String fileName = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		inputStream = file.getInputStream();
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if (!(extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("png")
				|| extension.equalsIgnoreCase("gif"))) {
			throw new RuntimeException("Please use image with *.jpg, *.png, *.gif only");
		}
		String random = UUID.randomUUID().toString() + "." + extension;
		String orginalFileName= file.getOriginalFilename();
		fileName = fileConfig.getLocationpath() + file.getOriginalFilename();
		outputStream = new FileOutputStream(fileName);
		int readBytes = 0;
		byte[] buffer = new byte[10000];
		while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
			outputStream.write(buffer, 0, readBytes);
		}
		outputStream.close();
		inputStream.close();
		return new Photo(fileName,orginalFileName, file.getBytes());
	}

	@RequestMapping(value = "/deletefiles", method = RequestMethod.DELETE)
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@RequestMapping(value = "/createdirectory/{applicationpath}/{path}", method = RequestMethod.POST)
	public boolean createFolder(@PathVariable String applicationpath, @PathVariable String path) {
		boolean flag = false;
		try {
			File folder = new File(applicationpath + path);
			if (!folder.exists()) {

			}
			flag = true;
		} catch (Exception e) {
			// CalibrateItLogger.error("Folder creation failed",e);
		}
		return flag;
	}
	/*
	 * @RequestMapping(value = "/document/", method = RequestMethod.POST) public
	 * Document uploadDocument(@RequestBody MultipartFile file) throws
	 * IOException { String extension =
	 * FilenameUtils.getExtension(file.getOriginalFilename()); if
	 * (!(extension.equalsIgnoreCase("pdf") || extension.equalsIgnoreCase("doc")
	 * || extension.equalsIgnoreCase("docx"))) { throw new RuntimeException(
	 * "Please select file with pdf or doc"); } String random =
	 * UUID.randomUUID().toString() + "." + extension; System.out.println(
	 * "uploaded success"); Files.copy(file.getInputStream(),
	 * this.rootLocation.resolve(random)); Document document = new Document();
	 * document.setFileSize(file.getSize());
	 * document.setName(file.getOriginalFilename()); document.setPath(random);
	 * document.setFilePath(random); System.out.println("uploaded success");
	 * System.out.println("getname" + file.getName() + "orginalname" +
	 * file.getOriginalFilename()); return document; }
	 */

	@RequestMapping(value = "/document/", method = RequestMethod.POST)
	private Document uploadDocument(@RequestBody MultipartFile file) throws IOException {
		String fileName = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		Document document = new Document();
		if (file != null) {
			inputStream = file.getInputStream();
			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			if (!(extension.equalsIgnoreCase("pdf") || extension.equalsIgnoreCase("doc")
					|| extension.equalsIgnoreCase("docx") || extension.equalsIgnoreCase("csv")
					|| extension.equalsIgnoreCase("pdf") || extension.equalsIgnoreCase("xls")
					|| extension.equalsIgnoreCase("xlsx"))) {
				throw new RuntimeException("PDF,Docs and Excel File Types are allowed to upload!");
			}
			String random = UUID.randomUUID().toString() + "." + extension;

			fileName = fileConfig.getLocationpath() + file.getOriginalFilename();
			outputStream = new FileOutputStream(fileName);
			int readBytes = 0;
			byte[] buffer = new byte[100000];
			while ((readBytes = inputStream.read(buffer, 0, 100000)) != -1) {
				outputStream.write(buffer, 0, readBytes);
			}
			outputStream.close();
			inputStream.close();

			document.setFileSize(file.getSize());
			document.setName(file.getOriginalFilename());
			document.setPath(random);
			document.setFilePath(random);
			document.setExtension(extension);

		} else {
			throw new RuntimeException("Please Choose a file");
		}
		return document;
	}

	@RequestMapping(value = "image/{username:.+}", method = RequestMethod.GET)
	public byte[] getBytesData(@PathVariable("username") String username) throws IOException {
		UserAccount dbuserAccount = userAccountRepository.findByUsername(username);
		if (dbuserAccount == null) {
			throw new RuntimeException("User Does not Exist");
		}
		Person dbPerson = personRepository.findByUserAccount_Id(dbuserAccount.getId());
		Path path = Paths.get(dbPerson.getProfilePic());
		// Path path = load(tokenId + ".jpg");
		Resource file = new UrlResource(path.toUri());
		if (file.exists() || file.isReadable()) {
		} else {
			throw new RuntimeException("Could not read file: ");
		}
		return Files.readAllBytes(path);
	}
	
	
	@RequestMapping(value = "imageS3/{username:.+}", method = RequestMethod.GET)
	public S3LocatioDto getBytesDataS3(@PathVariable("username") String username) throws IOException {
		UserAccount dbuserAccount = userAccountRepository.findByUsername(username);
		if (dbuserAccount == null) {
			throw new RuntimeException("User Does not Exist");
		}
		Person dbPerson = personRepository.findByUserAccount_Id(dbuserAccount.getId());
		S3LocatioDto s3LocatioDto = new S3LocatioDto();
		String filePathUrl =AccessS3Bucket.URL_TO_ACCESS_GLOBLA_EXTERAL_FILES_LOGOS + dbPerson.getProfilePic();
		s3LocatioDto.setLocation(filePathUrl);
		return s3LocatioDto;
	}

	// @RequestMapping(value = "image/{username:.+}", method =
	// RequestMethod.GET)
	public byte[] extractBytes(@PathVariable("username") String username) throws IOException {
		// open image
		UserAccount dbuserAccount = userAccountRepository.findByUsername(username);
		if (dbuserAccount == null) {
			throw new RuntimeException("User Does not Exist");
		}
		Person dbPerson = personRepository.findByUserAccount_Id(dbuserAccount.getId());

		File imgPath = new File(dbPerson.getProfilePic());
		BufferedImage bufferedImage = ImageIO.read(imgPath);

		// get DataBufferBytes from Raster
		WritableRaster raster = bufferedImage.getRaster();
		DataBufferByte data = (DataBufferByte) raster.getDataBuffer();

		return (data.getData());
	}

	@RequestMapping(value = "/pdf/{documentId}", method = RequestMethod.GET, produces = "application/pdf")
	public ResponseEntity<InputStreamResource> download(@PathVariable("documentId") Long documentId)
			throws IOException {
		Document dbDocument = documentRepository.findOne(documentId);
		if (dbDocument == null) {
			throw new RuntimeException("Document Does not Exist");
		}
		Resource pdfFile = loadFile("http://dev.teamwork.s3.amazonaws.com/EN_9328_Ope/Patient/PA_5459_ope/Insurance/", "beginning-angularjs.pdf");
		/*
		 * ClassPathResource pdfFile = new ClassPathResource(
		 * "upload-dir/openspaceinnovates/Patient/2563525/0719dad3-c7ad-4669-a7d7-366547bcb18f.pdf"
		 * );
		 */
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
		headers.add("Access-Control-Allow-Headers", "Content-Type");
		headers.add("Content-Disposition", "filename=" + documentId);
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		headers.setContentLength(pdfFile.contentLength());
		ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
				new InputStreamResource(pdfFile.getInputStream()), headers, HttpStatus.OK);
		return response;

	}
	@RequestMapping(value = "pdfS3/{documentId}", method = RequestMethod.GET)
	public S3LocatioDto getpdfS3(@PathVariable("documentId") Long documentId) throws IOException {
		Document dbDocument = documentRepository.findOne(documentId);
		if (dbDocument == null) {
			throw new RuntimeException("Document Does not Exist");
		}
		S3LocatioDto s3LocatioDto=new S3LocatioDto();
		String filePathUrl =AccessS3Bucket.URL_TO_ACCESS_GLOBLA_EXTERAL_FILES_LOGOS + dbDocument.getPath();
		s3LocatioDto.setLocation(filePathUrl);
		return s3LocatioDto;
	}
}