package com.example.demo.service;

import com.example.demo.controller.PlanController;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


@Service
public class PdfService {

    @Autowired
    LessonService lessonService;
    @Autowired
    PlanController planController;

    private static final String PDF_RESOURCES = "/pdf-resources/";
    private final SpringTemplateEngine templateEngine;

    @Autowired
    public PdfService(LessonService lessonService, SpringTemplateEngine templateEngine, PlanController planController){
        this.lessonService = lessonService;
        this.templateEngine = templateEngine;
        this.planController = planController;
    }

    public File generatePdf() throws IOException, DocumentException{
        Context context = getContext();
        String html = loadAndFillTemplate(context);
        return renderPdf(html);
    }

    private File renderPdf(String html) throws IOException, DocumentException {
        File file = File.createTempFile("plan", ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }

    //Id should be variable taken from path
    private Context getContext() {
        Context context = new Context();
        context.setVariable("Monday", lessonService.getLessonsForSpecificDayAndPlan("Monday", 1));
        context.setVariable("Tuesday", lessonService.getLessonsForSpecificDayAndPlan("Tuesday", 1));
        context.setVariable("Wednesday", lessonService.getLessonsForSpecificDayAndPlan("Wednesday", 1));
        context.setVariable("Thursday", lessonService.getLessonsForSpecificDayAndPlan("Thursday", 1));
        context.setVariable("Friday", lessonService.getLessonsForSpecificDayAndPlan("Friday", 1));
        context.setVariable("Saturday", lessonService.getLessonsForSpecificDayAndPlan("Saturday", 1));
        context.setVariable("Sunday", lessonService.getLessonsForSpecificDayAndPlan("Sunday", 1));
        return context;
    }

    private String loadAndFillTemplate(Context context) {
        return templateEngine.process("pdf_plan", context);
    }
}
