package org.eltoro.mountebank.consumer

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestOperations
import org.springframework.web.client.RestTemplate

@Service
class ContactInformationClientService {

//  @Value('${providerUrl}')
  String providerUrl = "http://localhost:8080/provider"

  RestOperations restOperations = new RestTemplate()

  ResponseEntity<ContactInformation> getContractInformation(String id) {
    restOperations.getForEntity("$providerUrl/contactinformation/$id", ContactInformation)
  }

  ResponseEntity<String> saveContactInformation(ContactInformation contactInformation) {
    restOperations.postForEntity("$providerUrl/contactinformation", contactInformation, String)
  }
}
