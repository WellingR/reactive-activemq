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

package akka.stream.integration.activemq.extension.config

import com.typesafe.config.Config

import scalaz.syntax.std.boolean._

object ProducerConfig {
  def apply(config: Config): ProducerConfig = ProducerConfig(
    config.getString("conn"),
    config.getString("topic"),
    config.hasPath("reply-to").option(config.getString("reply-to"))
  )
}

case class ProducerConfig(conn: String, topic: String, replyTo: Option[String]) extends EndpointDefinition {
  override lazy val endpoint: String = {
    val maybeReplyTo = replyTo.map(dest ⇒ s"?replyTo=$dest&preserveMessageQos=true").getOrElse("")
    s"$conn:topic:VirtualTopic.$topic$maybeReplyTo"
  }
}
