locals {
  project_id = "testproject-410111"
  region     = "europe-central2"
}

provider "google" {
  project = local.project_id
  region  = local.region
}


resource "google_app_engine_application" "app" {
  project     = local.project_id
  location_id = local.region
}

resource "google_sql_database" "database" {
  name     = "my-database"
  instance = google_sql_database_instance.instance.name
}

resource "google_sql_database_instance" "instance" {
  name             = "my-database-instance"
  region           = local.region
  database_version = "POSTGRES_15"
  settings {
    tier = "db-f1-micro"
  }

  deletion_protection = "true"
}
resource "google_sql_user" "users" {
  name     = "jrwojdylak@student.agh.edu.pl"
  instance = google_sql_database_instance.instance.name
  password = ""
}