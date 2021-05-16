resource "aws_instance" "EC2 Instance" {
    ami = ""
    instance_type = "t2.micro"
    key_name = "test1"
    tags = {
        Name = "EC2_Instance_Terraform"
    }
}