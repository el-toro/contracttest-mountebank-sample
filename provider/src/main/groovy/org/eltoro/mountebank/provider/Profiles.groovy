package org.eltoro.mountebank.provider

import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.core.type.AnnotatedTypeMetadata

class Profiles {

  static class ProductionModeEnabled implements Condition {

    boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
      context.environment.getActiveProfiles().length == 0
    }
  }

  static class ContractTestModeEnabled implements Condition {

    boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
      context.environment.acceptsProfiles("contract-test-mode")
    }
  }
}
