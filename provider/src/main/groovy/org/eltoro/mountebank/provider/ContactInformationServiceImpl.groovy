package org.eltoro.mountebank.provider

import org.springframework.context.annotation.Conditional
import org.springframework.stereotype.Service

@Service
@Conditional(Profiles.ProductionModeEnabled)
class ContactInformationServiceImpl implements ContactInformationService {

  ContactInformation getContractInformation(String id) {
    // retrieve contact information
    [
        lastName : "Dampf",
        firstName: "Hans",
        email    : "me@example.com"
    ] as ContactInformation
  }

  String saveContactInformation(ContactInformation contactInformation) {
    // save contact information
    "myId"
  }
}