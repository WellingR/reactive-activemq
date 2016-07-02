/*
 * Copyright 2016 Dennis Vriend
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.dnvriend.stream.xml

import com.github.dnvriend.stream.TestSpec

class ValidationTest extends TestSpec {
  it should "validate xml/persons.xml" in {
    withByteStringSource("xml/persons.xml") { src ⇒
      src.runWith(Validation.sink("xml/persons.xsd")).futureValue.status should be a 'success
    }
  }

  it should "validate xml/invalid-persons.xml" in {
    withByteStringSource("xml/invalid-persons.xml") { src ⇒
      src.runWith(Validation.sink("xml/persons.xsd")).futureValue.status should be a 'failure
    }
  }

  it should "validate xml/lot-of-persons.xml" in {
    withByteStringSource("xml/lot-of-persons.xml") { src ⇒
      src.runWith(Validation.sink("xml/persons.xsd")).futureValue.status should be a 'success
    }
  }
}
