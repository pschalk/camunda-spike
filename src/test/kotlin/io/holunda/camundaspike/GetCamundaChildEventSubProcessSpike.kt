package io.holunda.camundaspike

import io.github.oshai.KotlinLogging
import io.holunda.camundaspike.GetCamundaChildEventSubProcessSpike.Companion.BPM
import org.assertj.core.api.Assertions.assertThat
import org.camunda.bpm.engine.runtime.ActivityInstance
import org.camunda.bpm.engine.runtime.ProcessInstance
import org.camunda.bpm.engine.test.Deployment
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.runtimeService
import org.camunda.bpm.extension.junit5.test.ProcessEngineExtension
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

private val logger = KotlinLogging.logger {}

@ExtendWith(ProcessEngineExtension::class, MockitoExtension::class)
@Deployment(resources = [BPM])
class GetCamundaChildEventSubProcessSpike: AbstractProcessEngineRuleTest() {

    companion object {
        const val PROCESS_KEY = "processWithEventSubProcess"
        const val BPM = "bpmn/process_with_event_sub_process.bpmn"
    }

    @Test
    internal fun name() {
        val processInstance = runtimeService().startProcessInstanceByKey(PROCESS_KEY, mapOf(Pair("startSubProcess", true)))

        val eventSubProcess = getEventSubProcessAcitvity(processInstance)

        assertThat(eventSubProcess).isNotNull
        assertThat(eventSubProcess?.activityId).isEqualTo("myEventSubProcess")
    }

    private fun getEventSubProcessAcitvity(processInstance: ProcessInstance): ActivityInstance? {
        return runtimeService()
                .getActivityInstance(processInstance.id)
                .childActivityInstances.find { it.activityType == "subProcess" }
    }

}

