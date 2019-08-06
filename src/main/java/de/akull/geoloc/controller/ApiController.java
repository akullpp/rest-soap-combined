package de.akull.geoloc.controller;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Target(TYPE)
@Retention(RUNTIME)
@Documented
@Controller
@ResponseBody
@RequestMapping
@CrossOrigin
public @interface ApiController {

    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] value() default {};
}
