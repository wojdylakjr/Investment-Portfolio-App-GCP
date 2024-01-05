locals {
  project_id = "nonprod-410222"
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
  name     = "wallet"
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

#Cloud function
resource "google_storage_bucket" "cloud_function_bucket" {
  name     = "cloud-function-bucket-wallet-4242184903284-jw"
  location = "EU"
}

resource "google_storage_bucket_object" "archive" {
  name   = "cloud_function.zip"
  bucket = google_storage_bucket.cloud_function_bucket.name
  source = "../build/dist/cloud_function.zip"
}

resource "google_cloudfunctions_function" "function" {
  name        = "daily-portfolio-email-sender"
  description = "Function to get current value of portfolio and send email"
  runtime     = "python311"

  available_memory_mb   = 128
  source_archive_bucket = google_storage_bucket.cloud_function_bucket.name
  source_archive_object = google_storage_bucket_object.archive.name
  trigger_http          = true
  entry_point           = "hello_http"
}

# IAM entry for all users to invoke the function
resource "google_cloudfunctions_function_iam_member" "invoker" {
  project        = google_cloudfunctions_function.function.project
  region         = google_cloudfunctions_function.function.region
  cloud_function = google_cloudfunctions_function.function.name

  role   = "roles/cloudfunctions.invoker"
  member = "allUsers"
}

#Scheduler for Cloud Function
resource "google_cloud_scheduler_job" "trigger_email_sender_cloud_function_everyday_at_11" {
  name             = "trigger-email-sender-cloud-function-everyday-at-11"
  description      = "Trigger Cloud Function to get daily value of portfolio and send email to me"
  schedule         = "0 11 * * *"
  time_zone        = "Europe/Warsaw"
  attempt_deadline = "320s"

  retry_config {
    retry_count = 1
  }

  http_target {
    http_method = "POST"
    uri         = google_cloudfunctions_function.function.https_trigger_url
    headers     = {
      "Content-Type" = "application/json"
    }
  }
}