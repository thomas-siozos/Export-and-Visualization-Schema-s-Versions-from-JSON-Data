/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sample;

import org.openjdk.jmh.annotations.Benchmark;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openjdk.jmh.annotations.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class MyBenchmark {
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Warmup(iterations = 5, time = 5)
	@Threads(value = 1)
	@Measurement(iterations = 10, time = 5, timeUnit = TimeUnit.MILLISECONDS)
	public void testJackson() {
		Scanner scanner;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			scanner = new Scanner(new File("test_countries_2_entries.json"));
			while (scanner.hasNextLine()) {
				   String line = scanner.nextLine();
				   try {
					   @SuppressWarnings("unused")
					   JsonNode jsonNode = objectMapper.readTree(line);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Warmup(iterations = 5, time = 5)
	@Threads(value = 1)
	@Measurement(iterations = 10, time = 5, timeUnit = TimeUnit.MILLISECONDS)
	public void testGson() {
		Gson gson = new Gson();
		Scanner scanner;
		try {
			scanner = new Scanner(new File("test_countries_2_entries.json"));
			while (scanner.hasNextLine()) {
			   String line = scanner.nextLine();
			   @SuppressWarnings("unused")
			   String userJson = gson.toJson(line); 
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Warmup(iterations = 5, time = 5)
	@Threads(value = 1)
	@Measurement(iterations = 10, time = 5, timeUnit = TimeUnit.MILLISECONDS)
	public void testJSONP() {
		Scanner scanner;
		try {
			scanner = new Scanner(new File("test_countries_2_entries.json"));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				JsonReader jsonReader = Json.createReader(new StringReader(line));
				@SuppressWarnings("unused")
				JsonObject jobj = jsonReader.readObject();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Warmup(iterations = 5, time = 5)
	@Threads(value = 1)
	@Measurement(iterations = 10, time = 5, timeUnit = TimeUnit.MILLISECONDS)
	public void testJsonSimple() {
		JSONParser parser = new JSONParser();
		Scanner scanner;
		try {
			scanner = new Scanner(new File("test_countries_2_entries.json"));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				try {
					@SuppressWarnings("unused")
					JSONObject jsonObject = (JSONObject) parser.parse(line);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Warmup(iterations = 5, time = 5)
	@Threads(value = 1)
	@Measurement(iterations = 10, time = 5, timeUnit = TimeUnit.MILLISECONDS)
	public void testJacksonBigFile() {
		Scanner scanner;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			scanner = new Scanner(new File("photo.json"));
			while (scanner.hasNextLine()) {
				   String line = scanner.nextLine();
				   try {
					   @SuppressWarnings("unused")
					   JsonNode jsonNode = objectMapper.readTree(line);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Warmup(iterations = 5, time = 5)
	@Threads(value = 1)
	@Measurement(iterations = 10, time = 5, timeUnit = TimeUnit.MILLISECONDS)
	public void testGsonBigFile() {
		Gson gson = new Gson();
		Scanner scanner;
		try {
			scanner = new Scanner(new File("photo.json"));
			while (scanner.hasNextLine()) {
			   String line = scanner.nextLine();
			   @SuppressWarnings("unused")
			   String userJson = gson.toJson(line); 
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Warmup(iterations = 5, time = 5)
	@Threads(value = 1)
	@Measurement(iterations = 10, time = 5, timeUnit = TimeUnit.MILLISECONDS)
	public void testJSONPBigFile() {
		Scanner scanner;
		try {
			scanner = new Scanner(new File("photo.json"));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				JsonReader jsonReader = Json.createReader(new StringReader(line));
				@SuppressWarnings("unused")
				JsonObject jobj = jsonReader.readObject();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Warmup(iterations = 5, time = 5)
	@Threads(value = 1)
	@Measurement(iterations = 10, time = 5, timeUnit = TimeUnit.MILLISECONDS)
	public void testJsonSimpleBigFile() {
		JSONParser parser = new JSONParser();
		Scanner scanner;
		try {
			scanner = new Scanner(new File("photo.json"));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				try {
					@SuppressWarnings("unused")
					JSONObject jsonObject = (JSONObject) parser.parse(line);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
