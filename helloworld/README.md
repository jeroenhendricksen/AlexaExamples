# HelloWorld AWS Lambda function for Alexa

A simple [AWS Lambda](http://aws.amazon.com/lambda) function that demonstrates how to write a skill for the Amazon Echo using the Alexa SDK.

## Concepts

This simple sample has no external dependencies or session management, and shows the most basic example of how to create a Lambda function for handling Alexa Skill requests.

## Setup

To run this example skill you need to do two things. The first is to deploy the example code in lambda using the provided automation script, and the second is to configure the Alexa skill to use Lambda.

### AWS Lambda Setup

#### Create execution role

1. Go to the AWS Console and click on the [IAM link](https://console.aws.amazon.com/iam/home?region=us-east-1#/home)
1. Click `roles`.
1. Click `Create role`.
1. Click `Lambda` from the wizard.
1. Select `AWSLambdaRole` and click `Next`.
1. Choose a role name, for example: `AWSLambdaRole`.
1. Click `Create role` button.
1. Click the newly created role and remember the `Role ARN`.

#### Create S3 bucket for jar

1. Go to the AWS Console and click on the [S3 link](https://s3.console.aws.amazon.com/s3/home?region=us-east-1#)
1. Click `Create bucket`.
1. Choose a lowercase name for the bucket, for example `skilltester93267` and remember it for later usage.
1. Choose `Next`, `Next`, `Next`, `Create bucket`.

### Alexa Skill Setup

1. Go to the [Alexa Console](https://developer.amazon.com/edw/home.html) and click `Get started > ` for the `Alexa Skills Kit`.
1. Click `Add a New Skill`.
1. Set `HelloWorld` as the skill name and `my hello world` as the invocation name, this is what is used to activate your skill. For example you would say: "Alexa, ask my hello world to say hello.". You can also start without a direct intent: "Alexa, ask my hello world".
1. Select the Lambda ARN for the skill Endpoint and paste the ARN copied from above. Click Next.
1. Copy the Intent Schema from the included IntentSchema.json.
1. Copy the Sample Utterances from the included SampleUtterances.txt. Click Next.
1. Go back to the skill Information tab and copy the appId. Paste the appId into the HelloWorldSpeechletRequestStreamHandler.java file for the variable supportedApplicationIds,
   then update the lambda source zip file with this change and upload to lambda again, this step makes sure the lambda function only serves request from authorized source. At current, it has my skill id prefilled. Be sure to either remove it, or fill it with your skill id.
1. You are now able to start testing your sample skill! You should be able to go to the [Echo webpage](http://echo.amazon.com/#skills) and see your skill enabled.
1. In order to test it, try to say some of the Sample Utterances from the Examples section below.
1. Your skill is now saved and once you are finished testing you can continue to publish your skill.

### Deployment

To automatically deploy, do the following:

1. Rename `env-sample.sh` to `env.sh`
1. Fill all the values
1. Run `deploy.sh` to deploy (on Mac or Linux) 

### Testing

1. To test the skill, say "Alexa, ask my hello world"
