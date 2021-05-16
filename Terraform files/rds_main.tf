resource "aws_db_instance" "default" {
    allocated_storage = 30
    identifier = "RDS Terraform Instance"
    engine = "mysql"
    engine_version = "5.7"
    instance_class = "db.m4.large"
    name = "demo"
    username = "root"
    password = "<secret>"
    parameter_group_name = "default.mysql5.7"
    publicly_accessible = true
}