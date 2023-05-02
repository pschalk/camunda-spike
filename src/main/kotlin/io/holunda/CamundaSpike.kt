package io.holunda

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@Suppress("UNUSED_PARAMETER")
fun main(args: Array<String>) {
    runApplication<CamundaSpike>().let { }
}

@SpringBootApplication
open class CamundaSpike
