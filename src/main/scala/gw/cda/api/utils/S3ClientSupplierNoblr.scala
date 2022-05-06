package gw.cda.api.utils

//import com.amazonaws.auth.{AWSStaticCredentialsProvider, BasicAWSCredentials}
import com.amazonaws.services.s3.{AmazonS3, AmazonS3ClientBuilder}
import com.guidewire.cda.config.SecretsManager
//import org.apache.logging.log4j.LogManager


/** Supplier object for AmazonS3 client object.
 * Initializes it here so that that only one instance is needed throughout
 * the code
 */
object S3ClientSupplierNoblr {
//  private val log = LogManager.getLogger

  val secretsManager = new SecretsManager()
  val secret = secretsManager.call_secret()

  val noBlrs3Client: AmazonS3 = AmazonS3ClientBuilder.standard().withRegion("us-west-2").withForceGlobalBucketAccessEnabled(true).build()
//  val s3Client: AmazonS3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds)).withForceGlobalBucketAccessEnabled(true).build();

}
