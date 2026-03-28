package io.github.surpsg

import io.github.surpsg.deltacoverage.config.CoverageEntity
import java.io.File
import java.net.URL

class DiffSourceConfiguration(
    var url: URL? = null,
    var git: String? = null,
    var file: File? = null
) {
    override fun toString(): String {
        return "DiffSourceConfiguration(url=$url, git=$git, file=$file)"
    }
}

class ViolationsConfiguration(
    var failOnViolation: Boolean = false,
    var minLines: Double = 0.0,
    var minBranches: Double = 0.0,
    var minInstructions: Double = 0.0,

    var minCoverage: Double = MIN_COVERAGE_PROPERTY_DEFAULT,

    var entityCountThreshold: Int = 0,
    var entityCountThresholdLines: Int = 0,
    var entityCountThresholdBranches: Int = 0,
    var entityCountThresholdInstructions: Int = 0,
) {
    val entityCountThresholdOrNull: Int?
        get() = entityCountThreshold.takeIf { it > 0 }

    fun entityCountThresholdForEntity(entity: CoverageEntity): Int? {
        val perEntity = when (entity) {
            CoverageEntity.LINE -> entityCountThresholdLines
            CoverageEntity.BRANCH -> entityCountThresholdBranches
            CoverageEntity.INSTRUCTION -> entityCountThresholdInstructions
        }
        return perEntity.takeIf { it > 0 } ?: entityCountThresholdOrNull
    }
}

class Report(
    var type: String? = null,
    var enabled: Boolean = true,
)

internal const val MIN_COVERAGE_PROPERTY_DEFAULT: Double = -1.0;
