CREATE TABLE "CUSTOMERS" ("ID" INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "CUSTOMER_NAME" VARCHAR(255), EMAIL VARCHAR(255), PASSWORD VARCHAR(255));

ALTER TABLE "CUSTOMERS" ADD CONSTRAINT "SQL120325130144011" PRIMARY KEY ("ID");

CREATE TABLE "EVENTS" ("ID" INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "CUSTOMER_ID" INTEGER, NAME VARCHAR(255), "DATE_OF" DATE, PRIMARY KEY ("ID")) ;



CREATE TABLE "REGISTRATIONS" ("ID" INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "CUSTOMER_ID" INTEGER, "EVENT_ID" INTEGER, PRIMARY KEY ("ID")) ;

