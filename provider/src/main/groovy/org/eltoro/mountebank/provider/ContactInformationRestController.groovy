package org.eltoro.mountebank.provider

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.HttpClientErrorException

import javax.servlet.http.HttpServletResponse

@RestController
class ContactInformationRestController {

  @Autowired
  ContactInformationService contactInformationService

  @RequestMapping(value = "/contactinformation/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  ContactInformation getContractInformation(@PathVariable String id) {
    contactInformationService.getContractInformation(id)
  }

  @RequestMapping(value = "/contactinformation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  String saveContactInformation(@RequestBody ContactInformation contactInformation) {
    contactInformationService.saveContactInformation(contactInformation)
  }

  @ExceptionHandler
  def handleHttpClientErrorException(HttpClientErrorException e, HttpServletResponse response) {
    response.sendError(e.statusCode.value())
  }
}
