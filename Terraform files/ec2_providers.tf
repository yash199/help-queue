provider "aws" {
    access_key = "${var.aws_access_key}" // get using aws configure
    secret_key = "${var.aws_secret_key}"
    region = "us-east-1"
}