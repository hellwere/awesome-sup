CREATE TABLE "user" (
  "id" integer PRIMARY KEY,
  "name" varchar2,
  "login" varchar2,
  "password" varchar2,
  "email" varchar2 UNIQUE,
  "creationDate" current_timestamp
);

CREATE TABLE "permission" (
  "id" integer PRIMARY KEY,
  "name" varchar2,
  "grants" varchar2,
  "user_id" integer
);

CREATE TABLE "task" (
  "id" integer PRIMARY KEY,
  "projects" integer,
  "status" varchar2,
  "tags" varchar2,
  "creationDate" current_timestamp,
  "priority" varchar2,
  "attachments" integer,
  "logged_time" integer,
  "date_logged" datetime
);

CREATE TABLE "project" (
  "id" integer PRIMARY KEY,
  "task_id" integer,
  "number" integer,
  "status" varchar2,
  "tags" varchar2,
  "creationDate" datetime,
  "estimate" int,
  "priority" varchar2
);

CREATE TABLE "project_members" (
  "user_id" integer,
  "project_id" integer,
  "role" varchar2,
  PRIMARY KEY ("user_id", "project_id")
);

CREATE TABLE "comment" (
  "id" integer PRIMARY KEY,
  "user_id" integer,
  "entity_id" integer,
  "content" varchar2 NOT NULL,
  "created_at" current_timestamp
);

CREATE TABLE "attachment" (
  "id" integer PRIMARY KEY,
  "task_id" integer,
  "file_id" varchar2,
  "upload_at" current_timestamp
);

CREATE TABLE "file" (
  "id" integer PRIMARY KEY,
  "file" varchar2 NOT NULL
);

ALTER TABLE "permission" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "task" ADD FOREIGN KEY ("projects") REFERENCES "project" ("id");

ALTER TABLE "task" ADD FOREIGN KEY ("attachments") REFERENCES "attachment" ("id");

ALTER TABLE "task" ADD FOREIGN KEY ("id") REFERENCES "project" ("task_id");

ALTER TABLE "project_members" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "project_members" ADD FOREIGN KEY ("project_id") REFERENCES "project" ("id");

ALTER TABLE "comment" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "comment" ADD FOREIGN KEY ("entity_id") REFERENCES "task" ("id");

ALTER TABLE "task" ADD FOREIGN KEY ("id") REFERENCES "attachment" ("task_id");

ALTER TABLE "file" ADD FOREIGN KEY ("id") REFERENCES "attachment" ("file_id");
