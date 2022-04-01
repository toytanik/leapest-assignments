package com.edcast.interwiev.frauddetection.client;

import com.edcast.interwiev.frauddetection.model.Payload;
import com.edcast.interwiev.frauddetection.model.PayloadResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "pdf-service", url = "${pdf.service.host}")
public interface PdfCreatorApi {

    @PostMapping(value = "/api/generate")
    PayloadResponse createPdf(@RequestBody Payload payload);
}
