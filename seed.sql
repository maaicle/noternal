drop table notes_tags;
drop table tags;
drop table note_events;
drop table notes;
drop table users;

CREATE TABLE users (
	"id" serial NOT NULL,
	"first_name" varchar(255) NOT NULL,
	"last_name" varchar(255) NOT NULL,
	"email" varchar(255) NOT NULL,
	"user_name" varchar(255) NOT NULL,
	"pass_hash" varchar(255) NOT NULL,
	"account_non_locked" varchar(255) NOT NULL,
	"created" TIME NOT NULL,
	CONSTRAINT "users_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE notes (
	"id" serial NOT NULL,
	"body" TEXT NOT NULL,
	"created" TIME NOT NULL,
	"updated" TIME NOT NULL,
	"archived" BOOLEAN NOT NULL,
	CONSTRAINT "notes_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE note_events (
	"id" serial NOT NULL,
	"note_id" integer NOT NULL,
	"body" TEXT NOT NULL,
	"updated" TIME NOT NULL,
	"archived" BOOLEAN NOT NULL,
	CONSTRAINT "note_events_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE tags (
	"id" serial NOT NULL,
	"user_id" integer NOT NULL,
	"view" varchar(255),
	"type" varchar(255),
	"value" varchar(255) NOT NULL,
	"created" TIME NOT NULL,
	CONSTRAINT "tags_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE notes_tags (
	"id" serial NOT NULL,
	"tag_id" integer NOT NULL,
	"note_id" integer NOT NULL,
	"created" TIME NOT NULL,
	CONSTRAINT "notes_tags_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);





ALTER TABLE "note_events" ADD CONSTRAINT "note_events_fk0" FOREIGN KEY ("note_id") REFERENCES "notes"("id");

ALTER TABLE "tags" ADD CONSTRAINT "tags_fk0" FOREIGN KEY ("user_id") REFERENCES "users"("id");

ALTER TABLE "notes_tags" ADD CONSTRAINT "notes_tags_fk0" FOREIGN KEY ("tag_id") REFERENCES "tags"("id");
ALTER TABLE "notes_tags" ADD CONSTRAINT "notes_tags_fk1" FOREIGN KEY ("note_id") REFERENCES "notes"("id");
