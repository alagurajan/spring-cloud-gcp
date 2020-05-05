/*
 * Copyright 2017-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.gcp.test;

import org.junit.Assume;
import org.junit.rules.ExternalResource;

/**
 * TODO: send users to https://cloud.google.com/spanner/docs/emulator#using_the_gcloud_cli_with_the_emulator
 */
public class SpannerEmulatorRule extends ExternalResource {

	private SpannerEmulatorHelper emulatorHelper = new SpannerEmulatorHelper();

	@Override
	protected void before() throws Throwable {
		String emulatorHost = System.getenv("SPANNER_EMULATOR_HOST");
		Assume.assumeFalse(
				"Run this command prior to running an emulator test and set SPANNER_EMULATOR_HOST environment variable:\ngcloud beta emulators spanner env-init",
				emulatorHost == null || emulatorHost.isEmpty());
		emulatorHelper.startEmulator();
	}

	@Override
	protected void after() {
		emulatorHelper.shutdownEmulator();
	}

}
