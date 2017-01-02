package org.eltoro.mountebank.consumer

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.mbtest.javabank.Client
import org.mbtest.javabank.fluent.ImposterBuilder
import org.mbtest.javabank.http.imposters.Imposter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.HttpClientErrorException
import spock.lang.Specification

@ContextConfiguration(classes = ContactInformationClientService)
class ContactInformationRequestContractTest extends Specification {

  @Autowired
  ContactInformationClientService contractInformationClientService

  def imposterPort = 5555

  def cleanup() {
    new Client().deleteImposter(imposterPort)
  }

  def "ContactInformation Request is understood"() {
    given:
    def contactInformation = new ContactInformation(firstName: "Hans", lastName: "Dampf", email: "dampf@me.com")
    def contactInformationAsJson = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writeValueAsString(contactInformation)

    def requestPath = "/contactinformation"

    Imposter imposter = ImposterBuilder.anImposter()
        .onPort(imposterPort)
        .stub()
        .predicate().equals().path(requestPath).method("POST").body(contactInformationAsJson).end().end()
        .response().is().statusCode(200).end().end()
        .end()
        .stub().response().is().statusCode(400).end().end().end()
        .build()

    when:
    Client mountebankClient = new Client()
    mountebankClient.createImposter(imposter)

    and:
    def responseEntity = contractInformationClientService.saveContactInformation(contactInformation)

    then:
    responseEntity.statusCode == HttpStatus.OK
  }

  def "ContactInformation Request is NOT understood when unknown attributes are contained in request"() {
    given:
    def contactInformation = new ContactInformation(street: "Bahnhofsstr.")
    def contactInformationAsJson = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writeValueAsString(contactInformation)

    def requestPath = "/contactinformation"

    Imposter imposter = ImposterBuilder.anImposter()
        .onPort(imposterPort)
        .stub()
        .predicate().equals().path(requestPath).method("POST").body(contactInformationAsJson).end().end()
        .response().is().statusCode(200).end().end()
        .end()
        .stub().response().is().statusCode(400).end().end().end()
        .build()
    when:
    Client mountebankClient = new Client()
    mountebankClient.createImposter(imposter)

    and:
    contractInformationClientService.saveContactInformation(contactInformation)

    then:
    def exception = thrown(HttpClientErrorException)
    exception.statusCode == HttpStatus.BAD_REQUEST
  }
}
