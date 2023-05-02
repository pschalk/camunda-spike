package io.holunda

import io.github.oshai.KotlinLogging
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

private val logger = KotlinLogging.logger {}

@Suppress("UNUSED_PARAMETER")
fun main(args: Array<String>) {
    runApplication<CamundaSpike>().let { }
}

@SpringBootApplication
open class CamundaSpike {

    @Bean
    open fun dummyDelegate() : JavaDelegate {
        return JavaDelegate { _ -> logger.info("Dummy delegate invoked") }
    }
}
