package org.eltoro.mountebank.provider

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Conditional
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
@Conditional(Profiles.ContractTestModeEnabled)
class ContactInformationServiceContractTestImpl implements ContactInformationService {

  // to be injected from outside
  def imposterUrl = "http://localhost:5555/contactinformation"

  RestTemplate restTemplate = new RestTemplate()

  ContactInformation getContractInformation(String id) {
    def responseEntity = restTemplate.getForEntity("$imposterUrl/$id", ContactInformation)
    responseEntity.body
  }

  String saveContactInformation(ContactInformation contactInformation) {
    String payloadAsJsonWithoutNullValues = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writeValueAsString(contactInformation)
    restTemplate.postForEntity(imposterUrl, payloadAsJsonWithoutNullValues, ContactInformation)
    return "id"
  }
}
