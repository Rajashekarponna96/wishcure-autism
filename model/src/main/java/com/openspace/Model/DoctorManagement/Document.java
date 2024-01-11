package com.openspace.Model.DoctorManagement;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openspace.Model.Parent.Folder;

@Entity
public class Document implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String path;

	//private DocumentType documentType;

	private Patient patient;

	private Long fileSize;

	private String directoryPath;

	private String filePath;

	private Folder folder;
	
	private String extension;

	//private DocumentTypeLookup documentTypeLookup;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@JsonIgnore
	@ManyToOne
	public Patient getPatient() {
		return patient;
	}

	@JsonProperty
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getDirectoryPath() {
		return directoryPath;
	}

	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/*@Enumerated(EnumType.STRING)
	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}
*/
	@JsonIgnore
	@ManyToOne
	public Folder getFolder() {
		return folder;
	}

	@JsonProperty
	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	/*@OneToOne(cascade=CascadeType.MERGE)
	public DocumentTypeLookup getDocumentTypeLookup() {
		return documentTypeLookup;
	}

	public void setDocumentTypeLookup(DocumentTypeLookup documentTypeLookup) {
		this.documentTypeLookup = documentTypeLookup;
	}*/

	
	
}
