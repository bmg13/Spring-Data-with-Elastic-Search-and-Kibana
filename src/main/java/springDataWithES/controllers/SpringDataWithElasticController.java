package springDataWithES.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        path = "/spring-data-es",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class SpringDataWithElasticController {

/*
    private Service service;

    @Autowired
    public SpringDataWithElasticController(Service service) {
        this.service = service;
    }

    @GetMapping(path = "")
    public String retrieveLocationData(
            @PathVariable String data1,
            @PathVariable String data2) {
        LOGGER.info("About to retrieve data.");
        return this.service.request(data1, data2);
    }
 */
}