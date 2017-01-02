package org.eltoro.mountebank.consumer

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class ContactInformation {

  String firstName
  String lastName
  String email
  String street
  String city
  String zipCode
}
