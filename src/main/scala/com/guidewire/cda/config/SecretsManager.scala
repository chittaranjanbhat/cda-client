package com.guidewire.cda.config

import com.amazonaws.services.secretsmanager.AWSSecretsManagerClient
import com.amazonaws.services.secretsmanager.model._
import spray.json._


class SecretsManager {

  def call_secret() : spray.json.JsObject = {
    val secret_name = "dev/postgres"
    val region_name = "us-west-2"
    //  val secretsmanager_client = AWSSecretsManagerClient.builder.withCredentials(aws_credentials).withRegion(region).build
    val secretsmanager_client = AWSSecretsManagerClient.builder.withRegion(region_name).build

    val getSecretValueRequest = new GetSecretValueRequest().withSecretId(secret_name)
    val getSecretValueResult = secretsmanager_client.getSecretValue(getSecretValueRequest)
    val secret = getSecretValueResult.getSecretString()
    secret.parseJson.asJsObject
  }

}
