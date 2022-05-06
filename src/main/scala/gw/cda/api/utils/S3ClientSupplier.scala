package gw.cda.api.utils

import com.amazonaws.auth.{AWSStaticCredentialsProvider, BasicAWSCredentials}
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.guidewire.cda.config.SecretsManager
import spray.json._
import DefaultJsonProtocol._
import org.apache.logging.log4j.LogManager


/** Supplier object for AmazonS3 client object.
 * Initializes it here so that that only one instance is needed throughout
 * the code
 */
object S3ClientSupplier {
  private val log = LogManager.getLogger

  val secretsManager = new SecretsManager()
  val secret = secretsManager.call_secret()
//  val IAM_role = "ARN-arn:aws:iam::371787833056:role/noblr-guidewire-ec2-role"
//  val region = Regions.US_WEST_2

//  val s3Client: AmazonS3 = AmazonS3ClientBuilder.standard().withForceGlobalBucketAccessEnabled(true).build()
  log.info("------------------------------------------------------------------------")
  log.info(secret.fields("gw_secret_key").convertTo[String])
  log.info(secret.fields("gw_access_key").convertTo[String])
  log.info("------------------------------------------------------------------------")
  val creds = new BasicAWSCredentials(secret.fields("gw_secret_key").convertTo[String], secret.fields("gw_access_key").convertTo[String]);
  log.info(creds.getAWSSecretKey)
  log.info(creds.getAWSAccessKeyId)
  log.info(creds)
  val s3Client: AmazonS3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds)).withForceGlobalBucketAccessEnabled(true).build();

}
