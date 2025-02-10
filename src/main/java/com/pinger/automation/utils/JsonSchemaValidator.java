package com.pinger.automation.utils;

import lombok.extern.slf4j.Slf4j;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;

@Slf4j
public class JsonSchemaValidator {

    public static boolean validateJsonSchema(String jsonFilePath, String schemaFilePath) {
        try (FileInputStream jsonStream = new FileInputStream(jsonFilePath);
             FileInputStream schemaStream = new FileInputStream(schemaFilePath)) {

            JSONObject rawSchema = new JSONObject(new JSONTokener(schemaStream));
            Schema schema = SchemaLoader.load(rawSchema);
            schema.validate(new JSONObject(new JSONTokener(jsonStream)));

            log.info("JSON is valid against the schema!");
            return true;
        } catch (Exception e) {
            log.error("JSON validation failed: " + e);
            return false;
        }
    }
}
