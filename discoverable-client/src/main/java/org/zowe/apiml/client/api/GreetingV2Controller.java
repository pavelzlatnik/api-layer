package org.zowe.apiml.client.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.web.bind.annotation.*;
import org.zowe.apiml.client.model.Greeting;

import java.util.Date;

/**
 * Version 2 of the controller that returns greetings.
 */
@RestController
@Api(tags = {"Other Operations"})
@SwaggerDefinition(tags = {
    @Tag(name = "Other Operations", description = "General Operations")})
@RequestMapping("/api/v2")
public class GreetingV2Controller {
    private static final String TEMPLATE = "Hi, %s!";

    /**
     * Gets a greeting for anyone.
     */
    @GetMapping(value = "/greeting")
    @ApiOperation(value = "Get a greeting", response = Greeting.class,
        tags = {"Other Operations"})
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "user") String name,
                             @RequestParam(value = "delayMs", defaultValue = "0", required = false) Integer delayMs) {
        if (delayMs > 0) {
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return new Greeting(new Date(), String.format(TEMPLATE, name));
    }

    /**
     * Gets a custom greeting.
     */
    @GetMapping(value = {"/greeting/{yourName}"})
    @ApiOperation(value = "Get a greeting", response = Greeting.class,
        tags = {"Other Operations"})
    public Greeting customGreeting(@PathVariable(value = "yourName") String yourName) {
        return new Greeting(new Date(), String.format(TEMPLATE, yourName));
    }
}
