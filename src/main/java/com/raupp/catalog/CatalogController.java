package com.raupp.catalog;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RefreshScope
public class CatalogController {

    @Autowired
    EurekaClient client;


    @RequestMapping("/")
    @HystrixCommand(fallbackMethod = "displayDefaultHome")
    public String getCatalogHome() {
        String courseAppMesage = "";
        //String courseAppURL = "http://localhost:8080/";
        InstanceInfo instanceInfo = client.getNextServerFromEureka("course-service",false);
        String courseAppURL = instanceInfo.getHomePageUrl();
        RestTemplate restTemplate = new RestTemplate();


        courseAppMesage = restTemplate.getForObject(courseAppURL,String.class);

        return("Welcome to Raupp Course Catalog "+courseAppMesage);
    }

    public String displayDefaultHome(){

        return("Welcome to Microservice Test "+"Please try later");
    }

    @RequestMapping("/catalog")
    public String getCatalog() {
        String courses = "";
        InstanceInfo instanceInfo = client.getNextServerFromEureka("course-service",false);
        String courseAppURL = instanceInfo.getHomePageUrl();
        courseAppURL = courseAppURL+"/courses";
        //String courseAppURL = "http://localhost:8080/courses";
        RestTemplate restTemplate = new RestTemplate();
        courses = restTemplate.getForObject(courseAppURL,String.class);

        return("Our courses are "+courses);
    }

    @RequestMapping("/firstcourse")
    public CourseUsers getSpecificCourse() {

        CourseUsers courseUsers = new CourseUsers();

        //String courseAppURL = "http://localhost:8080/1";
        InstanceInfo instanceInfo = client.getNextServerFromEureka("course-service",false);
        String courseAppURL = instanceInfo.getHomePageUrl();
        courseAppURL = courseAppURL+"/1";
        RestTemplate restTemplate = new RestTemplate();
        courseUsers.setCourse(restTemplate.getForObject(courseAppURL,Course.class));

        instanceInfo = client.getNextServerFromEureka("user-service",false);
        String userAppURL = instanceInfo.getHomePageUrl();
        userAppURL = userAppURL+"/course/"+courseUsers.getCourse().getCourseId();

        courseUsers.setUsers(restTemplate.getForObject(userAppURL, List.class));

        return courseUsers;
    }

}
