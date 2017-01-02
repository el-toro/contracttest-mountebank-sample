package org.eltoro.mountebank.provider

interface ContactInformationService {

  ContactInformation getContractInformation(String id)

  String saveContactInformation(ContactInformation contactInformation)
}
