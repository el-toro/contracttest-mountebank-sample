package org.eltoro.mountebank.provider

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class ContactInformation {

  String firstName
  String lastName
  String email
  String phone
}
