package in.co.techiesandeep.controller;

import in.co.techiesandeep.exception.HTTP400Exception;
import in.co.techiesandeep.exception.HTTP404Exception;
import in.co.techiesandeep.exception.RestAPIExceptionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;

public abstract class AbstractController implements ApplicationEventPublisherAware {

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    protected ApplicationEventPublisher eventPublisher;
    protected  static final String DEFAULT_PAGE_SIZE = "20";
    protected  static String DEFAULT_PAGE_SIZE_NUMBER = "0";

    Counter http400ExceptionCounter =
            Metrics.counter("co.in.techiesandeep.controller.ProductController.HTTP400");

    Counter http404ExceptionCounter =
            Metrics.counter("co.in.techiesandeep.controller.ProductController.HTTP404");

     public RestAPIExceptionInfo handleBadRequestException(HTTP400Exception ex,
                                                           WebRequest request,
                                                           HttpServletResponse response) {
         logger.info("Recieved bad Request exception "+ex.getLocalizedMessage());
         http400ExceptionCounter.increment();

        return new RestAPIExceptionInfo(ex.getLocalizedMessage(), "Request didn't have correct parameters");
     }

     public RestAPIExceptionInfo handleResourceNotFoundException(HTTP404Exception ex) {
         logger.info("Received resource not found exception "+ex.getLocalizedMessage());
         http404ExceptionCounter.increment();
         return new RestAPIExceptionInfo(ex.getLocalizedMessage(), "Requested resource was not found");
     }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    public static <T> T checkResourceFound(final T resource) {
        if (resource == null) {
            throw new HTTP404Exception("Resource Not Found");
        }
        return resource;
    }
}
