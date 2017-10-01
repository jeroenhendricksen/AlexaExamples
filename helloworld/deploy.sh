#!/bin/bash

#Load secrets
source env.sh

echo "AWS_ACCESS_KEY: '${AWS_ACCESS_KEY}'"
echo "AWS_S3_BUCKET: '${AWS_S3_BUCKET}'"
echo "AWS_ROLE_ARN: '${AWS_ROLE_ARN}'"
echo "AWS_REGION: '${AWS_REGION}'"

mvn clean
mvn assembly:assembly -DdescriptorId=jar-with-dependencies package
mvn -DaccessKey="${AWS_ACCESS_KEY}" -DsecretKey="${AWS_SECRET_KEY}" \
    -Ds3Bucket="${AWS_S3_BUCKET}" -DlambdaRoleArn="${AWS_ROLE_ARN}" \
    -Dregion="${AWS_REGION}" \
    package shade:shade lambda:deploy-lambda
