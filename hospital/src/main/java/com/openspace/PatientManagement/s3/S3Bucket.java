package com.openspace.PatientManagement.s3;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3Bucket implements AccessS3Bucket {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// credentials object identifying user for authentication
	// user must have AWSConnector and AmazonS3FullAccess for
	// this example to work
	AWSCredentials credentials = new BasicAWSCredentials(keyName, securityKey);

	// create a client connection based on credentials
	AmazonS3 s3client = new AmazonS3Client(credentials);

	// create bucket - name must be unique for all S3 users
	String bucketName1 = bucketName;
	// s3client.createBucket(bucketName);
	
	// create folder into bucket
			String folderName = "testfolder";
			//createFolder(bucketName1, folderName, s3client);
			
			// upload file to folder and set it to public
			/*String fileName = folderName + SUFFIX + "beginning-angularjs.pdf";
			s3client.putObject(new PutObjectRequest(bucketName, fileName, 
					new File("C:\\Users\\suresh\\Desktop\\beginning-angularjs.pdf"))
					.withCannedAcl(CannedAccessControlList.PublicRead));*/
			

	public  void createFolder( String folderName) {
		AmazonS3 client=s3client;
		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);
		// create empty content
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest(AccessS3Bucket.bucketName, folderName + SUFFIX, emptyContent,
				metadata);
		// send request to S3 to create folder
		client.putObject(putObjectRequest);
		// boolean exists = client.doesObjectExist(AccessS3Bucket.bucketName, folderName);
		
		
		
	}
	
	public  void uploadFile( String folderName,String filename) {
		String fileName = folderName + SUFFIX ;
		s3client.putObject(new PutObjectRequest(bucketName, fileName, 
				new File(filename))
				.withCannedAcl(CannedAccessControlList.PublicRead));
	}
	
	public  boolean isValidFile(String path)
			throws AmazonClientException, AmazonServiceException {
		AmazonS3 s3=s3client;
		boolean isValidFile = true;
		try {
			ObjectMetadata objectMetadata = s3.getObjectMetadata(AccessS3Bucket.bucketName, path+SUFFIX);
		} catch (AmazonS3Exception s3e) {
			if (s3e.getStatusCode() == 404) {
				// i.e. 404: NoSuchKey - The specified key does not exist
				isValidFile = false;
			} else {
				throw s3e; // rethrow all S3 exceptions other than 404
			}
		}
		return isValidFile;
	}
	
	
	public static void allFiles(String bucketName, String path, AmazonS3 s3)
			throws AmazonClientException, AmazonServiceException {
		try {

			boolean exists = s3.doesObjectExist(bucketName, path);
			if (exists) {
				System.out.println("Object \"" + bucketName + "/" + path + "\" exists!");
			} else {
				System.out.println("Object \"" + bucketName + "/" + path + "\" does not exist!");
			}

		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which means your request made it "
					+ "to Amazon S3, but was rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which means the client encountered "
					+ "a serious internal problem while trying to communicate with S3, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}

	}

}
