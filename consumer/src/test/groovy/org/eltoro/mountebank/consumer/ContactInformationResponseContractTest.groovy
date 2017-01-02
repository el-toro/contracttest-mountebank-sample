package org.eltoro.mountebank.consumer

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.mbtest.javabank.Client
import org.mbtest.javabank.fluent.ImposterBuilder
import org.mbtest.javabank.http.imposters.Imposter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = ContactInformationClientService)
class ContactInformationResponseContractTest extends Specification {

  @Autowired
  ContactInformationClientService contractInformationClientService

  def imposterPort = 5555

  def cleanup() {
    new Client().deleteImposter(imposterPort)
  }

  def "ContactInformation Response is returned as expected"() {
    given:
    def contactInformation = new ContactInformation(firstName: "Hans", lastName: "Dampf", email: "dampf@me.com")
    def contactInformationAsJson = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writeValueAsString(contactInformation)

    def requestPath = "/contactinformation/anyId"

    Imposter imposter = ImposterBuilder.anImposter()
        .onPort(imposterPort)
        .stub()
        .predicate().equals().path(requestPath).method("GET").end().end()
        .response().is().statusCode(200).body(contactInformationAsJson).header("Content-Type", "application/json").end().end()
        .end()
        .stub().response().is().statusCode(400).end().end().end()
        .build()

    when:
    Client mountebankClient = new Client()
    mountebankClient.createImposter(imposter)

    and:
    def responseEntity = contractInformationClientService.getContractInformation("anyId")

    then:
    responseEntity.statusCode == HttpStatus.OK
    responseEntity.body == contactInformation
  }

  def "ContactInformation Response is empty when unknown attributes are expected to be returned"() {
    given:
    def contactInformation = new ContactInformation(street: "Bahnhofsstr.")
    def contactInformationAsJson = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writeValueAsString(contactInformation)

    def requestPath = "/contactinformation/anyId"

    Imposter imposter = ImposterBuilder.anImposter()
        .onPort(imposterPort)
        .stub()
        .predicate().equals().path(requestPath).method("GET").end().end()
        .response().is().statusCode(200).body(contactInformationAsJson).header("Content-Type", "application/json").end().end()
        .end()
        .stub().response().is().statusCode(400).end().end().end()
        .build()

    when:
    Client mountebankClient = new Client()
    mountebankClient.createImposter(imposter)

    and:
    def responseEntity = contractInformationClientService.getContractInformation("anyId")

    then:
    responseEntity.statusCode == HttpStatus.OK
    responseEntity.body == new ContactInformation()
  }
}
